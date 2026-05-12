"""
Padel API proxy: live + scheduled matches for the current calendar month.

Docs: https://padelapi.org/docs — Bearer token via PADEL_API_TOKEN.
"""

from __future__ import annotations

import calendar
import logging
import os
import re
from datetime import date, datetime, timedelta
from typing import Any, Dict, List, Optional, Tuple

import httpx
from fastapi import APIRouter, HTTPException, Query

logger = logging.getLogger(__name__)

router = APIRouter()

PADEL_API_BASE = os.getenv("PADEL_API_BASE", "https://padelapi.org/api").rstrip("/")
PADEL_API_TOKEN = os.getenv("PADEL_API_TOKEN", "").strip()


def _auth_headers() -> Dict[str, str]:
    if not PADEL_API_TOKEN:
        raise HTTPException(
            status_code=503,
            detail="PADEL_API_TOKEN is not set — add it to .env for model-service.",
        )
    return {
        "Authorization": f"Bearer {PADEL_API_TOKEN}",
        "Accept": "application/json",
    }


def _month_bounds(y: int, m: int) -> Tuple[str, str]:
    last_day = calendar.monthrange(y, m)[1]
    return f"{y:04d}-{m:02d}-01", f"{y:04d}-{m:02d}-{last_day:02d}"


def _pair_names(team_raw: Any) -> Tuple[str, str]:
    """Padel API returns team_1 / team_2 as list of player objects."""
    if team_raw is None:
        return "Unknown", "Unknown"
    if isinstance(team_raw, dict):
        n = (team_raw.get("name") or "").strip() or "Unknown"
        return n, "Unknown"
    if isinstance(team_raw, list):
        names = []
        for item in team_raw[:4]:
            if isinstance(item, dict):
                names.append((item.get("name") or "").strip() or "Unknown")
        while len(names) < 2:
            names.append("Unknown")
        return names[0], names[1]
    return "Unknown", "Unknown"


def _map_ui_status(api_status: str) -> str:
    if api_status == "live":
        return "live"
    if api_status == "scheduled":
        return "upcoming"
    if api_status in ("ended", "finished", "retired", "walkover"):
        return "finished"
    return "upcoming"


def _winner_side(m: Dict[str, Any], p1: str, p2: str, p3: str, p4: str) -> Optional[str]:
    w = m.get("winner")
    if not w or w == "hidden_free_plan":
        return None
    ws = str(w).lower()
    left = f"{p1} {p2}".lower()
    right = f"{p3} {p4}".lower()
    if any(ws.count(x) > 0 for x in [p1.lower(), p2.lower()] if len(x) > 2):
        return "team_1"
    if any(ws.count(x) > 0 for x in [p3.lower(), p4.lower()] if len(x) > 2):
        return "team_2"
    if left[:20] in ws or ws in left:
        return "team_1"
    if right[:20] in ws or ws in right:
        return "team_2"
    return None


def _score_parts(m: Dict[str, Any]) -> Tuple[int, int]:
    raw = m.get("score")
    if not raw or raw == "hidden_free_plan":
        return 0, 0
    s = str(raw)
    sets = re.findall(r"(\d+)\s*[-–]\s*(\d+)", s)
    if not sets:
        return 0, 0
    t1 = sum(1 for a, b in sets if int(a) > int(b))
    t2 = sum(1 for a, b in sets if int(b) > int(a))
    return t1, t2


def _month_date_bounds(y: int, mo: int) -> Tuple[date, date]:
    last_d = calendar.monthrange(y, mo)[1]
    return date(y, mo, 1), date(y, mo, last_d)


def _match_in_calendar_month(m: Dict[str, Any], y: int, mo: int) -> bool:
    """Include match if it is scheduled/live for a tournament overlapping this month, or played_at in month."""
    m_first, m_last = _month_date_bounds(y, mo)
    ts = m.get("_tournament_start")
    te = m.get("_tournament_end")
    if ts and te:
        try:
            sd = date.fromisoformat(str(ts)[:10])
            ed = date.fromisoformat(str(te)[:10])
            tour_ok = sd <= m_last and ed >= m_first
            if tour_ok and (m.get("status") in ("scheduled", "live")):
                return True
        except Exception:
            pass

    pa = m.get("played_at")
    if pa:
        try:
            d = datetime.fromisoformat(str(pa).replace("Z", "+00:00")).date()
            if d.year == y and d.month == mo:
                return True
        except Exception:
            pass

    st = m.get("status") or ""
    if st in ("scheduled", "live"):
        # Fallback when tournament dates missing (e.g. /live merge).
        return True
    return False


