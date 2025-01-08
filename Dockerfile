# Backend Dockerfile
FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
