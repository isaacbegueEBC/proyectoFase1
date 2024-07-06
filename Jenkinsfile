pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/isaacbegueEBC/proyectoFase1.git'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t lanzadados-app .'
            }
        }
        stage('Run Docker Container') {
            steps {
                sh 'docker run --name lanzadados-container -d lanzadados-app'
            }
        }
        stage('Verify Logs') {
            steps {
                sh 'docker logs lanzadados-container'
            }
        }
        stage('Cleanup Old Images') {
            steps {
                sh '''
                docker image prune -f
                docker container prune -f
                '''
            }
        }
    }
}
