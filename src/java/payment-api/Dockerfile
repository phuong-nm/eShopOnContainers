# This Dockerfile shall be executed from upper directory
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app
RUN mkdir -p \
    eventbus \
    eventbus-rabbitmq \
    ordering-signalrhub \
    payment-api

COPY ./eventbus/pom.xml ./eventbus
COPY ./eventbus/src ./eventbus/src
COPY ./eventbus-rabbitmq/pom.xml ./eventbus-rabbitmq
COPY ./eventbus-rabbitmq/src ./eventbus-rabbitmq/src
COPY ./ordering-signalrhub/pom.xml ./ordering-signalrhub
COPY ./ordering-signalrhub/src ./ordering-signalrhub/src
COPY ./payment-api/pom.xml ./payment-api
COPY ./payment-api/src ./payment-api/src
COPY ./pom.xml .

# Build
RUN mvn clean install --pl eventbus,eventbus-rabbitmq,payment-api -DskipTests

FROM eclipse-temurin:17-jdk-alpine-3.22
WORKDIR /app
COPY --from=build /app/payment-api/target/payment-api-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "payment-api-0.0.1-SNAPSHOT.jar"]
