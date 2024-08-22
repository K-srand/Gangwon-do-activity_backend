# Stage 1: Build the backend application
FROM openjdk:17-jdk-slim as backend

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 4040 available to the world outside this container
EXPOSE 4040

# The application's jar file
ARG JAR_FILE=build/libs/*.jar

# Define build arguments for sensitive information
ARG SPRING_MAIL_CREDENTIALS_USERNAME
ARG SPRING_MAIL_CREDENTIALS_PASSWORD
ARG AWS_ACCESS_KEY_ID
ARG AWS_SECRET_ACCESS_KEY
ARG NAVER_CLIENT_ID
ARG NAVER_CLIENT_SECRET


# Set environment variables using the build arguments
ENV SPRING_MAIL_CREDENTIALS_USERNAME=${SPRING_MAIL_CREDENTIALS_USERNAME}
ENV SPRING_MAIL_CREDENTIALS_PASSWORD=${SPRING_MAIL_CREDENTIALS_PASSWORD}
ENV AWS_ACCESS_KEY_ID=${AWS_ACCESS_KEY_ID}
ENV AWS_SECRET_ACCESS_KEY=${AWS_SECRET_ACCESS_KEY}
ENV NAVER_CLIENT_ID=${NAVER_CLIENT_ID}
ENV NAVER_CLIENT_SECRET=${NAVER_CLIENT_SECRET}


# Add the application's jar to the container
COPY ${JAR_FILE} app.jar

# Run the jar file with environment variables
ENTRYPOINT ["sh", "-c", "java -jar /app.jar --spring.mail.credentials.username=${SPRING_MAIL_CREDENTIALS_USERNAME} --spring.mail.credentials.password=${SPRING_MAIL_CREDENTIALS_PASSWORD} --cloud.aws.credentials.accessKey=${AWS_ACCESS_KEY_ID} --cloud.aws.credentials.secretKey=${AWS_SECRET_ACCESS_KEY} --naver.client.id=${NAVER_CLIENT_ID} --naver.client.secret=${NAVER_CLIENT_SECRET}"]

