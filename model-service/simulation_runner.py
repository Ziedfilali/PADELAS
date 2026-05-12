#!/usr/bin/env python3
"""
Production-style monitoring drills for this stack:

  1) High traffic → HTTP latency / rate visible in Grafana + Prometheus latency alerts.
  2) Model/data drift → POST /monitoring/snapshot pushes gauges → drift & accuracy/confidence alerts.
  3) API errors → 5xx spikes via GET /debug/boom (needs ENABLE_DEBUG_ENDPOINTS=true).

Run inside the model-service container (paths in Docker: /app/simulation_runner.py):
  docker compose exec model-service python /app/simulation_runner.py \\
      --scenario all --api-url http://127.0.0.1:8000
"""

from __future__ import annotations

import argparse
import asyncio
import random
import time

import aiohttp
import numpy as np


class TrafficSimulator:
    """Sustained request load (default GET /health; optional POST /predict/matchup)."""

    def __init__(
        self,
        api_url: str,
        duration_seconds: int,
        rps: int,
        max_concurrent: int,
        traffic_endpoint: str = "health",
    ):
        self.api_url = api_url.rstrip("/")
        self.duration = duration_seconds
        self.rps = rps
        self.max_concurrent = max(1, max_concurrent)
        self.traffic_endpoint = traffic_endpoint
        self.total = 0
        self.success = 0
        self.failed = 0

    async def send_request(self, session: aiohttp.ClientSession):
        payload = {
            "tournament_name": "Qatar Major",
            "round": "Semifinal",
            "team1_player1_name": "Arturo Coello",
            "team1_player2_name": "Agustin Tapia",
            "team2_player1_name": "Alejandro Galan",
            "team2_player2_name": "Juan Lebron",
        }
        try:
            if self.traffic_endpoint == "matchup":
                async with session.post(
                    f"{self.api_url}/predict/matchup",
                    json=payload,
                    timeout=aiohttp.ClientTimeout(total=600),
                ) as res:
                    data = await res.json()
                    return res.status == 200 and data.get("status") == "ok"
            async with session.get(
                f"{self.api_url}/health",
                timeout=aiohttp.ClientTimeout(total=30),
            ) as res:
                data = await res.json()
                return res.status == 200 and data.get("status") == "ok"
        except Exception:
            return False

    async def run(self):
        start = time.time()
        sem = asyncio.Semaphore(self.max_concurrent)

        async def bounded_send(session: aiohttp.ClientSession):
            async with sem:
                return await self.send_request(session)

        timeout = aiohttp.ClientTimeout(total=120)
        connector = aiohttp.TCPConnector(limit=self.max_concurrent)
        async with aiohttp.ClientSession(
            timeout=timeout, connector=connector
        ) as session:
            while time.time() - start < self.duration:
                tasks = [bounded_send(session) for _ in range(self.rps)]
                results = await asyncio.gather(*tasks, return_exceptions=True)
                for ok in results:
                    self.total += 1
                    if ok is True:
                        self.success += 1
                    else:
                        self.failed += 1
                await asyncio.sleep(1)
        print(f"[traffic] total={self.total} success={self.success} failed={self.failed}")


async def push_snapshot(
    api_url: str,
    accuracy: float,
    confidence: float,
    drift_shift: float,
    missing: float,
    freshness: float,
):
    payload = {
        "model_accuracy_value": accuracy,
        "model_confidence_mean_value": confidence,
        "model_confidence_std_value": float(
            np.std([confidence, confidence * 0.95, confidence * 1.05])
        ),
        "data_missing_values_percent_value": missing,
        "data_freshness_hours_value": freshness,
        "feature_drift_scores": {
            "aces_t1": drift_shift,
            "double_faults_t1": max(0.0, drift_shift - 0.05),
            "total_points_won_t1": max(0.0, drift_shift - 0.1),
        },
        "model_healthy": True,
        "api_healthy": True,
    }
    timeout = aiohttp.ClientTimeout(total=120, connect=15)
    for attempt in range(3):
        try:
            async with aiohttp.ClientSession(timeout=timeout) as session:
                async with session.post(
                    f"{api_url.rstrip('/')}/monitoring/snapshot", json=payload
                ) as res:
                    if res.status != 200:
                        print(f"[snapshot] failed status={res.status}")
                    return
        except (asyncio.TimeoutError, aiohttp.ClientError) as exc:
            if attempt == 2:
                raise
            await asyncio.sleep(2 + attempt * 2)
            print(f"[snapshot] retry after {exc}")


