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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

//+---------------------+---------------+------+-----+---------+----------------+
//| Field               | Type          | Null | Key | Default | Extra          |
//+---------------------+---------------+------+-----+---------+----------------+
//| id                  | int(11)       | NO   | PRI | NULL    | auto_increment |
//| user_id             | int(11)       | NO   | MUL | NULL    |                |
//| plant_id            | int(11)       | NO   | MUL | NULL    |                |
//| height_inches       | double        | YES  |     | NULL    |                |
//| spread_inches       | double        | YES  |     | NULL    |                |
//| nickname            | varchar(200)  | YES  |     | NULL    |                |
//| pot_diameter_inches | double        | YES  |     | NULL    |                |
//| image_url           | varchar(1000) | YES  |     | NULL    |                |
//| home_location       | varchar(500)  | YES  |     | NULL    |                |
//| description         | text          | YES  |     | NULL    |                |
//| active              | tinyint(4)    | NO   |     | 1       |                |
//+---------------------+---------------+------+-----+---------+----------------+

@Entity
@Table(name = "user_plant")
public class UserPlant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "height_inches")
	private double height;

	@Column(name = "spread_inches")
	private double spread;

	private String nickname;

	@Column(name = "pot_diameter_inches")
	private double potDiameter;

	@Column(name = "image_Url")
	private String imageUrl;

	@Column(name = "home_location")
	private String homeLocation;

	private String description;

	private boolean active;
	
	@Column(name="growth_description")
	private String growthDescription;

	@Column(name="growth_image")
	private String growthImage;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "plant_id")
	private Plant plant;

	@JsonIgnore
	@OneToMany(mappedBy = "userPlant")
	private List<Todo> todos;

	@OneToMany(mappedBy = "userPlant")
	@JsonIgnore
	private List<PlantPhoto> photos;
	
	@OneToMany(mappedBy= "userPlant")
	private List<PlantGrowth> growthData;

	public UserPlant() {
		super();
	}
	
	public void addPlantGrowth(PlantGrowth growth) {
		if(growthData == null) {
			growthData = new ArrayList<>();
			
		}
		
		if(!growthData.contains(growth)) {
			growthData.add(growth);
			growth.setUserPlant(this);
		}
	}
	
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getSpread() {
		return spread;
	}

	public void setSpread(double spread) {
		this.spread = spread;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public double getPotDiameter() {
		return potDiameter;
	}

	public void setPotDiameter(double potDiameter) {
		this.potDiameter = potDiameter;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getHomeLocation() {
		return homeLocation;
	}

	public void setHomeLocation(String homeLocation) {
		this.homeLocation = homeLocation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}
	
	
	

	public List<PlantGrowth> getGrowthData() {
		return growthData;
	}

	public void setGrowthData(List<PlantGrowth> growthData) {
		this.growthData = growthData;
	}



	public List<PlantPhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<PlantPhoto> photos) {
		this.photos = photos;
	}

	public List<Todo> getTodos() {
		return todos;
	}

	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}
	
	

	public String getGrowthDescription() {
		return growthDescription;
	}

	public void setGrowthDescription(String growthDescription) {
		this.growthDescription = growthDescription;
	}

	public String getGrowthImage() {
		return growthImage;
	}

	public void setGrowthImage(String growthImage) {
		this.growthImage = growthImage;
	}

	@Override
	public String toString() {
		return "UserPlant [id=" + id + ", height=" + height + ", spread=" + spread + ", nickname=" + nickname
				+ ", potDiameter=" + potDiameter + ", imageUrl=" + imageUrl + ", homeLocation=" + homeLocation
				+ ", description=" + description + ", active=" + active + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, description, growthData, growthDescription, growthImage, height, homeLocation, id,
				imageUrl, nickname, photos, plant, potDiameter, spread, todos, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserPlant other = (UserPlant) obj;
		return active == other.active && Objects.equals(description, other.description)
				&& Objects.equals(growthData, other.growthData)
				&& Objects.equals(growthDescription, other.growthDescription)
				&& Objects.equals(growthImage, other.growthImage)
				&& Double.doubleToLongBits(height) == Double.doubleToLongBits(other.height)
				&& Objects.equals(homeLocation, other.homeLocation) && id == other.id
				&& Objects.equals(imageUrl, other.imageUrl) && Objects.equals(nickname, other.nickname)
				&& Objects.equals(photos, other.photos) && Objects.equals(plant, other.plant)
				&& Double.doubleToLongBits(potDiameter) == Double.doubleToLongBits(other.potDiameter)
				&& Double.doubleToLongBits(spread) == Double.doubleToLongBits(other.spread)
				&& Objects.equals(todos, other.todos) && Objects.equals(user, other.user);
	}

}
