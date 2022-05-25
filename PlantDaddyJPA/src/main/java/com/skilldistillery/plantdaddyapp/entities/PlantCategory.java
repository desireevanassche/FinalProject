package com.skilldistillery.plantdaddyapp.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/*
 * +-------------+---------------+------+-----+---------+----------------+
| Field       | Type          | Null | Key | Default | Extra          |
+-------------+---------------+------+-----+---------+----------------+
| id          | int(11)       | NO   | PRI | NULL    | auto_increment |
| name        | varchar(100)  | YES  |     | NULL    |                |
| description | varchar(500)  | YES  |     | NULL    |                |
| image_url   | varchar(1000) | YES  |     | NULL    |                |
+-------------+---------------+------+-----+---------+----------------+

 */

@Entity(name="plant_category")
public class PlantCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String description;

	@Column(name = "image_url")
	private String imageUrl;
	
	@ManyToMany(mappedBy="plantCategories")
	private List<Plant> plants;
	
	
	
	// -------------------CONSTRUCTORS --------------------------

	public PlantCategory() {
		super();
		
	}
	
	// ------------------- METHODS -----------------------

	public void addPlants(Plant plant) {
		if (plants == null) {
			plants = new ArrayList<>();
			if (!plants.contains(plant)) {
				plants.add(plant);
				plant.addPlantCategory(this);
				
				
			}
		}
	}
	
	public void removePlants(Plant plant) {
		
		if(plants != null && plants.contains(plant)) {
			plants.remove(plant);
			plant.removePlantCategory(this);
			
		
			
		}
		
		
	}
	
	
	// ------------------- GETTERS & SETTERS --------------------------
	
	

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	
	
	public List<Plant> getPlants() {
		return plants;
	}

	public void setPlants(List<Plant> plants) {
		this.plants = plants;
	}



	@Override
	public String toString() {
		return "plantCategory [id=" + id + ", name=" + name + ", description=" + description + ", imageUrl=" + imageUrl
				+ "]";
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
		PlantCategory other = (PlantCategory) obj;
		return id == other.id;
	}

}
