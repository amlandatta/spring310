# Spring on Kubernetes

Deploy SpringBoot application to Kubernetes. Load configuration using ConfigMap

### Pre-requisites

* kind
* Docker
* kubectl

### Get a local Kubernetes cluster

```shell
# Install kind (MacOS). Refer https://kind.sigs.k8s.io/docs/user/quick-start/#installation
brew install kind

# Create cluster
kind create cluster --name spring-world
kind get clusters

# Set context
kubectl cluster-info --context kind-spring-world
```

### Load images to local Kind cluster

```shell
kind load docker-image ad-library/spring310-course-service:0.0.1-SNAPSHOT --name spring-world
```

### Deploy to Kubernetes

```shell
kubectl create configmap spring-with-k8s-config \
    --from-file=${HOME}/workspace/config-repo/course-service.properties \
    --from-file=${HOME}/workspace/config-repo/student-service.properties \
    -n spring-world-ns

kubectl create deployment course-service --image=ad-library/spring310-course-service:0.0.1-SNAPSHOT

# this will deploy app but will not load the configurations
# to load configurations refer course-service-deployment.yaml

kubectl expose deployment course-service --port=8181 --target-port=8080

#./course-service-build-to-deploy.sh

# expose app to host
kubectl port-forward service/course-service 8181:8181
```

### Test

`http http://localhost:8181/courses/recommend`