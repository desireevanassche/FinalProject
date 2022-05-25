package com.skilldistillery.plantdaddyapp.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String task;  
	
	@Column(name = "due_date")
	private String dueDate;

	@Column(name = "completion_date")
	private String completeDate;

	@Column(name = "date_created")
	@CreationTimestamp
	private LocalDateTime createdAt;
	

}
