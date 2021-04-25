package com.app.seminar.studentService.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.seminar.studentService.domain.Student;
import com.app.seminar.studentService.service.StudentService;

@RestController
public class StudentServiceController {

	private static final Logger log = LogManager.getLogger(StudentServiceController.class.getName());
	@Autowired
	StudentService service;

	@GetMapping(value = "/student/{name}")
	public List<Student> getStudents(@PathVariable String name) {
		log.info("Getting Student details for name" + name);
		List<Student> studentList = null;
		try {
			long id = Long.parseLong(name);
			studentList = Arrays.asList(service.getStudentById(id));

		} catch (NumberFormatException e) {
			studentList = service.getStudentByName(name);
		}
		if (studentList == null) {
			log.info("Student not found");
		}
		return studentList;
	}

	@RequestMapping(value = "/student", method = RequestMethod.POST)
	@PostMapping(value = "/student", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Student> postBody(@RequestBody Student stu) {
		Student persistedStudent = service.saveStudent(stu);
		return ResponseEntity.created(URI.create(String.format("/student/%s", persistedStudent.getName())))
				.body(persistedStudent);
	}

	@GetMapping(value = "/student/offerOfTheDay")
	public String getOffersOfTheDay(@RequestParam(name = "courseName", required = true) String courseNames,
			@RequestParam(name = "location", required = true) String state) {
		log.info("Getting offerOfTheDay");
		String response = null;
		if (StringUtils.hasText(courseNames) && StringUtils.hasText(state)) {
			response = service.getOffers(courseNames, state);
		}
		return response;
	}
}
