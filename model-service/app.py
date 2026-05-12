import hashlib
import json
import logging
import os
import threading
import time
import traceback
import uuid
from contextlib import asynccontextmanager, contextmanager
from datetime import datetime
from functools import wraps
from pathlib import Path
from typing import Any, Dict, List, Optional, Tuple
from urllib import request as urllib_request

import joblib
import mlflow
import numpy as np
import pandas as pd
from fastapi import FastAPI, HTTPException, Request
from fastapi.middleware.cors import CORSMiddleware
from fastapi.responses import JSONResponse, Response
from mlflow.models import infer_signature
from mlflow.tracking import MlflowClient
from prometheus_client import Counter, Gauge, Histogram, generate_latest
from pydantic import BaseModel, Field
from sklearn.cluster import KMeans
from sklearn.compose import ColumnTransformer
from sklearn.ensemble import (
    GradientBoostingClassifier,
    GradientBoostingRegressor,
    RandomForestClassifier,
)
from sklearn.impute import SimpleImputer
from sklearn.linear_model import Ridge
from sklearn.metrics import (
    accuracy_score,
    f1_score,
    mean_absolute_error,
    precision_score,
    recall_score,
    silhouette_score,
)
from sklearn.mixture import GaussianMixture
from sklearn.model_selection import train_test_split
from sklearn.pipeline import Pipeline
from sklearn.preprocessing import OneHotEncoder, StandardScaler

from db_io import load_table
from routers.analytics import router as analytics_router
from routers.auth import router as auth_router
from routers.padel_api import router as padel_api_router
from routers.padelas_hero import router as padelas_hero_router
from routers.powerbi import router as powerbi_router


@asynccontextmanager
async def _app_lifespan(app: FastAPI):
    _start_auto_retrain_watcher()
    yield


app = FastAPI(
    title="Padel ML Training Service", version="1.0.0", lifespan=_app_lifespan
)
app.include_router(auth_router, prefix="/api/auth", tags=["auth"])
app.include_router(powerbi_router, prefix="/api/powerbi", tags=["powerbi"])
app.include_router(analytics_router, prefix="/api/analytics", tags=["analytics"])
app.include_router(padel_api_router, prefix="/api/padel", tags=["padel"])
app.include_router(padelas_hero_router, prefix="/api/padelas-hero", tags=["padelas-hero"])

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
ALERT_WEBHOOK_URL = os.getenv("ALERT_WEBHOOK_URL", "")
ENABLE_DEBUG_ENDPOINTS = os.getenv("ENABLE_DEBUG_ENDPOINTS", "true").lower() == "true"
MODEL_VERSION = os.getenv("MODEL_VERSION", "v1")
MONITORING_BASELINE_ACCURACY = float(os.getenv("MONITORING_BASELINE_ACCURACY", "0.95"))
MONITORING_BASELINE_CONFIDENCE_MEAN = float(
    os.getenv("MONITORING_BASELINE_CONFIDENCE_MEAN", "0.85")
)

mlflow.set_tracking_uri(MLFLOW_TRACKING_URI)
ml_client = MlflowClient()
TRAINING_LOCK = threading.Lock()

# Baseline SHA-256 over training CSV fallbacks; used by POST /retrain + auto-watcher.
_retrain_data_signature: Optional[str] = None


@contextmanager
def _training_guard():
    if not TRAINING_LOCK.acquire(blocking=False):
        raise HTTPException(
            status_code=429,
            detail=(
                "Training already running. Wait for the current job to finish, "
                "or use another worker. Endpoints: /retrain, /train/all, /train/winner, "
                "/train/views, /train/clustering, /train/timeseries."
            ),
        )
    try:
        yield
    finally:
        if TRAINING_LOCK.locked():
            TRAINING_LOCK.release()


logger = logging.getLogger("padel_api")
logger.setLevel(logging.INFO)
if not logger.handlers:
    formatter = logging.Formatter("%(asctime)s | %(levelname)s | %(name)s | %(message)s")
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

# ========== Prometheus metrics ==========
requests_total = Counter(
    "requests_total",
    "Total HTTP requests",
    ["method", "endpoint", "status"],
)
request_duration_seconds = Histogram(
    "request_duration_seconds",
    "HTTP request latency",
    ["method", "endpoint"],
    buckets=(0.01, 0.025, 0.05, 0.1, 0.25, 0.5, 1.0, 2.5, 5.0),
)
active_requests = Gauge("active_requests", "Currently active requests")

