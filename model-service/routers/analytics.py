"""Dashboard-style payloads built from dbo.matches_with_views_interactions (+ CSV fallback)."""

import calendar
import hashlib
import os
import re
from typing import Any, Dict, List, Optional, Tuple

import numpy as np
import pandas as pd
from fastapi import APIRouter

from db_io import (
    DB_HOST,
    DB_NAME,
    DB_PORT,
    DB_USER,
    DW_FALLBACK_TO_CSV,
    db_connection,
    last_load_origin,
    load_table,
)

router = APIRouter()


@router.get("/warehouse-status")
def warehouse_status() -> Dict[str, Any]:
    """
    Diagnostics for SQL Server warehouse connectivity (no secrets returned).
    Call from host: GET http://localhost:8000/api/analytics/warehouse-status
    """
    pwd_set = bool(os.getenv("DB_PASSWORD", "").strip())
    cfg_ok = bool(DB_HOST and DB_NAME and DB_USER and pwd_set)
    out: Dict[str, Any] = {
        "credentialsConfigured": cfg_ok,
        "host": DB_HOST or None,
        "port": DB_PORT,
        "database": DB_NAME or None,
        "user": DB_USER or None,
        "fallbackToCsvEnabled": DW_FALLBACK_TO_CSV,
        "targetTable": "dbo.matches_with_views_interactions",
    }
    conn = db_connection()
    if not conn:
        out["connected"] = False
        out["hint"] = (
            "ODBC connection failed. Docker Desktop (Windows): set DB_HOST=host.docker.internal, "
            "DB_PORT=1433. Enable SQL Server TCP/IP, allow SQL authentication, open firewall for 1433."
        )
        return out
    try:
        cur = conn.cursor()
        cur.execute("SELECT COUNT(*) AS n FROM dbo.matches_with_views_interactions")
        row = cur.fetchone()
        n = int(row[0]) if row and row[0] is not None else 0
        out["connected"] = True
        out["warehouseReachable"] = True
        out["matchesRowCount"] = n
    except Exception as exc:
        out["connected"] = True
        out["warehouseReachable"] = False
        out["tableError"] = str(exc)
        out["hint"] = "Connected to SQL Server but query failed — check table/view name and permissions."
    finally:
        conn.close()
    return out


# If the view adds per-set games columns, include them in SELECT; Python reads set{n}_t1 / set{n}_t2 when present.
_MATCHES_SQL = """
SELECT TOP 1800
  tournament_name,
  round,
  [date] AS match_date,
  winner,
  team1_player1_name, team1_player2_name,
  team2_player1_name, team2_player2_name,
  views,
  aces_t1, aces_t2,
  double_faults_t1, double_faults_t2,
  won_on_1st_serve_t1, won_on_1st_serve_t2,
  won_on_2nd_serve_t1, won_on_2nd_serve_t2,
  total_points_won_t1, total_points_won_t2,
  break_points_converted_t1, break_points_converted_t2,
  total_won_on_return_t1, total_won_on_return_t2
FROM dbo.matches_with_views_interactions
ORDER BY CASE WHEN [date] IS NULL THEN 1 ELSE 0 END, [date] DESC
"""

_MONTHLY_SQL = """
SELECT
  YEAR([date]) AS year,
  MONTH([date]) AS month,
  COUNT(*) AS match_count,
  SUM(COALESCE(CAST([views] AS FLOAT), 0)) AS total_views
FROM dbo.matches_with_views_interactions
WHERE [date] IS NOT NULL
GROUP BY YEAR([date]), MONTH([date])
ORDER BY YEAR([date]), MONTH([date])
"""


def _norm_cols(df: pd.DataFrame) -> pd.DataFrame:
    df = df.copy()
    df.columns = [str(c).strip().lower() for c in df.columns]
    return df


def _float_metric(v: Any) -> Optional[float]:
    if v is None:
        return None
    try:
        if isinstance(v, float) and np.isnan(v):
            return None
        if pd.isna(v):
            return None
    except (TypeError, ValueError):
        pass
    try:
        f = float(v)
        if np.isnan(f):
            return None
        return f
    except (TypeError, ValueError):
        return None


