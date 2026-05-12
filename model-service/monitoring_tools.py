import json
from datetime import datetime, timedelta, timezone
from pathlib import Path
from typing import Dict, List

import numpy as np
from scipy.stats import ks_2samp


class AlertLogger:
    def __init__(self, log_file: str = "logs/alerts.log"):
        self.log_file = Path(log_file)
        self.log_file.parent.mkdir(parents=True, exist_ok=True)

    def log_alert(self, alert_name: str, severity: str, description: str, metric_value=None):
        entry = {
            "timestamp": datetime.now(timezone.utc).isoformat(),
            "alert_name": alert_name,
            "severity": severity,
            "description": description,
            "metric_value": metric_value,
        }
        with self.log_file.open("a", encoding="utf-8") as f:
            f.write(json.dumps(entry) + "\n")

    def get_recent_alerts(self, minutes: int = 60) -> List[Dict]:
        if not self.log_file.exists():
            return []
        since = datetime.now(timezone.utc) - timedelta(minutes=minutes)
        alerts = []
        for line in self.log_file.read_text(encoding="utf-8").splitlines():
            try:
                payload = json.loads(line)
                ts = datetime.fromisoformat(payload["timestamp"].replace("Z", "+00:00"))
                if ts >= since:
                    alerts.append(payload)
            except Exception:
                continue
        return alerts


class DataDriftDetector:
    def __init__(self, baseline_data=None, threshold: float = 0.05):
        self.baseline_data = baseline_data or {}
        self.threshold = threshold
        self.drift_scores: Dict[str, float] = {}

    def detect_drift(self, current_data: Dict[str, List[float]]):
        results = {}
        for feature_name, current_values in current_data.items():
            if feature_name not in self.baseline_data:
                continue
            baseline_values = self.baseline_data[feature_name]
            ks_stat, p_value = ks_2samp(baseline_values, current_values)
            self.drift_scores[feature_name] = float(ks_stat)
            results[feature_name] = {
                "ks_statistic": float(ks_stat),
                "p_value": float(p_value),
                "is_drifted": bool(ks_stat > self.threshold),
            }
        return results

    def get_drift_report(self):
        drifted = [(k, v) for k, v in self.drift_scores.items() if v > self.threshold]
        return {
            "total_features_monitored": len(self.drift_scores),
            "drifted_features_count": len(drifted),
            "drifted_features": drifted,
        }


class AccuracyDegradationDetector:
    def __init__(self, baseline_accuracy: float = 0.95, degradation_threshold: float = 0.05):
        self.baseline = baseline_accuracy
        self.threshold = degradation_threshold

    def check_degradation(self, current_accuracy: float):
        degradation = self.baseline - current_accuracy
        degradation_percent = degradation / self.baseline
        return {
            "is_degraded": degradation_percent > self.threshold,
            "degradation_percent": float(degradation_percent),
            "degradation_absolute": float(degradation),
        }


class ConfidenceMonitor:
    def __init__(self, baseline_confidence: float = 0.85, min_confidence: float = 0.70):
        self.baseline = baseline_confidence
        self.min_threshold = min_confidence
        self.values: List[float] = []

    def record_confidence(self, confidence: float):
        self.values.append(float(confidence))

    def get_statistics(self, window: int = 1000):
        recent = self.values[-window:] if len(self.values) > window else self.values
        if not recent:
            return {
                "mean_confidence": 0.0,
                "std_confidence": 0.0,
                "anomaly_detected": False,
            }
        return {
            "mean_confidence": float(np.mean(recent)),
            "std_confidence": float(np.std(recent)),
            "anomaly_detected": bool(np.mean(recent) < self.min_threshold),
        }
