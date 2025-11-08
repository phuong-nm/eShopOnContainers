docker compose -f docker-compose.yml -f docker-compose.override.yml up --build -d `
    webspa webstatus `
    ordering-backgroundtasks `
    ordering-signalrhub `
    payment-api `
    seq
docker compose --project-directory ..\deploy -f docker-compose.elk.yml up --build -d
