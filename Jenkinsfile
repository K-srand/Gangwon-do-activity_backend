pipeline {
    agent any
    environment {
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
                sh './gradlew build --warning-mode all'  // 모든 경고를 표시하도록 설정
                sh 'java -version'  // Java 버전 확인
            }
        }
        stage('Test') {
            steps {
                echo '테스트 실행 중...'
                script {
                    withCredentials([
                        usernamePassword(credentialsId: 'spring.mail.credential', usernameVariable: 'SPRING_MAIL_CREDENTIALS_USERNAME', passwordVariable: 'SPRING_MAIL_CREDENTIALS_PASSWORD'),
                        string(credentialsId: 'AWS_ACCESS_KEY', variable: 'AWS_ACCESS_KEY_ID'),
                        string(credentialsId: 'AWS_SECRET_KEY', variable: 'AWS_SECRET_ACCESS_KEY')
                    ]) {
                        sh './gradlew test --warning-mode all'  // 테스트 단계에서도 모든 경고를 표시
                    }
                }
            }
            post {
                always {
                    junit 'build/reports/tests/test/*.xml' // 테스트 결과를 Jenkins에서 확인 가능하도록 설정
                }
                failure {
                    echo '테스트 실패!'
                    error '테스트 단계에서 실패하여 빌드가 중단되었습니다.'
                }
            }
        }
        stage('Docker Build') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                echo 'Docker 빌드 준비 중...'
                script {
                    withCredentials([
                        usernamePassword(credentialsId: 'spring.mail.credential', usernameVariable: 'SPRING_MAIL_CREDENTIALS_USERNAME', passwordVariable: 'SPRING_MAIL_CREDENTIALS_PASSWORD'),
                        string(credentialsId: 'AWS_ACCESS_KEY', variable: 'AWS_ACCESS_KEY_ID'),
                        string(credentialsId: 'AWS_SECRET_KEY', variable: 'AWS_SECRET_ACCESS_KEY'),
                        string(credentialsId: 'naver.client.id', variable: 'NAVER_CLIENT_ID'),
                        string(credentialsId: 'naver.client.secret', variable: 'NAVER_CLIENT_SECRET')
                    ]) {
                        sh 'docker buildx version' // Docker Buildx가 설치되었는지 확인
                        sh """
                            echo "Docker Buildx를 사용하여 이미지 빌드 중..."
                            docker buildx build --progress=plain -t backend-app:latest \
                            --build-arg SPRING_MAIL_CREDENTIALS_USERNAME=${SPRING_MAIL_CREDENTIALS_USERNAME} \
                            --build-arg SPRING_MAIL_CREDENTIALS_PASSWORD=${SPRING_MAIL_CREDENTIALS_PASSWORD} \
                            --build-arg AWS_ACCESS_KEY_ID=${AWS_ACCESS_KEY_ID} \
                            --build-arg AWS_SECRET_ACCESS_KEY=${AWS_SECRET_ACCESS_KEY} \
                            --build-arg NAVER_CLIENT_ID=${NAVER_CLIENT_ID} \
                            --build-arg NAVER_CLIENT_SECRET=${NAVER_CLIENT_SECRET} \
                            -f Dockerfile .
                        """.trim()
                    }
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
