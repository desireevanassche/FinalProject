package com.skilldistillery.plantdaddyapp.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "potting_mix")
public class PottingMix {

	/*
	 * +--------------+---------------+------+-----+---------+----------------+ |
	 * Field | Type | Null | Key | Default | Extra |
	 * +--------------+---------------+------+-----+---------+----------------+ | id
	 * | int(11) | NO | PRI | NULL | auto_increment | | user_id | int(11) | NO | MUL
	 * | NULL | | | brand | varchar(500) | YES | | NULL | | | name | varchar(500) |
	 * YES | | NULL | | | type | varchar(500) | YES | | NULL | | | quantity |
	 * int(11) | YES | | NULL | | | unit | varchar(100) | YES | | NULL | | |
	 * image_url | varchar(1000) | YES | | NULL | | | date_created | datetime | YES
	 * | | NULL | | | active | tinyint(4) | NO | | 1 | |
	 * +--------------+---------------+------+-----+---------+----------------+
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String brand;

	private String name;

	private String type;

	private Integer quantity;

	private String unit;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "date_created")
	private LocalDate dateCreated;

	private Boolean active;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@JsonIgnore
	@ManyToMany(mappedBy="pottingMixes")
	private List<Plant> plants;
	
	// -------------------CONSTRUCTORS --------------------------

	public PottingMix() {
		super();
	}

	// ------------------- METHODS -----------------------
	
	public void addPlant(Plant plant) {
		if (plants == null) { 
			plants = new ArrayList<>();
			if (!plants.contains(plant)) {
				plants.add(plant);
				plant.addPottingMix(this);
			}
		}
		
	}
	
	public void removePlant(Plant plant) {
		if(plants != null && plants.contains(plant)) {
			plants.remove(plant);
			plant.removePottingMix(this);
			 
		}
		
	}
	
	
	// -------------------GETTERS & SETTERS --------------------------
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	public List<Plant> getPlants() {
		return plants;
	}

	public void setPlants(List<Plant> plants) {
		this.plants = plants;
	}

	@Override
	public String toString() {
		return "PottingMix [id=" + id + ", brand=" + brand + ", name=" + name + ", type=" + type + ", quantity="
				+ quantity + ", unit=" + unit + ", imageUrl=" + imageUrl + ", dateCreated=" + dateCreated + ", active="
				+ active + "]";
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
		PottingMix other = (PottingMix) obj;
		return Objects.equals(id, other.id);
	}

}
