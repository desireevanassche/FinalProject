package com.skilldistillery.plantdaddyapp.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/*+------------+---------------+------+-----+---------+----------------+
| Field      | Type          | Null | Key | Default | Extra          |
+------------+---------------+------+-----+---------+----------------+
| /id         | int(11)       | NO   | PRI | NULL    | auto_increment |
| /address_id | int(11)       | YES  | MUL | NULL    |                |
|/ username   | varchar(100)  | NO   | UNI | NULL    |                |
|/ password   | varchar(200)  | NO   |     | NULL    |                |
|/ enabled    | tinyint(4)    | NO   |     | 1       |                |
|/ first_name | varchar(100)  | YES  |     | NULL    |                |
| /last_name  | varchar(100)  | YES  |     | NULL    |                |
| /email      | varchar(100)  | YES  |     | NULL    |                |
| /image_url  | varchar(1000) | YES  |     | NULL    |                |
| /biography  | text          | YES  |     | NULL    |                |
|/ role       | varchar(100)  | YES  |     | NULL    |                |
+------------+---------------+------+-----+---------+----------------+ */ 

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String username;

	private String password;
	
	private boolean enabled;
	
	@Column(name="first_name ")
	private String firstName ;
	
	@Column(name="last_name ")
	private String lastName ;

	private String email;  
	
	@Column(name="image_url") 
	private String imageUrl; 
	
	private String biography;  

	private String role;
	
	@OneToMany(mappedBy="user")
	private List<Post> posts;
	
	@OneToOne
	@JoinColumn(name="address_id")
	private Address address;
		
	
	@OneToMany(mappedBy="user")
	private List<Blog> blogs;
	
	@OneToMany(mappedBy="user")
	private List<Plant> plants; 
	

	@OneToMany(mappedBy="user")
	private List<UserPlant> userPlants; 
	

	@OneToMany(mappedBy = "user")
	private List<PottingMix> pottingMix;
	
	@OneToMany
	@JoinTable(name="friend", 
	joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="friend_id"))
	private List<User> friends;

	
	
	
	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	
	

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	public List<Plant> getPlants() {
		return plants;
	}

	public void setPlants(List<Plant> plants) {
		this.plants = plants;
	}
	
	public List<PottingMix> getPottingMix() {
		return pottingMix;
	}

	public void setPottingMix(List<PottingMix> pottingMix) {
		this.pottingMix = pottingMix;
	}

	public List<UserPlant> getUserPlants() {
		return userPlants;
	}

	public void setUserPlants(List<UserPlant> userPlants) {
		this.userPlants = userPlants;
	}
	
	

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", imageUrl=" + imageUrl
				+ ", biography=" + biography + ", role=" + role + "]";
	}

}
