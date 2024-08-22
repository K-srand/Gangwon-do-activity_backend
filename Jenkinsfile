pipeline {
    agent any
    environment {
        AWS_ACCESS_KEY_ID = credentials('AWS_ACCESS_KEY')
        AWS_SECRET_ACCESS_KEY = credentials('AWS_SECRET_KEY')
        NAVER_CLIENT_ID = credentials('naver.client.id')
        NAVER_CLIENT_SECRET = credentials('naver.client.secret')
        SPRING_MAIL_CREDENTIALS_USERNAME = credentials('spring.mail.username')
        SPRING_MAIL_CREDENTIALS_PASSWORD = credentials('spring.mail.password')
        JAVA_HOME = '/usr/lib/jvm/java-17-amazon-corretto.x86_64'
        PATH = "${JAVA_HOME}/bin:/usr/bin:${env.PATH}"
    }
    stages {
        stage('Checkout') {
            steps {
                echo '코드 체크아웃 중...'
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
                sh './gradlew build --warning-mode all'
                sh 'java -version'
            }
        }
        stage('Docker Build') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                echo 'Docker 빌드 준비 중...'
                script {
                    sh 'docker buildx version' // Docker Buildx가 설치되었는지 확인
                    sh '''
                        echo "Docker Buildx를 사용하여 이미지 빌드 중..."
                        docker buildx build --progress=plain -t backend-app:latest \
                        --build-arg SPRING_MAIL_CREDENTIALS_USERNAME=${SPRING_MAIL_CREDENTIALS_USERNAME} \
                        --build-arg SPRING_MAIL_CREDENTIALS_PASSWORD=${SPRING_MAIL_CREDENTIALS_PASSWORD} \
                        --build-arg AWS_ACCESS_KEY_ID=${AWS_ACCESS_KEY_ID} \
                        --build-arg AWS_SECRET_ACCESS_KEY=${AWS_SECRET_ACCESS_KEY} \
                        --build-arg NAVER_CLIENT_ID=${NAVER_CLIENT_ID} \
                        --build-arg NAVER_CLIENT_SECRET=${NAVER_CLIENT_SECRET} \
                        -f ./Dockerfile .
                    '''
                }
            }
        }
        stage('Deploy') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                echo '애플리케이션 배포 중...'
                script {
                    sh 'docker stop backend-app || true'
                    sh 'docker rm backend-app || true'
                    sh 'docker run -d -p 4040:4040 --name backend-app -e SPRING_MAIL_CREDENTIALS_USERNAME=${SPRING_MAIL_CREDENTIALS_USERNAME} -e SPRING_MAIL_CREDENTIALS_PASSWORD=${SPRING_MAIL_CREDENTIALS_PASSWORD} -e AWS_ACCESS_KEY_ID=${AWS_ACCESS_KEY_ID} -e AWS_SECRET_ACCESS_KEY=${AWS_SECRET_ACCESS_KEY} -e NAVER_CLIENT_ID=${NAVER_CLIENT_ID} -e NAVER_CLIENT_SECRET=${NAVER_CLIENT_SECRET} backend-app:latest'

                    echo "Docker 컨테이너가 성공적으로 시작되었습니다."
                }
            }
        }
    }
}
