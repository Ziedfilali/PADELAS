# PROJECT SUMMARY
# Frontend: Expo (React Native + expo-router) + React Native Web; static bundle served by nginx in
#   Docker service "frontend" on host port 8088 (internal 80). API base: EXPO_PUBLIC_API_BASE (empty
#   in Docker → same-origin /api via nginx).
# Backend: FastAPI (uvicorn) in Docker service "model-service" on host port 8000 (internal 8000).
# Database: Microsoft SQL Server (external to Compose); accessed via pyodbc + pandas read_sql in
#   db_io.load_table. Connection env vars: DB_DRIVER, DB_HOST, DB_PORT, DB_NAME, DB_USER, DB_PASSWORD,
#   DB_TRUST_CERT. CSV fallback under DATA_DIR when DW_FALLBACK_TO_CSV=true.
# Docker network: Compose default bridge; services reach API at http://model-service:8000.
# Key entities: dbo.matches_with_views_interactions (matches + stats + views); derived aggregates
#   for players, tournaments, monthly views (see padelas-hero/schema-map.json).
# LLM: default Groq OpenAI-compatible API (free tier key) or optional local Ollama — see GROQ_API_KEY / OLLAMA_BASE_URL.

"""PADELAS HERO — warehouse-aware chat (Groq free tier or Ollama; OpenAI-compatible SSE streaming)."""

from __future__ import annotations

import asyncio
import json
import logging
import os
import re
import threading
import time
from pathlib import Path
from typing import Any, AsyncIterator, Dict, List, Literal, Optional

import httpx
from fastapi import APIRouter, HTTPException
from fastapi.responses import StreamingResponse
from pydantic import BaseModel, Field

from routers import analytics as analytics_router

logger = logging.getLogger(__name__)

router = APIRouter()


def _schema_path() -> Path:
    """Docker: /app/padelas-hero/... ; local monorepo: n8n-mlops/padelas-hero/..."""
    here = Path(__file__).resolve().parent
    candidates = (
        here.parent / "padelas-hero" / "schema-map.json",
        here.parent.parent / "padelas-hero" / "schema-map.json",
    )
    for p in candidates:
        if p.is_file():
            return p
    return candidates[0]


_RATE_LOCK = threading.Lock()
_RATE_BUCKETS: Dict[str, List[float]] = {}
_RATE_LIMIT = 30
_RATE_WINDOW_S = 3600.0


def _load_schema_map() -> Dict[str, Any]:
    try:
        raw = _schema_path().read_text(encoding="utf-8")
        return json.loads(raw)
    except Exception as exc:
        logger.warning("padelas_hero_schema_read_failed: %s", exc)
        return {"entities": {}, "dataAccess": {}, "error": str(exc)}


def _check_rate_limit(session_id: str) -> None:
    now = time.time()
    cutoff = now - _RATE_WINDOW_S
    with _RATE_LOCK:
        bucket = _RATE_BUCKETS.setdefault(session_id, [])
        while bucket and bucket[0] < cutoff:
            bucket.pop(0)
        if len(bucket) >= _RATE_LIMIT:
            raise HTTPException(
                status_code=429,
                detail=f"Rate limit: max {_RATE_LIMIT} messages per hour per session.",
            )
        bucket.append(now)


