pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code...'
                git branch: 'feature/jenkinstest', url: 'https://github.com/K-srand/Gangwon-do-activity_backend.git'
            }
        }
        stage('Build') {
            steps {
                echo 'Building the project...'
                sh './gradlew build'
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
                    sh 'docker run -d -p 4040:4040 --name backend-app ' + dockerImage
                    echo "Docker container started successfully"
                }
            }
        }
    }
}
