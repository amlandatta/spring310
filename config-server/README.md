# config-server

Run a local instance of Spring config server project linked with local git repo.

### Pre-requisites

* Create a local git repo for configurations
  
```
mkdir -p ~/workspace/config-repo
cd ~/workspace/config-repo
cat > course-service.properties
course-service.recommended-course-id=2
git add .
git commit -m "Initial commit"
git branch -M main
```

Refer [config-repo from Github](https://github.com/amlandatta/config-repo.git)

### Key code snippets and configurations

1. Refer `pom.xml`

```
<properties>
    <java.version>11</java.version>
    <spring-cloud.version>2020.0.1</spring-cloud.version>
</properties>
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-config-server</artifactId>
    </dependency>
</dependencies>
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

2. Add `@EnableConfigServer` to `public class ConfigServerApplication`


3.  Refer `application.properties`

```
spring.application.name=config-server
server.port=9191

spring.cloud.config.server.git.uri=file://${HOME}/workspace/config-repo

#Spring config server will load properties from a master by default
spring.cloud.config.server.git.default-label=trunk
spring.cloud.config.server.git.force-pull=true
```

### Run

`mvn spring-boot:run -DSkiptest`

### Test

`http http://localhost:9191/config-server/env | jq '.'`

`http http://localhost:9191/course-service/default | jq '.'`

This should return:

```json
{
  "name": "course-service",
  "profiles": [
    "default"
  ],
  "label": null,
  "version": "...",
  "state": null,
  "propertySources": [
    {
      "name": "file:///../workspace/config-repo/course-service.properties",
      "source": {
        "course-service.recommended-course-id": "2"
      }
    }
  ]
}
```