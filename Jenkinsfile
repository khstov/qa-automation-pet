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

        stage('Smoke') {
            steps {
                sh './gradlew clean smokeTest'
            }
        }

        stage('Regression') {
            steps {
                sh './gradlew regressionTest'
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