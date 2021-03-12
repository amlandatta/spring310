#!/bin/bash

set -eo pipefail

echo "***********************************"
echo "building course service"

cd ~/workspace/world-of-spring/vmw/spring310/course-service/
mvn spring-boot:build-image -DskipTests

echo "***********************************"
echo "load image to kind cluster"

kind load docker-image ad-library/spring310-course-service:0.0.1-SNAPSHOT --name spring-world

cd ~/workspace/world-of-spring/vmw/spring310/kubernetes/spring-with-k8s/

echo "***********************************"
echo "deploying to kubernetes"

kubectl apply -f course-service-deployment.yaml

echo "All done!"
