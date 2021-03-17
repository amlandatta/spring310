# student-service: 

A sample SpringBoot, Kubernetes deployable project which uses OpenFeign to communicate with course-service application

### Pre-requisites

* Java 11
* Maven
* Docker
* Docker compose
* Kind
* kubectl


### What's new?

Change to deploy to Kubernetes

1. Removed Spring Cloud Config dependency but retained Spring Cloud dependencies as OpenFeign 
   
2. Included path for configuration in `application.properties` as `spring.config.import=optional:file:/deployments/config/student-service.properties`

3. Course service endpoint (hostname and port) injected from environment variable which is updated by Kubernetes by default. Refer `CourseClient.java` 

```java
@FeignClient(name="course-service",
        url = "${COURSE_SERVICE_SERVICE_HOST:http://localhost}:${COURSE_SERVICE_SERVICE_PORT:8181}")
@RequestMapping("/courses")
public interface CourseClient {
```