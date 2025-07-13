FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY ../sre/pom.xml .
COPY ../sre/src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/target/backend-1.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
