package com.app.seminar.courseOfferService.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CourseHelper {

	
	public List<String> toList(String commanSeparatedCourses){
		List<String> courseList = new ArrayList<>();
		if(StringUtils.hasText(commanSeparatedCourses)) {
			courseList = Arrays.asList(commanSeparatedCourses.split(","));
		}
		return courseList;
	}
}