def _paginate(
    client: httpx.Client,
    path: str,
    params: Optional[Dict[str, Any]] = None,
    *,
    max_pages: Optional[int] = None,
) -> List[Dict[str, Any]]:
    params = dict(params or {})
    initial = f"{PADEL_API_BASE}{path}"
    url: Optional[str] = initial
    out: List[Dict[str, Any]] = []
    headers = _auth_headers()
    page_idx = 0
    while url:
        if url == initial:
            r = client.get(url, params=params, headers=headers)
        else:
            r = client.get(url, headers=headers)
        page_idx += 1
        if max_pages is not None and page_idx > max_pages:
            logger.warning("padel_api_pagination_stopped_max_pages path=%s max_pages=%s", path, max_pages)
            break
        if r.status_code == 401:
            raise HTTPException(
                status_code=502,
                detail="Padel API returned 401 — check PADEL_API_TOKEN in model-service .env.",
            )
        if r.status_code >= 400:
            logger.warning("padel_api_request_failed %s %s", r.status_code, url[:120])
            break
        body = r.json()
        out.extend(body.get("data") or [])
        url = (body.get("links") or {}).get("next") or None
    return out


def _tournament_overlaps_month(t: Dict[str, Any], m_first: date, m_last: date) -> bool:
    try:
        sd = date.fromisoformat(str(t.get("start_date") or "")[:10])
        ed = date.fromisoformat(str(t.get("end_date") or "")[:10])
    except Exception:
        return False
    return sd <= m_last and ed >= m_first


def _season_overlaps_month(s: Dict[str, Any], m_first: date, m_last: date) -> bool:
    try:
        sd = date.fromisoformat(str(s.get("start_date") or "")[:10])
        ed = date.fromisoformat(str(s.get("end_date") or "")[:10])
    except Exception:
        return False
    return sd <= m_last and ed >= m_first


def _gather_overlapping_tournaments(
    client: httpx.Client, y: int, mo: int, m_first: date, m_last: date, meta: Dict[str, Any]
) -> List[Dict[str, Any]]:
    """Resolve tournaments whose calendar intersects (y, mo); try wide list, then unpaged list, then season APIs."""
    by_id: Dict[int, Dict[str, Any]] = {}

    win_start = (m_first - timedelta(days=90)).strftime("%Y-%m-%d")
    win_end = (m_last + timedelta(days=90)).strftime("%Y-%m-%d")
    raw = _paginate(
        client,
        "/tournaments",
        {
            "after_date": win_start,
            "before_date": win_end,
            "sort_by": "start_date",
            "order_by": "asc",
        },
        max_pages=50,
    )
    meta["tournamentsInWindow"] = len(raw)
    for t in raw:
        if _tournament_overlaps_month(t, m_first, m_last):
            by_id[int(t["id"])] = t

    if not by_id:
        raw2 = _paginate(
            client,
            "/tournaments",
            {"sort_by": "start_date", "order_by": "desc"},
            max_pages=40,
        )
        meta["tournamentsFallbackNoDateFilter"] = len(raw2)
        for t in raw2:
            if _tournament_overlaps_month(t, m_first, m_last):
                by_id[int(t["id"])] = t

    if not by_id:
        seasons = _paginate(client, "/seasons", {"sort_by": "year", "order_by": "desc"}, max_pages=8)
        meta["seasonsLoaded"] = len(seasons)
        for s in seasons:
            if not _season_overlaps_month(s, m_first, m_last):
                continue
            sid = int(s["id"])
            st_list = _paginate(client, f"/seasons/{sid}/tournaments", {}, max_pages=40)
            for t in st_list:
                if _tournament_overlaps_month(t, m_first, m_last):
                    by_id[int(t["id"])] = t
        meta["seasonFallbackUsed"] = True

    meta["tournamentsOverlappingMonth"] = len(by_id)
    return list(by_id.values())


