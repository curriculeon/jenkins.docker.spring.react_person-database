pipeline {
    agent {
        docker {
            image 'jamesdbloom/docker-java8-maven:latest' 
            args '-u root -p 8060:8060' 
        }
    }
    stages {
        stage('Set Up') {
            steps {
                script {
                    sh 'rm -rf jenkins.docker.spring.react.selenium_person-database'
                }
            }
        }
        stage('SCM Checkout') {
            steps {
                sh 'git clone https://github.com/SushilGautam/jenkins.docker.spring.react.selenium_person-database.git $PWD/jenkins.docker.spring.react.selenium_person-database'
            }
        }
        stage('Compile-Package-Test') {
            steps {
                script {
                    dir('$PWD/jenkins.docker.spring.react.selenium_person-database') {
                        sh "mvn spring-boot:run"
                    }
                }
            }
        }
    }
}
