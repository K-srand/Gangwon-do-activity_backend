# 베이스 이미지 설정 (JDK 설치된 이미지 사용)
FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# JAR 파일 복사
COPY /root/gangwonActivity-0.0.1-SNAPSHOT.jar /app/gangwonActivity.jar

# JAR 파일 실행 명령어
CMD ["java", "-jar", "gangwonActivity.jar"]
