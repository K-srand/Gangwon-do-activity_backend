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

        stage('Inject Properties') {
            steps {
                echo 'application.properties 파일에 환경 변수 주입 중...'
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
                    // application.properties 업데이트
                    script {
                        def replacements = [
                            "AWS_ACCESS_KEY=${AWS_ACCESS_KEY}",
                            "AWS_SECRET_KEY=${AWS_SECRET_KEY}",
                            "SPRING_MAIL_USERNAME=${SPRING_MAIL_USERNAME}",
                            "SPRING_MAIL_PASSWORD=${SPRING_MAIL_PASSWORD}",
                            "SSL_KEY_PASSWORD=${SSL_KEY_PASSWORD}",
                            "KAKAO_OAUTH2_ID=${KAKAO_OAUTH2_ID}",
                            "KAKAO_OAUTH2_SECRET=${KAKAO_OAUTH2_SECRET}",
                            "SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}",
                            "SPRING_DATASOURCE_USERNAME=${SPRING_DATASOURCE_USERNAME}",
                            "SPRING_DATASOURCE_PASSWORD=${SPRING_DATASOURCE_PASSWORD}",
                            "JWT_SECRET_KEY=${JWT_SECRET_KEY}",
                            "JSON_KEY=${JSON_KEY}",
                            "NAVER_CLIENT_ID=${NAVER_CLIENT_ID}",
                            "NAVER_CLIENT_SECRET=${NAVER_CLIENT_SECRET}",
                            "TOUR_API_KEY=${TOUR_API_KEY}",
                            "GOOGLE_API_KEY=${GOOGLE_API_KEY}"
                        ]

                        replacements.each { replacement ->
                            def key = replacement.split('=')[0]
                            def value = replacement.split('=')[1]
                            sh "sed -i 's#\\\${${key}}#${value}#g' src/main/resources/application.properties"
                        }
                    }
                    // 변경된 파일 확인
                    sh 'cat src/main/resources/application.properties'
                }
            }
        }

        stage('Build') {
            steps {
                echo '프로젝트 빌드 중...'
                sh './gradlew build'
                sh 'ls -al build/libs'
            }
        }

        stage('Build & Deploy') {
            steps {
                echo '애플리케이션 빌드 및 배포 중...'
                sh 'docker-compose down || true'
                sh 'docker-compose up -d --build'
            }
        }

    }
}
