package com.skilldistillery.plantdaddyapp.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/* +-------------------+---------------+------+-----+---------+----------------+
| Field             | Type          | Null | Key | Default | Extra          |
+-------------------+---------------+------+-----+---------+----------------+
|/ id                | int(11)       | NO   | PRI | NULL    | auto_increment |
|/ created_by_id     | int(11)       | NO   | MUL | NULL    |                |
|/ common_name       | varchar(500)  | NO   |     | NULL    |                |
|/ description       | text          | YES  |     | NULL    |                |
|/ image_url         | varchar(1000) | YES  |     | NULL    |                |
|/ botanical_name    | varchar(500)  | YES  |     | NULL    |                |
|/ care_difficulty   | varchar(200)  | YES  |     | NULL    |                |
|/ water_cycle       | varchar(500)  | YES  |     | NULL    |                |
|/ water_type        | varchar(100)  | YES  |     | NULL    |                |
| /light_requirement | varchar(100)  | YES  |     | NULL    |                |
| /active            | tinyint(4)    | NO   |     | 1       |                |
+-------------------+---------------+------+-----+---------+----------------+ */ 

@Entity
public class Plant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// created_by_id  
	  
	
	// this is a test edit for merge
	
	
	@Column(name="common_name")
	private String commonName; 
	
	private String description;  
	
	@Column(name="image_url") 
	private String imageUrl;
	
	
	@Column(name="botanical_name")
	private String botanicalName; 
	 
	@Column(name="care_difficulty")
	private String careDifficulty; 
	
	
	@Column(name="water_cycle") 
	private String waterCycle; 
	
	@Column(name="water_type")
	private String waterType; 
	
	@Column(name="light_requirement") 
	private String lightReq; 
	
	//---------END FIELDS ---------------------------------- 
	
	

	
	private boolean active;

	public Plant() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getBotanicalName() {
		return botanicalName;
	}

	public void setBotanicalName(String botanicalName) {
		this.botanicalName = botanicalName;
	}

	public String getCareDifficulty() {
		return careDifficulty;
	}

	public void setCareDifficulty(String careDifficulty) {
		this.careDifficulty = careDifficulty;
	}

	public String getWaterCycle() {
		return waterCycle;
	}

	public void setWaterCycle(String waterCycle) {
		this.waterCycle = waterCycle;
	}

	public String getWaterType() {
		return waterType;
	}

	public void setWaterType(String waterType) {
		this.waterType = waterType;
	}

	public String getLightReq() {
		return lightReq;
	}

	public void setLightReq(String lightReq) {
		this.lightReq = lightReq;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plant other = (Plant) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Plant [id=" + id + ", commonName=" + commonName + ", description=" + description + ", imageUrl="
				+ imageUrl + ", botanicalName=" + botanicalName + ", careDifficulty=" + careDifficulty + ", waterCycle="
				+ waterCycle + ", waterType=" + waterType + ", lightReq=" + lightReq + ", active=" + active + "]";
	}  

	
	

}
