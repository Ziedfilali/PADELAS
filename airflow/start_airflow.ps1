param(
    [string]$ContainerName = "airflow-local",
    [string]$AirflowUser = "admin",
    [string]$AirflowPassword = "admin123",
    [int]$Port = 8081,
    [string]$TalendJobsPath = "D:\Projet_Audit_Pi\talend_jobs",
    [string]$SourceDataPath = "C:\pi",
    [string]$ImageName = "airflow-talend:2.9.2-java"
)

$ProjectRoot = Split-Path -Parent $PSScriptRoot
$DagsPath = Join-Path $PSScriptRoot "dags"
New-Item -ItemType Directory -Force -Path $TalendJobsPath | Out-Null
if (-not (Test-Path $SourceDataPath)) {
    throw "Source data path not found: $SourceDataPath"
}

Write-Host "Stopping old container (if exists)..."
docker rm -f $ContainerName | Out-Null

Write-Host "Building Airflow image with Java..."
docker build -t $ImageName $PSScriptRoot
if ($LASTEXITCODE -ne 0) {
    throw "Failed to build Airflow image."
}

Write-Host "Starting Airflow standalone container..."
docker run -d `
  --name $ContainerName `
  -p "${Port}:8080" `
  --add-host "DESKTOP-QJ70MNR:host-gateway" `
  -v "${DagsPath}:/opt/airflow/dags" `
  -v "${TalendJobsPath}:/talend-jobs" `
  -v "${SourceDataPath}:/pi" `
  -e "_AIRFLOW_WWW_USER_USERNAME=${AirflowUser}" `
  -e "_AIRFLOW_WWW_USER_PASSWORD=${AirflowPassword}" `
  -e "TALEND_JOBS_ROOT=/talend-jobs" `
  -e "AIRFLOW__CORE__LOAD_EXAMPLES=False" `
  -e "AIRFLOW__WEBSERVER__WORKERS=1" `
  -e "AIRFLOW__WEBSERVER__WEB_SERVER_MASTER_TIMEOUT=600" `
  -e "AIRFLOW__CORE__PARALLELISM=1" `
  $ImageName `
  standalone | Out-Null

if ($LASTEXITCODE -ne 0) {
    throw "Failed to start Airflow container."
}

Write-Host "Waiting for Airflow webserver..."
Start-Sleep -Seconds 20

Write-Host "Enforcing admin password..."
docker exec $ContainerName airflow users create --username $AirflowUser --firstname Admin --lastname User --role Admin --email "$AirflowUser@example.com" --password $AirflowPassword 2>$null | Out-Null
docker exec $ContainerName airflow users reset-password --username $AirflowUser --password $AirflowPassword | Out-Null

docker ps --filter "name=$ContainerName"
Write-Host ""
Write-Host "Airflow URL: http://localhost:$Port"
Write-Host "Username: $AirflowUser"
Write-Host "Password: $AirflowPassword"
Write-Host "DAG to open: dw_etl_pipeline"
Write-Host "Talend jobs mount: $TalendJobsPath -> /talend-jobs"
Write-Host "Source files mount: $SourceDataPath -> /pi"
