# Multi-stage Dockerfile
FROM maven:3.9.6-eclipse-temurin-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests clean package spring-boot:repackage

FROM eclipse-temurin:11-jre
WORKDIR /app
COPY --from=build /app/target/rede-mais-social-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
