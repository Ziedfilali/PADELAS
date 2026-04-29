#!/usr/bin/env python3
import json
from collections import defaultdict
from datetime import datetime
from pathlib import Path
import statistics


def parse_dt(value: str) -> datetime:
    value = value.replace(" ", "T")
    return datetime.fromisoformat(value)


root = Path("D:/Projet_Audit_Pi/output")
runtime_raw = json.loads((root / "airflow_runtime_raw.json").read_text(encoding="utf-8-sig"))
health_lines = (root / "scheduler_health_samples.jsonl").read_text(encoding="utf-8-sig").splitlines()
health = [json.loads(line) for line in health_lines if line.strip()]

runs = runtime_raw["runs"]
tis = runtime_raw["task_instances"]
retry_diag = runtime_raw["fact_matchs_retry_diagnostics"]

run_summaries = []
for run in runs:
    start = parse_dt(run["start_date"])
    end = parse_dt(run["end_date"])
    queue_delay = (start - parse_dt(run["execution_date"])).total_seconds()
    runtime = (end - start).total_seconds()
    run_summaries.append(
        {
            "run_id": run["run_id"],
            "state": run["state"],
            "queue_delay_s": round(queue_delay, 3),
            "runtime_s": round(runtime, 3),
            "start_date": run["start_date"],
            "end_date": run["end_date"],
        }
    )

task_durations = defaultdict(list)
task_attempts = defaultdict(list)
for ti in tis:
    if ti["duration"] is not None:
        task_durations[ti["task_id"]].append(float(ti["duration"]))
    task_attempts[ti["task_id"]].append(int(ti["try_number"]))

task_stats = []
for task_id, vals in sorted(task_durations.items()):
    task_stats.append(
        {
            "task_id": task_id,
            "runs_observed": len(vals),
            "mean_duration_s": round(statistics.mean(vals), 3),
            "p95_duration_s": round(sorted(vals)[max(0, int(len(vals) * 0.95) - 1)], 3),
            "max_duration_s": round(max(vals), 3),
            "max_try_number": max(task_attempts[task_id]),
        }
    )

healthy_count = sum(1 for x in health if x.get("scheduler_status") == "healthy")
unhealthy_count = sum(1 for x in health if x.get("scheduler_status") == "unhealthy")
availability = (healthy_count / len(health) * 100) if health else 0.0

heartbeat_lag = []
for sample in health:
    if "latest_scheduler_heartbeat" not in sample:
        continue
    try:
        sample_t = parse_dt(sample["timestamp"])
        hb_t = parse_dt(sample["latest_scheduler_heartbeat"])
        heartbeat_lag.append((sample_t - hb_t).total_seconds())
    except Exception:
        pass

scheduler = {
    "samples": len(health),
    "healthy_samples": healthy_count,
    "unhealthy_samples": unhealthy_count,
    "healthy_ratio_pct": round(availability, 2),
    "heartbeat_lag_s": {
        "mean": round(statistics.mean(heartbeat_lag), 3) if heartbeat_lag else None,
        "max": round(max(heartbeat_lag), 3) if heartbeat_lag else None,
    },
}

retry_events = []
for entry in retry_diag:
    retry_events.append(
        {
            "run_id": entry["run_id"],
            "attempt1_exists": entry["attempt1_exists"],
            "attempt2_exists": entry["attempt2_exists"],
            "attempt1_evidence": entry.get("attempt1_evidence", []),
            "attempt2_evidence": entry.get("attempt2_evidence", []),
        }
    )

report = {
    "dag_id": runtime_raw["dag_id"],
    "run_history": run_summaries,
    "task_timing_summary": task_stats,
    "scheduler_reliability": scheduler,
    "task_attempt_diagnostics": retry_events,
}

(root / "airflow_runtime_analysis.json").write_text(
    json.dumps(report, indent=2),
    encoding="utf-8",
)

lines = [
    "# Airflow Runtime Analysis",
    "",
    f"DAG: **{runtime_raw['dag_id']}**",
    "",
    "## Execution Times (Real Runs)",
]
for r in run_summaries:
    lines.append(
        f"- `{r['run_id']}` | state={r['state']} | queue_delay={r['queue_delay_s']}s | runtime={r['runtime_s']}s"
    )

lines += ["", "## Task Duration Summary"]
for t in task_stats:
    lines.append(
        f"- `{t['task_id']}` mean={t['mean_duration_s']}s p95={t['p95_duration_s']}s max={t['max_duration_s']}s max_try={t['max_try_number']}"
    )

lines += [
    "",
    "## Scheduler Reliability Over Time",
    f"- Samples: {scheduler['samples']}",
    f"- Healthy samples: {scheduler['healthy_samples']}",
    f"- Unhealthy samples: {scheduler['unhealthy_samples']}",
    f"- Healthy ratio: {scheduler['healthy_ratio_pct']}%",
    f"- Mean heartbeat lag: {scheduler['heartbeat_lag_s']['mean']}s",
    f"- Max heartbeat lag: {scheduler['heartbeat_lag_s']['max']}s",
    "",
    "## Log-Based Diagnostics Per Attempt (FACT_MATCHS)",
]
for e in retry_events:
    lines.append(
        f"- `{e['run_id']}` attempt1={e['attempt1_exists']} attempt2={e['attempt2_exists']}"
    )
    for ln in e["attempt1_evidence"][-2:]:
        lines.append(f"  - attempt1: {ln}")
    for ln in e["attempt2_evidence"][-2:]:
        lines.append(f"  - attempt2: {ln}")

(root / "airflow_runtime_analysis.md").write_text("\n".join(lines), encoding="utf-8")
print("Wrote airflow_runtime_analysis.json and airflow_runtime_analysis.md")
