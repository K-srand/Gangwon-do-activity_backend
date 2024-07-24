pipeline {
    agent any
    environment {
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-17.0.9.0.9-2.el8_8.x86_64'
        PATH = "${JAVA_HOME}/bin:/usr/bin:${env.PATH}"
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
                    withCredentials([usernamePassword(credentialsId: 'SPRING_MAIL_CREDENTIALS', usernameVariable: 'SPRING_MAIL_USERNAME', passwordVariable: 'SPRING_MAIL_PASSWORD'),
                                     string(credentialsId: 'AWS_ACCESS_KEY', variable: 'AWS_ACCESS_KEY'),
                                     string(credentialsId: 'AWS_SECRET_KEY', variable: 'AWS_SECRET_KEY')]) {
                       sh '''
                           /usr/bin/docker build -t backend-app:latest \
                           --build-arg SPRING_MAIL_USERNAME=$SPRING_MAIL_USERNAME \
                           --build-arg SPRING_MAIL_PASSWORD=$SPRING_MAIL_PASSWORD \
                           --build-arg AWS_ACCESS_KEY=$AWS_ACCESS_KEY \
                           --build-arg AWS_SECRET_KEY=$AWS_SECRET_KEY \
                           -f Dockerfile .
                       '''

                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying the application...'
                script {
                    sh '/usr/bin/docker stop backend-app || true'
                    sh '/usr/bin/docker rm backend-app || true'
                    sh '/usr/bin/docker run -d -p 4040:4040 --name backend-app backend-app:latest'
                    echo "Docker container started successfully"
                }
            }
        }
    }
}
