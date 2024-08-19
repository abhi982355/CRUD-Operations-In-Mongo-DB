package com.mongodb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "students")
@Data
public class Student {

	@Id
	private Long rollNo;
	private String name;
	private String collegeName;
	private String city;
	
}
