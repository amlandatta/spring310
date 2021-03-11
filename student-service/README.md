# student-service: 

A sample SpringBoot project which uses OpenFeign to communicate with other app

### Pre-requisites

* Java 11
* Maven

### What's new?

To replace REST Template by OpenFeign implementation following were changed:

1. Included dependency in `pom.xml`

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

2. Included a `FeignClient` interface `CourseClient.java`

3. Included `@EnableFeignClients` in `StudentServiceApplication.java` to autowire FeignClient

4. Replace REST template by `FeignClient` in `StudentController.java`

### Run

`mvn spring-boot:run -DSkiptest`

- Run with test profile

`mvn spring-boot:run -DSkiptest -Dspring-boot.run.profiles=test`

### Test

`http http://localhost:8182/students`

`http http://localhost:8182/students/1/courses`
