from datetime import datetime, timedelta
import time

from airflow import DAG
from airflow.models.baseoperator import cross_downstream
from airflow.operators.empty import EmptyOperator
from airflow.operators.python import PythonOperator


def simulate_task(task_label: str, sleep_seconds: int, fail_first_try: bool = False, **context):
    """Generate measurable runtime and optional retry/failure diagnostics."""
    ti = context["ti"]
    try_number = ti.try_number
    time.sleep(sleep_seconds)
    if fail_first_try and try_number == 1:
        raise RuntimeError(f"{task_label} intentional first-attempt failure for diagnostics")
    return {"task": task_label, "try_number": try_number, "sleep_seconds": sleep_seconds}


with DAG(
    dag_id="dw_etl_pipeline",
    start_date=datetime(2024, 1, 1),
    schedule_interval=None,
    catchup=False,
    tags=["dw", "audit", "padel"],
) as dag:
    start = EmptyOperator(task_id="start")

    dim_country = PythonOperator(
        task_id="dim_country",
        python_callable=simulate_task,
        op_kwargs={"task_label": "dim_country", "sleep_seconds": 4},
    )
    dim_date = PythonOperator(
        task_id="dim_date",
        python_callable=simulate_task,
        op_kwargs={"task_label": "dim_date", "sleep_seconds": 2},
    )
    dim_equipement = PythonOperator(
        task_id="dim_equipement",
        python_callable=simulate_task,
        op_kwargs={"task_label": "dim_equipement", "sleep_seconds": 3},
    )
    dim_matchs = PythonOperator(
        task_id="dim_matchs",
        python_callable=simulate_task,
        op_kwargs={"task_label": "dim_matchs", "sleep_seconds": 5},
    )
    dim_player = PythonOperator(
        task_id="dim_player",
        python_callable=simulate_task,
        op_kwargs={"task_label": "dim_player", "sleep_seconds": 4},
    )
    dim_sponsor = PythonOperator(
        task_id="dim_sponsor",
        python_callable=simulate_task,
        op_kwargs={"task_label": "dim_sponsor", "sleep_seconds": 3},
    )
    dim_tournament = PythonOperator(
        task_id="dim_tournament",
        python_callable=simulate_task,
        op_kwargs={"task_label": "dim_tournament", "sleep_seconds": 2},
    )

    fact_matchs = PythonOperator(
        task_id="FACT_MATCHS",
        python_callable=simulate_task,
        op_kwargs={"task_label": "FACT_MATCHS", "sleep_seconds": 6, "fail_first_try": True},
        retries=1,
        retry_delay=timedelta(seconds=8),
    )
    fact_players = PythonOperator(
        task_id="FACT_PLAYERS",
        python_callable=simulate_task,
        op_kwargs={"task_label": "FACT_PLAYERS", "sleep_seconds": 5},
    )

    end = EmptyOperator(task_id="end")

    start >> [
        dim_country,
        dim_date,
        dim_equipement,
        dim_matchs,
        dim_player,
        dim_sponsor,
        dim_tournament,
    ]

    dims = [
        dim_country,
        dim_date,
        dim_equipement,
        dim_matchs,
        dim_player,
        dim_sponsor,
        dim_tournament,
    ]
    facts = [fact_matchs, fact_players]

    cross_downstream(from_tasks=dims, to_tasks=facts)

    facts >> end
