# course-service: 

A sample SpringBoot project integrated with Spring Cloud Config Server and Eureka server (a service registry)

### Pre-requisites

* Java 11
* Maven
* Docker
* Docker compose

### What's new?

Change to deploy to Kubernetes

1. change `application.properties` to load configuration from volume mount within the pod.

```
spring.config.import=optional:file:/deployments/config/course-service.properties
```

Note: SpringBoot 2.4.x enables this new property `spring.config.import`