model_accuracy = Gauge("model_accuracy", "Current model accuracy", ["model_version"])
model_confidence_mean = Gauge(
    "model_confidence_mean", "Mean prediction confidence", ["model_version"]
)
model_confidence_std = Gauge(
    "model_confidence_std", "Std dev of prediction confidence", ["model_version"]
)
predictions_total = Counter(
    "predictions_total", "Total predictions made", ["model_version", "label"]
)
# Survives Grafana confusion with HTTP scrapes; still resets on container restart.
full_training_completions_total = Counter(
    "full_training_completions_total",
    "Successful all-model training runs (manual /retrain, POST /train/all, or auto CSV watcher)",
    ["source"],
)

data_missing_values_percent = Gauge(
    "data_missing_values_percent", "Percentage of missing values in data"
)
data_freshness_hours = Gauge("data_freshness_hours", "Hours since last data update")
feature_drift_score = Gauge(
    "feature_drift_score", "Feature distribution drift score", ["feature_name"]
)

inference_duration_seconds = Histogram(
    "inference_duration_seconds",
    "Time taken for inference",
    ["model_version", "endpoint"],
    buckets=(0.001, 0.005, 0.01, 0.05, 0.1, 0.5, 1.0),
)
inference_errors_total = Counter("inference_errors_total", "Total inference errors", ["error_type"])

api_health_status = Gauge("api_health_status", "API health status (1=healthy, 0=unhealthy)")
model_health_status = Gauge(
    "model_health_status",
    "Model health status (1=healthy, 0=unhealthy)",
    ["model_version"],
)
monitoring_baseline_model_accuracy = Gauge(
    "monitoring_baseline_model_accuracy",
    "Baseline accuracy reference for alerting/dashboards (env MONITORING_BASELINE_ACCURACY).",
)
monitoring_baseline_confidence_mean = Gauge(
    "monitoring_baseline_confidence_mean",
    "Baseline mean-confidence reference (env MONITORING_BASELINE_CONFIDENCE_MEAN).",
)

# Conservative startup defaults.
api_health_status.set(1)
model_health_status.labels(model_version=MODEL_VERSION).set(1)
model_accuracy.labels(model_version=MODEL_VERSION).set(MONITORING_BASELINE_ACCURACY)
model_confidence_mean.labels(model_version=MODEL_VERSION).set(MONITORING_BASELINE_CONFIDENCE_MEAN)
monitoring_baseline_model_accuracy.set(MONITORING_BASELINE_ACCURACY)
monitoring_baseline_confidence_mean.set(MONITORING_BASELINE_CONFIDENCE_MEAN)
model_confidence_std.labels(model_version=MODEL_VERSION).set(0.05)
data_missing_values_percent.set(0.0)
data_freshness_hours.set(0.0)


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


class MonitoringSnapshot(BaseModel):
    model_accuracy_value: float | None = None
    model_confidence_mean_value: float | None = None
    model_confidence_std_value: float | None = None
    data_missing_values_percent_value: float | None = None
    data_freshness_hours_value: float | None = None
    feature_drift_scores: Dict[str, float] = Field(default_factory=dict)
    model_healthy: bool | None = None
    api_healthy: bool | None = None


class RetrainRequest(BaseModel):
    force: bool = Field(
        True,
        description=(
            "If true, run full training even when the CSV data signature matches the last "
            "successful run."
        ),
    )


def utc_now() -> str:
    return datetime.utcnow().strftime("%Y%m%d_%H%M%S")


def utc_now_readable() -> str:
    return datetime.utcnow().strftime("%Y-%m-%d %H:%M UTC")


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
    active_requests.inc()
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
        endpoint = request.url.path
        requests_total.labels(method=request.method, endpoint=endpoint, status="500").inc()
        inference_errors_total.labels(error_type="unhandled_exception").inc()
        api_health_status.set(0)
        _log_event(
            "error",
            "request_crashed",
            request_id=request_id,
            method=request.method,
            path=request.url.path,
            traceback=traceback.format_exc(),
        )
        raise
    finally:
        active_requests.dec()
    duration_s = time.perf_counter() - start
    duration_ms = round(duration_s * 1000, 2)
    endpoint = request.url.path
    requests_total.labels(
        method=request.method, endpoint=endpoint, status=str(response.status_code)
    ).inc()
    request_duration_seconds.labels(method=request.method, endpoint=endpoint).observe(duration_s)
    if response.status_code >= 500:
        api_health_status.set(0)
    else:
        api_health_status.set(1)
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


@app.get("/metrics")
def metrics():
    return Response(generate_latest(), media_type="text/plain; version=0.0.4; charset=utf-8")


