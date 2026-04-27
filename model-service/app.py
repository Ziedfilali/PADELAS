import json
import logging
import os
import time
import traceback
import uuid
from datetime import datetime
from functools import wraps
from pathlib import Path
from typing import Any, Dict, List, Tuple
from urllib import request as urllib_request

import joblib
import mlflow
import numpy as np
import pandas as pd
import pyodbc
from fastapi import FastAPI, HTTPException, Request
from fastapi.middleware.cors import CORSMiddleware
from fastapi.responses import JSONResponse
from mlflow.tracking import MlflowClient
from pydantic import BaseModel, Field
from sklearn.cluster import KMeans
from sklearn.compose import ColumnTransformer
from sklearn.ensemble import RandomForestClassifier
from sklearn.impute import SimpleImputer
from sklearn.linear_model import Ridge
from sklearn.metrics import accuracy_score, silhouette_score
from sklearn.model_selection import train_test_split
from sklearn.pipeline import Pipeline
from sklearn.preprocessing import OneHotEncoder, StandardScaler

app = FastAPI(title="Padel ML Training Service", version="1.0.0")

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=False,
    allow_methods=["*"],
    allow_headers=["*"],
)

DATA_DIR = Path(os.getenv("DATA_DIR", "/app/data"))
MODELS_DIR = Path(os.getenv("MODELS_DIR", "/app/models"))
LOG_FILE = Path(os.getenv("LOG_FILE", "/app/logs/api.log"))
MODEL_BUCKET = os.getenv("MODEL_BUCKET", "mlflow-artifacts")
MLFLOW_TRACKING_URI = os.getenv("MLFLOW_TRACKING_URI", "http://mlflow:5000")
DB_DRIVER = os.getenv("DB_DRIVER", "ODBC Driver 18 for SQL Server")
DB_HOST = os.getenv("DB_HOST", "")
DB_PORT = os.getenv("DB_PORT", "1433")
DB_NAME = os.getenv("DB_NAME", "")
DB_USER = os.getenv("DB_USER", "")
DB_PASSWORD = os.getenv("DB_PASSWORD", "")
DB_TRUST_CERT = os.getenv("DB_TRUST_CERT", "yes")
ALERT_WEBHOOK_URL = os.getenv("ALERT_WEBHOOK_URL", "")
ENABLE_DEBUG_ENDPOINTS = os.getenv("ENABLE_DEBUG_ENDPOINTS", "true").lower() == "true"

mlflow.set_tracking_uri(MLFLOW_TRACKING_URI)
ml_client = MlflowClient()

logger = logging.getLogger("padel_api")
logger.setLevel(logging.INFO)
if not logger.handlers:
    formatter = logging.Formatter(
        "%(asctime)s | %(levelname)s | %(name)s | %(message)s"
    )
    stream_handler = logging.StreamHandler()
    stream_handler.setFormatter(formatter)
    logger.addHandler(stream_handler)
    try:
        LOG_FILE.parent.mkdir(parents=True, exist_ok=True)
        file_handler = logging.FileHandler(LOG_FILE, encoding="utf-8")
        file_handler.setFormatter(formatter)
        logger.addHandler(file_handler)
    except Exception as exc:
        logger.warning("Could not initialize file logging: %s", exc)

MODEL_FILE_PREFIX = {
    "PadelWinnerClassifier": "winner_classifier_",
    "PadelViewsRegression": "views_regression_",
    "PadelPlayersClustering": "players_clustering_",
    "PadelTimeSeriesForecast": "timeseries_forecast_",
}


class PredictionRequest(BaseModel):
    model_name: str = Field(..., description="Registered model logical name")
    features: List[Dict[str, Any]] = Field(
        ..., min_length=1, description="Rows of features used for prediction"
    )


class MatchupRequest(BaseModel):
    tournament_name: str = Field(..., min_length=1)
    round: str = Field(..., min_length=1)
    team1_player1_name: str = Field(..., min_length=1)
    team1_player2_name: str = Field(..., min_length=1)
    team2_player1_name: str = Field(..., min_length=1)
    team2_player2_name: str = Field(..., min_length=1)


def utc_now() -> str:
    return datetime.utcnow().strftime("%Y%m%d_%H%M%S")


