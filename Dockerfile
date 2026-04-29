FROM python:3.12-slim

WORKDIR /app

COPY audit_talend_jobs.py /app/audit_talend_jobs.py

ENTRYPOINT ["python", "/app/audit_talend_jobs.py"]
