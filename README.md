# Padel-analytics

Monorepo for padel data analytics, ML automation, and ETL. **GitHub default branch is `main`.** This branch (`master`) holds only shared entrypoints: this README and `.gitignore`.

## Where the code lives

| Branch | Contents |
|--------|----------|
| [`main`](https://github.com/Ziedfilali/Padel-analytics/tree/main) | Legacy stack: Airflow DAGs (`dags/`), `api_ml.py`, `docker-compose.yaml`, `n8n_backup/`, `requirements.txt` |
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
# Example: original Airflow + API layout
git checkout main
```

## Secrets

Never commit `.env`, database passwords, API keys, or Slack webhooks. Use `.env.example` from the feature branch you work on (when available) and copy to `.env` locally.

## Repository

[https://github.com/Ziedfilali/Padel-analytics](https://github.com/Ziedfilali/Padel-analytics)
