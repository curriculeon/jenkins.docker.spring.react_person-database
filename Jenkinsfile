def portNumber = 8080

pipeline {
	agent any
	stages {
		stage('Run') {
			parallel {
				// ----------------------------------------------
				// ##############################################
				// ##############################################
				stage('Back-end') {
					agent {
						docker {
							  image 'jamesdbloom/docker-java8-maven:latest' 
							  args '-u root -v /root/.m2:/root/.m2 -p 8079:8079'
						}
					}
					stages {
						stage('Set Up') {
							steps {
								script {
									sh 'rm -rf spring-application'
								}
							}
						}
						stage('SCM Checkout') {
							steps {
								sh 'git clone https://github.com/curriculeon-student/jenkins.docker.spring.react.selenium_person-database spring-application'
							}
						}
						stage('Compile-Package-Test') {
							steps {
								script {
									dir('spring-application/webservice') {
										sh "mvn spring-boot:run &"
										sh "sleep 60s"
										sh "echo 'Closing Spring Application' "
									}
								}
							}
						}
					}
				}
				// ##############################################
				// ##############################################
				// ----------------------------------------------







				// ----------------------------------------------
				// ##############################################
				// ##############################################
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
									sh 'rm -rf react-application'
								}
							}
						}
						stage('SCM Checkout') {
							steps {
								sh 'git clone https://github.com/curriculeon-student/jenkins.docker.spring.react.selenium_person-database react-application'
							}
						}
						stage('Compile-Package-Test') {
							steps {
								script {
									dir('react-application/client') {
										sh "npm install"
										sh "npm start"
										sh "sleep 60s"
									}
								}
							}
						}
					}
				}
				// ##############################################
				// ##############################################
				// ----------------------------------------------







				// ----------------------------------------------
				// ##############################################
				// ##############################################
				stage('Integrations') {
					agent {
						docker {
							image 'jamesdbloom/docker-java8-maven:latest' 
							args '-u root' 
						}
					}
					stages {
						stage('Set Up') {
							steps {
								script {
									sh 'rm -rf integrations'
								}
							}
						}
						stage('SCM Checkout') {
							steps {
								sh 'git clone https://github.com/curriculeon-student/jenkins.docker.spring.react.selenium_person-database integrations'
							}
						}
						stage('Compile-Package-Test') {
							steps {
								script {
									dir('integrations/integration-testing') {
										sh "mvn package -Dmaven.test.failure.ignore=true"
									}
								}
							}
						}
					}
				}
				// ##############################################
				// ##############################################
				// ----------------------------------------------
			}
		}
	}
}