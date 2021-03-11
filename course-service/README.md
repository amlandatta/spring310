# course-service: 

A sample SpringBoot project integrated with Spring Cloud Config Server and Eureka server (a service registry)

### Pre-requisites

* Java 11
* Maven

### What's new?

1. Updated `pom.xml`

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

### Run

`mvn spring-boot:run -DSkiptest`

- Run with test profile

`mvn spring-boot:run -DSkiptest -Dspring-boot.run.profiles=test`

To test load balancing, we need to run multiple instance of the application. For this comment `spring.config.import` in `application.properties` and include different `server.port` value to run the application.

### Test

`http http://localhost:8181/courses/`


