pipeline {
    agent any
    stages {
        stage("Compile") {
            steps {
                sh "./gradlew compileJave"
            }
        }

        stage("Unit test") {
            steps {
                sh "./gradlew test"
            }
        }
    }
}
