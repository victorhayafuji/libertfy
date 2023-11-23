pipeline {
        agent any
    stages {
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
