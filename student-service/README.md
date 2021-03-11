# student-service: 

A sample SpringBoot project which uses REST template to communicate with other app

### Pre-requisites

* Java 11
* Maven

### What's new?

Refer `com.vmware.training.spring310.studentservice.StudentServiceApplication.java` and `getStudentCourses` method in `StudentController.java` for RESTTemplate implementation.

### Run

`mvn spring-boot:run -DSkiptest`

- Run with test profile

`mvn spring-boot:run -DSkiptest -Dspring-boot.run.profiles=test`

### Test

`http http://localhost:8182/students`

`http http://localhost:8182/students/1/courses`