def _send_alert(title: str, details: Dict[str, Any]):
    if not ALERT_WEBHOOK_URL:
        return
    # Slack incoming webhooks require a "text" field.
    details_json = json.dumps(details, default=str, ensure_ascii=False)
    payload = {
        "text": f"[Padel ML Training API] {title}\n{details_json}",
    }
    try:
        req = urllib_request.Request(
            ALERT_WEBHOOK_URL,
            data=json.dumps(payload).encode("utf-8"),
            headers={"Content-Type": "application/json"},
            method="POST",
        )
        with urllib_request.urlopen(req, timeout=4):
            pass
    except Exception as exc:
        logger.warning("Alert webhook failed: %s", exc)


def _with_retries(max_attempts: int = 3, wait_seconds: float = 0.8):
    def _decorator(fn):
        @wraps(fn)
        def _wrapped(*args, **kwargs):
            last_error = None
            for attempt in range(1, max_attempts + 1):
                try:
                    return fn(*args, **kwargs)
                except Exception as exc:
                    last_error = exc
                    logger.warning(
                        "Retryable operation failed (attempt %s/%s): %s",
                        attempt,
                        max_attempts,
                        exc,
                    )
                    if attempt < max_attempts:
                        time.sleep(wait_seconds)
            raise last_error

        return _wrapped

    return _decorator


def _log_event(level: str, message: str, **fields):
    line = json.dumps({"message": message, **fields}, default=str)
    if level == "error":
        logger.error(line)
    elif level == "warning":
        logger.warning(line)
    else:
        logger.info(line)


@app.middleware("http")
async def request_trace_middleware(request: Request, call_next):
    request_id = request.headers.get("x-request-id", str(uuid.uuid4()))
    request.state.request_id = request_id
    start = time.perf_counter()
    _log_event(
        "info",
        "request_start",
        request_id=request_id,
        method=request.method,
        path=request.url.path,
    )
    try:
        response = await call_next(request)
    except Exception:
        _log_event(
            "error",
            "request_crashed",
            request_id=request_id,
            method=request.method,
            path=request.url.path,
            traceback=traceback.format_exc(),
        )
        raise
    duration_ms = round((time.perf_counter() - start) * 1000, 2)
    response.headers["x-request-id"] = request_id
    _log_event(
        "info",
        "request_end",
        request_id=request_id,
        method=request.method,
        path=request.url.path,
        status_code=response.status_code,
        duration_ms=duration_ms,
    )
    return response


@app.exception_handler(HTTPException)
async def http_exception_handler(request: Request, exc: HTTPException):
    request_id = getattr(request.state, "request_id", "n/a")
    _log_event(
        "warning",
        "http_exception",
        request_id=request_id,
        path=request.url.path,
        status_code=exc.status_code,
        detail=exc.detail,
    )
    return JSONResponse(
        status_code=exc.status_code,
        content={
            "status": "error",
            "request_id": request_id,
            "error_type": "http_error",
            "detail": exc.detail,
        },
    )


@app.exception_handler(Exception)
async def unhandled_exception_handler(request: Request, exc: Exception):
    request_id = getattr(request.state, "request_id", "n/a")
    _log_event(
        "error",
        "unhandled_exception",
        request_id=request_id,
        path=request.url.path,
        error=str(exc),
        traceback=traceback.format_exc(),
    )
    _send_alert(
        "Unhandled API Exception",
        {"request_id": request_id, "path": request.url.path, "error": str(exc)},
    )
    return JSONResponse(
        status_code=500,
        content={
            "status": "error",
            "request_id": request_id,
            "error_type": "internal_error",
            "detail": "Unexpected server error. Check API logs with request_id.",
        },
    )


def _db_connection():
    if not all([DB_HOST, DB_NAME, DB_USER, DB_PASSWORD]):
        return None
    conn_str = (
        f"DRIVER={{{DB_DRIVER}}};"
        f"SERVER={DB_HOST},{DB_PORT};"
        f"DATABASE={DB_NAME};"
        f"UID={DB_USER};"
        f"PWD={DB_PASSWORD};"
        "Encrypt=yes;"
        f"TrustServerCertificate={DB_TRUST_CERT};"
    )
    return pyodbc.connect(conn_str)


def load_table(query: str, csv_fallback: str) -> pd.DataFrame:
    @_with_retries(max_attempts=3, wait_seconds=1.0)
    def _safe_read_sql(local_conn):
        return pd.read_sql(query, local_conn)

    conn = _db_connection()
    if conn:
        try:
            return _safe_read_sql(conn)
        except Exception:
            fallback = DATA_DIR / csv_fallback
            if fallback.exists():
                _log_event(
                    "warning",
                    "db_read_failed_using_fallback",
                    fallback=str(fallback),
                    query_preview=query[:120],
                )
                return pd.read_csv(fallback)
            raise
        finally:
            conn.close()
    fallback = DATA_DIR / csv_fallback
    if fallback.exists():
        return pd.read_csv(fallback)
    raise HTTPException(
        status_code=500,
        detail=f"Database unavailable and fallback file missing: {fallback}",
    )


