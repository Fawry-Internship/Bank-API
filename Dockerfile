
FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim

EXPOSE 8081

COPY target/Bank-Api-1.jar /app/Bank-Api.jar

CMD ["java", "-jar", "/app/Bank-Api.jar"]