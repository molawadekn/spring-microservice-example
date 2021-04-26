package com.app.seminar.studentService.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.app.seminar.studentService.delegate.CourseServiceDelegate;
import com.app.seminar.studentService.domain.Student;
import com.app.seminar.studentService.jpa.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository repository;

	@Autowired
	CourseServiceDelegate delegator;

	public Student saveStudent(Student s) {
		return repository.save(s);
	}

	public List<Student> getStudentByName(String name) {
		return repository.findByNameIgnoreCase(name);
	}

	public Student getStudentById(Long id) {
		return repository.findById(id).get();
	}

	public String getOffers(String courseNames, String state) {
		// get all courses from student data which got registered today
		LocalDate yesterday = LocalDate.now().minusDays(1L);
		List<Student> registrations = repository.findAllCoursesFromTodaysRegistration(
				Date.from(yesterday.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		String commaSeparatedCourses = "";
		if (StringUtils.hasText(courseNames)) {
			// filter registration based on requested courseNames
			commaSeparatedCourses = registrations.stream()
					.filter(s -> courseNames.contains(s.getCourseName()))
					.map(Student::getCourseName)
					.distinct().collect(Collectors.joining(","));

		} else {
			// take all courses which registered today
			commaSeparatedCourses = registrations
					.stream()
					.map(Student::getCourseName)
					.distinct()
					.collect(Collectors.joining(","));

		}

		// now we just considered courses for todays registrations in
		// commaSeparatedCourses
		// and for those courses we will fetch offers
		return delegator.callOfferServiceAndGetData(state, commaSeparatedCourses);
	}

}