def start_registry_run(experiment_name: str, model_name: str):
    mlflow.set_experiment(experiment_name)
    run = mlflow.start_run(run_name=f"{model_name}_{utc_now()}")
    return run


def register_version(model_name: str, artifact_subpath: str):
    try:
        ml_client.get_registered_model(model_name)
    except Exception:
        ml_client.create_registered_model(model_name)
    run_id = mlflow.active_run().info.run_id
    model_uri = f"runs:/{run_id}/{artifact_subpath}"
    version = mlflow.register_model(model_uri=model_uri, name=model_name)
    return version.version


def _latest_local_model_path(model_name: str) -> Path:
    prefix = MODEL_FILE_PREFIX.get(model_name)
    if not prefix:
        raise HTTPException(status_code=400, detail=f"Unsupported model_name: {model_name}")
    candidates = sorted(MODELS_DIR.glob(f"{prefix}*.joblib"), key=lambda p: p.stat().st_mtime)
    if not candidates:
        raise HTTPException(
            status_code=404,
            detail=(
                f"No local artifact found for {model_name}. "
                "Train first with POST /train/all to generate model files."
            ),
        )
    return candidates[-1]


def _run_prediction(loaded_model: Any, rows: List[Dict[str, Any]]):
    # Players clustering and timeseries are saved as dict payloads with model metadata.
    if isinstance(loaded_model, dict) and {"model", "features"}.issubset(loaded_model.keys()):
        model = loaded_model["model"]
        feature_names = loaded_model["features"]
        frame = pd.DataFrame(rows)
        missing = [f for f in feature_names if f not in frame.columns]
        if missing:
            raise HTTPException(
                status_code=400, detail=f"Missing required feature(s): {', '.join(missing)}"
            )
        x = frame[feature_names]
        if "scaler" in loaded_model:
            x = loaded_model["scaler"].transform(x)
        preds = model.predict(x)
        return np.asarray(preds).tolist()

    frame = pd.DataFrame(rows)
    preds = loaded_model.predict(frame)
    return np.asarray(preds).tolist()


def _pair_key(p1: str, p2: str) -> str:
    return " | ".join(sorted([str(p1).strip(), str(p2).strip()]))


