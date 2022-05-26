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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private Integer id;
	
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
	
	private boolean active;
	
	@ManyToOne
	@JoinColumn(name="created_by_id")
	private User user; 
	
	@JsonIgnore
	@OneToMany(mappedBy="plant")
	private List<UserPlant> plants;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="plant_has_plant_category",
	joinColumns = @JoinColumn(name="plant_id"),
	inverseJoinColumns = @JoinColumn(name="plant_category_id"))
	private List<PlantCategory> plantCategories;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="plant_has_potting_mix",
	joinColumns = @JoinColumn(name="plant_id"),
	inverseJoinColumns = @JoinColumn(name="potting_mix_id"))
	private List<PottingMix> pottingMixes;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="store_has_plant",
	joinColumns = @JoinColumn(name="plant_id"),
	inverseJoinColumns = @JoinColumn(name="store_id"))
	private List<Store> stores;
	
	
	
	//---------END FIELDS ---------------------------------- 
	
	

	
	// -------------------CONSTRUCTORS --------------------------

	public Plant() {
		super();
	}

	
	// ------------------- METHODS -----------------------
	
	public void addStore(Store store) {
		if (stores == null) {
			stores = new ArrayList<>();
			if (!stores.contains(store)) {
				stores.add(store);
				store.addPlant(this);
			}
		}
		
	}
	
	 
	public void removeStore(Store store) {
		
		if(stores != null && stores.contains(store)) {
			stores.remove(store);
			store.removePlant(this);
			
		}
	}
	
	public void addPottingMix(PottingMix pottingMix) {
		if (pottingMixes == null) {
			pottingMixes = new ArrayList<>();
			if (!pottingMixes.contains(pottingMix)) {
				pottingMixes.add(pottingMix);
				pottingMix.addPlant(this);
			}
		}
		
	}
	
	 
	public void removePottingMix(PottingMix pottingMix) {
		
		if(pottingMixes != null && pottingMixes.contains(pottingMix)) {
			pottingMixes.remove(pottingMix);
			pottingMix.removePlant(this);
			
		}
	}
	
	public void addPlantCategory(PlantCategory category) {
		if (plantCategories == null) {
			plantCategories = new ArrayList<>();
			if (!plantCategories.contains(category)) {
				plantCategories.add(category);
				category.addPlants(this);
			}
		}
		
		
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public void removePlantCategory(PlantCategory category) {
		
		if(plantCategories != null && plants.contains(category)) {
			plantCategories.remove(category);
			category.removePlants(this);
			
		}
		
	}
	
	
	// -------------------GETTERS & SETTERS --------------------------
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public List<Store> getStores() {
		return stores;
	}


	public void setStores(List<Store> stores) {
		this.stores = stores;
	}


	public List<PottingMix> getPottingMixes() {
		return pottingMixes;
	}


	public void setPottingMixes(List<PottingMix> pottingMixes) {
		this.pottingMixes = pottingMixes;
	}


	public List<UserPlant> getPlants() {
		return plants;
	}

	public void setPlants(List<UserPlant> plants) {
		this.plants = plants;
	}
	
	
	

	public List<PlantCategory> getPlantCategories() {
		return plantCategories;
	}


	public void setPlantCategories(List<PlantCategory> plantCategories) {
		this.plantCategories = plantCategories;
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
