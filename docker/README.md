# Docker-compose

Run a local docker instance of Spring config server project linked with local git repo.

### Pre-requisites

* Docker
* Docker compose

### What's new?

1. Build images for the spring projects

```shell
cd ../service-registry
mvn spring-boot:build-image -DskipTests

cd ../course-service
mvn spring-boot:build-image -DskipTests

cd ../student-service
mvn spring-boot:build-image -DskipTests
```

2. Start apps

```
# cd to the docker folder
docker-compose up --remove-orphans -d
```

3. Stop apps

```
docker-compose down --remove-orphans
```

### Test

Launch Eureka server and wait for the apps to get registered http://localhost:9191

And then test

`http http://localhost:8181/courses`

`http http://localhost:8182/students/1/courses`
