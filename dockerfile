FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8083
CMD ["java", "-jar", "app.jar"]
