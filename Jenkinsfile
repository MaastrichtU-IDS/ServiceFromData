pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'docker build --rm -f "Dockerfile" -t d2s-api:latest .'
      }
    }
    stage('Run') {
      steps {
        sh '''docker stop d2s-api || echo container was not running
docker run -d --rm --name d2s-api -p 85:8080 --link graphdb:graphdb -e ENDPOINT="http://graphdb:7200/repositories/ncats-red-kg" d2s-api'''
      }
    }
  }
}