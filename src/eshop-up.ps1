docker compose --project-name eshop -f docker-compose.yml -f docker-compose.override.yml up --build -d `
    webspa webstatus `
    ordering-backgroundtasks `
    ordering-signalrhub `
    payment-spring-api `
    seq
docker compose --project-directory ..\deploy -f docker-compose.elk.yml up --build -d
