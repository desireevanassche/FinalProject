package com.skilldistillery.plantdaddyapp.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
/* +-----------------+--------------+------+-----+---------+----------------+
| Field           | Type         | Null | Key | Default | Extra          |
+-----------------+--------------+------+-----+---------+----------------+
| /id              | int(11)      | NO   | PRI | NULL    | auto_increment |
| user_plant_id   | int(11)      | NO   | MUL | NULL    |                |
| /task            | varchar(500) | YES  |     | NULL    |                |
| /date_created    | datetime     | YES  |     | NULL    |                |
| /due_date        | date         | YES  |     | NULL    |                |
| /completion_date | datetime     | YES  |     | NULL    |                |
+-----------------+--------------+------+-----+---------+----------------+ */
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;  
	
	private String description; 
	
	@Column(name = "due_date")
	private LocalDate dueDate;

	@Column(name = "completion_date")
	private String completeDate;

	@Column(name = "date_created")
	@CreationTimestamp
	private LocalDateTime createdAt;

	
	@ManyToOne
	@JoinColumn(name="user_plant_id")
	private UserPlant userPlant;
	
	
	
	
	public Todo() {
		super();
	}

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

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public String getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
	

	public UserPlant getUserPlant() {
		return userPlant;
	}

	public void setUserPlant(UserPlant userPlant) {
		this.userPlant = userPlant;
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
		Todo other = (Todo) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", name=" + name + ", description=" + description + ", dueDate=" + dueDate
				+ ", completeDate=" + completeDate + ", createdAt=" + createdAt + "]";
	}

	


	
	

}
