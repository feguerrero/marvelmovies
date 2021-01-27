pipeline {
  agent any
  stages {
    stage('Lint') {
       steps {
         sh './gradlew ktlintCheck'
       }
     }
    stage('warrior stage') {
      steps {
        sh './gradlew clean'
      }
    }
  }
}