def _historical_pair_stats():
    query = """
    SELECT
        team1_player1_name, team1_player2_name, team2_player1_name, team2_player2_name,
        winner, aces_t1, aces_t2, double_faults_t1, double_faults_t2,
        won_on_1st_serve_t1, won_on_1st_serve_t2,
        won_on_2nd_serve_t1, won_on_2nd_serve_t2,
        total_points_won_t1, total_points_won_t2,
        break_points_converted_t1, break_points_converted_t2
    FROM dbo.matches_with_views_interactions
    WHERE winner IN ('team_1', 'team_2')
    """
    df = load_table(query, "matches_with_views_interactions.csv")

    pair_records: List[Dict[str, Any]] = []
    h2h_records: List[Dict[str, Any]] = []
    for _, r in df.iterrows():
        t1a, t1b = r.get("team1_player1_name"), r.get("team1_player2_name")
        t2a, t2b = r.get("team2_player1_name"), r.get("team2_player2_name")
        if not all([t1a, t1b, t2a, t2b]):
            continue

        pair1 = _pair_key(t1a, t1b)
        pair2 = _pair_key(t2a, t2b)

        pair_records.append(
            {
                "pair_key": pair1,
                "aces": r.get("aces_t1", 0),
                "double_faults": r.get("double_faults_t1", 0),
                "won_on_1st_serve": r.get("won_on_1st_serve_t1", 0),
                "won_on_2nd_serve": r.get("won_on_2nd_serve_t1", 0),
                "total_points_won": r.get("total_points_won_t1", 0),
                "break_points_converted": r.get("break_points_converted_t1", 0),
                "wins": 1 if r["winner"] == "team_1" else 0,
            }
        )
        pair_records.append(
            {
                "pair_key": pair2,
                "aces": r.get("aces_t2", 0),
                "double_faults": r.get("double_faults_t2", 0),
                "won_on_1st_serve": r.get("won_on_1st_serve_t2", 0),
                "won_on_2nd_serve": r.get("won_on_2nd_serve_t2", 0),
                "total_points_won": r.get("total_points_won_t2", 0),
                "break_points_converted": r.get("break_points_converted_t2", 0),
                "wins": 1 if r["winner"] == "team_2" else 0,
            }
        )

        h2h_records.append(
            {
                "pair_a": pair1,
                "pair_b": pair2,
                "pair_a_win": 1 if r["winner"] == "team_1" else 0,
                "aces_a": r.get("aces_t1", 0),
                "double_faults_a": r.get("double_faults_t1", 0),
                "won_on_1st_serve_a": r.get("won_on_1st_serve_t1", 0),
                "won_on_2nd_serve_a": r.get("won_on_2nd_serve_t1", 0),
                "total_points_won_a": r.get("total_points_won_t1", 0),
                "break_points_converted_a": r.get("break_points_converted_t1", 0),
                "aces_b": r.get("aces_t2", 0),
                "double_faults_b": r.get("double_faults_t2", 0),
                "won_on_1st_serve_b": r.get("won_on_1st_serve_t2", 0),
                "won_on_2nd_serve_b": r.get("won_on_2nd_serve_t2", 0),
                "total_points_won_b": r.get("total_points_won_t2", 0),
                "break_points_converted_b": r.get("break_points_converted_t2", 0),
            }
        )

    pairs = pd.DataFrame(pair_records).dropna(subset=["pair_key"])
    if pairs.empty:
        raise HTTPException(status_code=500, detail="No pair history available for matchup prediction")

    agg = (
        pairs.groupby("pair_key")
        .agg(
            aces=("aces", "mean"),
            double_faults=("double_faults", "mean"),
            won_on_1st_serve=("won_on_1st_serve", "mean"),
            won_on_2nd_serve=("won_on_2nd_serve", "mean"),
            total_points_won=("total_points_won", "mean"),
            break_points_converted=("break_points_converted", "mean"),
            win_rate=("wins", "mean"),
        )
        .reset_index()
    )
    pair_stats_map: Dict[str, Dict[str, float]] = {}
    for _, row in agg.iterrows():
        pair_stats_map[str(row["pair_key"]).strip()] = {
            "aces": float(row["aces"]),
            "double_faults": float(row["double_faults"]),
            "won_on_1st_serve": float(row["won_on_1st_serve"]),
            "won_on_2nd_serve": float(row["won_on_2nd_serve"]),
            "total_points_won": float(row["total_points_won"]),
            "break_points_converted": float(row["break_points_converted"]),
            "win_rate": float(row["win_rate"]),
        }

    h2h_df = pd.DataFrame(h2h_records) if h2h_records else pd.DataFrame()
    return pair_stats_map, h2h_df


def _get_pair_profile(pair_key: str, pair_stats: Dict[str, Dict[str, float]], defaults: Dict[str, float]):
    return pair_stats.get(pair_key, defaults)


def _head_to_head_profiles(pair_a: str, pair_b: str, h2h_df: pd.DataFrame):
    if h2h_df.empty:
        return None, None
    direct = h2h_df[(h2h_df["pair_a"] == pair_a) & (h2h_df["pair_b"] == pair_b)]
    reverse = h2h_df[(h2h_df["pair_a"] == pair_b) & (h2h_df["pair_b"] == pair_a)]
    if direct.empty and reverse.empty:
        return None, None

    rows = []
    if not direct.empty:
        rows.append(direct)
    if not reverse.empty:
        rev = reverse.rename(
            columns={
                "pair_a": "pair_b",
                "pair_b": "pair_a",
                "pair_a_win": "pair_b_win",
                "aces_a": "aces_b",
                "double_faults_a": "double_faults_b",
                "won_on_1st_serve_a": "won_on_1st_serve_b",
                "won_on_2nd_serve_a": "won_on_2nd_serve_b",
                "total_points_won_a": "total_points_won_b",
                "break_points_converted_a": "break_points_converted_b",
                "aces_b": "aces_a",
                "double_faults_b": "double_faults_a",
                "won_on_1st_serve_b": "won_on_1st_serve_a",
                "won_on_2nd_serve_b": "won_on_2nd_serve_a",
                "total_points_won_b": "total_points_won_a",
                "break_points_converted_b": "break_points_converted_a",
            }
        ).copy()
        rev["pair_a_win"] = 1 - reverse["pair_a_win"]
        rows.append(rev)

    combined = pd.concat(rows, ignore_index=True)
    a_profile = {
        "aces": float(combined["aces_a"].mean()),
        "double_faults": float(combined["double_faults_a"].mean()),
        "won_on_1st_serve": float(combined["won_on_1st_serve_a"].mean()),
        "won_on_2nd_serve": float(combined["won_on_2nd_serve_a"].mean()),
        "total_points_won": float(combined["total_points_won_a"].mean()),
        "break_points_converted": float(combined["break_points_converted_a"].mean()),
        "win_rate": float(combined["pair_a_win"].mean()),
    }
    b_profile = {
        "aces": float(combined["aces_b"].mean()),
        "double_faults": float(combined["double_faults_b"].mean()),
        "won_on_1st_serve": float(combined["won_on_1st_serve_b"].mean()),
        "won_on_2nd_serve": float(combined["won_on_2nd_serve_b"].mean()),
        "total_points_won": float(combined["total_points_won_b"].mean()),
        "break_points_converted": float(combined["break_points_converted_b"].mean()),
        "win_rate": float(1 - combined["pair_a_win"].mean()),
    }
    return a_profile, b_profile


