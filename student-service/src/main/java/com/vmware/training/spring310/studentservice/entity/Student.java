package com.vmware.training.spring310.studentservice.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Student {

    private Integer studentId;
    private String name;
    private String email;
}

