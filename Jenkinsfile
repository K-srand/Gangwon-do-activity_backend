pipeline {
    agent any
    environment {
        JAVA_HOME = '/usr/lib/jvm/java-17-amazon-corretto.x86_64'
        PATH = "${JAVA_HOME}/bin:/usr/bin:${env.PATH}"
        SPRING_MAIL_USERNAME = credentials('spring.mail.username')
        SPRING_MAIL_PASSWORD = credentials('spring.mail.password')
        AWS_ACCESS_KEY_ID = credentials('AWS_ACCESS_KEY')
        AWS_SECRET_ACCESS_KEY = credentials('AWS_SECRET_KEY')
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
                sh './gradlew build'
                sh 'java -version'  // Java 버전 확인
                sh 'ls -al build/libs'
            }
        }
        stage('Docker Build') {
            steps {
                echo 'Docker 빌드 준비 중...'
                script {
                sh 'docker buildx version' // Docker Buildx가 설치되었는지 확인
                        echo "Docker를 사용하여 이미지 빌드 중..."
                        sh 'docker build -t ksuji/backend-app -f Dockerfile .'
                        sh 'docker push ksuji/backend-app'
                        sh 'docker rmi ksuji/backend-app'
                }
            }
        }
        stage('Deploy') {
            steps {
                echo '애플리케이션 배포 중...'
                script {
                    sh 'docker stop backend-app || true'
                    sh 'docker rm backend-app || true'
                    sh 'docker run -d -p 4040:4040 --name backend-app backend:latest'
                    echo "Docker 컨테이너가 성공적으로 시작되었습니다."
                }
            }
        }
    }
}
