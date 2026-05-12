# Code quality: flake8 (PEP8), black, isort, mypy — run from repo root.
# Usage: .\scripts\check_code.ps1          # check only
#         .\scripts\check_code.ps1 -Fix   # apply black + isort
$ErrorActionPreference = "Stop"
Set-Location (Split-Path $PSScriptRoot -Parent)

if (-not (Get-Command python -ErrorAction SilentlyContinue)) {
    Write-Error "python not found on PATH"
}

$fix = $args -contains "-Fix"

Write-Host "== flake8 (PEP8 + bugbear) ==" -ForegroundColor Cyan
python -m flake8 model-service

Write-Host "== black ==" -ForegroundColor Cyan
if ($fix) {
    python -m black model-service
} else {
    python -m black --check model-service
}

Write-Host "== isort ==" -ForegroundColor Cyan
if ($fix) {
    python -m isort model-service
} else {
    python -m isort --check-only model-service
}

Write-Host "== mypy ==" -ForegroundColor Cyan
python -m mypy model-service/app.py model-service/monitoring_tools.py model-service/simulation_runner.py model-service/tests/test_app.py

Write-Host "All checks passed." -ForegroundColor Green
