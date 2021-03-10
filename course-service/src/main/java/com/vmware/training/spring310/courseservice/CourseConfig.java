package com.vmware.training.spring310.courseservice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("course-service")
public class CourseConfig {

    private int recommendedCourseId;
}
