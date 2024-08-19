package com.mongodb.service;

import com.mongodb.entity.Student;

public interface StudentService {

	Iterable<Student> getAllStudentData();

	Student saveStudentData(Student student);

	Student updateStudentData(Long id, Student updateStudent);

	void deleteStudent(Long id);
	
	Student getStudentDataById(Long id);
	
	Student findStudentByName(String name);
}
