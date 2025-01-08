# Backend Dockerfile
FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY /root/gangwonActivity-0.0.1-SNAPSHOT.jar /app/gangwonActivity.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
