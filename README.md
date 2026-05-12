# Padel ML Automation (n8n + Docker + Model Versioning)

This project gives you a full ML automation pipeline aligned with your professor grid:

- Triggered workflow (`Scheduler Trigger`)
- Data retrieval from SQL Server
- Model execution for your 4 project tasks
- Model saving and versioning (old versions remain accessible)
- Labelled and documented workflow exported in JSON

## 1) Architecture

`n8n` schedules and orchestrates the flow.  
`model-service` trains all models and registers a new version in MLflow.  
`MLflow + MinIO` stores versioned models and artifacts.

## 2) What is automated

The API endpoint `POST /train/all` runs all four tasks. You can also train **one task at a time**:

- `POST /train/winner` — classification (two algorithms per run)
- `POST /train/views` — regression (two algorithms per run)
- `POST /train/clustering` — clustering (two algorithms per run)
- `POST /train/timeseries` — forecasting (two algorithms per run)

For each registered name (`PadelWinnerClassifier`, `PadelViewsRegression`, `PadelPlayersClustering`, `PadelTimeSeriesForecast`), every training run registers **two** MLflow model versions (two different estimators), each with its own metrics (for example accuracy / F1 for classifiers, R² / MAE for regression, silhouette / BIC for clustering, train R² for time series). Version rows in MLflow include a **description** like `Version 12 — 2026-05-04 14:30 UTC — RandomForest` (version number, UTC date and time, algorithm). Artifacts are stored in MinIO via MLflow as before.

## 3) Start the project

1. Open terminal in `n8n-mlops`
2. Create env file:
   - Copy `.env.example` to `.env`
   - Update SQL Server credentials
3. Start everything:

```bash
docker compose --env-file .env up -d --build
```

## 4) Open services

