#!/usr/bin/env bash
# Code quality: flake8, black, isort, mypy — run from repo root.
# Usage: ./scripts/check_code.sh        # check only
#        ./scripts/check_code.sh --fix # apply black + isort
set -euo pipefail
cd "$(dirname "$0")/.."

FIX=false
[[ "${1:-}" == "--fix" ]] && FIX=true

echo "== flake8 =="
python -m flake8 model-service

echo "== black =="
if $FIX; then
  python -m black model-service
else
  python -m black --check model-service
fi

echo "== isort =="
if $FIX; then
  python -m isort model-service
else
  python -m isort --check-only model-service
fi

echo "== mypy =="
python -m mypy model-service/app.py model-service/monitoring_tools.py model-service/simulation_runner.py model-service/tests/test_app.py

echo "All checks passed."
