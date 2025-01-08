FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY /root/*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
