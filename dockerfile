# -------- BUILD STAGE --------
FROM eclipse-temurin:8-jdk AS build
WORKDIR /app

# Copy gradle wrapper & config first (for cache)
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Download dependencies
RUN chmod +x gradlew && ./gradlew dependencies

# Copy source
COPY src src

# Build app
RUN ./gradlew build -x test

# -------- RUN STAGE --------
FROM eclipse-temurin:8-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
