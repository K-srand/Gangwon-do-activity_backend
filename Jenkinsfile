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
                    // Docker 이미지를 명확하게 빌드
                    sh 'docker build -t backend-app:latest -f Dockerfile .'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // 이미 실행 중인 컨테이너를 중지하고 삭제
                    sh 'docker stop backend-app || true && docker rm backend-app || true'

                    // 새로운 컨테이너를 실행하면서 환경 변수를 전달
                    sh '''
                    docker run -d --name backend-app -p 4040:4040 \
                    -e AWS_ACCESS_KEY=$AWS_ACCESS_KEY \
                    -e AWS_SECRET_KEY=$AWS_SECRET_KEY \
                    -e NAVER_CLIENT_ID=$NAVER_CLIENT_ID \
                    -e NAVER_CLIENT_SECRET=$NAVER_CLIENT_SECRET \
                    -e SPRING_MAIL_USERNAME=$SPRING_MAIL_USERNAME \
                    -e SPRING_MAIL_PASSWORD=$SPRING_MAIL_PASSWORD \
                    backend-app:latest
                    '''
                }
            }
        }
    }
}
