package com.mongodb.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mongodb.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

	// Use to print by Name
	@Query(value = "{'name' : ?0}")
	Student findStudentByName(String name);

	/*
	 * Using fields value is 0 to ignored that feilds
	 * 
	 * @Query(value="{'name' : ?0}",fields="{'rollNo' :0 , 'city' :0}") Student
	 * findStudentByName(String name);
	 */

	/*
	 * Using fields value is 1 to print only that given feilds others are ignored
	 * 
	 * @Query(value="{'name' : ?0}",fields="{'city' :1}") Student
	 * findStudentByName(String name);
	 */

}