def _blend_profiles(base_profile: Dict[str, float], h2h_profile: Dict[str, float], h2h_weight: float):
    return {
        k: float((1 - h2h_weight) * base_profile[k] + h2h_weight * h2h_profile[k])
        for k in base_profile.keys()
    }


def _feature_row_from_profiles(tournament_name: str, round_name: str, t1: Dict[str, float], t2: Dict[str, float]):
    return {
        "tournament_name": tournament_name,
        "round": round_name,
        "aces_t1": t1["aces"],
        "aces_t2": t2["aces"],
        "double_faults_t1": t1["double_faults"],
        "double_faults_t2": t2["double_faults"],
        "won_on_1st_serve_t1": t1["won_on_1st_serve"],
        "won_on_1st_serve_t2": t2["won_on_1st_serve"],
        "won_on_2nd_serve_t1": t1["won_on_2nd_serve"],
        "won_on_2nd_serve_t2": t2["won_on_2nd_serve"],
        "total_points_won_t1": t1["total_points_won"],
        "total_points_won_t2": t2["total_points_won"],
        "break_points_converted_t1": t1["break_points_converted"],
        "break_points_converted_t2": t2["break_points_converted"],
    }


@app.get("/health")
def health():
    return {"status": "ok", "time": utc_now()}


@app.get("/debug/boom")
def debug_boom():
    if not ENABLE_DEBUG_ENDPOINTS:
        raise HTTPException(status_code=404, detail="Debug endpoints are disabled")
    raise RuntimeError("Intentional debug crash for robustness testing")


@app.post("/predict")
def predict(request: PredictionRequest):
    model_path = _latest_local_model_path(request.model_name)
    @_with_retries(max_attempts=3, wait_seconds=0.6)
    def _load_model():
        return joblib.load(model_path)

    model = _load_model()
    predictions = _run_prediction(model, request.features)
    _log_event(
        "info",
        "predict_success",
        model_name=request.model_name,
        rows=len(request.features),
        model_path=str(model_path),
    )
    return {
        "status": "ok",
        "model_name": request.model_name,
        "artifact_path": str(model_path),
        "rows": len(request.features),
        "predictions": predictions,
    }


