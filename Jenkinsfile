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
        stage('Remove Existing Container') {
            steps {
                script {
                    def containerId = sh(script: "docker ps -a -q -f name=lanzadados-container", returnStdout: true).trim()
                    if (containerId) {
                        sh "docker stop ${containerId}"
                        sh "docker rm ${containerId}"
                    }
                }
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
        stage('Verificar las las images') {
            steps {
                sh 'sudo docker images'
            }
        }
        stage('Cleanup Docker Images') {
            steps {
                sh 'docker image prune -f'
            }
        }
    }
}
