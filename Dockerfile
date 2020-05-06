# Build stage
FROM maven:3-jdk-8 as build

WORKDIR /tmp

# Cache dependencies separately, only runs if pom.xml changes
COPY pom.xml .
RUN mvn verify clean --fail-never

# Build from source
COPY src/ ./src/
RUN mvn package spring-boot:repackage


# Lightweight container used for execution
FROM openjdk:8-jre-slim

WORKDIR /app
COPY --from=build /tmp/target/d2s-api-*.jar /app/d2s-api.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","d2s-api.jar"]
