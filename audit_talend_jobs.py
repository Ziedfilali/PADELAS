#!/usr/bin/env python3
"""Audit selected Talend job .item files and export a report."""

from __future__ import annotations

import argparse
import json
from dataclasses import dataclass, asdict
from pathlib import Path
import re
import sys
import xml.etree.ElementTree as ET


TARGET_JOBS = [
    "dim_country",
    "dim_date",
    "dim_equipement",
    "dim_matchs",
    "dim_player",
    "dim_sponsor",
    "dim_tournament",
    "FACT_MATCHS",
    "FACT_PLAYERS",
]

SCHEDULING_COMPONENTS = {
    "tWaitForFile",
    "tSleep",
    "tChronometerStart",
    "tChronometerStop",
}

FAILURE_RELATED_COMPONENTS = {
    "tDie",
    "tWarn",
    "tLogCatcher",
    "tStatCatcher",
    "tFlowMeterCatcher",
}

DB_OUTPUT_COMPONENTS = {
    "tDBOutput",
    "tMSSqlOutput",
    "tMysqlOutput",
    "tOracleOutput",
    "tPostgresqlOutput",
}

INPUT_HINT_COMPONENTS = {
    "tFileInputDelimited",
    "tFileInputExcel",
    "tMSSqlInput",
    "tMysqlInput",
    "tOracleInput",
    "tPostgresqlInput",
}


@dataclass
class JobAudit:
    job_name: str
    version: str
    file: str
    node_count: int
    connection_count: int
    connectors: dict[str, int]
    components: list[str]
    source_components: list[str]
    sink_components: list[str]
    failure_components: list[str]
    scheduling_components: list[str]
    stats_flags: dict[str, str]
    inferred_dependencies: list[str]
    warnings: list[str]


def parse_job_file(item_path: Path) -> JobAudit:
    root = ET.parse(item_path).getroot()

    nodes = root.findall("node")
    connections = root.findall("connection")

    component_names: list[str] = []
    source_nodes: set[str] = set()
    sink_nodes: set[str] = set()
    component_by_unique_name: dict[str, str] = {}

    for node in nodes:
        component_name = node.attrib.get("componentName", "")
        if component_name:
            component_names.append(component_name)

        unique_name = ""
        for p in node.findall("elementParameter"):
            if p.attrib.get("name") == "UNIQUE_NAME":
                unique_name = p.attrib.get("value", "")
                break
        if unique_name:
            component_by_unique_name[unique_name] = component_name

    connector_counts: dict[str, int] = {}
    for conn in connections:
        connector = conn.attrib.get("connectorName", "UNKNOWN")
        connector_counts[connector] = connector_counts.get(connector, 0) + 1
        src = conn.attrib.get("source", "")
        tgt = conn.attrib.get("target", "")
        if src:
            source_nodes.add(src)
        if tgt:
            sink_nodes.add(tgt)

    sources = sorted(
        {
            component_by_unique_name.get(name, name)
            for name in source_nodes
            if name not in sink_nodes
        }
    )
    sinks = sorted(
        {
            component_by_unique_name.get(name, name)
            for name in sink_nodes
            if name not in source_nodes
        }
    )

    all_values = [p.attrib for p in root.findall(".//elementParameter")]
    stats_flags = {
        "on_logcatcher": next(
            (d.get("value", "") for d in all_values if d.get("name") == "ON_LOGCATCHER_FLAG"),
            "",
        ),
        "on_statcatcher": next(
            (d.get("value", "") for d in all_values if d.get("name") == "ON_STATCATCHER_FLAG"),
            "",
        ),
        "on_metercatcher": next(
            (d.get("value", "") for d in all_values if d.get("name") == "ON_METERCATCHER_FLAG"),
            "",
        ),
        "catch_runtime_errors": next(
            (d.get("value", "") for d in all_values if d.get("name") == "CATCH_RUNTIME_ERRORS"),
            "",
        ),
        "catch_user_errors": next(
            (d.get("value", "") for d in all_values if d.get("name") == "CATCH_USER_ERRORS"),
            "",
        ),
        "catch_user_warning": next(
            (d.get("value", "") for d in all_values if d.get("name") == "CATCH_USER_WARNING"),
            "",
        ),
    }

    failure_components = sorted(
        set(component_names).intersection(FAILURE_RELATED_COMPONENTS)
    )
    scheduling_components = sorted(
        set(component_names).intersection(SCHEDULING_COMPONENTS)
    )

    job_base, version = split_job_name_and_version(item_path.stem)
    inferred_dependencies = infer_dependencies(job_base, component_names, sinks)
    warnings = build_warnings(
        connector_counts=connector_counts,
        sinks=sinks,
        stats_flags=stats_flags,
        failure_components=failure_components,
        scheduling_components=scheduling_components,
    )

    return JobAudit(
        job_name=job_base,
        version=version,
        file=str(item_path),
        node_count=len(nodes),
        connection_count=len(connections),
        connectors=connector_counts,
        components=sorted(set(component_names)),
        source_components=sources,
        sink_components=sinks,
        failure_components=failure_components,
        scheduling_components=scheduling_components,
        stats_flags=stats_flags,
        inferred_dependencies=inferred_dependencies,
        warnings=warnings,
    )


