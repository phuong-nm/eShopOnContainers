# This Dockerfile shall be executed from upper directory
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app
RUN mkdir -p \
    eventbus \
    eventbus-rabbitmq \
    payment-api

COPY ./eventbus/pom.xml ./eventbus
COPY ./eventbus/src ./eventbus/src
COPY ./eventbus-rabbitmq/pom.xml ./eventbus-rabbitmq
COPY ./eventbus-rabbitmq/src ./eventbus-rabbitmq/src
COPY ./payment-api/pom.xml ./payment-api
COPY ./payment-api/src ./payment-api/src

# Build
RUN mvn clean install -DskipTests -f eventbus
RUN mvn clean install -DskipTests -f eventbus-rabbitmq
RUN mvn clean install -DskipTests -f payment-api

FROM eclipse-temurin:17-jdk-alpine-3.22
WORKDIR /app
COPY --from=build /app/payment-api/target/payment-api-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "payment-api-0.0.1-SNAPSHOT.jar"]
