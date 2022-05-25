package com.skilldistillery.plantdaddyapp.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name; 
	
	private String description; 
	

	@Column(name="image_url") 
	private String imageUrl;
}
