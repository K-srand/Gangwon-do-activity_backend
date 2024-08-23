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
                dir('backend') { // backend 디렉토리로 이동
                    sh './gradlew build'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('backend') { // backend 디렉토리로 이동
                    script {
                        docker.build('backend-app', '-f Dockerfile .')
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // 이미 실행 중인 컨테이너를 중지하고 삭제
                    sh 'docker stop backend-app || true && docker rm backend-app || true'

                    // 새로운 컨테이너를 실행
                    sh 'docker run -d --name backend-app -p 4040:4040 backend-app'
                }
            }
        }
    }
}
