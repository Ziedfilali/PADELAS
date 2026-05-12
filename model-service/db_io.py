"""Shared reads from SQL Server (or CSV under DATA_DIR) used by training and analytics."""

import json
import logging
import os
import time
from functools import wraps
from pathlib import Path

import pandas as pd
import pyodbc
from fastapi import HTTPException

logger = logging.getLogger(__name__)

"""Set on each load_table() — 'sql' or 'csv' (CSV when DB missing or query failed)."""
_LAST_TABLE_ORIGIN = "unknown"

DATA_DIR = Path(os.getenv("DATA_DIR", "/app/data"))
DB_DRIVER = os.getenv("DB_DRIVER", "ODBC Driver 18 for SQL Server")
DB_HOST = os.getenv("DB_HOST", "")
DB_PORT = os.getenv("DB_PORT", "1433")
DB_NAME = os.getenv("DB_NAME", "")
DB_USER = os.getenv("DB_USER", "")
DB_PASSWORD = os.getenv("DB_PASSWORD", "")
DB_TRUST_CERT = os.getenv("DB_TRUST_CERT", "yes")
# When false, never read CSV if SQL fails — use after DW connectivity is verified (forces real warehouse).
DW_FALLBACK_TO_CSV = os.getenv("DW_FALLBACK_TO_CSV", "true").lower() in (
    "1",
    "true",
    "yes",
)


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


def db_connection():
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
    try:
        return pyodbc.connect(conn_str)
    except Exception as exc:
        logger.warning(
            "%s",
            json.dumps(
                {
                    "message": "db_connection_failed_using_csv_fallback_if_present",
                    "error": str(exc),
                },
                default=str,
            ),
        )
        return None


def load_table(query: str, csv_fallback: str) -> pd.DataFrame:
    global _LAST_TABLE_ORIGIN

    @_with_retries(max_attempts=3, wait_seconds=1.0)
    def _safe_read_sql(local_conn):
        return pd.read_sql(query, local_conn)

    conn = db_connection()
    if conn:
        try:
            df = _safe_read_sql(conn)
            _LAST_TABLE_ORIGIN = "sql"
            return df
        except Exception as exc:
            if not DW_FALLBACK_TO_CSV:
                raise HTTPException(
                    status_code=503,
                    detail=f"SQL query failed and DW_FALLBACK_TO_CSV=false: {exc}",
                ) from exc
            fallback = DATA_DIR / csv_fallback
            if fallback.exists():
                logger.warning(
                    "%s",
                    json.dumps(
                        {
                            "message": "db_read_failed_using_fallback",
                            "fallback": str(fallback),
                            "query_preview": query[:120],
                        },
                        default=str,
                    ),
                )
                _LAST_TABLE_ORIGIN = "csv"
                return pd.read_csv(fallback)
            raise
        finally:
            conn.close()

    if not DW_FALLBACK_TO_CSV:
        raise HTTPException(
            status_code=503,
            detail=(
                "Cannot connect to SQL Server and DW_FALLBACK_TO_CSV=false. "
                "Fix DB_HOST (use host.docker.internal from Docker on Windows), DB_PORT, DB_NAME, "
                "DB_USER, DB_PASSWORD — or set DW_FALLBACK_TO_CSV=true to allow CSV under DATA_DIR."
            ),
        )

    fallback = DATA_DIR / csv_fallback
    if fallback.exists():
        _LAST_TABLE_ORIGIN = "csv"
        return pd.read_csv(fallback)

    raise HTTPException(
        status_code=500,
        detail=f"Database unavailable and fallback file missing: {fallback}",
    )


def last_load_origin() -> str:
    """Origin of the most recent load_table call: sql | csv | unknown."""
    return _LAST_TABLE_ORIGIN


__all__ = ["load_table", "db_connection", "DATA_DIR", "last_load_origin", "DW_FALLBACK_TO_CSV"]
