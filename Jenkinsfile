pipeline {
    agent any

    environment {
        JAVA_HOME = '/usr/lib/jvm/java-17-amazon-corretto.x86_64'
        PATH = "${JAVA_HOME}/bin:/usr/bin:${env.PATH}"
    }

    stages {
        stage('Inject Environment Variables') {
            steps {
                echo 'Docker Compose 실행 시 젠킨스 자격 증명으로 환경 변수 주입 준비 중...'

                withCredentials([
                    [$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'AWS_CREDENTAIL'],
                    usernamePassword(credentialsId: 'SPRING_MAIL_CREDENTIAL'),
                    string(credentialsId: 'SSL_KEY_PASSWORD'),
                    string(credentialsId: 'KAKAO_OAUTH2_ID'),
                    string(credentialsId: 'KAKAO_OAUTH2_SECRET'),
                    string(credentialsId: 'SPRING_DATASOURCE_URL'),
                    usernamePassword(credentialsId: 'SPRING_DATASOURCE_CREDENTIAL'),
                    string(credentialsId: 'JWT_SECRET_KEY'),
                    string(credentialsId: 'JSON_KEY'),
                    string(credentialsId: 'NAVER_CLIENT_ID'),
                    string(credentialsId: 'NAVER_CLIENT_SECRET'),
                    string(credentialsId: 'TOUR_API_KEY'),
                    string(credentialsId: 'GOOGLE_API_KEY')
                ]) {
                    // 젠킨스의 환경 변수를 Docker Compose의 환경 변수 파일로 주입
                    sh """
                    echo "AWS_ACCESS_KEY=${AWS_ACCESS_KEY}" > .env
                    echo "AWS_SECRET_KEY=${AWS_SECRET_KEY}" >> .env
                    echo "SPRING_MAIL_USERNAME=${SPRING_MAIL_USERNAME}" >> .env
                    echo "SPRING_MAIL_PASSWORD=${SPRING_MAIL_PASSWORD}" >> .env
                    echo "SSL_KEY_PASSWORD=${SSL_KEY_PASSWORD}" >> .env
                    echo "KAKAO_OAUTH2_ID=${KAKAO_OAUTH2_ID}" >> .env
                    echo "KAKAO_OAUTH2_SECRET=${KAKAO_OAUTH2_SECRET}" >> .env
                    echo "SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}" >> .env
                    echo "SPRING_DATASOURCE_USERNAME=${SPRING_DATASOURCE_USERNAME}" >> .env
                    echo "SPRING_DATASOURCE_PASSWORD=${SPRING_DATASOURCE_PASSWORD}" >> .env
                    echo "JWT_SECRET_KEY=${JWT_SECRET_KEY}" >> .env
                    echo "JSON_KEY=${JSON_KEY}" >> .env
                    echo "NAVER_CLIENT_ID=${NAVER_CLIENT_ID}" >> .env
                    echo "NAVER_CLIENT_SECRET=${NAVER_CLIENT_SECRET}" >> .env
                    echo "TOUR_API_KEY=${TOUR_API_KEY}" >> .env
                    echo "GOOGLE_API_KEY=${GOOGLE_API_KEY}" >> .env
                    """
                }
            }
        }

        stage('Build & Deploy') {
            steps {
                echo '애플리케이션 빌드 및 배포 중...'
                sh 'docker-compose down || true'
                sh 'docker-compose --env-file .env up -d --build'
            }
        }
    }
}