@app.post("/predict/matchup")
def predict_matchup(request: MatchupRequest):
    model_path = _latest_local_model_path("PadelWinnerClassifier")
    @_with_retries(max_attempts=3, wait_seconds=0.6)
    def _load_model():
        return joblib.load(model_path)

    model = _load_model()

    pair_stats, h2h_df = _historical_pair_stats()
    stats_values = list(pair_stats.values())
    defaults = pd.DataFrame(stats_values).mean(numeric_only=True).to_dict() if stats_values else {}
    if not defaults:
        raise HTTPException(status_code=500, detail="Unable to build matchup features from history")

    team1_key = _pair_key(request.team1_player1_name, request.team1_player2_name)
    team2_key = _pair_key(request.team2_player1_name, request.team2_player2_name)

    team1 = _get_pair_profile(team1_key, pair_stats, defaults)
    team2 = _get_pair_profile(team2_key, pair_stats, defaults)

    h2h_t1, h2h_t2 = _head_to_head_profiles(team1_key, team2_key, h2h_df)
    h2h_match_count = 0
    if not h2h_df.empty:
        h2h_match_count = int(
            len(h2h_df[(h2h_df["pair_a"] == team1_key) & (h2h_df["pair_b"] == team2_key)])
            + len(h2h_df[(h2h_df["pair_a"] == team2_key) & (h2h_df["pair_b"] == team1_key)])
        )
    if h2h_t1 and h2h_t2 and h2h_match_count > 0:
        h2h_weight = min(0.5, 0.12 * h2h_match_count)
        team1 = _blend_profiles(team1, h2h_t1, h2h_weight)
        team2 = _blend_profiles(team2, h2h_t2, h2h_weight)

    feature_row = _feature_row_from_profiles(request.tournament_name, request.round, team1, team2)
    reverse_row = _feature_row_from_profiles(request.tournament_name, request.round, team2, team1)

    def _p_team1(row: Dict[str, Any]) -> float:
        if hasattr(model, "predict_proba"):
            proba = model.predict_proba(pd.DataFrame([row]))[0].tolist()
            return float(proba[1] if len(proba) > 1 else proba[0])
        pred = int(np.asarray(model.predict(pd.DataFrame([row]))).tolist()[0])
        return float(pred)

    # Symmetry fix: evaluate both team orientations, then reconcile.
    p_forward = _p_team1(feature_row)
    p_reverse = _p_team1(reverse_row)
    p_team1 = float((p_forward + (1.0 - p_reverse)) / 2.0)

    winner = "team_1" if p_team1 >= 0.5 else "team_2"
    response = {
        "status": "ok",
        "model_name": "PadelWinnerClassifier",
        "artifact_path": str(model_path),
        "winner": winner,
        "winner_team_players": (
            [request.team1_player1_name, request.team1_player2_name]
            if winner == "team_1"
            else [request.team2_player1_name, request.team2_player2_name]
        ),
        "input": request.model_dump(),
        "derived_features": feature_row,
        "head_to_head_matches_count": h2h_match_count,
    }
    response["team_1_probability"] = p_team1
    _log_event(
        "info",
        "predict_matchup_success",
        model_path=str(model_path),
        winner=winner,
        team_1_probability=p_team1,
        head_to_head_matches_count=h2h_match_count,
        tournament_name=request.tournament_name,
        round=request.round,
    )
    return response


def train_winner_classifier() -> Dict:
    query = """
    SELECT
        winner, tournament_name, round,
        aces_t1, aces_t2, double_faults_t1, double_faults_t2,
        won_on_1st_serve_t1, won_on_1st_serve_t2,
        won_on_2nd_serve_t1, won_on_2nd_serve_t2,
        total_points_won_t1, total_points_won_t2,
        break_points_converted_t1, break_points_converted_t2
    FROM dbo.matches_with_views_interactions
    WHERE winner IN ('team_1', 'team_2')
    """
    df = load_table(query, "matches_with_views_interactions.csv").dropna(subset=["winner"])
    y = (df["winner"] == "team_1").astype(int)
    X = df.drop(columns=["winner"])

    preprocessor = ColumnTransformer(
        transformers=[
            (
                "num",
                Pipeline([("imputer", SimpleImputer(strategy="median"))]),
                X.select_dtypes(include=[np.number]).columns.tolist(),
            ),
            (
                "cat",
                Pipeline(
                    [
                        ("imputer", SimpleImputer(strategy="most_frequent")),
                        ("onehot", OneHotEncoder(handle_unknown="ignore")),
                    ]
                ),
                X.select_dtypes(exclude=[np.number]).columns.tolist(),
            ),
        ]
    )
    model = Pipeline(
        [
            ("prep", preprocessor),
            ("clf", RandomForestClassifier(n_estimators=350, max_depth=12, random_state=42)),
        ]
    )

    X_train, X_test, y_train, y_test = train_test_split(
        X, y, test_size=0.2, random_state=42, stratify=y
    )
    model.fit(X_train, y_train)
    acc = float(accuracy_score(y_test, model.predict(X_test)))

    MODELS_DIR.mkdir(parents=True, exist_ok=True)
    local_path = MODELS_DIR / f"winner_classifier_{utc_now()}.joblib"
    joblib.dump(model, local_path)

    with start_registry_run("padel_winner_classification", "PadelWinnerClassifier"):
        mlflow.log_metric("accuracy", acc)
        mlflow.log_param("algorithm", "RandomForestClassifier")
        mlflow.log_artifact(str(local_path), artifact_path="model_files")
        mlflow.sklearn.log_model(model, artifact_path="model", registered_model_name=None)
        version = register_version("PadelWinnerClassifier", "model")
    return {"model": "PadelWinnerClassifier", "version": version, "accuracy": acc}


