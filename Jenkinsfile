pipeline {
    agent any

    environment {
        JAVA_HOME = '/opt/java/openjdk'
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }

    stages {

        stage('Inject Environment Variables') {
            steps {
                echo 'Docker Compose 실행 시 젠킨스 자격 증명으로 환경 변수 주입 준비 중...'

                withCredentials([
                    [$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'AWS_CREDENTAIL', accessKeyVariable: 'AWS_ACCESS_KEY', secretKeyVariable: 'AWS_SECRET_KEY'],
                    usernamePassword(credentialsId: 'SPRING_MAIL_CREDENTIAL', usernameVariable: 'SPRING_MAIL_USERNAME', passwordVariable: 'SPRING_MAIL_PASSWORD'),
                    string(credentialsId: 'SSL_KEY_PASSWORD', variable: 'SSL_KEY_PASSWORD'),
                    string(credentialsId: 'KAKAO_OAUTH2_ID', variable: 'KAKAO_OAUTH2_ID'),
                    string(credentialsId: 'KAKAO_OAUTH2_SECRET', variable: 'KAKAO_OAUTH2_SECRET'),
                    string(credentialsId: 'SPRING_DATASOURCE_URL', variable: 'SPRING_DATASOURCE_URL'),
                    usernamePassword(credentialsId: 'SPRING_DATASOURCE_CREDENTIAL', usernameVariable: 'SPRING_DATASOURCE_USERNAME', passwordVariable: 'SPRING_DATASOURCE_PASSWORD'),
                    string(credentialsId: 'JWT_SECRET_KEY', variable: 'JWT_SECRET_KEY'),
                    string(credentialsId: 'JSON_KEY', variable: 'JSON_KEY'),
                    string(credentialsId: 'NAVER_CLIENT_ID', variable: 'NAVER_CLIENT_ID'),
                    string(credentialsId: 'NAVER_CLIENT_SECRET', variable: 'NAVER_CLIENT_SECRET'),
                    string(credentialsId: 'TOUR_API_KEY', variable: 'TOUR_API_KEY'),
                    string(credentialsId: 'GOOGLE_API_KEY', variable: 'GOOGLE_API_KEY')
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

        stage('Clean Gradle Cache') {
            steps {
                echo 'Cleaning Gradle cache...'
                sh 'rm -rf ~/.gradle/caches || true'
            }
        }

        stage('Project Build') {
            steps {
                script {
                    sh 'chmod +x ./gradlew'
                    sh './gradlew clean bootJar'
                    sh 'ls -al build/libs'
                }
            }
        }

        stage('Install Docker if not exists') {
            steps {
                echo 'Checking if Docker is installed...'
                sh """
                if ! command -v docker &> /dev/null; then
                    echo "Docker not found, installing..."
                    apt-get update && apt-get install -y docker.io
                else
                    echo "Docker is already installed."
                fi
                """
            }
        }

        stage('Docker Build & Deploy') {
            steps {
                echo '애플리케이션 빌드 및 배포 중...'
                sh 'docker-compose down || true'
                sh 'docker-compose --env-file .env up -d --build'
            }
        }
    }
}