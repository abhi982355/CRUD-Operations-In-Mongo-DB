package com.mongodb;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.entity.Student;
import com.mongodb.service.StudentService;

import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/student")
public class StudentController {

	Logger logger = (Logger) LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;

	@PostMapping("/save")
	public ResponseEntity<Student> saveStudentData(@RequestBody Student student) {
		logger.info("saveStudentData() methods executions starts...");
		try {
			Student stu = this.studentService.saveStudentData(student);
			logger.info("saveStudentData() methods executions ends...");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(stu);
		} catch (Exception e) {
			logger.error(" Something Went Wrong.... saveStudentData() method  " +e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/getallstudents")
	public ResponseEntity<Iterable<Student>> getAllStudentData() {
		Iterable<Student> student = this.studentService.getAllStudentData();
		return ResponseEntity.status(HttpStatus.OK).body(student);
	}

	@GetMapping("/getstudentbyid/{id}")
	public ResponseEntity<Student> getStudentDataById(@PathVariable("id") Long id) {
		try {
			Student student = this.studentService.getStudentDataById(id);
			return ResponseEntity.status(HttpStatus.OK).body(student);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Student> updateStudentData(@PathVariable("id") Long id, @RequestBody Student updatedStudent) {
		Student student = this.studentService.updateStudentData(id, updatedStudent);
		if (student != null) {
			return ResponseEntity.ok(student);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteStudentById(@PathVariable("id") Long id) {
		this.studentService.deleteStudent(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Student Deleted and his id is : " + id);
	}
	
	// Query Methods......
	@GetMapping("/getstudentbyname/{name}")
	public ResponseEntity<Student> getStudentDataByName(@PathVariable("name") String name) {
		try {
			Student student = this.studentService.findStudentByName(name);
			return ResponseEntity.status(HttpStatus.OK).body(student);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