def _collect_set_games_pairs(r: Dict[str, Any]) -> List[Tuple[float, float]]:
    """
    Optional per-set games columns (lowercase keys after _norm_cols).
    Supports set1_t1 / set1_t2, set2_t1 / set2_t2, … and team1_set1 / team2_set1 style.
    Returns [(team1_games, team2_games), ...] in set order.
    """
    lower = {str(k).lower().strip(): v for k, v in r.items()}
    t1: Dict[int, float] = {}
    t2: Dict[int, float] = {}
    for key, val in lower.items():
        m = re.match(r"^set(\d+)_t([12])$", key)
        if m:
            idx, side = int(m.group(1)), m.group(2)
            fv = _float_metric(val)
            if fv is None:
                continue
            (t1 if side == "1" else t2)[idx] = fv
            continue
        m2 = re.match(r"^team([12])_set(\d+)$", key)
        if m2:
            side, idx = m2.group(1), int(m2.group(2))
            fv = _float_metric(val)
            if fv is None:
                continue
            (t1 if side == "1" else t2)[idx] = fv
    idxs = sorted(set(t1) & set(t2))
    out: List[Tuple[float, float]] = []
    for i in idxs:
        a, b = t1[i], t2[i]
        if a == b:
            continue
        out.append((a, b))
    return out


def _winner_from_set_pairs(pairs: List[Tuple[float, float]]) -> Optional[str]:
    if not pairs:
        return None
    s1 = sum(1 for a, b in pairs if a > b)
    s2 = sum(1 for a, b in pairs if b > a)
    if s1 == s2:
        return None
    return "team_1" if s1 > s2 else "team_2"


def _winner_from_total_points(r: Dict[str, Any]) -> Optional[str]:
    """When winner is blank but per-point totals exist, infer match side (last resort)."""
    a = _float_metric(r.get("total_points_won_t1"))
    b = _float_metric(r.get("total_points_won_t2"))
    if a is None or b is None or a <= 0 or b <= 0:
        return None
    if abs(a - b) < 8:
        return None
    return "team_1" if a > b else "team_2"


def _effective_winner(r: Dict[str, Any]) -> Optional[str]:
    """
    Prefer normalized warehouse `winner`; else infer from per-set games columns;
    else from total_points_won_t* when one side clearly dominated.
    """
    db_w = _normalize_winner(r.get("winner"))
    if db_w is not None:
        return db_w
    pairs = _collect_set_games_pairs(r)
    w_sets = _winner_from_set_pairs(pairs)
    if w_sets is not None:
        return w_sets
    return _winner_from_total_points(r)


def _normalize_winner(raw: Any) -> Optional[str]:
    """
    Map DB/UI variants to training labels team_1 / team_2.
    DW_padel sometimes stores different casing or spacing than the notebooks.
    """
    if raw is None:
        return None
    try:
        if isinstance(raw, float) and np.isnan(raw):
            return None
        if pd.isna(raw):
            return None
    except (TypeError, ValueError):
        pass
    try:
        f = float(raw)
        if abs(f - 1.0) < 1e-9:
            return "team_1"
        if abs(f - 2.0) < 1e-9:
            return "team_2"
    except (TypeError, ValueError):
        pass
    s = str(raw).strip().lower()
    s = re.sub(r"[\s\-]+", "_", s)
    if s in ("team_1", "team1", "t1", "pair1", "pair_1", "side1", "side_1"):
        return "team_1"
    if s in ("team_2", "team2", "t2", "pair2", "pair_2", "side2", "side_2"):
        return "team_2"
    return None


def _slug_tid(name: str) -> str:
    base = re.sub(r"[^a-z0-9]+", "-", (name or "").lower()).strip("-") or "event"
    h = hashlib.md5((name or "").encode("utf-8")).hexdigest()[:8]
    return f"t-{base[:40]}-{h}"


def _match_id(row: Dict[str, Any]) -> str:
    raw = "|".join(
        str(row.get(k) or "")
        for k in (
            "tournament_name",
            "round",
            "match_date",
            "team1_player1_name",
            "team1_player2_name",
            "team2_player1_name",
            "team2_player2_name",
            "winner",
        )
    )
    return "m-" + hashlib.md5(raw.encode("utf-8")).hexdigest()[:14]


