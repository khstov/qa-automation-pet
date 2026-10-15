pipeline {
    agent any

    options {
        skipDefaultCheckout(true)
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Test') {
            steps {
                sh './gradlew clean test'
            }
        }
    }

    post {
        always {
            allure commandline: 'allure',
                    results: [[path: 'build/allure-results']]
        }
    }
}