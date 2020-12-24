pipeline {
  agent none
  stages {
    stage('Run') {
      parallel {

        
        
        
        stage('Back-end') {
          agent {
            docker {
              image 'jamesdbloom/docker-java8-maven:latest'
              args '-v /root/.m2:/root/.m2 -p 8079:8079'
            }
          }
          stages {
            stage('Set Up') {
              steps {
                script {
                  sh 'rm -rf jenkins.docker.spring.react_person-database'
                }
              }
            }
            stage('SCM Checkout') {
              steps {
                script {
                  sh 'git clone git@github.com:curriculeon-student/jenkins.docker.spring.react_person-database.git'
                }
              }
            }
            stage('Compile-Package-Test') {
              steps {
                script {
                  dir('$PWD/jenkins.docker.spring.react_person-database/webservice') {
                    sh "mvn spring-boot:run"
                  }
                }
              }
            }
          }
        }

        
        
        
        stage('Front-end') {
          agent {
            docker {
              image 'node:10'
              args '-v /root/.m2:/root/.m2 -p 3000:3000'
            }
          }
          stages {
            stage('Set Up') {
              steps {
                script {
                  sh 'rm -rf jenkins.docker.spring.react_person-database'
                }
              }
            }
            stage('SCM Checkout') {
              steps {
                script {
                  sh 'git clone git@github.com:curriculeon-student/jenkins.docker.spring.react_person-database.git'
                }
              }
            }
            stage('Compile-Package-Test') {
              steps {
                script {
                  dir('$PWD/jenkins.docker.spring.react_person-database') {
                    sh "npm install"
                    sh "npm start"
                    sh "pause"
                  }
                }
              }
            }
          }
        }

        
        
        
        stage('Integration-Testing') {
          agent {
            docker {
              image 'jamesdbloom/docker-java8-maven:latest'
              args '-v /root/.m2:/root/.m2 -p 8050:8050'
            }
          }
          stages {
            stage('Set Up') {
              steps {
                script {
                  sh 'rm -rf jenkins.docker.spring.react_person-database'
                }
              }
            }
            stage('SCM Checkout') {
              steps {
                script {
                  sh 'git clone git@github.com:curriculeon-student/jenkins.docker.spring.react_person-database.git'
                }
              }
            }
            stage('Compile-Package-Test') {
              steps {
                script {
                  dir('$PWD/jenkins.docker.spring.react_person-database/integration-testing') {
                    sh "mvn package -Dmaven.test.failure.ignore=true"
                  }
                }
              }
            }
          }
        }
      }
    }
  }

}
