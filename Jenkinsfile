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
                sh './gradlew build' // Gradle 빌드 실행
                sh 'ls -al build/libs' // 빌드 결과 확인
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Docker 빌드 준비 중...'
                script {
                    sh 'docker buildx version' // Docker Buildx가 설치되었는지 확인
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
                withCredentials([
                    usernamePassword(credentialsId: 'SPRING_MAIL_CREDENTAIL', usernameVariable: 'SPRING_MAIL_USERNAME', passwordVariable: 'SPRING_MAIL_PASSWORD'),
                    string(credentialsId: 'AWS_ACCESS_KEY', variable: 'AWS_ACCESS_KEY_ID'),
                    string(credentialsId: 'AWS_SECRET_KEY', variable: 'AWS_SECRET_ACCESS_KEY')
                ]) {
                    sh '''
                    # 명시적으로 Docker 이미지를 정의합니다.
                    IMAGE_NAME="ksuji/backend-app:latest"

                    # Docker 컨테이너 실행
                    docker run -d -p 4040:4040 --name backend-app \
                    -e SPRING_MAIL_USERNAME=$SPRING_MAIL_USERNAME \
                    -e SPRING_MAIL_PASSWORD=$SPRING_MAIL_PASSWORD \
                    -e AWS_ACCESS_KEY_ID=$AWS_ACCESS_KEY_ID \
                    -e AWS_SECRET_ACCESS_KEY=$AWS_SECRET_ACCESS_KEY \
                    $IMAGE_NAME
                    '''
                }
                echo "Docker 컨테이너가 성공적으로 시작되었습니다."
            }
        }
    }
}
