# -------- BUILD STAGE --------
FROM gradle:8.5-jdk8 AS build
WORKDIR /app
COPY . .
RUN gradle build -x test

# -------- RUN STAGE --------
FROM eclipse-temurin:8-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
