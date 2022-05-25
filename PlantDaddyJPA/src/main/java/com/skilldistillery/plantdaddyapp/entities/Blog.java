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

/* +-------------+---------------+------+-----+---------+----------------+
| Field       | Type          | Null | Key | Default | Extra          |
+-------------+---------------+------+-----+---------+----------------+
| /id          | int(11)       | NO   | PRI | NULL    | auto_increment |
| /user_id     | int(11)       | NO   | MUL | NULL    |                |
| /title       | varchar(100)  | YES  |     | NULL    |                |
| /content     | text          | YES  |     | NULL    |                |
| /image_url   | varchar(1000) | YES  |     | NULL    |                |
| /create_date | datetime      | YES  |     | NULL    |                |
| /update_date | datetime      | YES  |     | NULL    |                |
| /active      | tinyint(4)    | NO   |     | 1       |                |
+-------------+---------------+------+-----+---------+----------------+ */
@Entity
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title; 
	
	private String content;  
	
	
	@Column(name="image_url") 
	private String imageUrl;
	
	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name = "update_date")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	private boolean active;
	
	//user_id 
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, content, createdAt, id, imageUrl, title, updatedAt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blog other = (Blog) obj;
		return active == other.active && Objects.equals(content, other.content)
				&& Objects.equals(createdAt, other.createdAt) && id == other.id
				&& Objects.equals(imageUrl, other.imageUrl) && Objects.equals(title, other.title)
				&& Objects.equals(updatedAt, other.updatedAt);
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", content=" + content + ", imageUrl=" + imageUrl
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", active=" + active + "]";
	} 
	
	
	
	
	
}
