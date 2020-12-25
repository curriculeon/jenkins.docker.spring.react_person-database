parallel {
        stage('Back-end') {
          agent {
            docker {
              image 'node:10'
              args '-v /root/.m2:/root/.m2 -p 8060:8060'
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
                  sh 'git clone https://github.com/curriculeon-student/jenkins.docker.spring.react_person-database.git $PWD/jenkins.docker.spring.react_person-database'
                }
              }
            }
            stage('Compile-Package-Test') {
              steps {
                script {
                  dir('$PWD/jenkins.docker.spring.react_person-database/client') {
                    sh "npm install"
                    sh "npm start"
                  }
                }
              }
            }
          }
        }
     }