def split_job_name_and_version(stem: str) -> tuple[str, str]:
    m = re.match(r"(.+)_([0-9]+\.[0-9]+)$", stem)
    if not m:
        return stem, "unknown"
    return m.group(1), m.group(2)


def infer_dependencies(job_base: str, components: list[str], sinks: list[str]) -> list[str]:
    deps: list[str] = []
    if any(c in INPUT_HINT_COMPONENTS for c in components):
        deps.append("upstream source readiness required")
    if any(c in DB_OUTPUT_COMPONENTS for c in components) or any("Output" in s for s in sinks):
        deps.append("target DW tables and DB connection availability required")
    if job_base.startswith("FACT_"):
        deps.append("dimension jobs should complete before this fact load")
    return deps


def build_warnings(
    connector_counts: dict[str, int],
    sinks: list[str],
    stats_flags: dict[str, str],
    failure_components: list[str],
    scheduling_components: list[str],
) -> list[str]:
    warns: list[str] = []
    if not connector_counts:
        warns.append("No connections found in job graph.")
    if not sinks:
        warns.append("No sink component detected; verify load target.")
    if stats_flags.get("on_logcatcher") == "false":
        warns.append("ON_LOGCATCHER_FLAG disabled.")
    if stats_flags.get("on_statcatcher") == "false":
        warns.append("ON_STATCATCHER_FLAG disabled.")
    if not failure_components and stats_flags.get("catch_runtime_errors") != "true":
        warns.append("Limited failure handling signals detected.")
    if not scheduling_components:
        warns.append("No explicit scheduling component inside the job.")
    return warns


def make_markdown_report(audits: list[JobAudit]) -> str:
    lines: list[str] = []
    lines.append("# Talend ETL Workflow Audit")
    lines.append("")
    lines.append(f"Jobs audited: **{len(audits)}**")
    lines.append("")
    for job in audits:
        lines.append(f"## {job.job_name} ({job.version})")
        lines.append(f"- File: `{job.file}`")
        lines.append(f"- Nodes: `{job.node_count}` | Connections: `{job.connection_count}`")
        lines.append(f"- Connectors: `{json.dumps(job.connectors, ensure_ascii=True)}`")
        lines.append(
            "- Source components: "
            + (", ".join(job.source_components) if job.source_components else "none")
        )
        lines.append(
            "- Sink components: "
            + (", ".join(job.sink_components) if job.sink_components else "none")
        )
        lines.append(
            "- Failure-related components: "
            + (", ".join(job.failure_components) if job.failure_components else "none")
        )
        lines.append(
            "- Scheduling components: "
            + (", ".join(job.scheduling_components) if job.scheduling_components else "none")
        )
        lines.append(
            "- Inferred dependencies: "
            + (", ".join(job.inferred_dependencies) if job.inferred_dependencies else "none")
        )
        if job.warnings:
            lines.append("- Warnings:")
            for w in job.warnings:
                lines.append(f"  - {w}")
        lines.append("")
    return "\n".join(lines)


def main() -> int:
    parser = argparse.ArgumentParser(
        description="Audit selected Talend jobs for ETL workflow review."
    )
    parser.add_argument(
        "--talend-root",
        default="/talend-project",
        help="Mounted Talend project root containing process/DW",
    )
    parser.add_argument(
        "--out-dir",
        default="/output",
        help="Directory where audit report files will be written",
    )
    args = parser.parse_args()

    talend_root = Path(args.talend_root)
    process_dw = talend_root / "process" / "DW"
    if not process_dw.exists():
        print(f"ERROR: process folder not found: {process_dw}", file=sys.stderr)
        return 2

    audits: list[JobAudit] = []
    missing: list[str] = []
    for job in TARGET_JOBS:
        item_path = process_dw / f"{job}_0.1.item"
        if not item_path.exists():
            missing.append(job)
            continue
        audits.append(parse_job_file(item_path))

    out_dir = Path(args.out_dir)
    out_dir.mkdir(parents=True, exist_ok=True)

    json_path = out_dir / "etl_audit_report.json"
    md_path = out_dir / "etl_audit_report.md"

    payload = {
        "jobs_requested": TARGET_JOBS,
        "jobs_found": [a.job_name for a in audits],
        "jobs_missing": missing,
        "audit": [asdict(a) for a in audits],
    }
    json_path.write_text(json.dumps(payload, indent=2, ensure_ascii=True), encoding="utf-8")
    md_path.write_text(make_markdown_report(audits), encoding="utf-8")

    print(f"Report generated: {json_path}")
    print(f"Report generated: {md_path}")
    if missing:
        print("Missing jobs:", ", ".join(missing))
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
