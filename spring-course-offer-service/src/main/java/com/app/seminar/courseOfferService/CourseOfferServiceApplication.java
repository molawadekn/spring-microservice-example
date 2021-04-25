package com.app.seminar.courseOfferService;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.app.seminar.courseOfferService.domain.Course;
import com.app.seminar.courseOfferService.jpa.CourseRepository;




@SpringBootApplication
//@EnableHystrixDashboard
//@EnableCircuitBreaker
public class CourseOfferServiceApplication {
	private static final Logger log = LogManager.getLogger(CourseOfferServiceApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(CourseOfferServiceApplication.class, args);
	}
	
	@Bean
	  public CommandLineRunner demo(CourseRepository repository) {
	    return (args) -> {
	      // save a few Students
	    	Course c = new Course("Maharashtra","Java",BigDecimal.valueOf(10000),10.0f);
	    	repository.save(c);

			c = new Course("Karnataka", "Java", BigDecimal.valueOf(10000), 15.0f);
			repository.save(c);
			c = new Course("Maharashtra", "Spring", BigDecimal.valueOf(12000), 10.0f);
			repository.save(c);
			c = new Course("Karnataka", "Spring", BigDecimal.valueOf(12000), 15.0f);
			repository.save(c);
			c = new Course("Maharashtra", "IOT", BigDecimal.valueOf(8000), 2.0f);
			repository.save(c);
			c = new Course("Karnataka", "IOT", BigDecimal.valueOf(8000), 2.0f);
			repository.save(c);
			c = new Course("Maharashtra", "Hibernet", BigDecimal.valueOf(8500), 4.0f);
			repository.save(c);
			c = new Course("Karnataka", "Hibernet", BigDecimal.valueOf(8500), 3.5f);
			repository.save(c);
			c = new Course("Gujarat", "Java", BigDecimal.valueOf(10000), 0.0f);
			repository.save(c);
			c = new Course("Gujarat", "IOT", BigDecimal.valueOf(8000), 0.0f);
			repository.save(c);
			c = new Course("Gujarat", "Spring", BigDecimal.valueOf(12000), 0.0f);
			repository.save(c);
			c = new Course("Gujarat", "Hibernet", BigDecimal.valueOf(8500), 0.0f);
			repository.save(c);


	      // fetch all Courses
	      log.info("Course found with findAll():");
	      log.info("-------------------------------");
	      for (Course c1 : repository.findAll()) {
	        log.info(c1.toString());
	      }
	      log.info("");

	      // fetch an individual Student by ID
	      Course c2 = repository.findById(1L).get();
	      log.info("Course found with findById(1L):");
	      log.info("--------------------------------");
	      log.info(c2.toString());
	      log.info("");

	      // fetch Students by last name
	      log.info("Student found with findByLastName('Bauer'):");
	      log.info("--------------------------------------------");
	      repository.findByNameIgnoreCase("JAVA").forEach(s -> {
	        log.info(s.toString());
	      });
	      // for (Student bauer : repository.findByLastName("Bauer")) {
	      //  log.info(bauer.toString());
	      // }
	      log.info("");
	    };
	  }
}