def _log_monitoring_snapshot_anomalies(snapshot: MonitoringSnapshot) -> None:
    """Structured warning logs when injected metrics cross soft thresholds (see alert_rules.yml)."""
    flags: Dict[str, Any] = {}

    acc = snapshot.model_accuracy_value
    if acc is not None:
        lo = MONITORING_BASELINE_ACCURACY * 0.90
        if acc < lo:
            flags["accuracy_below_90pct_baseline"] = {"value": acc, "threshold": lo}
        if (MONITORING_BASELINE_ACCURACY - acc) / max(MONITORING_BASELINE_ACCURACY, 1e-9) > 0.05:
            flags["accuracy_drop_vs_baseline_gt_5pct"] = {"value": acc}

    cf = snapshot.model_confidence_mean_value
    if cf is not None:
        if cf < 0.7:
            flags["confidence_below_70pct"] = {"value": cf}
        denom = max(MONITORING_BASELINE_CONFIDENCE_MEAN, 1e-9)
        if (denom - cf) / denom > 0.05:
            flags["confidence_drop_vs_baseline_gt_5pct"] = {"value": cf}

    if snapshot.data_missing_values_percent_value is not None:
        if snapshot.data_missing_values_percent_value > 0.10:
            flags["missing_values_high"] = {
                "value": snapshot.data_missing_values_percent_value
            }

    if snapshot.data_freshness_hours_value is not None:
        if snapshot.data_freshness_hours_value > 12:
            flags["data_stale"] = {"hours": snapshot.data_freshness_hours_value}

    for fname, score in snapshot.feature_drift_scores.items():
        if float(score) > 0.3:
            flags[f"drift_{fname}"] = {"score": float(score)}

    if flags:
        _log_event("warning", "monitoring_snapshot_anomalies", anomalies=flags)


@app.post("/monitoring/snapshot")
def update_monitoring_snapshot(snapshot: MonitoringSnapshot):
    if snapshot.model_accuracy_value is not None:
        model_accuracy.labels(model_version=MODEL_VERSION).set(snapshot.model_accuracy_value)
    if snapshot.model_confidence_mean_value is not None:
        model_confidence_mean.labels(model_version=MODEL_VERSION).set(
            snapshot.model_confidence_mean_value
        )
    if snapshot.model_confidence_std_value is not None:
        model_confidence_std.labels(model_version=MODEL_VERSION).set(
            snapshot.model_confidence_std_value
        )
    if snapshot.data_missing_values_percent_value is not None:
        data_missing_values_percent.set(snapshot.data_missing_values_percent_value)
    if snapshot.data_freshness_hours_value is not None:
        data_freshness_hours.set(snapshot.data_freshness_hours_value)
    for feature_name, score in snapshot.feature_drift_scores.items():
        feature_drift_score.labels(feature_name=feature_name).set(float(score))
    if snapshot.model_healthy is not None:
        model_health_status.labels(model_version=MODEL_VERSION).set(
            1 if snapshot.model_healthy else 0
        )
    if snapshot.api_healthy is not None:
        api_health_status.set(1 if snapshot.api_healthy else 0)

    _log_monitoring_snapshot_anomalies(snapshot)

    return {"status": "ok"}


def start_registry_run(experiment_name: str, run_name: str):
    # Defensive guard: if a previous run is still active in this process,
    # close it before opening a new run to avoid "already active" failures.
    while mlflow.active_run() is not None:
        mlflow.end_run()
    mlflow.set_experiment(experiment_name)
    return mlflow.start_run(run_name=run_name)


def register_version(
    model_name: str, artifact_subpath: str, *, algorithm_name: str
) -> Dict[str, Any]:
    try:
        ml_client.get_registered_model(model_name)
    except Exception:
        ml_client.create_registered_model(model_name)
    run_id = mlflow.active_run().info.run_id
    model_uri = f"runs:/{run_id}/{artifact_subpath}"
    registered = mlflow.register_model(model_uri=model_uri, name=model_name)
    ver_str = str(registered.version)
    stamp = utc_now_readable()
    desc = f"Version {ver_str} — {stamp} — {algorithm_name}"
    try:
        ml_client.update_model_version(
            name=model_name,
            version=ver_str,
            description=desc,
        )
    except Exception as exc:
        _log_event("warning", "mlflow_version_description_failed", error=str(exc))
    mlflow.set_tag("algorithm", algorithm_name)
    mlflow.set_tag("version_label", desc)
    return {
        "version": int(ver_str) if ver_str.isdigit() else ver_str,
        "description": desc,
        "algorithm": algorithm_name,
        "run_id": run_id,
    }


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
                "Train first with POST /train/all or a single-task POST /train/winner, "
                "/train/views, /train/clustering, or /train/timeseries."
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
        raise HTTPException(
            status_code=500, detail="No pair history available for matchup prediction"
        )

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


