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
                sh 'sudo docker build -t lanzadados-app .'
            }
        }
        stage('Run Docker Container') {
            steps {
                sh 'sudo docker run --name lanzadados-container -d lanzadados-app'
            }
        }
        stage('Verify Logs') {
            steps {
                sh 'sudo docker logs lanzadados-container'
            }
        }
    }
}
