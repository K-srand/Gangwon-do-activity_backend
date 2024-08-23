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
                    // Docker 이미지를 빌드하고 태그 지정
                    sh 'docker build -t $DOCKER_IMAGE_NAME:latest -f Dockerfile .'
                }
            }
        }

        stage('Login to Docker Hub') {
            steps {
                script {
                    // Docker Hub 로그인
                    sh 'echo $DOCKER_HUB_PASSWORD | docker login -u $DOCKER_HUB_USERNAME --password-stdin'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    // Docker 이미지를 Docker Hub에 푸시
                    sh 'docker push $DOCKER_IMAGE_NAME:latest'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // 기존 컨테이너 중지 및 삭제
                    sh 'docker stop backend-app || true && docker rm backend-app || true'

                    // Docker Hub에서 이미지를 가져와서 컨테이너 실행
                    sh '''
                    docker pull $DOCKER_IMAGE_NAME:latest
                    docker run -d --name backend-app -p 4040:4040 \
                    -e AWS_ACCESS_KEY=$AWS_ACCESS_KEY \
                    -e AWS_SECRET_KEY=$AWS_SECRET_KEY \
                    -e NAVER_CLIENT_ID=$NAVER_CLIENT_ID \
                    -e NAVER_CLIENT_SECRET=$NAVER_CLIENT_SECRET \
                    -e SPRING_MAIL_USERNAME=$SPRING_MAIL_USERNAME \
                    -e SPRING_MAIL_PASSWORD=$SPRING_MAIL_PASSWORD \
                    $DOCKER_IMAGE_NAME:latest
                    '''
                }
            }
        }
    }

    post {
        always {
            script {
                // Docker Hub 로그아웃
                sh 'docker logout'
            }
        }
    }
}
