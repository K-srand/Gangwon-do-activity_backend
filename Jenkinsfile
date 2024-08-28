pipeline {
    agent any
    environment {
        JAVA_HOME = '/usr/lib/jvm/java-17-amazon-corretto.x86_64'
        PATH = "${JAVA_HOME}/bin:/usr/bin:${env.PATH}"
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/K-srand/Gangwon-do-activity_backend.git'
            }
        }

        stage('Grant Permissions') {
            steps {
                echo 'gradlew에 실행 권한 부여 중...'
                sh 'chmod +x ./gradlew'
            }
        }

        stage('Build') {
            steps {
                echo '프로젝트 빌드 중...'

                // Adding the withCredentials block to use S3 credentials
                withCredentials([string(credentialsId: 'AWS_CREDENTIAL', variable: 'AWS_CREDENTIAL')]) {
                    sh './gradlew build' // Gradle 빌드 실행
                    sh 'ls -al build/libs' // 빌드 결과 확인
                }
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Docker 빌드 준비 중...'
                script {
                    sh 'docker --version' // Docker Buildx가 설치되었는지 확인
                    echo "Docker를 사용하여 이미지 빌드 중..."
                    sh 'docker build -t ksuji/backend-app -f Dockerfile .'
                }
            }
        }

        stage('Docker Push') {
            steps {
                echo 'Docker Hub에 로그인 중...'
                withCredentials([
                    string(credentialsId: 'docker-hub-username', variable: 'DOCKER_HUB_USERNAME'),
                    string(credentialsId: 'docker-hub-password', variable: 'DOCKER_HUB_PASSWORD')
                ]) {
                    sh 'echo $DOCKER_HUB_PASSWORD | docker login -u $DOCKER_HUB_USERNAME --password-stdin'
                }
                echo "Docker 이미지를 푸시 중..."
                sh 'docker push ksuji/backend-app:latest'
            }
        }

        stage('Deploy') {
            steps {
                echo '애플리케이션 배포 중...'
                script {
                    sh 'docker stop backend-app || true'
                    sh 'docker rm backend-app || true'
                }

                sh 'docker run -d -p 4040:4040 --name backend-app ksuji/backend-app:latest'

                echo "Docker 컨테이너가 성공적으로 시작되었습니다."
            }
        }
    }
}
