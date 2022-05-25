package com.skilldistillery.plantdaddyapp.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/* +----------------+--------------+------+-----+---------+----------------+
| Field           | Type         | Null | Key | Default | Extra          |
+----------------+--------------+------+-----+---------+----------------+
| /id             | int(11)      | NO   | PRI | NULL    | auto_increment |
| /post_id        | int(11)      | NO   | MUL | NULL    |                |
| /in_reply_to_id | int(11)      | YES  | MUL | NULL    |                |
| /content        | varchar(500) | NO   |     | NULL    |                |
| create_date     | datetime     | YES  |     | NULL    |                |
| active          | tinyint(4)   | NO   |     | 1       |                |
+---------------- +--------------+------+-----+---------+----------------+ */ 

@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String content;  
	
	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDateTime createdAt;

	
	private boolean active;
	
	// post_id   
	// in_reply_to_id
		

	public Comment() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
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
		Comment other = (Comment) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", createdAt=" + createdAt + ", active=" + active + "]";
	}


	
	
}