def _get_pair_profile(
    pair_key: str, pair_stats: Dict[str, Dict[str, float]], defaults: Dict[str, float]
):
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


def _blend_profiles(
    base_profile: Dict[str, float], h2h_profile: Dict[str, float], h2h_weight: float
):
    return {
        k: float((1 - h2h_weight) * base_profile[k] + h2h_weight * h2h_profile[k])
        for k in base_profile.keys()
    }


def _feature_row_from_profiles(
    tournament_name: str, round_name: str, t1: Dict[str, float], t2: Dict[str, float]
):
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
    infer_start = time.perf_counter()
    try:
        model_path = _latest_local_model_path(request.model_name)

        @_with_retries(max_attempts=3, wait_seconds=0.6)
        def _load_model():
            return joblib.load(model_path)

        model = _load_model()
        predictions = _run_prediction(model, request.features)
        infer_duration = time.perf_counter() - infer_start
        inference_duration_seconds.labels(
            model_version=MODEL_VERSION, endpoint="/predict"
        ).observe(infer_duration)
        labels, counts = np.unique(np.asarray(predictions), return_counts=True)
        for label, count in zip(labels.tolist(), counts.tolist()):
            predictions_total.labels(model_version=MODEL_VERSION, label=str(label)).inc(int(count))
        model_health_status.labels(model_version=MODEL_VERSION).set(1)
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
    except Exception:
        inference_errors_total.labels(error_type="predict_failure").inc()
        model_health_status.labels(model_version=MODEL_VERSION).set(0)
        raise


@app.post("/predict/matchup")
def predict_matchup(request: MatchupRequest):
    infer_start = time.perf_counter()
    try:
        model_path = _latest_local_model_path("PadelWinnerClassifier")

        @_with_retries(max_attempts=3, wait_seconds=0.6)
        def _load_model():
            return joblib.load(model_path)

        model = _load_model()

        pair_stats, h2h_df = _historical_pair_stats()
        stats_values = list(pair_stats.values())
        defaults = (
            pd.DataFrame(stats_values).mean(numeric_only=True).to_dict() if stats_values else {}
        )
        if not defaults:
            raise HTTPException(
                status_code=500, detail="Unable to build matchup features from history"
            )

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

        feature_row = _feature_row_from_profiles(
            request.tournament_name, request.round, team1, team2
        )
        reverse_row = _feature_row_from_profiles(
            request.tournament_name, request.round, team2, team1
        )

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
        model_confidence_mean.labels(model_version=MODEL_VERSION).set(p_team1)
        model_confidence_std.labels(model_version=MODEL_VERSION).set(abs(p_forward - p_team1))
        predictions_total.labels(model_version=MODEL_VERSION, label=str(int(p_team1 >= 0.5))).inc()
        inference_duration_seconds.labels(
            model_version=MODEL_VERSION, endpoint="/predict/matchup"
        ).observe(time.perf_counter() - infer_start)

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
    except Exception:
        inference_errors_total.labels(error_type="predict_matchup_failure").inc()
        model_health_status.labels(model_version=MODEL_VERSION).set(0)
        raise


