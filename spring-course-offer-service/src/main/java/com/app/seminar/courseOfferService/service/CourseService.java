package com.app.seminar.courseOfferService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.seminar.courseOfferService.domain.Course;
import com.app.seminar.courseOfferService.jpa.CourseRepository;

@Service
public class CourseService {

	@Autowired
	CourseRepository repository;

	public List<Course> getStudentByName(String name) {
		return repository.findByNameIgnoreCase(name);
	}

	public Course getStudentById(Long id) {
		return repository.findById(id).get();
	}

	public List<Course> getAllCourses() {
		List<Course> courses = new ArrayList<>();
		repository.findAll().forEach(it -> courses.add(it));
		return courses;
	}

	public List<Course> getOffers(List<String> list, String state) {
		return repository.findCourseByNameListAndCourse(list, state);

	}

	public List<Course> getOffersByCourseName(List<String> list) {
		return repository.findCourseByNameList(list);
	}

	public List<Course> getOffersByState(String state) {
		return repository.findByLocationIgnoreCase(state);

	}

}