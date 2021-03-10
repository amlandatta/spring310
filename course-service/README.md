# course-service: A sample project

### Pre-requisites

* Java 11 (will work on Java 8 too)
* Maven

### What's new?

1. Included two properties 

```
server.port=8181
course-service.recommended-course-id=2
```

Application to start in different port (8181) instead of default port (8080)

2. Included CourseConfig.java and updated CourseController.java with a recommend endpoint which uses the custom configuration

3. Included additional property file application-dev.properties to separate local property from test environment properties


### Run

`mvn spring-boot:run -DSkiptest`

- Run with test profile

`mvn spring-boot:run -DSkiptest -Dspring-boot.run.profiles=test`

### Test

`curl http://localhost:8181/courses`

`curl http://localhost:8181/courses/1`

`curl http://localhost:8181/courses/recommend`

