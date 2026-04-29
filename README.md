# Docker ETL Audit for DW Jobs

This container audits the Talend jobs you requested:

- `dim_country`
- `dim_date`
- `dim_equipement`
- `dim_matchs`
- `dim_player`
- `dim_sponsor`
- `dim_tournament`
- `FACT_MATCHS`
- `FACT_PLAYERS`

## What it produces

- `etl_audit_report.json`
- `etl_audit_report.md`

The report includes:

- workflow structure (nodes, connectors, sources/sinks)
- inferred task dependencies
- failure-handling signals (catch/log flags and components)
- scheduling signals in the job graph

## Run on Windows (PowerShell)

From this folder:

```powershell
.\run_audit.ps1
```

If your Talend path is different:

```powershell
.\run_audit.ps1 -TalendPath "C:\Program Files (x86)\TOS_DI-8.0.1\studio\workspace\DW_PADEL"
```

## Run manually with Docker

```powershell
docker build -t talend-etl-audit .
docker run --rm `
  -v "C:\Program Files (x86)\TOS_DI-8.0.1\studio\workspace\DW_PADEL:/talend-project:ro" `
  -v "${PWD}\output:/output" `
  talend-etl-audit `
  --talend-root /talend-project `
  --out-dir /output
```
