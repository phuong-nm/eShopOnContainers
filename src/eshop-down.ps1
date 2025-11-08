docker compose -f docker-compose.yml -f docker-compose.override.yml down -v
docker compose --project-directory ..\deploy -f docker-compose.elk.yml down -v
