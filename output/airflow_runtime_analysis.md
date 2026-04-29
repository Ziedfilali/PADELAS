# Airflow Runtime Analysis

DAG: **dw_etl_pipeline**

## Execution Times (Real Runs)
- `audit_run_20260429000840_1` | state=success | queue_delay=1.241s | runtime=196.848s
- `audit_run_20260429000840_2` | state=success | queue_delay=42.707s | runtime=168.291s
- `audit_run_20260429000840_3` | state=success | queue_delay=37.707s | runtime=168.294s

## Task Duration Summary
- `FACT_MATCHS` mean=6.182s p95=6.185s max=6.188s max_try=2
- `FACT_PLAYERS` mean=5.211s p95=5.196s max=5.256s max_try=1
- `dim_country` mean=4.271s p95=4.207s max=4.414s max_try=1
- `dim_date` mean=2.218s p95=2.209s max=2.241s max_try=1
- `dim_equipement` mean=3.204s p95=3.202s max=3.209s max_try=1
- `dim_matchs` mean=5.202s p95=5.199s max=5.212s max_try=1
- `dim_player` mean=4.212s p95=4.212s max=4.215s max_try=1
- `dim_sponsor` mean=3.199s p95=3.197s max=3.205s max_try=1
- `dim_tournament` mean=2.202s p95=2.206s max=2.207s max_try=1
- `end` mean=0.0s p95=0.0s max=0.0s max_try=0
- `start` mean=0.0s p95=0.0s max=0.0s max_try=0

## Scheduler Reliability Over Time
- Samples: 20
- Healthy samples: 14
- Unhealthy samples: 6
- Healthy ratio: 70.0%
- Mean heartbeat lag: 21.34s
- Max heartbeat lag: 90.957s

## Log-Based Diagnostics Per Attempt (FACT_MATCHS)
- `audit_run_20260429000840_1` attempt1=True attempt2=True
  - attempt1: [2026-04-28T23:09:40.504+0000] {taskinstance.py:1206} INFO - Marking task as UP_FOR_RETRY. dag_id=dw_etl_pipeline, task_id=FACT_MATCHS, run_id=audit_run_20260429000840_1, execution_date=20260428T230843, start_date=20260428T230934, end_date=20260428T230940
  - attempt1: [2026-04-28T23:09:40.527+0000] {standard_task_runner.py:110} ERROR - Failed to execute job 10 for task FACT_MATCHS (FACT_MATCHS intentional first-attempt failure for diagnostics; 293)
  - attempt2: [2026-04-28T23:11:26.148+0000] {python.py:237} INFO - Done. Returned value was: {'task': 'FACT_MATCHS', 'try_number': 2, 'sleep_seconds': 6}
  - attempt2: [2026-04-28T23:11:26.171+0000] {taskinstance.py:1206} INFO - Marking task as SUCCESS. dag_id=dw_etl_pipeline, task_id=FACT_MATCHS, run_id=audit_run_20260429000840_1, execution_date=20260428T230843, start_date=20260428T231119, end_date=20260428T231126
- `audit_run_20260429000840_2` attempt1=True attempt2=True
  - attempt1: [2026-04-28T23:11:35.768+0000] {taskinstance.py:1206} INFO - Marking task as UP_FOR_RETRY. dag_id=dw_etl_pipeline, task_id=FACT_MATCHS, run_id=audit_run_20260429000840_2, execution_date=20260428T230849, start_date=20260428T231129, end_date=20260428T231135
  - attempt1: [2026-04-28T23:11:35.795+0000] {standard_task_runner.py:110} ERROR - Failed to execute job 27 for task FACT_MATCHS (FACT_MATCHS intentional first-attempt failure for diagnostics; 448)
  - attempt2: [2026-04-28T23:12:09.508+0000] {python.py:237} INFO - Done. Returned value was: {'task': 'FACT_MATCHS', 'try_number': 2, 'sleep_seconds': 6}
  - attempt2: [2026-04-28T23:12:09.548+0000] {taskinstance.py:1206} INFO - Marking task as SUCCESS. dag_id=dw_etl_pipeline, task_id=FACT_MATCHS, run_id=audit_run_20260429000840_2, execution_date=20260428T230849, start_date=20260428T231203, end_date=20260428T231209
- `audit_run_20260429000840_3` attempt1=True attempt2=True
  - attempt1: [2026-04-28T23:11:52.616+0000] {taskinstance.py:1206} INFO - Marking task as UP_FOR_RETRY. dag_id=dw_etl_pipeline, task_id=FACT_MATCHS, run_id=audit_run_20260429000840_3, execution_date=20260428T230854, start_date=20260428T231146, end_date=20260428T231152
  - attempt1: [2026-04-28T23:11:52.645+0000] {standard_task_runner.py:110} ERROR - Failed to execute job 29 for task FACT_MATCHS (FACT_MATCHS intentional first-attempt failure for diagnostics; 466)
  - attempt2: [2026-04-28T23:12:18.388+0000] {python.py:237} INFO - Done. Returned value was: {'task': 'FACT_MATCHS', 'try_number': 2, 'sleep_seconds': 6}
  - attempt2: [2026-04-28T23:12:18.423+0000] {taskinstance.py:1206} INFO - Marking task as SUCCESS. dag_id=dw_etl_pipeline, task_id=FACT_MATCHS, run_id=audit_run_20260429000840_3, execution_date=20260428T230854, start_date=20260428T231212, end_date=20260428T231218