def _infer_category(name: str) -> str:
    u = (name or "").upper()
    if "WORLD" in u or "CHAMPIONSHIP" in u:
        return "WORLD"
    if "MAJOR" in u:
        return "MAJOR"
    if "GOLD" in u:
        return "GOLD"
    if "RISE" in u:
        return "RISE"
    if "PREMIER" in u or " P1 " in u or u.endswith("P1"):
        return "P1"
    return "P1"


def _fmt_date(raw) -> str:
    if pd.isna(raw):
        return ""
    if hasattr(raw, "strftime"):
        return raw.strftime("%Y-%m-%d")
    ts = pd.to_datetime(raw, errors="coerce")
    if pd.isna(ts):
        return str(raw)[:10]
    return ts.strftime("%Y-%m-%d")


def _stats_block(
    aces,
    dfs,
    w1,
    w2,
    tpw,
    bpc,
    twr,
):
    pts = tpw + 1e-6
    return {
        "aces": float(aces or 0),
        "doubleFaults": float(dfs or 0),
        "firstServePct": 72,
        "wonOn1stServe": float(w1 or 0),
        "wonOn2ndServe": float(w2 or 0),
        "totalPointsWon": float(tpw or 0),
        "breakPointsConverted": float(bpc or 0),
        "totalWonOnReturn": float(twr or 0),
        "estimatedFirstServePct": max(
            0, min(99, int(100 * float(w1 or 0) / float(pts)))
        ),
    }


def _row_to_frontend_match(r: Dict[str, Any]) -> Dict[str, Any]:
    pairs = _collect_set_games_pairs(r)
    db_w = _normalize_winner(r.get("winner"))
    w = _effective_winner(r)
    finished = w is not None

    tournament_name = str(r.get("tournament_name") or "").strip()
    tid = _slug_tid(tournament_name)

    vraw = r.get("views")
    if vraw is None or (isinstance(vraw, float) and np.isnan(vraw)) or pd.isna(vraw):
        views = 0.0
    else:
        try:
            views = float(vraw)
        except (TypeError, ValueError):
            views = 0.0

    interactions = 0
    if pd.notna(r.get("interactions")):
        try:
            interactions = int(float(r["interactions"]))
        except (TypeError, ValueError):
            interactions = 0

    status = "finished" if finished else "scheduled"

    if pairs:
        t1_score = sum(1 for a, b in pairs if a > b)
        t2_score = sum(1 for a, b in pairs if b > a)
    elif finished:
        if db_w is not None:
            t1_score = 2 if w == "team_1" else 1
            t2_score = 2 if w == "team_2" else 1
        else:
            t1_score = 1 if w == "team_1" else 0
            t2_score = 1 if w == "team_2" else 0
    else:
        t1_score = 0
        t2_score = 0

    match_date = _fmt_date(r.get("match_date"))
    winner_out = w if finished else None
    set_games = [[int(round(a)), int(round(b))] for a, b in pairs] if pairs else []
    t1_sets_list = [int(round(a)) for a, _ in pairs] if pairs else []
    t2_sets_list = [int(round(b)) for _, b in pairs] if pairs else []

    payload = {
        "id": _match_id(r),
        "tournamentId": tid,
        "tournamentName": tournament_name or "Unknown event",
        "round": str(r.get("round") or "").strip() or "—",
        "date": match_date,
        "time": "",
        "status": status,
        "duration": "FT" if finished else "",
        "team1": {
            "player1": str(r.get("team1_player1_name") or "").strip(),
            "player1Country": "XX",
            "player2": str(r.get("team1_player2_name") or "").strip(),
            "player2Country": "XX",
            "score": t1_score,
            "sets": t1_sets_list,
            "stats": _stats_block(
                r.get("aces_t1"),
                r.get("double_faults_t1"),
                r.get("won_on_1st_serve_t1"),
                r.get("won_on_2nd_serve_t1"),
                r.get("total_points_won_t1"),
                r.get("break_points_converted_t1"),
                r.get("total_won_on_return_t1"),
            ),
        },
        "team2": {
            "player1": str(r.get("team2_player1_name") or "").strip(),
            "player1Country": "XX",
            "player2": str(r.get("team2_player2_name") or "").strip(),
            "player2Country": "XX",
            "score": t2_score,
            "sets": t2_sets_list,
            "stats": _stats_block(
                r.get("aces_t2"),
                r.get("double_faults_t2"),
                r.get("won_on_1st_serve_t2"),
                r.get("won_on_2nd_serve_t2"),
                r.get("total_points_won_t2"),
                r.get("break_points_converted_t2"),
                r.get("total_won_on_return_t2"),
            ),
        },
        "winner": winner_out,
        "setGames": set_games,
        "views": views,
        "interactions": interactions,
    }
    return payload


