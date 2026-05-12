from datetime import datetime, timedelta
import os
from pathlib import Path
import subprocess

from airflow import DAG
from airflow.models.baseoperator import cross_downstream
from airflow.operators.empty import EmptyOperator
from airflow.operators.python import PythonOperator


def run_talend_job(job_name: str, **_context):
    """
    Run an exported Talend job script from inside the Airflow container.
    Expected location:
      /talend-jobs/<job_name>/<job_name>_run.sh
    Override root with TALEND_JOBS_ROOT environment variable.
    """
    jobs_root = Path(os.environ.get("TALEND_JOBS_ROOT", "/talend-jobs"))
    script_path = jobs_root / job_name / f"{job_name}_run.sh"

    if not script_path.exists():
        raise FileNotFoundError(
            f"Talend executable not found for {job_name}: {script_path}. "
            "Export each Talend job as standalone script/JAR and mount it into the Airflow container."
        )

    # Talend jobs were authored with Windows-like absolute paths (e.g. C:/pi/*.xlsx).
    # On Linux containers, map that path form by exposing "C:" from the job directory.
    windows_drive_alias = script_path.parent / "C:"
    if not windows_drive_alias.exists():
        os.symlink("/", windows_drive_alias)

    result = subprocess.run(
        ["/bin/bash", str(script_path), "--context=Default"],
        capture_output=True,
        text=True,
        check=False,
    )
    if result.returncode != 0:
        raise RuntimeError(
            f"{job_name} failed with exit code {result.returncode}\n"
            f"STDOUT:\n{result.stdout}\nSTDERR:\n{result.stderr}"
        )
    return {"job_name": job_name, "exit_code": result.returncode}


with DAG(
    dag_id="dw_etl_pipeline",
    start_date=datetime(2024, 1, 1),
    schedule_interval="0 0 * * *",
    catchup=False,
    max_active_runs=1,
    tags=["dw", "audit", "padel"],
) as dag:
    start = EmptyOperator(task_id="start")

    dim_country = PythonOperator(
        task_id="dim_country",
        python_callable=run_talend_job,
        op_kwargs={"job_name": "dim_country"},
    )
    dim_date = PythonOperator(
        task_id="dim_date",
        python_callable=run_talend_job,
        op_kwargs={"job_name": "dim_date"},
    )
    dim_equipement = PythonOperator(
        task_id="dim_equipement",
        python_callable=run_talend_job,
        op_kwargs={"job_name": "dim_equipement"},
    )
    dim_matchs = PythonOperator(
        task_id="dim_matchs",
        python_callable=run_talend_job,
        op_kwargs={"job_name": "dim_matchs"},
    )
    dim_player = PythonOperator(
        task_id="dim_player",
        python_callable=run_talend_job,
        op_kwargs={"job_name": "dim_player"},
    )
    dim_sponsor = PythonOperator(
        task_id="dim_sponsor",
        python_callable=run_talend_job,
        op_kwargs={"job_name": "dim_sponsor"},
    )
    dim_tournament = PythonOperator(
        task_id="dim_tournament",
        python_callable=run_talend_job,
        op_kwargs={"job_name": "dim_tournament"},
    )

    fact_matchs = PythonOperator(
        task_id="FACT_MATCHS",
        python_callable=run_talend_job,
        op_kwargs={"job_name": "FACT_MATCHS"},
        retries=1,
        retry_delay=timedelta(seconds=8),
    )
    fact_players = PythonOperator(
        task_id="FACT_PLAYERS",
        python_callable=run_talend_job,
        op_kwargs={"job_name": "FACT_PLAYERS"},
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
