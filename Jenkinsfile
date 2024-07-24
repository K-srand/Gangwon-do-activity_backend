pipeline {
    agent any
    environment {
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-17.0.9.0.9-2.el8_8.x86_64'
        PATH = "${JAVA_HOME}/bin:${env.PATH}"

        SPRING_MAIL_USERNAME = credentials('SPRING_MAIL_USERNAME') // Jenkins Credentials 플러그인을 사용하여 보안 설정
        SPRING_MAIL_PASSWORD = credentials('SPRING_MAIL_PASSWORD')
        AWS_ACCESS_KEY = credentials('AWS_ACCESS_KEY')
        AWS_SECRET_KEY = credentials('AWS_SECRET_KEY')
    }
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code...'
                git branch: 'main', url: 'https://github.com/K-srand/Gangwon-do-activity_backend.git'
            }
        }
        stage('Grant Permissions') {
            steps {
                echo 'Granting execute permission to gradlew...'
                sh 'chmod +x ./gradlew'
            }
        }
        stage('Build') {
            steps {
                echo 'Building the project...'
                sh 'java -version'  // Java 버전 확인
                sh './gradlew build'
            }
        }
        stage('Docker Build') {
            steps {
                echo 'Building Docker image...'
                script {
                    def dockerImage = docker.build('backend-app:latest', "-f Dockerfile . --build-arg SPRING_MAIL_USERNAME=${env.SPRING_MAIL_USERNAME} --build-arg SPRING_MAIL_PASSWORD=${env.SPRING_MAIL_PASSWORD} --build-arg AWS_ACCESS_KEY=${env.AWS_ACCESS_KEY} --build-arg AWS_SECRET_KEY=${env.AWS_SECRET_KEY}")
                    echo "Docker image built successfully: ${dockerImage.imageName()}"
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying the application...'
                script {
                    sh 'docker stop backend-app || true'
                    sh 'docker rm backend-app || true'
                    sh 'docker run -d -p 4040:4040 --name backend-app backend-app:latest'
                    echo "Docker container started successfully"
                }
            }
        }
    }
}
