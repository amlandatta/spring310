# student-service: 

A sample SpringBoot project which uses OpenFeign to communicate with other app. Integrated with Config Server and Eureka server (service registry)

### Pre-requisites

* Java 11
* Maven

### What's new?

To replace REST Template by OpenFeign implementation following were changed:

### What's new?

1. Updated `pom.xml`

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

2. Removed `url` from `@FeignClient` in the interface `CourseClient.java` as course-service application will be auto discovered via Eureka server.


3. Included `eureka.client.service-url.defaultZone=http://localhost:8761/eureka/` in `application.property`. Refer config-repo


4. Note: for load balancing Ribbon is replaced by Spring Cloud Loadbalancer and Ribbon can be disabled using  `spring.cloud.loadbalancer.ribbon.enabled=false`

### Run

`mvn spring-boot:run -DSkiptest`

- Run with test profile

`mvn spring-boot:run -DSkiptest -Dspring-boot.run.profiles=test`

### Test

`http http://localhost:8182/students`

`http http://localhost:8182/students/1/courses`
