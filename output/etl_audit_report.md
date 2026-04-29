# Talend ETL Workflow Audit

Jobs audited: **9**

## dim_country (0.1)
- File: `/talend-project/process/DW/dim_country_0.1.item`
- Nodes: `6` | Connections: `5`
- Connectors: `{"FLOW": 3, "UNIQUE": 1, "FILTER": 1}`
- Source components: tMSSqlInput
- Sink components: tMSSqlOutput
- Failure-related components: none
- Scheduling components: none
- Inferred dependencies: upstream source readiness required, target DW tables and DB connection availability required
- Warnings:
  - ON_LOGCATCHER_FLAG disabled.
  - ON_STATCATCHER_FLAG disabled.
  - No explicit scheduling component inside the job.

## dim_date (0.1)
- File: `/talend-project/process/DW/dim_date_0.1.item`
- Nodes: `3` | Connections: `2`
- Connectors: `{"FLOW": 2}`
- Source components: tRowGenerator
- Sink components: tMSSqlOutput
- Failure-related components: none
- Scheduling components: none
- Inferred dependencies: target DW tables and DB connection availability required
- Warnings:
  - ON_LOGCATCHER_FLAG disabled.
  - ON_STATCATCHER_FLAG disabled.
  - No explicit scheduling component inside the job.

## dim_equipement (0.1)
- File: `/talend-project/process/DW/dim_equipement_0.1.item`
- Nodes: `5` | Connections: `4`
- Connectors: `{"FLOW": 3, "FILTER": 1}`
- Source components: tMSSqlInput
- Sink components: tMSSqlOutput
- Failure-related components: none
- Scheduling components: none
- Inferred dependencies: upstream source readiness required, target DW tables and DB connection availability required
- Warnings:
  - ON_LOGCATCHER_FLAG disabled.
  - ON_STATCATCHER_FLAG disabled.
  - No explicit scheduling component inside the job.

## dim_matchs (0.1)
- File: `/talend-project/process/DW/dim_matchs_0.1.item`
- Nodes: `6` | Connections: `5`
- Connectors: `{"FLOW": 3, "UNIQUE": 1, "FILTER": 1}`
- Source components: tMSSqlInput
- Sink components: tMSSqlOutput
- Failure-related components: none
- Scheduling components: none
- Inferred dependencies: upstream source readiness required, target DW tables and DB connection availability required
- Warnings:
  - ON_LOGCATCHER_FLAG disabled.
  - ON_STATCATCHER_FLAG disabled.
  - No explicit scheduling component inside the job.

## dim_player (0.1)
- File: `/talend-project/process/DW/dim_player_0.1.item`
- Nodes: `6` | Connections: `5`
- Connectors: `{"FLOW": 3, "UNIQUE": 1, "FILTER": 1}`
- Source components: tMSSqlInput
- Sink components: tMSSqlOutput
- Failure-related components: none
- Scheduling components: none
- Inferred dependencies: upstream source readiness required, target DW tables and DB connection availability required
- Warnings:
  - ON_LOGCATCHER_FLAG disabled.
  - ON_STATCATCHER_FLAG disabled.
  - No explicit scheduling component inside the job.

## dim_sponsor (0.1)
- File: `/talend-project/process/DW/dim_sponsor_0.1.item`
- Nodes: `5` | Connections: `4`
- Connectors: `{"FLOW": 3, "FILTER": 1}`
- Source components: tMSSqlInput
- Sink components: tMSSqlOutput
- Failure-related components: none
- Scheduling components: none
- Inferred dependencies: upstream source readiness required, target DW tables and DB connection availability required
- Warnings:
  - ON_LOGCATCHER_FLAG disabled.
  - ON_STATCATCHER_FLAG disabled.
  - No explicit scheduling component inside the job.

## dim_tournament (0.1)
- File: `/talend-project/process/DW/dim_tournament_0.1.item`
- Nodes: `5` | Connections: `4`
- Connectors: `{"FLOW": 3, "FILTER": 1}`
- Source components: tMSSqlInput
- Sink components: tMSSqlOutput
- Failure-related components: none
- Scheduling components: none
- Inferred dependencies: upstream source readiness required, target DW tables and DB connection availability required
- Warnings:
  - ON_LOGCATCHER_FLAG disabled.
  - ON_STATCATCHER_FLAG disabled.
  - No explicit scheduling component inside the job.

## FACT_MATCHS (0.1)
- File: `/talend-project/process/DW/FACT_MATCHS_0.1.item`
- Nodes: `9` | Connections: `8`
- Connectors: `{"FLOW": 8}`
- Source components: tFileInputExcel, tMSSqlInput
- Sink components: tMSSqlOutput
- Failure-related components: none
- Scheduling components: none
- Inferred dependencies: upstream source readiness required, target DW tables and DB connection availability required, dimension jobs should complete before this fact load
- Warnings:
  - ON_LOGCATCHER_FLAG disabled.
  - ON_STATCATCHER_FLAG disabled.
  - No explicit scheduling component inside the job.

## FACT_PLAYERS (0.1)
- File: `/talend-project/process/DW/FACT_PLAYERS_0.1.item`
- Nodes: `13` | Connections: `12`
- Connectors: `{"FLOW": 11, "UNIQUE": 1}`
- Source components: tFileInputExcel, tMSSqlInput
- Sink components: tMSSqlOutput
- Failure-related components: none
- Scheduling components: none
- Inferred dependencies: upstream source readiness required, target DW tables and DB connection availability required, dimension jobs should complete before this fact load
- Warnings:
  - ON_LOGCATCHER_FLAG disabled.
  - ON_STATCATCHER_FLAG disabled.
  - No explicit scheduling component inside the job.
