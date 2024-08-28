# Backend Dockerfile
FROM openjdk:17-jdk-slim
VOLUME /tmp

# 빌드 인수를 활용할 수 있는 부분 예시
ENV AWS_ACCESS_KEY_ID=${AWS_ACCESS_KEY_ID}
ENV AWS_SECRET_ACCESS_KEY=${AWS_SECRET_ACCESS_KEY}

COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", \
"-DAWS_ACCESS_KEY_ID=${AWS_ACCESS_KEY_ID}", \
"-DAWS_SECRET_ACCESS_KEY=${AWS_SECRET_ACCESS_KEY}", \
"-jar", \
"/app.jar"]
