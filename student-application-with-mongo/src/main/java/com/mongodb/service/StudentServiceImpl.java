package com.mongodb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.entity.Student;
import com.mongodb.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public Iterable<Student> getAllStudentData() {
		return studentRepository.findAll();
	}

	@Override
	public Student getStudentDataById(Long id) {
		Optional<Student> studentIsExists = this.studentRepository.findById(id);
		if(studentIsExists.isPresent()) {
			Student s =  this.studentRepository.findById(id).get();
			return s;
		}
		return null;
	}


	@Override
	public Student saveStudentData(Student student) {
		return studentRepository.save(student);
	}
	

	@Override
	public Student updateStudentData(Long id, Student updateStudent) {
		Optional<Student> studentIsExists = this.studentRepository.findById(id);
		if(studentIsExists.isPresent()) {
			Student existingStudent = studentIsExists.get();
			existingStudent.setName(updateStudent.getName());
			existingStudent.setCollegeName(updateStudent.getCollegeName());
			existingStudent.setCity(updateStudent.getCity());
			
			return this.studentRepository.save(existingStudent);
		}
		return null;
	}

	@Override
	public void deleteStudent(Long id) {
		Optional<Student> studentIsExists = this.studentRepository.findById(id);
		if(studentIsExists.isPresent()) {
			this.studentRepository.deleteById(id);
		}		
	}

	// Query Methods..........
	@Override
	public Student findStudentByName(String name) {
		return studentRepository.findStudentByName(name);
	}

}
