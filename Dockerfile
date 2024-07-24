# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 4040 available to the world outside this container
EXPOSE 4040

# The application's jar file
ARG JAR_FILE=build/libs/*.jar

# Define build arguments for sensitive information
ARG SPRING_MAIL_USERNAME
ARG SPRING_MAIL_PASSWORD
ARG AWS_ACCESS_KEY
ARG AWS_SECRET_KEY

# Set environment variables using the build arguments
ENV SPRING_MAIL_USERNAME=${SPRING_MAIL_USERNAME}
ENV SPRING_MAIL_PASSWORD=${SPRING_MAIL_PASSWORD}
ENV AWS_ACCESS_KEY=${AWS_ACCESS_KEY}
ENV AWS_SECRET_KEY=${AWS_SECRET_KEY}

# Add the application's jar to the container
COPY ${JAR_FILE} app.jar

# Run the jar file with environment variables
ENTRYPOINT ["sh", "-c", "java -jar /app.jar --spring.mail.username=${SPRING_MAIL_USERNAME} --spring.mail.password=${SPRING_MAIL_PASSWORD} --cloud.aws.credentials.accessKey=${AWS_ACCESS_KEY} --cloud.aws.credentials.secretKey=${AWS_SECRET_KEY}"]
