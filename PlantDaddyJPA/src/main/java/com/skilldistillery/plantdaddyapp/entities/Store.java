package com.skilldistillery.plantdaddyapp.entities;

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
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
 
	
	@Column(name="image_url")
	private String imageUrl;
	
	@OneToOne
	@JoinColumn(name="address_id")
	private Address address;
	
	@JsonIgnore
	@ManyToMany(mappedBy="stores")
	private List<Plant> plants;

	
	// -------------------CONSTRUCTORS --------------------------
	
	public Store() {
		super();
	}
	
	// ------------------- METHODS -----------------------
	
	public void addPlant(Plant plant) {
		
		if (plants == null) {
			plants = new ArrayList<>();
			if (!plants.contains(plant)) {
				plants.add(plant);
				plant.addStore(this);
			}
		}
	}
	
	
	public void removePlant(Plant plant) {
		
		if(plants != null && plants.contains(plant)) {
			plants.remove(plant);
			plant.removeStore(this);
			
		}
		
	}
	
	
	// -------------------GETTERS & SETTERS --------------------------

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public List<Plant> getPlants() {
		return plants;
	}

	public void setPlants(List<Plant> plants) {
		this.plants = plants;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", imageUrl=" + imageUrl + ", address=" + address + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, id, imageUrl);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Store other = (Store) obj;
		return Objects.equals(address, other.address) && Objects.equals(id, other.id)
				&& Objects.equals(imageUrl, other.imageUrl);
	}
	
	
	
}