def _tournaments_from_matches(matches: List[Dict[str, Any]]) -> List[Dict[str, Any]]:
    by_tid: Dict[str, Dict[str, Any]] = {}
    for m in matches:
        tid = m.get("tournamentId")
        name = m.get("tournamentName") or ""
        if not tid:
            continue
        if tid not in by_tid:
            by_tid[tid] = {
                "id": tid,
                "name": name,
                "short": (name[:32] + "…") if len(name) > 34 else name,
                "location": "",
                "country": "XX",
                "category": _infer_category(name),
                "surface": "—",
                "status": "finished",
                "dates": [],
                "rounds_seen": set(),
            }
        by_tid[tid]["dates"].append(m.get("date"))
        if m.get("round"):
            by_tid[tid]["rounds_seen"].add(m["round"])

    out = []
    for tid, meta in sorted(by_tid.items(), key=lambda x: x[1]["name"]):
        ds = sorted([d for d in meta["dates"] if d])
        start = ds[0] if ds else ""
        end = ds[-1] if ds else ""
        rounds = sorted(meta["rounds_seen"])
        out.append(
            {
                **{k: v for k, v in meta.items() if k not in ("dates", "rounds_seen")},
                "location": "",
                "startDate": start,
                "endDate": end,
                "prize": "—",
                "rounds": rounds if rounds else ["—"],
                "totalMatches": len([m for m in matches if m.get("tournamentId") == tid]),
                "liveMatches": sum(
                    1 for m in matches if m.get("tournamentId") == tid and m.get("status") == "live"
                ),
            }
        )
    return out


CLUSTER_COLORS = ["#00FF57", "#5BA3F5", "#F5C518", "#FF7D26"]
CLUSTER_NAMES = ["Elite band", "High tier", "Mid tier", "Developing"]


def _players_from_matches(df: pd.DataFrame) -> List[Dict[str, Any]]:
    df = df.copy()
    if "match_date" not in df.columns and "date" in df.columns:
        df.rename(columns={"date": "match_date"}, inplace=True)
    df["_dt"] = pd.to_datetime(df.get("match_date"), errors="coerce")
    df.sort_values("_dt", ascending=True, inplace=True)

    records: List[Dict[str, Any]] = []
    for _, r in df.iterrows():
        rd = r.to_dict()
        nw = _effective_winner(rd)
        if nw is None:
            continue
        t1_players = [rd.get("team1_player1_name"), rd.get("team1_player2_name")]
        t2_players = [rd.get("team2_player1_name"), rd.get("team2_player2_name")]
        dt = rd.get("_dt")
        for p in t1_players:
            pname = str(p or "").strip()
            if pname:
                records.append(
                    {
                        "player_name": pname,
                        "win": 1 if nw == "team_1" else 0,
                        "aces": float(rd.get("aces_t1") or 0),
                        "double_faults": float(rd.get("double_faults_t1") or 0),
                        "total_points_won": float(rd.get("total_points_won_t1") or 0),
                        "total_won_on_return": float(rd.get("total_won_on_return_t1") or 0),
                        "dt": dt,
                    }
                )
        for p in t2_players:
            pname = str(p or "").strip()
            if pname:
                records.append(
                    {
                        "player_name": pname,
                        "win": 1 if nw == "team_2" else 0,
                        "aces": float(rd.get("aces_t2") or 0),
                        "double_faults": float(rd.get("double_faults_t2") or 0),
                        "total_points_won": float(rd.get("total_points_won_t2") or 0),
                        "total_won_on_return": float(rd.get("total_won_on_return_t2") or 0),
                        "dt": dt,
                    }
                )

    if not records:
        return []

    ply = pd.DataFrame(records)
    agg = (
        ply.groupby("player_name")
        .agg(
            win_rate=("win", "mean"),
            wins=("win", "sum"),
            aces=("aces", "mean"),
            double_faults=("double_faults", "mean"),
            total_points_won=("total_points_won", "mean"),
            total_won_on_return=("total_won_on_return", "mean"),
            matches=("win", "size"),
        )
        .reset_index()
    )

    n = len(agg)
    if n == 0:
        return []

    rank = agg["win_rate"].rank(method="first", ascending=False)
    agg["cluster"] = np.minimum(3, np.floor((rank - 1) * 4.0 / max(n, 1))).astype(int)
    agg = agg.sort_values("win_rate", ascending=False)

    # Last five results chronologically per player
    last5_map: Dict[str, List[str]] = {pn: [] for pn in agg["player_name"]}
    for pname in agg["player_name"]:
        sub = ply[ply["player_name"] == pname].sort_values("dt")
        tail = sub.tail(5)
        wins = tail["win"].tolist()
        last5_map[pname] = ["W" if x == 1 else "L" for x in wins]

    players: List[Dict[str, Any]] = []
    for i, (_, row) in enumerate(agg.iterrows(), start=1):
        pid = hashlib.md5(str(row["player_name"]).encode("utf-8")).hexdigest()[:10]
        c = int(row["cluster"])
        players.append(
            {
                "id": f"p-{pid}",
                "name": str(row["player_name"]),
                "country": "--",
                "ranking": i,
                "cluster": c,
                "clusterName": CLUSTER_NAMES[c],
                "profileColor": CLUSTER_COLORS[c % len(CLUSTER_COLORS)],
                "stats": {
                    "winRate": float(row["win_rate"]),
                    "wins": int(row["wins"]),
                    "losses": int(row["matches"]) - int(row["wins"]),
                    "aces": float(row["aces"]),
                    "doubleFaults": float(row["double_faults"]),
                    "totalPointsWon": float(row["total_points_won"]),
                    "totalWonOnReturn": float(row["total_won_on_return"]),
                    "matchesPlayed": int(row["matches"]),
                },
                "recentResults": last5_map.get(row["player_name"], []),
            }
        )

    return players


