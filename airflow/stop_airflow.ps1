param(
    [string]$ContainerName = "airflow-local"
)

docker rm -f $ContainerName
