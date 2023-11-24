pipeline {
        agent any
    stages {
        stage('Change GitHub branch to Backend'){
        steps {
            sh 'git checkout backend'
            }
        }
        stage('Build'){
        steps {
            sh 'git pull'
            }
        }
	stage('Deploy'){
        steps {
            sh 'cd /var/lib/jenkins/workspace/Libertfy/libertfy-backend'
	    sh 'mvn clean package'
            }
        }
    }
}
