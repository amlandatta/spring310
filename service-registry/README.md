# service-registry

Run a local docker instance of Eureka server i.e a Service Registry

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
[INFO] Successfully built image 'docker.io/ad-library/spring310-service-registry:0.0.1-SNAPSHOT'
```

### Run

`docker run -p 8761:8761 ad-library/spring310-service-registry:0.0.1-SNAPSHOT`

