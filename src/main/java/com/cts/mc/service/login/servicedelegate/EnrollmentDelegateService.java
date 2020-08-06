package com.cts.mc.service.login.servicedelegate;


import com.cts.mc.service.login.model.EmployeeInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Date;

import static com.cts.mc.service.login.constant.DBConstant.*;


@Service
public class EnrollmentDelegateService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "callEmployeeServiceAndGetData_Fallback")
    public EmployeeInfo getEmployeeAndCourseInfo(String name) {


        EmployeeInfo response = restTemplate.getForObject(
                GET_EMPLOYEE_INFO_URL,
                EmployeeInfo.class,name);

        return response;
    }

    @HystrixCommand(fallbackMethod = "callEmployeeServiceAndPostAndPutData_Fallback")
    public String addEmployeeAndCourseDetails(EmployeeInfo employeeInfo)
    {


        String response=restTemplate.postForObject(
                ADD_EMPLOYEE_COURSE_URL,
                employeeInfo,
                String.class);

        return response;

    }

    @HystrixCommand(fallbackMethod = "callEmployeeServiceAndPostAndPutData_Fallback")
    public void updateCourseStatusByEmployee(EmployeeInfo employeeInfo)
    {

        restTemplate.put(
                UPDATE_EMPLOYEE_COURSE_STATUS_URL,
                employeeInfo,
                String.class);

    }

    @HystrixCommand(fallbackMethod = "callEmployeeServiceAndGetData_Fallback")
    public EmployeeInfo retriveCompletedCourseByEmployee(String name) {


        EmployeeInfo response = restTemplate.getForObject(
                GET_COMPLETED_COURSE_INFO_URL,
                EmployeeInfo.class,name);

        return response;
    }


    @SuppressWarnings("unused")
    private EmployeeInfo callEmployeeServiceAndGetData_Fallback(String name) {

        EmployeeInfo employeeInfo=new EmployeeInfo();
        System.out.println("Employee Service is down!!! fallback route enabled...");
        employeeInfo.setEmployeeName("CIRCUIT BREAKER ENABLED!!! No Response From Employee Service at this moment for user "+name);
        return employeeInfo;
    }

    @SuppressWarnings("unused")
    private String callEmployeeServiceAndPostAndPutData_Fallback(String name) {


        System.out.println("Student Service is down!!! fallback route enabled...");

        return "CIRCUIT BREAKER ENABLED!!! No Response From Student Service at this moment. " +
                " Service will be back shortly - " + new Date();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
