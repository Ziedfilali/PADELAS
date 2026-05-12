# PADELAS

**PADELAS** is a padel analytics & MLOps platform: n8n-orchestrated training and serving, MLflow versioning, optional Expo frontend and Grafana/Prometheus monitoring — plus a Talend + Airflow audit/ETL track in a second branch.

Source code is hosted in the GitHub repository **[Padel-analytics](https://github.com/Ziedfilali/Padel-analytics)** (this page). **Default branch `master`** holds this landing **README**, **`.gitignore`**, [**MIT `LICENSE`**](LICENSE), and [**`SECURITY.md`**](SECURITY.md). Application code is on the feature branches below.

## Where the code lives

| Branch | Contents |
|--------|----------|
| [`feature/mlops-frontend-monitoring-may2026`](https://github.com/Ziedfilali/Padel-analytics/tree/feature/mlops-frontend-monitoring-may2026) | n8n + `model-service` + MLflow/MinIO + Expo `frontend/` + monitoring (Grafana/Prometheus) + `web-app/` |
| [`feature/audit-pi-talend-airflow-docker`](https://github.com/Ziedfilali/Padel-analytics/tree/feature/audit-pi-talend-airflow-docker) | Talend job exports (`talend_jobs/`), Airflow Docker runtime, audit scripts and `output/` reports |

Check out the branch you need, then follow the README on that branch (compose commands, `.env`, etc.).

### Quick checkout

```bash
git fetch origin
# Example: MLOps stack
git checkout feature/mlops-frontend-monitoring-may2026
# Example: Audit / Talend + Airflow
git checkout feature/audit-pi-talend-airflow-docker
```

## Secrets

Never commit `.env`, database passwords, API keys, or Slack webhooks. Use `.env.example` from the feature branch you work on (when available) and copy to `.env` locally.

## Repository

[https://github.com/Ziedfilali/Padel-analytics](https://github.com/Ziedfilali/Padel-analytics) (PADELAS source)

## GitHub “About” (paste once on the repo homepage)

GitHub does not read these from a file; open the repo → gear icon **About** → set:

- **Description:**  
  `PADELAS — padel analytics & MLOps: n8n + FastAPI + MLflow/MinIO, Expo UI & monitoring; Talend + Airflow audit ETL.`
- **Topics:** type **one tag at a time**, press **Enter** after each, then add the next. **Do not** paste the whole line as a single topic (spaces are not allowed inside one topic).

Suggested tags (copy **one line**, add as **one** topic, repeat for each line):

  - `python`
  - `mlops`
  - `docker`
  - `fastapi`
  - `n8n`
  - `mlflow`
  - `airflow`
  - `talend`
  - `grafana`
  - `prometheus`
  - `expo`
  - `sqlserver`
  - `padel`
  - `data-engineering`
  - `padelas`

If GitHub rejects a tag, skip it or try a shorter synonym (e.g. `etl` instead of `data-engineering`).
