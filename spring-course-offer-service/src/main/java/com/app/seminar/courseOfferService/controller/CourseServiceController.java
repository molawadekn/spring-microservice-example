package com.app.seminar.courseOfferService.controller;

import java.util.ArrayList;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.seminar.courseOfferService.domain.Course;
import com.app.seminar.courseOfferService.domain.CourseHelper;
import com.app.seminar.courseOfferService.service.CourseService;

@RestController
public class CourseServiceController {

	private static final Logger log = LogManager.getLogger(CourseServiceController.class.getName());
//	@Autowired
//	StudentServiceDelegate studentServiceDelegate;
	@Autowired
	CourseService service;
	@Autowired
	CourseHelper helper;

	@GetMapping(value = "/offer")
	public List<Course> getOfferByCourseOrLocation(@RequestParam(name="courseName",required=false) String courseName, @RequestParam(name="location",required=false) String state) {
		log.info("calling service to get offers by course or location or both");
		List<Course> courseList=new ArrayList<>();
		
		if(StringUtils.hasText(courseName) && StringUtils.hasText(state)) {
			courseList = service.getOffers(helper.toList(courseName.toUpperCase()), state.toUpperCase());
		}else if(StringUtils.hasText(courseName)) {
			courseList = service.getOffersByCourseName(helper.toList(courseName.toUpperCase()));
		}else if(StringUtils.hasText(state)) {
			courseList = service.getOffersByState(state.toUpperCase());
		}
		log.info(courseList);
		return courseList ;
	}
	
	@GetMapping(value = "/course/all")
	public List<Course> getAll() {
		return service.getAllCourses();
	}
	
}