def train_views_regression() -> Dict:
    query = """
    SELECT
        views, round, tournament_name,
        aces_t1, aces_t2, double_faults_t1, double_faults_t2,
        total_points_won_t1, total_points_won_t2,
        break_points_converted_t1, break_points_converted_t2
    FROM dbo.matches_with_views_interactions
    WHERE views IS NOT NULL
    """
    df = load_table(query, "matches_with_views_interactions.csv").dropna(subset=["views"])
    y = np.log1p(df["views"])
    X = df.drop(columns=["views"])

    preprocessor = ColumnTransformer(
        transformers=[
            (
                "num",
                Pipeline([("imputer", SimpleImputer(strategy="median"))]),
                X.select_dtypes(include=[np.number]).columns.tolist(),
            ),
            (
                "cat",
                Pipeline(
                    [
                        ("imputer", SimpleImputer(strategy="most_frequent")),
                        ("onehot", OneHotEncoder(handle_unknown="ignore")),
                    ]
                ),
                X.select_dtypes(exclude=[np.number]).columns.tolist(),
            ),
        ]
    )
    model = Pipeline([("prep", preprocessor), ("reg", Ridge(alpha=1.0))])

    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)
    model.fit(X_train, y_train)
    r2 = float(model.score(X_test, y_test))

    MODELS_DIR.mkdir(parents=True, exist_ok=True)
    local_path = MODELS_DIR / f"views_regression_{utc_now()}.joblib"
    joblib.dump(model, local_path)

    with start_registry_run("padel_views_regression", "PadelViewsRegression"):
        mlflow.log_metric("r2", r2)
        mlflow.log_param("algorithm", "Ridge")
        mlflow.log_artifact(str(local_path), artifact_path="model_files")
        mlflow.sklearn.log_model(model, artifact_path="model", registered_model_name=None)
        version = register_version("PadelViewsRegression", "model")
    return {"model": "PadelViewsRegression", "version": version, "r2": r2}


def train_players_clustering() -> Dict:
    query = """
    SELECT
        team1_player1_name, team1_player2_name, team2_player1_name, team2_player2_name,
        winner, aces_t1, aces_t2, double_faults_t1, double_faults_t2,
        total_points_won_t1, total_points_won_t2, total_won_on_return_t1, total_won_on_return_t2
    FROM dbo.matches_with_views_interactions
    WHERE winner IN ('team_1', 'team_2')
    """
    df = load_table(query, "matches_with_views_interactions.csv")

    records = []
    for _, r in df.iterrows():
        t1_players = [r.get("team1_player1_name"), r.get("team1_player2_name")]
        t2_players = [r.get("team2_player1_name"), r.get("team2_player2_name")]
        for p in t1_players:
            records.append(
                {
                    "player_name": p,
                    "win": 1 if r["winner"] == "team_1" else 0,
                    "aces": r.get("aces_t1", 0),
                    "double_faults": r.get("double_faults_t1", 0),
                    "total_points_won": r.get("total_points_won_t1", 0),
                    "total_won_on_return": r.get("total_won_on_return_t1", 0),
                }
            )
        for p in t2_players:
            records.append(
                {
                    "player_name": p,
                    "win": 1 if r["winner"] == "team_2" else 0,
                    "aces": r.get("aces_t2", 0),
                    "double_faults": r.get("double_faults_t2", 0),
                    "total_points_won": r.get("total_points_won_t2", 0),
                    "total_won_on_return": r.get("total_won_on_return_t2", 0),
                }
            )

    players = pd.DataFrame(records).dropna(subset=["player_name"])
    agg = (
        players.groupby("player_name")
        .agg(
            win_rate=("win", "mean"),
            aces=("aces", "mean"),
            double_faults=("double_faults", "mean"),
            total_points_won=("total_points_won", "mean"),
            total_won_on_return=("total_won_on_return", "mean"),
        )
        .reset_index()
    )
    agg["IA"] = (agg["aces"] * agg["win_rate"]).fillna(0)
    agg["IE"] = (agg["double_faults"] / (agg["total_points_won"] + 1)).fillna(0)
    agg["ID"] = agg["total_won_on_return"].fillna(0)

    features = ["win_rate", "IA", "IE", "ID"]
    scaler = StandardScaler()
    x_scaled = scaler.fit_transform(agg[features])

    model = KMeans(n_clusters=4, random_state=42, n_init=10)
    labels = model.fit_predict(x_scaled)
    sil = float(silhouette_score(x_scaled, labels))
    agg["cluster"] = labels

    MODELS_DIR.mkdir(parents=True, exist_ok=True)
    local_path = MODELS_DIR / f"players_clustering_{utc_now()}.joblib"
    payload = {"scaler": scaler, "model": model, "features": features}
    joblib.dump(payload, local_path)
    agg.to_csv(MODELS_DIR / "players_cluster_output.csv", index=False)

    with start_registry_run("padel_players_clustering", "PadelPlayersClustering"):
        mlflow.log_metric("silhouette", sil)
        mlflow.log_param("algorithm", "KMeans")
        mlflow.log_artifact(str(local_path), artifact_path="model_files")
        mlflow.log_artifact(str(MODELS_DIR / "players_cluster_output.csv"), artifact_path="outputs")
        mlflow.sklearn.log_model(model, artifact_path="model", registered_model_name=None)
        version = register_version("PadelPlayersClustering", "model")
    return {"model": "PadelPlayersClustering", "version": version, "silhouette": sil}


