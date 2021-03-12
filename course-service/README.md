# course-service: 

A sample SpringBoot project integrated with Spring Cloud Config Server and Eureka server (a service registry)

### Pre-requisites

* Java 11
* Maven
* Docker
* Docker compose

### What's new?

Change to create docker image

1. Updated `pom.xml`

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <image>
                    <name>ad-library/spring310-${project.artifactId}:${project.version}</name>
                </image>
                <pullPolicy>IF_NOT_PRESENT</pullPolicy>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### Build image

`mvn spring-boot:build-image -DskipTests`

result:
```
[INFO] Successfully built image 'docker.io/ad-library/spring310-course-service:0.0.1-SNAPSHOT'
```

### Run

`docker run -p 8080:8080 ad-library/spring310-course-service:0.0.1-SNAPSHOT`

Note: If Eureka and Config server is not running then expect errors but the REST endpoints will be accessible.

At this stage no change required to deploy to Kubernetes