def _format_live_snapshot(overview: Dict[str, Any]) -> str:
    lines: List[str] = []
    lines.append(f"dataOrigin: {overview.get('dataOrigin', 'unknown')}")
    players = overview.get("players") or []
    matches = overview.get("matches") or []
    tournaments = overview.get("tournaments") or []
    lines.append(f"totalPlayers: {len(players)}")
    lines.append(f"totalMatches: {len(matches)}")
    lines.append(f"totalTournaments: {len(tournaments)}")
    lines.append("--- top 5 players (by ranking / win rate in payload) ---")
    for p in players[:5]:
        st = p.get("stats") or {}
        wr = st.get("winRate")
        wins = st.get("wins")
        losses = st.get("losses")
        wl = ""
        if wins is not None and losses is not None:
            wl = f" | wins={wins} losses={losses}"
        lines.append(
            f"- {p.get('name')} | rank={p.get('ranking')} | winRate={wr}{wl} "
            f"| matches={st.get('matchesPlayed')} | cluster={p.get('clusterName')}"
        )
    lines.append("--- last 8 matches (most recent first in API list) ---")
    for m in matches[:8]:
        t1 = m.get("team1") or {}
        t2 = m.get("team2") or {}
        sg = m.get("setGames") or []
        sg_txt = f" setGames={sg}" if sg else ""
        lines.append(
            f"- {m.get('date')} | {m.get('tournamentName')} | {m.get('round')} | "
            f"{t1.get('player1')}/{t1.get('player2')} vs {t2.get('player1')}/{t2.get('player2')} | "
            f"status={m.get('status')} winner={m.get('winner')} sets={t1.get('sets')}{sg_txt} "
            f"views={m.get('views')}"
        )
    lines.append("--- tournaments / schedule-relevant (sample; not exhaustive) ---")
    for t in tournaments[:10]:
        lines.append(
            f"- {t.get('name')} | id={t.get('id')} | matches={t.get('totalMatches')} "
            f"| start={t.get('startDate')} end={t.get('endDate')} category={t.get('category')}"
        )
    upcoming = [m for m in matches if m.get("status") == "scheduled"]
    lines.append(f"--- upcoming-style matches (no winner, status=scheduled): {len(upcoming)} ---")
    for m in upcoming[:8]:
        lines.append(
            f"- {m.get('date')} {m.get('tournamentName')} {m.get('round')} "
            f"{(m.get('team1') or {}).get('player1')} / {(m.get('team2') or {}).get('player1')}"
        )
    return "\n".join(lines)


async def _gather_live_context() -> tuple[bool, str, Optional[str]]:
    try:
        overview = await asyncio.to_thread(analytics_router.analytics_overview)
        text = _format_live_snapshot(overview)
        return True, text, None
    except Exception as exc:
        logger.exception("padelas_hero_live_context_failed")
        return False, "", str(exc)


class ChatMessage(BaseModel):
    role: Literal["user", "assistant"]
    content: str = Field(..., min_length=1, max_length=32000)


class ChatRequest(BaseModel):
    messages: List[ChatMessage] = Field(..., min_length=1, max_length=50)
    sessionId: str = Field(..., min_length=4, max_length=128)


def _build_system_prompt(schema: Dict[str, Any], live_ok: bool, live_text: str, ts: str) -> str:
    # Keep schema bounded — large prompts hit Groq free-tier TPM limits quickly.
    schema_blob = json.dumps(schema, ensure_ascii=False, indent=2)[:12000]
    live_section = (
        live_text
        if live_ok
        else (
            "Warehouse excerpt: unavailable (query failed). "
            "Answer from SCHEMA and general padel knowledge only; say numbers are not verified."
        )
    )
    if live_ok and live_text:
        live_section = f"Warehouse excerpt (UTC {ts}):\n{live_text}"

    return f"""You are PADELAS HERO, the AI intelligence assistant for this padel management platform.

You have full knowledge of this platform's data structure:
SCHEMA:
{schema_blob}

{live_section}

Answer questions about players, matches, tournaments, rankings, and statistics.
Use real names and numbers from the warehouse excerpt above when it is present.
Do not use stock phrases like "live data snapshot" or "according to the snapshot" — answer directly.
Match winner is `team_1` or `team_2` (same order as team1 vs team2 in each row). For "how many wins" for a player,
use each match's winner plus whether that player appears on team1 or team2; player lines include wins= and losses= when present.
When `setGames` is present, each inner pair is [team1_games, team2_games] for that set; the side with more games won the set.
Be helpful and concise: prefer short paragraphs; format win rates as percentages (e.g. 94.3%) not long decimals.
If the user asks for counts (e.g. how many tournaments), use totalTournaments / totals in the excerpt when shown.
If you don't have the data to answer, say so honestly.
You are PADELAS HERO. If asked who you are, say your name proudly."""


