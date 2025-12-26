# -------- BUILD STAGE --------
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

COPY . .

RUN chmod +x gradlew
RUN ./gradlew build -x test

# -------- RUN STAGE --------
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8083
CMD ["java", "-jar", "app.jar"]
