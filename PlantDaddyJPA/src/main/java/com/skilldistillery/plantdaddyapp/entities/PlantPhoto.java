package com.skilldistillery.plantdaddyapp.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Table(name="plant_photo")
public class PlantPhoto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;


	@Column(name = "date_created")
	@CreationTimestamp
	private LocalDateTime createdAt;
	

	@Column(name="image_url") 
	private String imageUrl;
}