def _resolve_openai_compat_llm() -> Dict[str, Any]:
    """
    Prefer Groq (free API key). Else Ollama at OLLAMA_BASE_URL (local, no key).
    """
    groq_key = (os.getenv("GROQ_API_KEY") or "").strip()
    if groq_key:
        model = (os.getenv("GROQ_MODEL") or "llama-3.1-8b-instant").strip()
        return {
            "provider": "groq",
            "url": "https://api.groq.com/openai/v1/chat/completions",
            "headers": {
                "Authorization": f"Bearer {groq_key}",
                "Content-Type": "application/json",
            },
            "model": model,
        }

    ollama = (os.getenv("OLLAMA_BASE_URL") or "").strip().rstrip("/")
    if ollama:
        model = (os.getenv("OLLAMA_MODEL") or "llama3.2").strip()
        return {
            "provider": "ollama",
            "url": f"{ollama}/v1/chat/completions",
            "headers": {"Content-Type": "application/json"},
            "model": model,
        }

    custom_url = (os.getenv("PADELAS_OPENAI_COMPAT_URL") or "").strip()
    custom_key = (os.getenv("PADELAS_OPENAI_COMPAT_KEY") or "").strip()
    if custom_url:
        return {
            "provider": "openai_compat",
            "url": custom_url,
            "headers": {
                **({"Authorization": f"Bearer {custom_key}"} if custom_key else {}),
                "Content-Type": "application/json",
            },
            "model": (os.getenv("PADELAS_OPENAI_COMPAT_MODEL") or "gpt-4o-mini").strip(),
        }

    raise HTTPException(
        status_code=503,
        detail=(
            "PADELAS HERO needs a free LLM key: set GROQ_API_KEY (free at https://console.groq.com/keys) "
            "or run Ollama locally and set OLLAMA_BASE_URL (e.g. http://host.docker.internal:11434 for Docker on Windows)."
        ),
    )


def _groq_retry_after_seconds(body: str) -> Optional[float]:
    m = re.search(r"try again in ([0-9.]+)\s*s", body, re.IGNORECASE)
    if m:
        try:
            return float(m.group(1))
        except ValueError:
            return None
    return None


def _llm_http_user_message(status_code: int, body: str) -> str:
    """Short user-facing text; avoid dumping raw JSON in the chat bubble."""
    if status_code == 429:
        wait = _groq_retry_after_seconds(body)
        hint = f" Wait about {int(wait) + 1} seconds and try again." if wait is not None else " Wait a short moment and try again."
        try:
            err = json.loads(body).get("error") or {}
            api_msg = err.get("message")
            if isinstance(api_msg, str) and "rate limit" in api_msg.lower():
                return (
                    "Groq free-tier rate limit (tokens per minute). "
                    + hint
                    + " Shorter questions help; upgrading Groq or using local Ollama avoids this."
                )
        except (json.JSONDecodeError, TypeError, AttributeError):
            pass
        return "Groq rate limit reached." + hint
    if status_code >= 500:
        return f"LLM service error (HTTP {status_code}). Try again in a moment."
    return f"LLM request failed (HTTP {status_code})."


