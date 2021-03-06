package com.vmware.training.spring310.courseservice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Course {

    private Integer id;
    private String name;
    private int duration;

}
