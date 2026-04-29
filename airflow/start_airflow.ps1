param(
    [string]$ContainerName = "airflow-local",
    [string]$AirflowUser = "admin",
    [string]$AirflowPassword = "admin123",
    [int]$Port = 8081
)

$ProjectRoot = Split-Path -Parent $PSScriptRoot
$DagsPath = Join-Path $PSScriptRoot "dags"

Write-Host "Stopping old container (if exists)..."
docker rm -f $ContainerName | Out-Null

Write-Host "Starting Airflow standalone container..."
docker run -d `
  --name $ContainerName `
  -p "${Port}:8080" `
  -v "${DagsPath}:/opt/airflow/dags" `
  -e "_AIRFLOW_WWW_USER_USERNAME=${AirflowUser}" `
  -e "_AIRFLOW_WWW_USER_PASSWORD=${AirflowPassword}" `
  -e "AIRFLOW__CORE__LOAD_EXAMPLES=False" `
  apache/airflow:2.9.2 `
  standalone | Out-Null

if ($LASTEXITCODE -ne 0) {
    throw "Failed to start Airflow container."
}

Write-Host "Waiting for Airflow webserver..."
Start-Sleep -Seconds 20

Write-Host "Enforcing admin password..."
docker exec $ContainerName airflow users reset-password --username $AirflowUser --password $AirflowPassword | Out-Null

docker ps --filter "name=$ContainerName"
Write-Host ""
Write-Host "Airflow URL: http://localhost:$Port"
Write-Host "Username: $AirflowUser"
Write-Host "Password: $AirflowPassword"
Write-Host "DAG to open: dw_etl_pipeline"
