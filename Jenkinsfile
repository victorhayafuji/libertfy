pipeline {
    agent any

    stages {
        stage('Change GitHub branch to Backend') {
            steps {
                sh 'git checkout backend'
            }
        }

        stage('Build') {
            steps {
                sh 'git pull'
            }
        }

        stage('Deploy') {
            steps {
                dir('/var/lib/jenkins/workspace/TestePipeline/libertfy-backend') {
                    sh 'mvn clean package'

                    // Adicionar o comando Java -jar com nohup e disown
                    sh 'nohup java -jar /var/lib/jenkins/workspace/TestePipeline/libertfy-backend/demo-0.0.1-SNAPSHOT.jar > output.log 2>&1 & disown'
                }
            }
        }
    }
}
