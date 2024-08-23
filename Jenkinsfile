pipeline {
    agent any

    environment {
        GITHUB_ACCESS_TOKEN = credentials('github-access-token')
        AWS_ACCESS_KEY = credentials('AWS_ACCESS_KEY')
        AWS_SECRET_KEY = credentials('AWS_SECRET_KEY')
        NAVER_CLIENT_ID = credentials('naver.client.id')
        NAVER_CLIENT_SECRET = credentials('naver.client.secret')
        SPRING_MAIL_USERNAME = credentials('spring.mail.username')
        SPRING_MAIL_PASSWORD = credentials('spring.mail.password')
        DOCKER_HUB_USERNAME = credentials('docker-hub-username')
        DOCKER_HUB_PASSWORD = credentials('docker-hub-password')
        DOCKER_IMAGE_NAME = "ksuji/backend-app"
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/K-srand/Gangwon-do-activity_backend.git', credentialsId: 'github-access-token'
            }
        }

        stage('Build') {
            steps {
                sh 'chmod +x ./gradlew'
                sh './gradlew build'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t $DOCKER_IMAGE_NAME:latest -f Dockerfile .'
                }
            }
        }

        stage('Login to Docker Hub') {
            steps {
                script {
                    sh 'echo $DOCKER_HUB_PASSWORD | docker login -u $DOCKER_HUB_USERNAME --password-stdin'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    sh 'docker push $DOCKER_IMAGE_NAME:latest'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    sh 'docker stop backend-app || true && docker rm backend-app || true'
                    sh 'docker pull $DOCKER_IMAGE_NAME:latest'
                    sh """
                    docker run -d --name backend-app -p 4040:4040 \
                    -e AWS_ACCESS_KEY=$AWS_ACCESS_KEY \
                    -e AWS_SECRET_KEY=$AWS_SECRET_KEY \
                    -e NAVER_CLIENT_ID=$NAVER_CLIENT_ID \
                    -e NAVER_CLIENT_SECRET=$NAVER_CLIENT_SECRET \
                    -e SPRING_MAIL_USERNAME=$SPRING_MAIL_USERNAME \
                    -e SPRING_MAIL_PASSWORD=$SPRING_MAIL_PASSWORD \
                    $DOCKER_IMAGE_NAME:latest
                    """
                }
            }
        }
    }

    post {
        always {
            script {
                sh 'docker logout'
            }
        }
    }
}
