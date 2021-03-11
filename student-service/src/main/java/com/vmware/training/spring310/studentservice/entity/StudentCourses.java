package com.vmware.training.spring310.studentservice.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StudentCourses {

    private Integer studentId;
    private List<Course> courses;
}
