import importlib.util
import sys
from pathlib import Path

import numpy as np
import pandas as pd
from fastapi.testclient import TestClient

_CACHED_APP_MODULE = None


def _load_app_module():
    global _CACHED_APP_MODULE
    if _CACHED_APP_MODULE is not None:
        return _CACHED_APP_MODULE
    app_root = Path(__file__).resolve().parents[1]
    if str(app_root) not in sys.path:
        sys.path.insert(0, str(app_root))
    app_path = app_root / "app.py"
    spec = importlib.util.spec_from_file_location("app_module", app_path)
    module = importlib.util.module_from_spec(spec)
    spec.loader.exec_module(module)
    _CACHED_APP_MODULE = module
    return _CACHED_APP_MODULE


class FakeWinnerModel:
    def predict_proba(self, frame):
        # Simple deterministic score based on team-level stat deltas.
        score = (
            (frame["total_points_won_t1"] - frame["total_points_won_t2"])
            + 0.4 * (frame["won_on_1st_serve_t1"] - frame["won_on_1st_serve_t2"])
            + 0.25 * (frame["won_on_2nd_serve_t1"] - frame["won_on_2nd_serve_t2"])
            + 0.15 * (frame["aces_t1"] - frame["aces_t2"])
            + 0.2 * (frame["break_points_converted_t1"] - frame["break_points_converted_t2"])
            - 0.3 * (frame["double_faults_t1"] - frame["double_faults_t2"])
        )
        p_team1 = 1 / (1 + np.exp(-score / 20.0))
        p_team0 = 1 - p_team1
        return np.vstack([p_team0.values, p_team1.values]).T

    def predict(self, frame):
        p = self.predict_proba(frame)[:, 1]
        return (p >= 0.5).astype(int)


def _mock_pair_stats():
    pair_stats = {
        "Agustin Tapia | Arturo Coello": {
            "aces": 4.5,
            "double_faults": 1.2,
            "won_on_1st_serve": 22.0,
            "won_on_2nd_serve": 11.0,
            "total_points_won": 66.0,
            "break_points_converted": 4.2,
            "win_rate": 0.76,
        },
        "Alejandro Galan | Juan Lebron": {
            "aces": 3.8,
            "double_faults": 2.0,
            "won_on_1st_serve": 20.0,
            "won_on_2nd_serve": 9.0,
            "total_points_won": 60.0,
            "break_points_converted": 3.1,
            "win_rate": 0.62,
        },
    }
    h2h_df = pd.DataFrame(
        [
            {
                "pair_a": "Agustin Tapia | Arturo Coello",
                "pair_b": "Alejandro Galan | Juan Lebron",
                "pair_a_win": 1,
                "aces_a": 4.7,
                "double_faults_a": 1.0,
                "won_on_1st_serve_a": 23.0,
                "won_on_2nd_serve_a": 11.2,
                "total_points_won_a": 67.0,
                "break_points_converted_a": 4.5,
                "aces_b": 3.5,
                "double_faults_b": 2.1,
                "won_on_1st_serve_b": 19.5,
                "won_on_2nd_serve_b": 8.8,
                "total_points_won_b": 58.0,
                "break_points_converted_b": 2.7,
            }
        ]
    )
    return pair_stats, h2h_df


def test_health_endpoint():
    module = _load_app_module()
    client = TestClient(module.app)
    res = client.get("/health")
    assert res.status_code == 200
    body = res.json()
    assert body["status"] == "ok"
    assert "time" in body


def test_debug_boom_returns_structured_error():
    module = _load_app_module()
    client = TestClient(module.app, raise_server_exceptions=False)
    res = client.get("/debug/boom")
    assert res.status_code == 500
    body = res.json()
    assert body["status"] == "error"
    assert body["error_type"] == "internal_error"
    assert "request_id" in body


def test_analytics_overview_has_payload(monkeypatch):
    import routers.analytics as analytics_router

    def fake_load(sql: str, csv_fb: str):
        if "COUNT(*)" in sql:
            return pd.DataFrame(
                [{"year": 2024, "month": 3, "match_count": 2, "total_views": 1500.0}]
            )
        return pd.DataFrame(
            [
                {
                    "tournament_name": "Demo Open",
                    "round": "Final",
                    "match_date": "2024-03-10",
                    "winner": "team_1",
                    "team1_player1_name": "A",
                    "team1_player2_name": "B",
                    "team2_player1_name": "C",
                    "team2_player2_name": "D",
                    "views": 1000,
                    "aces_t1": 5,
                    "aces_t2": 4,
                    "double_faults_t1": 1,
                    "double_faults_t2": 2,
                    "won_on_1st_serve_t1": 20,
                    "won_on_1st_serve_t2": 18,
                    "won_on_2nd_serve_t1": 10,
                    "won_on_2nd_serve_t2": 9,
                    "total_points_won_t1": 62,
                    "total_points_won_t2": 58,
                    "break_points_converted_t1": 3,
                    "break_points_converted_t2": 2,
                    "total_won_on_return_t1": 20,
                    "total_won_on_return_t2": 19,
                }
            ]
        )

    monkeypatch.setattr(analytics_router, "load_table", fake_load)
    monkeypatch.setattr(analytics_router, "last_load_origin", lambda: "sql")
    module = _load_app_module()
    client = TestClient(module.app)
    res = client.get("/api/analytics/overview")
    assert res.status_code == 200
    body = res.json()
    assert body["source"] == "warehouse"
    assert body["dataOrigin"] == "sql"
    assert len(body["matches"]) == 1
    assert body["matches"][0]["tournamentName"] == "Demo Open"
    assert len(body["tournaments"]) == 1
    assert len(body["monthlyViews"]) == 1
    assert len(body["players"]) == 4


def test_predict_matchup_is_swap_consistent(monkeypatch):
    module = _load_app_module()
    client = TestClient(module.app, raise_server_exceptions=False)

    monkeypatch.setattr(module, "_latest_local_model_path", lambda _: Path("/tmp/fake.joblib"))
    monkeypatch.setattr(module, "_historical_pair_stats", _mock_pair_stats)
    monkeypatch.setattr(module.joblib, "load", lambda _: FakeWinnerModel())

    a_vs_b = {
        "tournament_name": "Qatar Major",
        "round": "Semifinal",
        "team1_player1_name": "Arturo Coello",
        "team1_player2_name": "Agustin Tapia",
        "team2_player1_name": "Alejandro Galan",
        "team2_player2_name": "Juan Lebron",
    }
    b_vs_a = {
        "tournament_name": "Qatar Major",
        "round": "Semifinal",
        "team1_player1_name": "Alejandro Galan",
        "team1_player2_name": "Juan Lebron",
        "team2_player1_name": "Arturo Coello",
        "team2_player2_name": "Agustin Tapia",
    }

    r1 = client.post("/predict/matchup", json=a_vs_b)
    r2 = client.post("/predict/matchup", json=b_vs_a)
    assert r1.status_code == 200
    assert r2.status_code == 200
    b1 = r1.json()
    b2 = r2.json()
    assert b1["winner"] == "team_1"
    assert b2["winner"] == "team_2"
    p1 = float(b1["team_1_probability"])
    p2 = float(b2["team_1_probability"])
    assert abs((p1 + p2) - 1.0) < 0.02