def _collect_matches_this_month(
    client: httpx.Client, y: int, mo: int
) -> Tuple[List[Dict[str, Any]], Dict[str, Any]]:
    first, last = _month_bounds(y, mo)
    m_first, m_last = _month_date_bounds(y, mo)
    meta: Dict[str, Any] = {"monthFirst": first, "monthLast": last, "liveFetched": False}

    combined: Dict[int, Dict[str, Any]] = {}

    for m in _paginate(
        client,
        "/matches",
        {
            "after_date": first,
            "before_date": last,
            "sort_by": "played_at",
            "order_by": "asc",
        },
    ):
        combined[int(m["id"])] = dict(m)

    overlapping = _gather_overlapping_tournaments(client, y, mo, m_first, m_last, meta)

    for t in overlapping:
        tid = int(t["id"])
        tname = str(t.get("name") or "Tournament")
        ts = str(t.get("start_date") or "")
        te = str(t.get("end_date") or "")
        try:
            t_matches = _paginate(client, f"/tournaments/{tid}/matches", {})
        except Exception as exc:
            logger.warning("padel_tournament_matches_failed tid=%s err=%s", tid, exc)
            continue
        for m in t_matches:
            mid = int(m["id"])
            row = dict(m)
            row["_tournament_name"] = tname
            row["_tournament_id"] = tid
            row["_tournament_start"] = ts
            row["_tournament_end"] = te
            combined[mid] = row

    try:
        r = client.get(f"{PADEL_API_BASE}/live", headers=_auth_headers())
        meta["liveHttpStatus"] = r.status_code
        if r.status_code == 200:
            meta["liveFetched"] = True
            for m in r.json().get("data") or []:
                mid = int(m["id"])
                prev = combined.get(mid, {})
                merged = {**prev, **dict(m)}
                combined[mid] = merged
    except Exception as exc:
        meta["liveError"] = str(exc)

    rows: List[Dict[str, Any]] = []
    for m in combined.values():
        if m.get("status") == "bye":
            continue
        if m.get("status") not in ("scheduled", "live"):
            continue
        if not _match_in_calendar_month(m, y, mo):
            continue
        rows.append(m)

    def _sort_key(x: Dict[str, Any]) -> Tuple[str, str]:
        pa = x.get("played_at") or ""
        sl = x.get("schedule_label") or ""
        return (str(pa), sl)

    rows.sort(key=_sort_key)
    meta["matchRowsCombined"] = len(combined)
    meta["matchRowsScheduledLiveFiltered"] = len(rows)
    return rows, meta


def _to_ui_match(
    m: Dict[str, Any],
    prediction: Optional[Dict[str, Any]],
    _pred_error: Optional[str],
) -> Dict[str, Any]:
    pl = m.get("players") or {}
    p1, p2 = _pair_names(pl.get("team_1"))
    p3, p4 = _pair_names(pl.get("team_2"))
    ui_status = _map_ui_status(str(m.get("status") or "scheduled"))
    pa = m.get("played_at")
    date_str = pa[:10] if isinstance(pa, str) and len(pa) >= 10 else date.today().isoformat()
    time_label = str(m.get("schedule_label") or "").strip() or "--:--"

    s1, s2 = _score_parts(m)
    winner = None
    if ui_status == "finished":
        winner = _winner_side(m, p1, p2, p3, p4)

    tname = str(m.get("_tournament_name") or "").strip() or "Professional circuit"

    ml_pred = None
    if prediction and isinstance(prediction.get("team_1_probability"), (int, float)):
        ml_pred = {
            "winner": prediction.get("winner"),
            "team1Probability": float(prediction["team_1_probability"]),
            "modelVersion": None,
            "headToHeadMatches": prediction.get("head_to_head_matches_count"),
        }

    return {
        "id": f"padel-{m['id']}",
        "tournamentId": f"padel-t-{m.get('_tournament_id', 'x')}",
        "tournamentName": tname,
        "round": str(m.get("round_name") or "Round"),
        "date": date_str,
        "time": time_label,
        "status": ui_status,
        "duration": str(m.get("duration") or ""),
        "team1": {
            "player1": p1,
            "player1Country": "--",
            "player2": p2,
            "player2Country": "--",
            "score": s1,
            "sets": [],
            "stats": {
                "aces": 0,
                "doubleFaults": 0,
                "wonOn1stServe": 0,
                "wonOn2ndServe": 0,
                "totalPointsWon": 0,
                "breakPointsConverted": 0,
                "totalWonOnReturn": 0,
            },
        },
        "team2": {
            "player1": p3,
            "player1Country": "--",
            "player2": p4,
            "player2Country": "--",
            "score": s2,
            "sets": [],
            "stats": {
                "aces": 0,
                "doubleFaults": 0,
                "wonOn1stServe": 0,
                "wonOn2ndServe": 0,
                "totalPointsWon": 0,
                "breakPointsConverted": 0,
                "totalWonOnReturn": 0,
            },
        },
        "winner": winner,
        "views": 0,
        "interactions": 0,
        "mlPrediction": ml_pred,
        "padelApiId": int(m["id"]),
        "source": "padel_api",
        "padelCategory": m.get("category"),
        "padelCourt": m.get("court"),
    }


