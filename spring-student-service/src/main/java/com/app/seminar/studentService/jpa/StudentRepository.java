package com.app.seminar.studentService.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.seminar.studentService.domain.Student;
@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

  List<Student> findByNameIgnoreCase(String name);
  
  
  @Query("SELECT c FROM Student c WHERE c.created > :yesterday")
  List<Student> findAllCoursesFromTodaysRegistration(Date yesterday);
}