def _classification_preprocessor(X: pd.DataFrame) -> ColumnTransformer:
    return ColumnTransformer(
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


def train_winner_classifier() -> Dict[str, Any]:
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
    X_train, X_test, y_train, y_test = train_test_split(
        X, y, test_size=0.2, random_state=42, stratify=y
    )

    estimators: List[Tuple[str, Any]] = [
        ("RandomForest", RandomForestClassifier(n_estimators=350, max_depth=12, random_state=42)),
        (
            "GradientBoosting",
            GradientBoostingClassifier(
                random_state=42, max_depth=4, learning_rate=0.08, n_estimators=180
            ),
        ),
    ]
    runs: List[Dict[str, Any]] = []
    last_acc = 0.0
    for algo_name, clf in estimators:
        model = Pipeline([("prep", _classification_preprocessor(X)), ("clf", clf)])
        model.fit(X_train, y_train)
        y_pred = model.predict(X_test)
        acc = float(accuracy_score(y_test, y_pred))
        f1 = float(f1_score(y_test, y_pred, average="weighted", zero_division=0))
        prec = float(precision_score(y_test, y_pred, average="weighted", zero_division=0))
        rec = float(recall_score(y_test, y_pred, average="weighted", zero_division=0))

        MODELS_DIR.mkdir(parents=True, exist_ok=True)
        stamp = utc_now()
        local_path = MODELS_DIR / f"winner_classifier_{algo_name}_{stamp}.joblib"
        joblib.dump(model, local_path)

        run_name = f"PadelWinnerClassifier_{algo_name}_{stamp}"
        with start_registry_run("padel_winner_classification", run_name):
            input_example = X_train.head(5)
            signature = infer_signature(input_example, model.predict(input_example))
            mlflow.log_param("algorithm", algo_name)
            mlflow.log_metric("accuracy", acc)
            mlflow.log_metric("f1_weighted", f1)
            mlflow.log_metric("precision_weighted", prec)
            mlflow.log_metric("recall_weighted", rec)
            mlflow.log_artifact(str(local_path), artifact_path="model_files")
            mlflow.sklearn.log_model(
                model,
                artifact_path="model",
                registered_model_name=None,
                signature=signature,
                input_example=input_example,
            )
            reg = register_version("PadelWinnerClassifier", "model", algorithm_name=algo_name)
        runs.append(
            {
                **reg,
                "accuracy": acc,
                "f1_weighted": f1,
                "artifact_path": str(local_path),
            }
        )
        last_acc = acc

    model_accuracy.labels(model_version=MODEL_VERSION).set(last_acc)
    return {"model": "PadelWinnerClassifier", "runs": runs}


def _regression_preprocessor(X: pd.DataFrame) -> ColumnTransformer:
    return ColumnTransformer(
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


def train_views_regression() -> Dict[str, Any]:
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
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

    regressors: List[Tuple[str, Any]] = [
        ("Ridge", Ridge(alpha=1.0)),
        (
            "GradientBoosting",
            GradientBoostingRegressor(
                random_state=42, max_depth=4, n_estimators=150, learning_rate=0.08
            ),
        ),
    ]
    runs: List[Dict[str, Any]] = []
    for algo_name, reg in regressors:
        model = Pipeline([("prep", _regression_preprocessor(X)), ("reg", reg)])
        model.fit(X_train, y_train)
        y_pred = model.predict(X_test)
        r2 = float(model.score(X_test, y_test))
        mae = float(mean_absolute_error(y_test, y_pred))

        MODELS_DIR.mkdir(parents=True, exist_ok=True)
        stamp = utc_now()
        local_path = MODELS_DIR / f"views_regression_{algo_name}_{stamp}.joblib"
        joblib.dump(model, local_path)

        run_name = f"PadelViewsRegression_{algo_name}_{stamp}"
        with start_registry_run("padel_views_regression", run_name):
            input_example = X_train.head(5)
            signature = infer_signature(input_example, model.predict(input_example))
            mlflow.log_param("algorithm", algo_name)
            mlflow.log_metric("r2", r2)
            mlflow.log_metric("mae_log_views", mae)
            mlflow.log_artifact(str(local_path), artifact_path="model_files")
            mlflow.sklearn.log_model(
                model,
                artifact_path="model",
                registered_model_name=None,
                signature=signature,
                input_example=input_example,
            )
            reg_info = register_version("PadelViewsRegression", "model", algorithm_name=algo_name)
        runs.append({**reg_info, "r2": r2, "mae_log_views": mae, "artifact_path": str(local_path)})

    return {"model": "PadelViewsRegression", "runs": runs}


def train_players_clustering() -> Dict[str, Any]:
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
    x_frame = pd.DataFrame(x_scaled, columns=features)

    runs: List[Dict[str, Any]] = []

    km = KMeans(n_clusters=4, random_state=42, n_init=10)
    km_labels = km.fit_predict(x_scaled)
    sil_km = float(silhouette_score(x_scaled, km_labels))
    agg["cluster"] = km_labels

    MODELS_DIR.mkdir(parents=True, exist_ok=True)
    stamp_km = utc_now()
    local_km = MODELS_DIR / f"players_clustering_KMeans_{stamp_km}.joblib"
    payload_km = {"scaler": scaler, "model": km, "features": features}
    joblib.dump(payload_km, local_km)
    agg.to_csv(MODELS_DIR / "players_cluster_output.csv", index=False)

    run_name_km = f"PadelPlayersClustering_KMeans_{stamp_km}"
    with start_registry_run("padel_players_clustering", run_name_km):
        input_example = x_frame.head(5)
        signature = infer_signature(input_example, km.predict(input_example))
        mlflow.log_param("algorithm", "KMeans")
        mlflow.log_metric("silhouette", sil_km)
        mlflow.log_artifact(str(local_km), artifact_path="model_files")
        mlflow.log_artifact(str(MODELS_DIR / "players_cluster_output.csv"), artifact_path="outputs")
        mlflow.sklearn.log_model(
            km,
            artifact_path="model",
            registered_model_name=None,
            signature=signature,
            input_example=input_example,
        )
        reg_km = register_version("PadelPlayersClustering", "model", algorithm_name="KMeans")
    runs.append({**reg_km, "silhouette": sil_km, "artifact_path": str(local_km)})

    gm = GaussianMixture(n_components=4, random_state=42, covariance_type="full")
    gm.fit(x_scaled)
    gm_labels = gm.predict(x_scaled)
    sil_gm = float(silhouette_score(x_scaled, gm_labels))
    gmm_csv = MODELS_DIR / "players_cluster_output_GMM.csv"
    agg.assign(cluster=gm_labels).to_csv(gmm_csv, index=False)

    stamp_gm = utc_now()
    local_gm = MODELS_DIR / f"players_clustering_GMM_{stamp_gm}.joblib"
    payload_gm = {"scaler": scaler, "model": gm, "features": features}
    joblib.dump(payload_gm, local_gm)

    run_name_gm = f"PadelPlayersClustering_GMM_{stamp_gm}"
    with start_registry_run("padel_players_clustering", run_name_gm):
        signature_gm = infer_signature(input_example, gm.predict(input_example))
        mlflow.log_param("algorithm", "GaussianMixture")
        mlflow.log_metric("silhouette", sil_gm)
        mlflow.log_metric("bic", float(gm.bic(x_scaled)))
        mlflow.log_artifact(str(local_gm), artifact_path="model_files")
        mlflow.log_artifact(str(gmm_csv), artifact_path="outputs")
        mlflow.sklearn.log_model(
            gm,
            artifact_path="model",
            registered_model_name=None,
            signature=signature_gm,
            input_example=input_example,
        )
        reg_gm = register_version(
            "PadelPlayersClustering", "model", algorithm_name="GaussianMixture"
        )
    runs.append({**reg_gm, "silhouette": sil_gm, "artifact_path": str(local_gm)})

    return {"model": "PadelPlayersClustering", "runs": runs}


def train_timeseries_forecast() -> Dict[str, Any]:
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
    ts = df.set_index("year_month_dt")["total_views"].astype(float).resample("MS").sum().fillna(0)
    feat = pd.DataFrame({"y": ts})
    feat["lag1"] = feat["y"].shift(1)
    feat["lag2"] = feat["y"].shift(2)
    feat["month"] = feat.index.month
    feat = feat.dropna()

    x = feat[["lag1", "lag2", "month"]]
    y = feat["y"]
    feature_cols = ["lag1", "lag2", "month"]
    runs: List[Dict[str, Any]] = []

    ridge = Ridge(alpha=1.0)
    ridge.fit(x, y)
    score_ridge = float(ridge.score(x, y))
    stamp_r = utc_now()
    path_r = MODELS_DIR / f"timeseries_forecast_Ridge_{stamp_r}.joblib"
    joblib.dump({"model": ridge, "features": feature_cols}, path_r)
    run_name_r = f"PadelTimeSeriesForecast_Ridge_{stamp_r}"
    with start_registry_run("padel_timeseries_forecasting", run_name_r):
        input_example = x.head(5)
        signature = infer_signature(input_example, ridge.predict(input_example))
        mlflow.log_param("algorithm", "Ridge")
        mlflow.log_metric("train_r2", score_ridge)
        mlflow.log_artifact(str(path_r), artifact_path="model_files")
        mlflow.sklearn.log_model(
            ridge,
            artifact_path="model",
            registered_model_name=None,
            signature=signature,
            input_example=input_example,
        )
        reg_r = register_version("PadelTimeSeriesForecast", "model", algorithm_name="Ridge")
    runs.append({**reg_r, "train_r2": score_ridge, "artifact_path": str(path_r)})

    gbr = GradientBoostingRegressor(
        random_state=42, max_depth=3, n_estimators=200, learning_rate=0.06
    )
    gbr.fit(x, y)
    score_gbr = float(gbr.score(x, y))
    stamp_g = utc_now()
    path_g = MODELS_DIR / f"timeseries_forecast_GradientBoosting_{stamp_g}.joblib"
    joblib.dump({"model": gbr, "features": feature_cols}, path_g)
    run_name_g = f"PadelTimeSeriesForecast_GBR_{stamp_g}"
    with start_registry_run("padel_timeseries_forecasting", run_name_g):
        signature_g = infer_signature(input_example, gbr.predict(input_example))
        mlflow.log_param("algorithm", "GradientBoosting")
        mlflow.log_metric("train_r2", score_gbr)
        mlflow.log_artifact(str(path_g), artifact_path="model_files")
        mlflow.sklearn.log_model(
            gbr,
            artifact_path="model",
            registered_model_name=None,
            signature=signature_g,
            input_example=input_example,
        )
        reg_g = register_version(
            "PadelTimeSeriesForecast", "model", algorithm_name="GradientBoosting"
        )
    runs.append({**reg_g, "train_r2": score_gbr, "artifact_path": str(path_g)})

    return {"model": "PadelTimeSeriesForecast", "runs": runs}


def _training_data_csv_paths() -> List[Path]:
    names = ("matches_with_views_interactions.csv", "monthly_views.csv")
    return [DATA_DIR / name for name in names]


def _training_data_checksum() -> str:
    """SHA-256 over training CSV fallbacks under DATA_DIR (content + sizes + mtimes)."""
    digest = hashlib.sha256()
    for path in _training_data_csv_paths():
        digest.update(path.name.encode("utf-8"))
        digest.update(b"\0")
        if not path.exists():
            digest.update(b"MISSING\n")
            continue
        st = path.stat()
        digest.update(f"{st.st_size}\n".encode())
        digest.update(f"{st.st_mtime_ns}\n".encode())
        with path.open("rb") as fh:
            for chunk in iter(lambda: fh.read(1024 * 1024), b""):
                digest.update(chunk)
    return digest.hexdigest()


def _execute_train_all() -> Dict[str, Any]:
    results = {
        "winner_classification": train_winner_classifier(),
        "views_regression": train_views_regression(),
        "players_clustering": train_players_clustering(),
        "timeseries_forecasting": train_timeseries_forecast(),
    }
    _log_event("info", "train_all_success", models=list(results.keys()))
    return results


def _capture_retrain_baseline() -> None:
    """After a successful full training run, store signature so adds/edits/deletes can be detected."""
    global _retrain_data_signature
    _retrain_data_signature = _training_data_checksum()


def _auto_retrain_worker() -> None:
    global _retrain_data_signature
    enabled_raw = os.getenv("AUTO_RETRAIN_ENABLED", "true").lower()
    if enabled_raw in ("0", "false", "no"):
        logger.info("Auto-retrain disabled (AUTO_RETRAIN_ENABLED=%s)", enabled_raw)
        return
    try:
        interval = float(os.getenv("AUTO_RETRAIN_INTERVAL_SECONDS", "60"))
    except ValueError:
        interval = 60.0
    interval = max(15.0, interval)
    logger.info("Auto-retrain polling every %ss", interval)
    while True:
        time.sleep(interval)
        try:
            current = _training_data_checksum()
            if _retrain_data_signature is None:
                _retrain_data_signature = current
                continue
            if current == _retrain_data_signature:
                continue
            _log_event(
                "info",
                "auto_retrain_detected_change",
                previous_prefix=_retrain_data_signature[:12],
                current_prefix=current[:12],
            )
            try:
                with _training_guard():
                    _log_event(
                        "info",
                        "retraining_started",
                        trigger="auto_csv_watch",
                        endpoint="/retrain",
                        reason="data_fingerprint_changed",
                    )
                    _execute_train_all()
                    _capture_retrain_baseline()
                    full_training_completions_total.labels(source="auto_csv_watch").inc()
                _log_event("info", "auto_retrain_finished_ok")
            except HTTPException as exc:
                if exc.status_code == 429:
                    _log_event("warning", "auto_retrain_deferred_training_busy")
                else:
                    _log_event(
                        "error",
                        "auto_retrain_http_abort",
                        status=exc.status_code,
                        detail=str(exc.detail),
                    )
            except Exception as exc:
                _log_event(
                    "error",
                    "auto_retrain_failed",
                    error=str(exc),
                    traceback=traceback.format_exc(),
                )
                _send_alert("Auto-retrain failed", {"error": str(exc)})
        except Exception as exc:
            _log_event(
                "error",
                "auto_retrain_watch_failed",
                error=str(exc),
                traceback=traceback.format_exc(),
            )


def _start_auto_retrain_watcher() -> None:
    threading.Thread(
        target=_auto_retrain_worker, name="auto-retrain", daemon=True
    ).start()


def _run_single_train_job(target: str, fn):
    with _training_guard():
        try:
            result = fn()
            _log_event("info", "train_single_success", target=target)
            return {"status": "ok", "executed_at": datetime.utcnow().isoformat(), "result": result}
        except Exception as exc:
            _log_event(
                "error",
                "train_single_failed",
                target=target,
                error=str(exc),
                traceback=traceback.format_exc(),
            )
            _send_alert(f"Model training failed ({target})", {"error": str(exc)})
            raise


@app.post("/retrain")
def retrain(payload: RetrainRequest):
    current = _training_data_checksum()
    if (
        not payload.force
        and _retrain_data_signature is not None
        and current == _retrain_data_signature
    ):
        _log_event("info", "retraining_skipped", reason="data_unchanged", endpoint="/retrain")
        return {
            "status": "skipped",
            "reason": "data_unchanged",
            "executed_at": datetime.utcnow().isoformat(),
            "data_signature_sha256": current,
        }
    with _training_guard():
        _log_event(
            "info",
            "retraining_started",
            trigger="manual_retrain",
            endpoint="/retrain",
            force=payload.force,
        )
        try:
            results = _execute_train_all()
            _capture_retrain_baseline()
            full_training_completions_total.labels(source="retrain").inc()
            return {
                "status": "ok",
                "executed_at": datetime.utcnow().isoformat(),
                "trigger": "manual",
                "forced": payload.force,
                "data_signature_sha256": _retrain_data_signature,
                "results": results,
            }
        except Exception as exc:
            _log_event(
                "error", "retrain_failed", error=str(exc), traceback=traceback.format_exc()
            )
            _send_alert("Retrain failed", {"error": str(exc)})
            raise


@app.post("/train/all")
def train_all():
    with _training_guard():
        _log_event("info", "retraining_started", trigger="train_all", endpoint="/train/all")
        try:
            results = _execute_train_all()
            _capture_retrain_baseline()
            full_training_completions_total.labels(source="train_all").inc()
            return {
                "status": "ok",
                "executed_at": datetime.utcnow().isoformat(),
                "results": results,
            }
        except Exception as exc:
            _log_event(
                "error", "train_all_failed", error=str(exc), traceback=traceback.format_exc()
            )
            _send_alert("Model training failed", {"error": str(exc)})
            raise


@app.post("/train/winner")
def train_winner_only():
    return _run_single_train_job("winner_classification", train_winner_classifier)


@app.post("/train/views")
def train_views_only():
    return _run_single_train_job("views_regression", train_views_regression)


@app.post("/train/clustering")
def train_clustering_only():
    return _run_single_train_job("players_clustering", train_players_clustering)


@app.post("/train/timeseries")
def train_timeseries_only():
    return _run_single_train_job("timeseries_forecasting", train_timeseries_forecast)


@app.get("/models/versions")
def list_versions():
    model_names = [
        "PadelWinnerClassifier",
        "PadelViewsRegression",
        "PadelPlayersClustering",
        "PadelTimeSeriesForecast",
    ]

    def _version_sort_key(m) -> int:
        try:
            return int(m.version)
        except (TypeError, ValueError):
            return 0

    payload = {}
    for name in model_names:
        versions = ml_client.search_model_versions(f"name='{name}'")
        payload[name] = []
        for v in sorted(versions, key=_version_sort_key, reverse=True):
            params = {}
            if getattr(v, "run_id", None):
                try:
                    run = ml_client.get_run(v.run_id)
                    params = dict(getattr(run.data, "params", {}) or {})
                except Exception as exc:
                    logger.warning("Could not fetch MLflow params for run %s: %s", v.run_id, exc)
            payload[name].append(
                {
                    "version": v.version,
                    "description": getattr(v, "description", None) or "",
                    "stage": v.current_stage,
                    "run_id": v.run_id,
                    "params": params,
                }
            )
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
            "/retrain",
            "/train/all",
            "/train/winner",
            "/train/views",
            "/train/clustering",
            "/train/timeseries",
            "/predict",
            "/predict/matchup",
            "/models/versions",
            "/metrics",
            "/monitoring/snapshot",
            "/notebooks",
            "/api/auth/login",
            "/api/auth/me",
            "/api/powerbi/embed-config",
            "/api/padelas-hero/chat",
            "/api/padelas-hero/schema",
        ],
    }