def _bad_player_name(n: str) -> bool:
    s = (n or "").strip().lower()
    return not s or s in ("unknown", "tbd")


def _run_matchup_prediction(m: Dict[str, Any]) -> Tuple[Optional[Dict[str, Any]], Optional[str]]:
    pl = m.get("players") or {}
    p1, p2 = _pair_names(pl.get("team_1"))
    p3, p4 = _pair_names(pl.get("team_2"))
    if _bad_player_name(p1) or _bad_player_name(p2) or _bad_player_name(p3) or _bad_player_name(p4):
        return None, "incomplete_player_names"

    tname = str(m.get("_tournament_name") or "").strip() or "Padel API"
    rnd = str(m.get("round_name") or "Main draw")

    try:
        from app import MatchupRequest, predict_matchup

        req = MatchupRequest(
            tournament_name=tname,
            round=rnd,
            team1_player1_name=p1,
            team1_player2_name=p2,
            team2_player1_name=p3,
            team2_player2_name=p4,
        )
        out = predict_matchup(req)
        return out, None
    except Exception as exc:
        logger.warning("padel_matchup_prediction_failed match_id=%s err=%s", m.get("id"), exc)
        return None, str(exc)


@router.get("/month-schedule")
def padel_month_schedule(
    include_predictions: bool = Query(True, description="Run local ML matchup model per match"),
    year: Optional[int] = Query(None, ge=2020, le=2100),
    month: Optional[int] = Query(None, ge=1, le=12),
    max_predictions: int = Query(
        60,
        ge=1,
        le=200,
        description="Cap predictions to avoid timeouts on large slates",
    ),
) -> Dict[str, Any]:
    """
    Scheduled + live matches for the requested month from padelapi.org (Bearer token).
    Defaults to the current calendar month on the server.
    """
    today = date.today()
    y = year if year is not None else today.year
    mo = month if month is not None else today.month

    try:
        with httpx.Client(timeout=120.0) as client:
            raw_rows, fetch_meta = _collect_matches_this_month(client, y, mo)
    except httpx.HTTPError as exc:
        raise HTTPException(status_code=502, detail=f"Padel API HTTP error: {exc}") from exc

    ui_matches: List[Dict[str, Any]] = []
    pred_errors = 0

    for i, m in enumerate(raw_rows):
        pred: Optional[Dict[str, Any]] = None
        err: Optional[str] = None
        if include_predictions and i < max_predictions:
            pred, err = _run_matchup_prediction(m)
            if err:
                pred_errors += 1

        ui_matches.append(_to_ui_match(m, pred, err))

    return {
        "source": "padel_api",
        "month": f"{y:04d}-{mo:02d}",
        "matchCount": len(ui_matches),
        "predictionsIncluded": include_predictions,
        "predictionErrors": pred_errors,
        "fetch": fetch_meta,
        "matches": ui_matches,
    }
