pipeline {
    agent any

    stages {
        stage('Change GitHub branch to Backend') {
            steps {
                script {
                    sh 'git checkout backend || exit 1'
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    sh 'git pull || exit 1'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    dir('/var/lib/jenkins/workspace/TestePipeline/libertfy-backend') {
                        sh 'mvn clean package || exit 1'
                        sh 'java -jar target/seu-app.jar & || exit 1'
                    }
                }
            }
        }
    }

    post {
        always {
            catchError(buildResult: 'FAILURE', message: 'Erro durante a execução da pipeline') {
                // Código adicional de pós-processamento aqui
            }
        }
    }
}