- n8n: [http://localhost:5678](http://localhost:5678)
- MLflow: [http://localhost:5000](http://localhost:5000)
- MinIO Console: [http://localhost:9001](http://localhost:9001)

## 5) Import and activate the n8n workflow

1. In n8n: **Workflows -> Import from File**
2. Import `workflows/padel_ml_automation.json`
3. Open workflow and verify nodes:
   - `Manual Trigger (Training Test)` (full pipeline: health → train all)
   - `Scheduler Trigger (Daily)` (same)
   - `Webhook Trigger (Event Retrain)` → `POST .../webhook/padel-retrain-now`
   - **Single-task training (manual + webhook each):**
     - Classification: `Manual Train Classification Only` / `Webhook Retrain Classification` → `Train Classification Only` (`POST /train/winner`)
     - Regression: `Manual Train Regression Only` / `Webhook Retrain Regression` → `Train Regression Only` (`POST /train/views`)
     - Clustering: `Manual Train Clustering Only` / `Webhook Retrain Clustering` → `Train Clustering Only` (`POST /train/clustering`)
     - Time series: `Manual Train Time Series Only` / `Webhook Retrain Time Series` → `Train Time Series Only` (`POST /train/timeseries`)
   - `Check Training API Health`
   - `Execute All Models`
   - `Retrieve Model Versions`
   - `Workflow Documentation Output`
   - `Audit Training Result`
   - `Manual Trigger (Inference Test)`
   - `Scheduler Trigger (Inference Every 6h)`
   - `Automated Matchup Inference`
   - `Audit Inference Result`
   - `Workflow Error Trigger`
   - `Send Failure Alert (Webhook)`
4. Click **Activate**

Training trigger runs every 24h, inference trigger runs every 6h (you can change schedules inside trigger nodes).

For instant validation in n8n editor, use:

- `Manual Trigger (Training Test)` to run **all** models after a health check
- Any **`Manual Train … Only`** node to run **only** that ML task (then `Retrieve Model Versions` runs as usual)
- `Manual Trigger (Inference Test)` to run inference immediately

### 5.1) How to see each n8n criterion in action

#### A) Cron + HTTP + audit node

1. In n8n, click **Execute workflow** (or wait for schedule).
2. Open **Executions** tab.
3. Confirm successful path:
   - `Scheduler Trigger (Daily)` -> `Check Training API Health` -> `Execute All Models`
   - `Retrieve Model Versions` -> `Workflow Documentation Output`
   - `Audit Training Result`

#### B) Webhook trigger (event-driven retraining)

Retrain **everything** (same as scheduled flow):

```bash
curl -X POST http://localhost:5680/webhook/padel-retrain-now
```

Retrain **one** model family (maps to `POST /train/...` on `model-service`):

```bash
curl -X POST http://localhost:5680/webhook/padel-retrain-classification
curl -X POST http://localhost:5680/webhook/padel-retrain-regression
curl -X POST http://localhost:5680/webhook/padel-retrain-clustering
curl -X POST http://localhost:5680/webhook/padel-retrain-timeseries
```

Then check n8n **Executions** for the matching webhook node.

#### C) Automated inference pipeline

Run manually from n8n or wait for 6h schedule:

- `Scheduler Trigger (Inference Every 6h)` -> `Automated Matchup Inference` -> `Audit Inference Result`

You can also verify prediction calls in `logs/api.log` (`/predict/matchup` entries).

#### D) Error handling + notifications in n8n

1. Open node `Alert Config (Set URL)`.
2. Set field `alert_url` with your Slack/Discord/custom webhook URL.
3. Trigger a failure (for example stop `model-service`, then execute workflow).
4. Flow should run:
   - `Workflow Error Trigger` -> `Alert Config (Set URL)` -> `Send Failure Alert (Webhook)`

## 6) Verify versioning and old versions

After one run:

1. Open MLflow UI
2. Go to **Models**
3. Open each registered model:
   - `PadelWinnerClassifier`
   - `PadelViewsRegression`
   - `PadelPlayersClustering`
   - `PadelTimeSeriesForecast`
4. You will see Version 1, Version 2, ... after each schedule run.
5. Previous versions are still listed and downloadable.

You can also query versions via API:

```bash
curl http://localhost:8000/models/versions
```

## 6.1) Model Serving API (`/predict`)

This project now exposes a serving endpoint to return predictions from trained models.

1. Train models first (to generate local `.joblib` artifacts):

```bash
curl -X POST http://localhost:8000/train/all
```

2. Call prediction endpoint:

```bash
curl -X POST http://localhost:8000/predict \
  -H "Content-Type: application/json" \
  -d '{
    "model_name": "PadelWinnerClassifier",
    "features": [
      {
        "tournament_name": "Demo Open",
        "round": "Quarterfinal",
        "aces_t1": 3,
        "aces_t2": 2,
        "double_faults_t1": 1,
        "double_faults_t2": 2,
        "won_on_1st_serve_t1": 20,
        "won_on_1st_serve_t2": 18,
        "won_on_2nd_serve_t1": 10,
        "won_on_2nd_serve_t2": 8,
        "total_points_won_t1": 62,
        "total_points_won_t2": 55,
        "break_points_converted_t1": 4,
        "break_points_converted_t2": 3
      }
    ]
  }'
```

Example response:

```json
{
  "status": "ok",
  "model_name": "PadelWinnerClassifier",
  "artifact_path": "/app/models/winner_classifier_20260426_213000.joblib",
  "rows": 1,
  "predictions": [1]
}
```

`predictions` contains one value per input row in `features`.

### Matchup prediction from players (`/predict/matchup`)

Use this endpoint when you want to provide 2 pairs of players and get the predicted winner.

```bash
curl -X POST http://localhost:8000/predict/matchup \
  -H "Content-Type: application/json" \
  -d '{
    "tournament_name": "Qatar Major",
    "round": "Semifinal",
    "team1_player1_name": "Arturo Coello",
    "team1_player2_name": "Agustin Tapia",
    "team2_player1_name": "Alejandro Galan",
    "team2_player2_name": "Juan Lebron"
  }'
```

Example response:

```json
{
  "status": "ok",
  "model_name": "PadelWinnerClassifier",
  "winner": "team_1",
  "winner_team_players": ["Arturo Coello", "Agustin Tapia"],
  "team_1_probability": 0.71
}
```

## 7) Data source behavior

- Primary source: SQL Server (`matches_with_views_interactions`)
- If DB is not reachable, API tries fallback CSV files:
  - `data/matches_with_views_interactions.csv`
  - `data/monthly_views.csv`

## 8) Your original notebooks

Your 4 notebooks were copied to `notebooks/` for traceability:

- `padel_timeseries_forecasting (1).ipynb`
- `padel_views_regression_final.ipynb`
- `clustring.ipynb`
- `padel_match_winner_classification.ipynb`

## 9) Grid evidence (for report)

- **Workflow Design**: full trigger -> retrieval -> execution -> output pipeline in n8n, plus scheduled inference flow
- **Workflow Documentation**: nodes are labelled, and JSON export is included (`workflows/padel_ml_automation.json`)
- **n8n Node Usage**: Cron/Schedule + HTTP Request + Webhook + Set nodes are present
- **Automation Logic**: scheduled retraining (24h), event-driven retraining (webhook), and scheduled inference (6h)
- **Robustness/Monitoring**: n8n workflow error trigger + webhook alert node, plus API logs/retries/errors
- **Model Lifecycle**: model versions are managed in MLflow, old versions remain accessible

## 10) Robustness & Monitoring (Error handling + logs + retries + alerts)

The API includes a global robustness layer:

- Structured logs for all requests (start/end, status code, duration, request_id)
- Global exception handlers that return clear JSON errors with `request_id`
- Retry logic on critical operations (DB reads, model loading)
- Optional critical alerting via webhook (`ALERT_WEBHOOK_URL`)
- Traceable API logs written to `/app/logs/api.log` (configurable with `LOG_FILE`)

### How to test robustness in Swagger

1. Open docs: [http://localhost:8000/docs](http://localhost:8000/docs)
2. Call any endpoint and check response headers:
   - `x-request-id` should be present
3. Trigger a controlled error:
   - Call `POST /predict` with an unsupported model name (for example `unknown_model`)
   - Expected: structured error JSON with `status=error`, `request_id`, and `detail`
4. Trigger model-not-found error:
   - Call `POST /predict` before `POST /train/all`
   - Expected: `404` error JSON + logged warning
5. Verify logs:
   - `docker compose logs model-service`
   - or inspect `/app/logs/api.log` inside container

### Debug endpoint for error testing

Use `GET /debug/boom` to trigger an intentional server crash (`500`) and validate:

- global exception handling
- `request_id` propagation
- logging and alert hooks

If needed, disable this endpoint with:

- `ENABLE_DEBUG_ENDPOINTS=false`

### Where logs and errors are saved

- Docker stdout/stderr logs:
  - `docker compose logs model-service`
- File logs on host machine (persistent):
  - `n8n-mlops/logs/api.log`
- File logs path inside container:
  - `/app/logs/api.log`

Each error response includes `request_id`, which you can search in logs to trace the full failure path.

### Optional alerts

Set `ALERT_WEBHOOK_URL` in your environment (Slack/Discord/custom webhook).  
Unhandled exceptions and critical training failures will send alert payloads automatically.

## 11) Web App Integration (UI -> API -> Model -> Result)

A modern interactive frontend is included in `web-app/` with:

- 3D animated hero scene (Three.js)
- Professional glassmorphism layout
- Calls the prediction API at `http://localhost:8000` (docker `model-service`)
- Matchup form calling `POST /predict/matchup`
- Winner card + confidence bar
- Prediction history (localStorage)
- Team swap + demo autofill actions

### Run full stack

```bash
docker compose up -d --build model-service web-app
```

Open:

- Web app: [http://localhost:8083](http://localhost:8083)
- API docs: [http://localhost:8000/docs](http://localhost:8000/docs)

### End-to-end test

1. Open web app and click **Load Demo Data**
2. Click **Predict Winner**
3. Flow executed:
   - UI sends HTTP POST to `/predict/matchup`
   - API loads latest winner model
   - model predicts winner
   - UI displays winner and confidence

This demonstrates the complete integration requirement:

**User Interface -> API -> Model -> Displayed Result**

## 12) Code Quality

The project is structured and validated with:

- Centralized API error handling and traceable logs (`request_id`)
- Input validation with Pydantic models
- Deterministic, testable prediction endpoints
- Automated tests with `pytest`

### Automated tests included

Tests are located in `model-service/tests/test_app.py` and cover:

- `GET /health` success behavior
- Structured internal error response via `GET /debug/boom`
- Swap-consistency of `POST /predict/matchup` (A vs B and B vs A)

### Run tests

From project root:

```bash
docker compose exec model-service pytest -q
```

### Linting and formatting (flake8, black, isort, mypy)

Install dev tools on your machine (not inside the API image):

```bash
pip install -r requirements-dev.txt
```

- **flake8** — PEP 8 style and common issues (config: `.flake8`)
- **black** — automatic formatting (config: `pyproject.toml` → `[tool.black]`)
- **isort** — import order, compatible with Black (`[tool.isort]`)
- **mypy** — static typing checks (`[tool.mypy]`; third-party stubs may need extra `types-*` packages over time)

From the repo root on **Windows** (check only, exit non-zero if something fails):

```powershell
.\scripts\check_code.ps1
```

Apply **black** and **isort** fixes:

```powershell
.\scripts\check_code.ps1 -Fix
```

Equivalent manual commands:

```bash
flake8 model-service
black --check model-service
isort --check-only model-service
mypy model-service/app.py model-service/monitoring_tools.py model-service/simulation_runner.py model-service/tests/test_app.py
```

## 13) Production-like Monitoring Stack (Week S13)

The project now includes a full observability stack:

- Prometheus for metrics collection (`/metrics` scraping every 10s)
- Grafana with pre-provisioned Prometheus datasource and dashboard
- Alertmanager with routing for warning/critical alerts
- API-level metrics for traffic, latency, errors, model quality, data quality, and drift

### Start monitoring services

```bash
docker compose up -d --build model-service prometheus grafana alertmanager
```

If the browser shows **connection refused** on ports **9090**, **3000**, or **9093**, the monitoring containers are not running. Start them with the command above, or only the stack: `docker compose up -d prometheus grafana alertmanager` (requires `model-service` up for Prometheus targets).

### Access monitoring UIs

- Prometheus: [http://localhost:9090](http://localhost:9090)
- Grafana: [http://localhost:3000](http://localhost:3000) (`admin` / `admin`)
- Alertmanager: [http://localhost:9093](http://localhost:9093)

### Metrics endpoint

The API now exposes:

- `GET /metrics` (Prometheus format)
- `POST /monitoring/snapshot` (push synthetic monitoring values for demos/tests)

### Run simulation scenarios

To simulate load, drift, and degradation:

```bash
docker compose exec model-service python simulation_runner.py --api-url http://model-service:8000 --traffic-seconds 120 --rps 50 --drift-steps 30 --traffic-endpoint health
```

`--traffic-endpoint health` (default) keeps RPS realistic: each `POST /predict/matchup` reloads the full history and can take minutes on a single worker, so use `--traffic-endpoint matchup` only with low `--rps` and a high client timeout if you need that path stressed.

You should see corresponding behavior in Grafana and Prometheus alerts.
