package com.skilldistillery.plantdaddyapp.entities;

import java.time.LocalDateTime;
/* +---------------+---------------+------+-----+---------+----------------+
| Field         | Type          | Null | Key | Default | Extra          |
+---------------+---------------+------+-----+---------+----------------+
| id            | int(11)       | NO   | PRI | NULL    | auto_increment |
| user_plant_id | int(11)       | NO   | MUL | NULL    |                |
| image_url     | varchar(1000) | YES  |     | NULL    |                |
| date_created  | datetime      | YES  |     | NULL    |                |
+---------------+---------------+------+-----+---------+----------------+*/
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
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

	//user_plant_id

	public PlantPhoto() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
		PlantPhoto other = (PlantPhoto) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "PlantPhoto [id=" + id + ", createdAt=" + createdAt + ", imageUrl=" + imageUrl + "]";
	}
	
	 
	
	
	
	
	
}