def train_timeseries_forecast() -> Dict:
    query = """
    SELECT
        YEAR([date]) AS [year],
        MONTH([date]) AS [month],
        SUM(COALESCE([views], 0)) AS total_views
    FROM dbo.matches_with_views_interactions
    WHERE [date] IS NOT NULL
    GROUP BY YEAR([date]), MONTH([date])
    """
    df = load_table(query, "monthly_views.csv")
    if "year_month_dt" not in df.columns:
        df["year_month_dt"] = pd.to_datetime(
            df["year"].astype(str) + "-" + df["month"].astype(str) + "-01", errors="coerce"
        )
    df = df.dropna(subset=["year_month_dt"]).sort_values("year_month_dt")
    ts = (
        df.set_index("year_month_dt")["total_views"]
        .astype(float)
        .resample("MS")
        .sum()
        .fillna(0)
    )
    feat = pd.DataFrame({"y": ts})
    feat["lag1"] = feat["y"].shift(1)
    feat["lag2"] = feat["y"].shift(2)
    feat["month"] = feat.index.month
    feat = feat.dropna()

    x = feat[["lag1", "lag2", "month"]]
    y = feat["y"]
    model = Ridge(alpha=1.0)
    model.fit(x, y)
    score = float(model.score(x, y))

    MODELS_DIR.mkdir(parents=True, exist_ok=True)
    local_path = MODELS_DIR / f"timeseries_forecast_{utc_now()}.joblib"
    joblib.dump({"model": model, "features": ["lag1", "lag2", "month"]}, local_path)

    with start_registry_run("padel_timeseries_forecasting", "PadelTimeSeriesForecast"):
        mlflow.log_metric("train_r2", score)
        mlflow.log_param("algorithm", "LagFeatures+Ridge")
        mlflow.log_artifact(str(local_path), artifact_path="model_files")
        mlflow.sklearn.log_model(model, artifact_path="model", registered_model_name=None)
        version = register_version("PadelTimeSeriesForecast", "model")
    return {"model": "PadelTimeSeriesForecast", "version": version, "train_r2": score}


@app.post("/train/all")
def train_all():
    try:
        results = {
            "winner_classification": train_winner_classifier(),
            "views_regression": train_views_regression(),
            "players_clustering": train_players_clustering(),
            "timeseries_forecasting": train_timeseries_forecast(),
        }
        _log_event("info", "train_all_success", models=list(results.keys()))
        return {"status": "ok", "executed_at": datetime.utcnow().isoformat(), "results": results}
    except Exception as exc:
        _log_event("error", "train_all_failed", error=str(exc), traceback=traceback.format_exc())
        _send_alert("Model training failed", {"error": str(exc)})
        raise


@app.get("/models/versions")
def list_versions():
    model_names = [
        "PadelWinnerClassifier",
        "PadelViewsRegression",
        "PadelPlayersClustering",
        "PadelTimeSeriesForecast",
    ]
    payload = {}
    for name in model_names:
        versions = ml_client.search_model_versions(f"name='{name}'")
        payload[name] = [
            {"version": v.version, "stage": v.current_stage, "run_id": v.run_id}
            for v in versions
        ]
    return payload


@app.get("/notebooks")
def notebook_inventory():
    nb_dir = Path(os.getenv("NOTEBOOK_DIR", "/app/notebooks"))
    files = []
    for f in sorted(nb_dir.glob("*.ipynb")):
        files.append({"name": f.name, "size": f.stat().st_size})
    return {"count": len(files), "files": files}


@app.get("/")
def root():
    return {
        "service": "Padel ML Training API",
        "endpoints": [
            "/health",
            "/debug/boom",
            "/train/all",
            "/predict",
            "/predict/matchup",
            "/models/versions",
            "/notebooks",
        ],
    }
