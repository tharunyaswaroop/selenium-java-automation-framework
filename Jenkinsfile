pipeline {
  agent any
  options { timeout(time: 15, unit: 'MINUTES') }
  stages {
    stage('Checkout') { steps { checkout scm } }
    stage('Verify') { steps { sh 'mvn -B verify' } }
  }
  post {
    always {
      junit testResults: 'target/surefire-reports/TEST-*.xml', allowEmptyResults: false
      archiveArtifacts artifacts: 'target/surefire-reports/**,target/screenshots/**', allowEmptyArchive: true
    }
  }
}