async def run_drift_simulation(api_url: str, steps: int = 20):
    """Ramp metrics that Prometheus alerts classify as degradation / drift / stale data."""
    for idx in range(steps):
        factor = min(0.55, idx * 0.03)
        accuracy = max(0.75, 0.95 - factor * 0.35)
        confidence = max(0.58, 0.85 - factor * 0.42)
        await push_snapshot(
            api_url=api_url,
            accuracy=accuracy,
            confidence=confidence,
            drift_shift=factor,
            missing=min(0.15, 0.01 + idx * 0.005),
            freshness=min(24.0, idx * 0.8),
        )
        await asyncio.sleep(3)
    print("[drift] ramp complete (check Prometheus alerts + api.log for anomaly lines)")


async def run_api_error_simulation(api_url: str, boom_iterations: int = 40):
    """
    Spike 5xx via /debug/boom plus bad inference payloads.
    Requires ENABLE_DEBUG_ENDPOINTS=true on the API.
    """
    base = api_url.rstrip("/")
    boom_url = f"{base}/debug/boom"

    timeout = aiohttp.ClientTimeout(total=30)
    async with aiohttp.ClientSession(timeout=timeout) as session:
        for i in range(boom_iterations):
            try:
                async with session.get(boom_url) as res:
                    if res.status != 500:
                        print(f"[errors] boom status={res.status} (expected 500)")
            except aiohttp.ClientError as exc:
                print(f"[errors] boom attempt {i}: {exc}")
            await asyncio.sleep(0.15)

        for _ in range(25):
            if random.random() < 0.5:
                try:
                    async with session.post(
                        f"{base}/predict",
                        json={"model_name": "unknown_model", "features": [{"x": 1}]},
                        timeout=10,
                    ) as res:
                        pass
                except aiohttp.ClientError:
                    pass
            await asyncio.sleep(0.2)
    print("[errors] spike complete (inspect HighErrorRate / CriticalErrorRate in Prometheus)")


async def run_high_traffic(
    api_url: str,
    traffic_seconds: int,
    rps: int,
    max_concurrent: int,
    traffic_endpoint: str,
):
    await TrafficSimulator(
        api_url,
        traffic_seconds,
        rps,
        max_concurrent,
        traffic_endpoint=traffic_endpoint,
    ).run()


def build_parser() -> argparse.ArgumentParser:
    p = argparse.ArgumentParser(description="MLOps monitoring simulation scenarios.")
    p.add_argument(
        "--scenario",
        choices=("all", "traffic", "drift", "errors"),
        default="all",
        help="Which drill to run: traffic load, drift snapshot ramp, HTTP 5xx spike, or all in order.",
    )
    p.add_argument("--api-url", default="http://localhost:8000")
    p.add_argument("--traffic-seconds", type=int, default=90)
    p.add_argument("--rps", type=int, default=20)
    p.add_argument("--drift-steps", type=int, default=24)
    p.add_argument(
        "--max-concurrent",
        type=int,
        default=20,
        help="Cap parallel requests (protect single-worker uvicorn).",
    )
    p.add_argument(
        "--traffic-endpoint",
        choices=("health", "matchup"),
        default="health",
        help="'health' = fast sustained RPS; 'matchup' = heavy per-request latency.",
    )
    p.add_argument("--boom-requests", type=int, default=40)
    return p


async def main_async(args):
    scenario = args.scenario

    if scenario in ("all", "traffic"):
        print("[1/3 or 1/1] high traffic")
        await run_high_traffic(
            args.api_url,
            args.traffic_seconds,
            args.rps,
            args.max_concurrent,
            args.traffic_endpoint,
        )

    if scenario == "all":
        await asyncio.sleep(15)

    if scenario in ("all", "drift"):
        print("[2/3 or 1/1] drift + degradation snapshots")
        await run_drift_simulation(args.api_url, args.drift_steps)

        print("[finalize drift] sustained bad snapshot hold for alert pending windows")
        await push_snapshot(
            api_url=args.api_url,
            accuracy=0.84,
            confidence=0.62,
            drift_shift=0.38,
            missing=0.12,
            freshness=14.0,
        )
        await asyncio.sleep(65)

    if scenario == "all":
        await asyncio.sleep(10)

    if scenario in ("all", "errors"):
        print("[3/3 or 1/1] API error spike")
        await run_api_error_simulation(args.api_url, args.boom_requests)
        await asyncio.sleep(130)

    if scenario == "all":
        print("[done] all scenarios finished → open Grafana + Alertmanager + logs/api.log")
    else:
        print(f"[done] scenario={scenario}")


def main():
    asyncio.run(main_async(build_parser().parse_args()))


if __name__ == "__main__":
    main()
