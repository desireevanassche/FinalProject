package com.skilldistillery.plantdaddyapp.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Plant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String description;  
	
	@Column(name="image_url") 
	private String imageUrl;
	
	
	@Column(name="common_name")
	private String commonName; 
	
	@Column(name="botanical_name")
	private String botanicalName; 
	 
	private String type;  
	
	@Column(name="care_difficulty")
	private String careDifficulty; 
	
	@Column(name="is_outdoor") 
	private boolean isOutdoor; 
	
	@Column(name="is_indoor") 
	private boolean isIndoor; 
	
	@Column(name="is_toxic") 
	private boolean isToxic; 
	
	@Column(name="water_cycle") 
	private String waterCycle; 
	
	@Column(name="water_type")
	private String waterType; 
	
	@Column(name="light_requirement") 
	private String lightReq; 
	
	private boolean active;  


}
