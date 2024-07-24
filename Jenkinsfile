pipeline {
    agent any
    environment {
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-17.0.9.0.9-2.el8_8.x86_64'
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code...'
                git branch: 'main', url: 'https://github.com/K-srand/Gangwon-do-activity_backend.git'
            }
        }
        stage('Grant Permissions') {
            steps {
                echo 'Granting execute permission to gradlew...'
                sh 'chmod +x ./gradlew'
            }
        }
        stage('Build') {
            steps {
                echo 'Building the project...'
                sh 'java -version'  // Java 버전 확인
                sh './gradlew build --warning-mode all'
            }
        }
        stage('Docker Build') {
            steps {
                echo 'Building Docker image...'
                script {
                    def dockerImage = 'backend-app:latest'
                    dockerImage = docker.build(dockerImage)
                    echo "Docker image built successfully: ${dockerImage.id}"
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying the application...'
                script {
                    def dockerImage = 'backend-app:latest'
                    sh 'docker stop backend-app || true'
                    sh 'docker rm backend-app || true'
                    sh 'docker run -d -p 4040:4040 --name backend-app ' + dockerImage.id
                    echo "Docker container started successfully"
                }
            }
        }
    }
    post {
        always {
            echo 'Cleaning up temporary files...'
            sh 'rm -f *.log'
        }
        failure {
            echo 'Build failed. Please check the logs for more details.'
            script {
                sh './gradlew --version'
                sh './gradlew dependencies --warning-mode all'
            }
        }
    }
}
