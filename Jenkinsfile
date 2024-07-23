pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'backend-app:latest'
    }
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code...'
                git branch: 'feature/jenkinstest', url: 'https://github.com/K-srand/Gangwon-do-activity_backend.git'
            }
        }
        stage('Build') {
            when {
                branch 'feature/jenkinstest'
            }
            steps {
                echo 'Building the project...'
                sh './gradlew build'
            }
        }
        stage('Docker Build') {
            when {
                branch 'feature/jenkinstest'
            }
            steps {
                echo 'Building Docker image...'
                script {
                    dockerImage = docker.build(DOCKER_IMAGE)
                }
            }
        }
        stage('Deploy') {
            when {
                branch 'feature/jenkinstest'
            }
            steps {
                echo 'Deploying the application...'
                script {
                    sh 'docker stop backend-app || true'
                    sh 'docker rm backend-app || true'
                    sh 'docker run -d -p 4040:4040 --name backend-app backend-app:latest'
                }
            }
        }
    }
}
