# config-server

Run a local docker instance of Spring config server project linked with local git repo.

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

2. Changed git url from local file to github 

`spring.cloud.config.server.git.uri=https://github.com/amlandatta/config-repo.git`

### Build image

`mvn spring-boot:build-image -DskipTests`

result:
```
[INFO] Successfully built image 'docker.io/ad-library/spring310-config-server:0.0.1-SNAPSHOT'
```

### Run

`docker run -p 8761:8761 ad-library/spring310-config-server:0.0.1-SNAPSHOT`