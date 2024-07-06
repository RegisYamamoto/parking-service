FROM maven:3.9.8-amazoncorretto-21 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install

COPY --from=build /app/target/parking-service-0.0.1-SNAPSHOT.jar /app/app.jar

FROM openjdk:21-jdk