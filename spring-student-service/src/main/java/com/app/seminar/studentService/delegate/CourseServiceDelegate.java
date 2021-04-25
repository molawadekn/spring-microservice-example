package com.app.seminar.studentService.delegate;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class CourseServiceDelegate {
	@Autowired
	RestTemplate restTemplate;
	
	private static final Logger log = LogManager.getLogger(CourseServiceDelegate.class.getName());

	//@HystrixCommand(fallbackMethod = "callStudentServiceAndGetData_Fallback")
	public String callOfferServiceAndGetData(String location, String courseName) {
		log.info("Getting Offers details for " + courseName +" in " + location);
		String response = restTemplate
				.exchange("http://localhost:9090/offer?location={location}&courseName={courseName}"
				, HttpMethod.GET
				, null
				, new ParameterizedTypeReference<String>() {
			}, location, courseName).getBody();

		log.info("Response Received as " + response + " -  " + new Date());

		return response;
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
