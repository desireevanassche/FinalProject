package com.skilldistillery.plantdaddyapp.entities;

import java.time.LocalDateTime;
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

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	private Integer id;
	
	private String content;  
	
	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDateTime createdAt;

	
	private boolean active;
	
	
	@ManyToOne
	
	@JoinColumn(name="post_id")
	private Post post;
	 
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="in_reply_to_id")
	private Comment comment;
	
	@JsonIgnoreProperties({"comment"})
	@OneToMany(mappedBy="comment")
	private List<Comment> comments;
	
		

	public Comment() {
		super();
	}

	public Integer getId() {
		return id;
	} 

	public void setId(Integer id) {
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
	
	
	

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
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

