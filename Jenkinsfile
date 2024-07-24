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
                    withEnv(['DOCKER_IMAGE=backend-app:latest']) {
                        def dockerImage = env.DOCKER_IMAGE
                        dockerImage = docker.build(dockerImage)
                    }
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
                    withEnv(['DOCKER_IMAGE=backend-app:latest']) {
                        def dockerImage = env.DOCKER_IMAGE
                        sh 'docker stop backend-app || true'
                        sh 'docker rm backend-app || true'
                        sh 'docker run -d -p 4040:4040 --name backend-app ' + dockerImage
                    }
                }
            }
        }
    }
}
