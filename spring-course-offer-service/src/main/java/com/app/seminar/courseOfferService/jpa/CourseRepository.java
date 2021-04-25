package com.app.seminar.courseOfferService.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.seminar.courseOfferService.domain.Course;
@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

  List<Course> findByNameIgnoreCase(String name);

  List<Course> findByNameAndLocationIgnoreCase(String courseName, String state);

  List<Course> findByLocationIgnoreCase(String state);
  
  @Query(value = "SELECT c FROM Course c WHERE upper(c.name) IN :names")
  List<Course> findCourseByNameList(@Param("names") List<String> names);
  
  @Query(value = "SELECT c FROM Course c WHERE upper(c.name) IN :names AND upper(c.location)= :location")
  List<Course> findCourseByNameListAndCourse(@Param("names") List<String> names, @Param("location") String location );
}