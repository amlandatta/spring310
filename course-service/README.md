# course-service: 

A sample SpringBoot project integrated with Spring Cloud Config Server

### Pre-requisites

* Java 11
* Maven

### What's new?

Externalized configuration to git repo.

1. Removed all properties from application.properties and moved to config-repo. Deleted application-test.properties. 

```
server.port=8181
course-service.recommended-course-id=2
```

Note: `spring.config.import=` is required at start up to connect to config server. But this can be externalized and passed as environment variable.

2. Updated `pom.xml`

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
...
<dependencyManagement>
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-dependencies</artifactId>
        <version>${spring-cloud.version}</version>
        <type>pom</type>
        <scope>import</scope>
    </dependency>
</dependencies>
</dependencyManagement>
```

### Run

`mvn spring-boot:run -DSkiptest`

- Run with test profile

`mvn spring-boot:run -DSkiptest -Dspring-boot.run.profiles=test`

### Test

`http http://localhost:8181/courses/recommend`

Should return different recommendation for each environment based upon the configured value

To troubleshoot, check if the environment variables are loaded (this will require actuator to be included in the project and endpoints exposed `management.endpoints.web.exposure.include=*`)

`http http://localhost:8181/actuator/env | jq '.' | grep -i "course-service.recommended-course-id" -A3`
