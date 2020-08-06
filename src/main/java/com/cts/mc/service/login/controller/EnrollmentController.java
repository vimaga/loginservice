package com.cts.mc.service.login.controller;


import com.cts.mc.service.login.model.EmployeeInfo;
import com.cts.mc.service.login.servicedelegate.EnrollmentDelegateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/lms/v1")
public class EnrollmentController {

	private static final Logger logger = LoggerFactory.getLogger(EnrollmentController.class);

	@Autowired
	EnrollmentDelegateService enrollmentDelegateService;

	@GetMapping({ "/employee/{name}" })
	public EmployeeInfo getEmployeeAndCourseInfo(@PathVariable(name = "name") String name) {

		logger.info("Request name{}",name);
		EmployeeInfo response = enrollmentDelegateService.getEmployeeAndCourseInfo(name);

		return response;
	}

	@PostMapping("/enroll/course/creation")
	public String addEmployeeAndCourseDetails(@RequestBody EmployeeInfo employeeInfo)
	{

		logger.info("Request name{}",employeeInfo.getEmployeeName());

		String response= enrollmentDelegateService.addEmployeeAndCourseDetails(employeeInfo);

		return response;

	}

	@PutMapping("/enroll/course/update")
	public String updateCourseStatusByEmployee(@RequestBody EmployeeInfo employeeInfo)
	{

		logger.info("Request name{}",employeeInfo.getEmployeeName());

		enrollmentDelegateService.updateCourseStatusByEmployee(employeeInfo);

		return "Updated course data";

	}

	@GetMapping({ "/retrieve/completed/course/{name}" })
	public EmployeeInfo retriveCompletedCourseByEmployee(@PathVariable(name = "name") String name) {

		logger.info("Request name{}",name);
		EmployeeInfo response = enrollmentDelegateService.retriveCompletedCourseByEmployee(name);

		return response;
	}


}