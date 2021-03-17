#!/bin/bash

set -eo pipefail

echo "***********************************"
echo "building student-service"

cd ~/workspace/world-of-spring/vmw/spring310/student-service/
mvn spring-boot:build-image -DskipTests

echo "***********************************"
echo "load image to kind cluster"

kind load docker-image ad-library/spring310-student-service:0.0.1-SNAPSHOT --name spring-world

cd ~/workspace/world-of-spring/vmw/spring310/kubernetes/spring-with-k8s/

echo "***********************************"
echo "deploying student-service to kubernetes"

kubectl apply -f student-service-deployment.yaml

echo "All done!"
