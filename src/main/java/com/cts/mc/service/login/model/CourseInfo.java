package com.cts.mc.service.login.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CourseInfo {

    private String courseId;

    private String title;

    private String duration;

    private boolean mockTest;

    private String mockTestScore;

    private String enrollmentStatus;




}
