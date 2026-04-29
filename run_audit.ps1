param(
    [string]$TalendPath = "C:\Program Files (x86)\TOS_DI-8.0.1\studio\workspace\DW_PADEL",
    [string]$OutputPath = "$PSScriptRoot\output"
)

Write-Host "Building Docker image..."
docker build -t talend-etl-audit "$PSScriptRoot"
if ($LASTEXITCODE -ne 0) {
    throw "Docker build failed."
}

New-Item -ItemType Directory -Force -Path $OutputPath | Out-Null

Write-Host "Running ETL audit container..."
docker run --rm `
  -v "${TalendPath}:/talend-project:ro" `
  -v "${OutputPath}:/output" `
  talend-etl-audit `
  --talend-root /talend-project `
  --out-dir /output

if ($LASTEXITCODE -ne 0) {
    throw "Docker run failed."
}

Write-Host "Done. Reports are in: $OutputPath"
