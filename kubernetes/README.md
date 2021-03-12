# Spring on Kubernetes

Run a local docker instance of Spring config server project linked with local git repo.

### Pre-requisites

* kind
* Docker

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
kubectl create deployment course-service --image=test
kubectl expose deployment course-service --port=8181 --target-port=8080

#./course-service-build-to-deploy.sh

# expose app to host
kubectl port-forward service/course-service 8181:8181
```

### Changes

Passing configuration through environment variables 

```yaml
    spec:
      containers:
        - name: course-service
          image: ad-library/spring310-course-service:0.0.1-SNAPSHOT
          env:
            - name: "course-service.recommended-course-id"
              value: "3"
```

### Test

`http http://localhost:8181/courses/recommend`