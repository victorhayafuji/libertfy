pipeline {
        agent any
    stages {
        stage('Change GitHub branch to Backend'){
        steps {
            sh 'git checkout backend'
            }
        }
        stage('Pull Request for GitHub'){
        steps {
            sh 'git pull'
            }
        }
    }
}
