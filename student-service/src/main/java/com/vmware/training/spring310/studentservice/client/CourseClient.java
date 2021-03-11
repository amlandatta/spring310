package com.vmware.training.spring310.studentservice.client;

import com.vmware.training.spring310.studentservice.entity.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="courseservices", url = "localhost:8181")
@RequestMapping("/courses")
public interface CourseClient {

    @GetMapping("/{id}")
    Course getCourse(@PathVariable("id") Integer id);
}
