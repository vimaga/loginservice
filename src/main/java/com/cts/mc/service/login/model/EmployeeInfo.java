package com.cts.mc.service.login.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class EmployeeInfo {

    private String employeeId;
    private String employeeName;
    private String employeePwd;
    private String email;
    private String employeeType;
    private String address;
    private String city;
    private String state;
    private String country;
    private String createdAt;

    private Set<CourseInfo> coursesInfo=new HashSet<>();

}