async def _stream_openai_chat_completions(
    url: str,
    headers: Dict[str, str],
    model: str,
    system: str,
    messages: List[Dict[str, str]],
) -> AsyncIterator[str]:
    """Yield text deltas from an OpenAI-compatible streaming chat completions endpoint."""
    openai_messages: List[Dict[str, str]] = [{"role": "system", "content": system}]
    openai_messages.extend(messages)
    payload = {
        "model": model,
        "messages": openai_messages,
        "stream": True,
        "max_tokens": 900,
        "temperature": 0.35,
    }
    timeout = httpx.Timeout(120.0, connect=30.0)
    max_retries = int((os.getenv("PADELAS_HERO_LLM_429_RETRIES") or "2").strip() or "2")

    async with httpx.AsyncClient(timeout=timeout) as client:
        for attempt in range(max_retries + 1):
            async with client.stream("POST", url, headers=headers, json=payload) as resp:
                if resp.status_code == 429 and attempt < max_retries:
                    body = (await resp.aread()).decode("utf-8", errors="replace")[:4000]
                    wait = _groq_retry_after_seconds(body)
                    delay = min(max((wait or 3.0) + 0.75, 1.0), 60.0)
                    logger.warning(
                        "padelas_hero_groq_429_retry attempt=%s sleep=%.1fs", attempt + 1, delay
                    )
                    await asyncio.sleep(delay)
                    continue
                if resp.status_code >= 400:
                    body = (await resp.aread()).decode("utf-8", errors="replace")[:4000]
                    msg = _llm_http_user_message(resp.status_code, body)
                    raise HTTPException(status_code=502, detail=msg)
                async for line in resp.aiter_lines():
                    if not line or not line.startswith("data: "):
                        continue
                    data = line[6:].strip()
                    if data == "[DONE]":
                        break
                    try:
                        chunk = json.loads(data)
                    except json.JSONDecodeError:
                        continue
                    choices = chunk.get("choices") or []
                    if not choices:
                        continue
                    delta = (choices[0] or {}).get("delta") or {}
                    piece = delta.get("content") or ""
                    if piece:
                        yield piece
                return


@router.post("/chat")
async def padelas_hero_chat(body: ChatRequest):
    _check_rate_limit(body.sessionId.strip())

    llm = _resolve_openai_compat_llm()
    schema = _load_schema_map()
    live_ok, live_text, live_err = await _gather_live_context()
    ts = time.strftime("%Y-%m-%d %H:%M:%S UTC", time.gmtime())

    all_turns = [{"role": m.role, "content": m.content} for m in body.messages]
    max_ctx = int((os.getenv("PADELAS_HERO_CONTEXT_MESSAGES") or "14").strip() or "14")
    max_ctx = max(2, min(max_ctx, 50))
    truncated = len(all_turns) > max_ctx
    user_messages = all_turns[-max_ctx:] if truncated else all_turns

    system = _build_system_prompt(schema, live_ok, live_text, ts)
    if not live_ok and live_err:
        system += f"\n\n(Internal note for you: last warehouse error was: {live_err})"
    if truncated:
        system += (
            "\n\nNote: Only the most recent chat turns were sent (token budget). "
            "Use warehouse excerpt totals and samples for warehouse-wide counts."
        )

    async def event_stream() -> AsyncIterator[str]:
        yield f"data: {json.dumps({'type': 'meta', 'liveOk': live_ok, 'ts': ts, 'provider': llm['provider']})}\n\n"
        try:
            async for text in _stream_openai_chat_completions(
                llm["url"],
                llm["headers"],
                llm["model"],
                system,
                user_messages,
            ):
                if text:
                    yield f"data: {json.dumps({'type': 'text', 'text': text})}\n\n"
            yield f"data: {json.dumps({'type': 'done'})}\n\n"
        except HTTPException as exc:
            det = exc.detail
            msg = det if isinstance(det, str) else json.dumps(det, default=str)
            yield f"data: {json.dumps({'type': 'error', 'message': msg})}\n\n"
        except Exception as exc:
            logger.exception("padelas_hero_stream_failed")
            yield f"data: {json.dumps({'type': 'error', 'message': str(exc)})}\n\n"

    return StreamingResponse(
        event_stream(),
        media_type="text/event-stream",
        headers={
            "Cache-Control": "no-cache",
            "Connection": "keep-alive",
            "X-Accel-Buffering": "no",
        },
    )


@router.get("/schema")
def padelas_hero_schema():
    """Expose schema map for debugging / clients."""
    return _load_schema_map()