def _monthly_views_records(df: pd.DataFrame) -> List[Dict[str, Any]]:
    df = _norm_cols(df)
    if df.empty:
        return []
    need = {"year", "month", "total_views"}
    if not need <= set(df.columns):
        return []
    out = []
    for _, r in df.iterrows():
        mo = int(r["month"]) if pd.notna(r["month"]) else 1
        yr = int(r["year"]) if pd.notna(r["year"]) else 0
        label = calendar.month_abbr[mo] if 1 <= mo <= 12 else str(mo)
        mc_raw = r.get("match_count", 0)
        try:
            mc = int(float(mc_raw)) if pd.notna(mc_raw) else 0
        except (TypeError, ValueError):
            mc = 0
        try:
            tv = float(r["total_views"])
        except (TypeError, ValueError):
            tv = 0.0
        out.append(
            {
                "month": label,
                "year": yr,
                "totalViews": tv,
                "matches": mc,
            }
        )
    return out


@router.get("/overview")
def analytics_overview() -> Dict[str, Any]:
    """
    Matches, tournaments, monthly views & player aggregates from the warehouse
    (same source as ML training — SQL + CSV fallback under DATA_DIR).
    """
    raw = load_table(_MATCHES_SQL, "matches_with_views_interactions.csv")
    matches_data_origin = last_load_origin()
    df = _norm_cols(raw)

    alias_map = {}
    if "match_date" not in df.columns and "date" in df.columns:
        alias_map["date"] = "match_date"
    if alias_map:
        df.rename(columns=alias_map, inplace=True)

    rows = df.to_dict(orient="records")
    matches: List[Dict[str, Any]] = []
    for raw_row in rows:
        r = _row_to_frontend_match(raw_row)
        matches.append(r)

    tournaments = _tournaments_from_matches(matches)

    monthly_raw = load_table(_MONTHLY_SQL, "monthly_views.csv")
    monthly_views = _monthly_views_records(monthly_raw)

    players_block = df
    if "match_date" not in players_block.columns and "date" in players_block.columns:
        players_block = players_block.rename(columns={"date": "match_date"})
    players = _players_from_matches(players_block)

    return {
        "source": "warehouse",
        "dataOrigin": matches_data_origin,
        "tournaments": tournaments,
        "matches": matches,
        "monthlyViews": monthly_views,
        "players": players,
    }
