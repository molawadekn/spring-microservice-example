package com.app.seminar.studentService;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.app.seminar.studentService.controller.StudentServiceController;
import com.app.seminar.studentService.domain.Student;
import com.app.seminar.studentService.jpa.StudentRepository;

@SpringBootApplication
public class StudentServiceApplication {
	private static final Logger log = LogManager.getLogger(StudentServiceController.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(StudentRepository repository) {
		return (args) -> {
			// save a few Students
			Student std = new Student();
			std.setMobile("8888888888");
			std.setLocation("MAHARASHTRA");
			std.setName("Krishna");
			std.setCourseName("Java");
			std.setCreated(new Date());
			repository.save(std);

			std = new Student();
			std.setMobile("9888888888");
			std.setLocation("KARNATAKA");
			std.setName("Rama");
			std.setCourseName("Java");
			std.setCreated(new Date());
			repository.save(std);

			std = new Student();
			std.setMobile("9888888888");
			std.setLocation("KARNATAKA");
			std.setName("IOT");
			std.setCourseName("Java");
			std.setCreated(new Date());
			repository.save(std);

			std = new Student();
			std.setMobile("9888888888");
			std.setLocation("KARNATAKA");
			std.setName("Rama");
			std.setCourseName("Spring");
			std.setCreated(new Date());
			repository.save(std);

			std = new Student();
			std.setMobile("9888888888");
			std.setLocation("KARNATAKA");
			std.setName("Rama");
			std.setCourseName("Hibernet");
			std.setCreated(new Date());
			repository.save(std);

			// fetch all Students
			log.info("Students found with findAll():");
			log.info("-------------------------------");
			for (Student Student : repository.findAll()) {
				log.info(Student.toString());
			}

			// fetch an individual Student by ID
			Student Student = repository.findById(1L).get();
			log.info("Student found with findById(1L):");
			log.info("--------------------------------");
			log.info(Student.toString());
			log.info("");

			// fetch Students by last name
			log.info("Student found with findByLastName:");
			log.info("--------------------------------------------");
			repository.findByName("Krishna").forEach(s -> {
				log.info(s.toString());
			});
		};
	}
}
