package com.skilldistillery.plantdaddyapp.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title; 
	
	private String content;  
	
	@Column(name="image_url") 
	private String imageUrl;
	 
	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name = "update_date")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	
	private boolean active; 
}
