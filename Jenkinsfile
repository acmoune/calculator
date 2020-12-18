pipeline {
    agent any
    triggers {
        pollSCM('* * * * *')
    }
    stages {
        stage("Compile") {
            steps {
                sh "./gradlew compileJava"
            }
        }

        stage("Unit test") {
            steps {
                sh "./gradlew test"
            }
        }

        stage("Code coverage") {
            steps {
                sh "./gradlew jacocoTestReport"
                publishHTML (target: [
                    reportDir: 'build/reports/jacoco/test/html',
                    reportFiles: 'index.html',
                    reportName: "JaCoCo Report"
                ])
                sh "./gradlew jacocoTestCoverageVerification"
            }
        }

        stage("Package") {
            steps {
                sh "./gradlew build"
            }
        }

        stage("Docker build") {
            steps {
                sh "docker build -t acmoune/calculator:gke ."
            }
        }

        stage("Docker push") {
            steps {
                sh "docker login --username acmoune --password elmaljag#2"
                sh "docker push acmoune/calculator:gke"
            }
        }

        /*
        stage("Deploy to staging") {
            steps {
                sh "docker run -d --rm -p 8765:8080 --name calculator acmoune/calculator"
            }
        }

        stage("Acceptance test") {
            steps {
                sleep 60
                sh "./gradlew acceptanceTest -Dcalculator.url=http://172.17.0.1:8765"
                // sh "chmod +x acceptance_test.sh && ./acceptance_test.sh"
            }
        }
        */

        stage("Deploy on GKE") {
            steps {
                // sh "kubectl apply -f hazelcast.yaml"
                sh "kubectl apply -f deployment.yaml"
                sh "kubectl apply -f service.yaml"
            }
        }
    }

    post {
        always {
            sh "docker stop calculator"
        }
    }
}
