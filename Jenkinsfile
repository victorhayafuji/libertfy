pipeline {
        agent any
    stages {
        stage('Switch to directory'){
        steps {
            sh 'cd /var/lib/jenkins/workspace/Libertfy'
            }
        }
        stage('Change branch to Backend'){
        steps {
            sh 'git checkout backend'
            }
        }
        stage('Pull Request'){
        steps {
            sh 'git pull'
            }
        }
    }
}
