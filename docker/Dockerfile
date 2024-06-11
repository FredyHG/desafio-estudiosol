FROM gradle:8.8-jdk17-alpine AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build /app/build/libs/desafio-studiosol-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]