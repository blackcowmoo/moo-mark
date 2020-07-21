pipeline {
  agent any 

  environment {
    PROJECT_NAME="moomark-${BRANCH_NAME}"
    DEV_HOSTING_SERVER="https://dev-hosting.micalgenus.com"
    BACKEND_AUTH_JAR="backend.auth.jar"
    HELM_DIRECTORY="helm"
    HELM_RELEASE_NAME="${PROJECT_NAME}".replaceAll("PR", "pr")
    HELM_RELEASE_EXIST=sh(script: '[ -z $(helm ls ' + "${HELM_RELEASE_NAME}" + ' -a -q) ] && echo "false" || echo "true"', , returnStdout: true).trim()
    HELM_DEFAULT_OPTIONS="--set test.enabled=true \
                  --set frontend.istio.enabled=true \
                  --set frontend.istio.host=${HELM_RELEASE_NAME}.kubernetes.micalgenus.com \
                  --set frontend.deployment.replicas=1 \
                  --set frontend.deployment.local.file=frontend.tar \
                  --set gateway.istio.enabled=true \
                  --set gateway.istio.host=${HELM_RELEASE_NAME}-gateway.kubernetes.micalgenus.com \
                  --set gateway.deployment.replicas=1 \
                  --set gateway.deployment.local.file=gateway.tar \
                  --set backend.auth.istio.enabled=true \
                  --set backend.auth.istio.host=${HELM_RELEASE_NAME}-backend-auth.kubernetes.micalgenus.com \
                  --set backend.auth.deployment.replicas=1 \
                  --set test.rabbitmq.istio.host=${HELM_RELEASE_NAME}-backend-rabbitmq.kubernetes.micalgenus.com"
  }

  stages {
    stage('Prepare') {
      steps {
        sh "printenv"
        sh "echo $BRANCH_NAME"
        sh "helm repo list"
        sh "helm ls $HELM_RELEASE_NAME"
      }
    }

    stage('Build') {
      steps {
        sh "rm -rf $HELM_DIRECTORY/frontend.tar && tar cf $HELM_DIRECTORY/frontend.tar frontend"
        sh "rm -rf $HELM_DIRECTORY/gateway.tar && tar cf $HELM_DIRECTORY/gateway.tar gateway"
        sh "cd backend/auth && ./gradlew build -x checkstyleMain -x test && cd ../../ && rm -rf $BACKEND_AUTH_JAR && cp ./backend/auth/build/libs/*.jar $BACKEND_AUTH_JAR"
      }
    }

    stage('Upload') {
      steps {
        script {
          env.BACKEND_AUTH_JAR_NAMESPACE = sh(script: "curl -XPOST ${DEV_HOSTING_SERVER}/upload/file -F 'file=@${BACKEND_AUTH_JAR}' | jq '.namespace'", , returnStdout: true).trim().replaceAll("\"", "")
          env.BACKEND_AUTH_JAR_URL = "${DEV_HOSTING_SERVER}/download/${BACKEND_AUTH_JAR_NAMESPACE}/${BACKEND_AUTH_JAR}"
          env.HELM_OPTIONS = "${HELM_DEFAULT_OPTIONS} --set backend.auth.deployment.file.name=${BACKEND_AUTH_JAR} --set backend.auth.deployment.file.url=${BACKEND_AUTH_JAR_URL}"
        }
      }
    }

    stage('Test') {
      steps {
        script {
          sh "printenv"
        }
      }
    }

    stage('Deploy (install)') {
      when {
        environment name: 'HELM_RELEASE_EXIST', value: 'false'
      }

      steps {
        withCredentials([
          string(credentialsId: 'GOOGLE_CLIENT_ID_DEV', variable: 'GOOGLE_CLIENT_ID'),
          string(credentialsId: 'GOOGLE_CLIENT_SECRET_DEV', variable: 'GOOGLE_CLIENT_SECRET')
        ]) {
          echo "Install $HELM_RELEASE_NAME"
          sh "helm install --name $HELM_RELEASE_NAME --namespace $HELM_RELEASE_NAME ./helm $HELM_OPTIONS --set backend.auth.oauth.google.id=${GOOGLE_CLIENT_ID} --set backend.auth.oauth.google.secret=${GOOGLE_CLIENT_SECRET}"
        }
      }
    }

    stage('Deploy (update)') {
      when {
        environment name: 'HELM_RELEASE_EXIST', value: 'true'
      }

      steps {
        withCredentials([
          string(credentialsId: 'GOOGLE_CLIENT_ID_DEV', variable: 'GOOGLE_CLIENT_ID'),
          string(credentialsId: 'GOOGLE_CLIENT_SECRET_DEV', variable: 'GOOGLE_CLIENT_SECRET')
        ]) {
          echo "Update $HELM_RELEASE_NAME"
          sh "helm upgrade $HELM_RELEASE_NAME --namespace $HELM_RELEASE_NAME --recreate-pods ./helm $HELM_OPTIONS --set backend.auth.oauth.google.id=${GOOGLE_CLIENT_ID} --set backend.auth.oauth.google.secret=${GOOGLE_CLIENT_SECRET}"
        }
      }
    }

    stage('Result') {
      steps {
        sh "kubectl get events --namespace $HELM_RELEASE_NAME"
        sh "kubectl describe all --namespace $HELM_RELEASE_NAME"
      }
    }
  }

  post { 
    success { 
      script {
        FRONTEND_URL="http://${HELM_RELEASE_NAME}.kubernetes.micalgenus.com/"
        GATEWAY_URL="http://${HELM_RELEASE_NAME}-gateway.kubernetes.micalgenus.com/"
        BACKEND_RABBITMQ_URL="http://${HELM_RELEASE_NAME}-backend-rabbitmq.kubernetes.micalgenus.com/"
        BACKEND_AUTH_URL="http://${HELM_RELEASE_NAME}-backend-auth.kubernetes.micalgenus.com/"
        for (comment in pullRequest.comments) {
          // Remove all comments by blackcowmooo
          if (comment.user == 'blackcowmooo')  {
            pullRequest.deleteComment(comment.id)
          }
        }
        pullRequest.comment("Frontend: ${FRONTEND_URL}\nGateway: ${GATEWAY_URL}\nBackend.RabbitMQ: ${BACKEND_RABBITMQ_URL}\nBackend.Auth: ${BACKEND_AUTH_URL}\n")
      }
    }

    failure {
      script {
        sh "helm delete --purge $HELM_RELEASE_NAME"
        sh "kubectl delete ns $HELM_RELEASE_NAME"
      }
    }

    always {
      script {
        sh "echo test"
      }
    }
  }
}
