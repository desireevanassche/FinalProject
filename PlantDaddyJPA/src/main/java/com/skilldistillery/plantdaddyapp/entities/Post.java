package com.skilldistillery.plantdaddyapp.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*+-------------+---------------+------+-----+---------+----------------+
| Field       | Type          | Null | Key | Default | Extra          |
+-------------+---------------+------+-----+---------+----------------+
|/ id          | int(11)       | NO   | PRI | NULL    | auto_increment |
|/ user_id     | int(11)       | NO   | MUL | NULL    |                |
| /topic_id    | int(11)       | NO   | MUL | NULL    |                |
|/ title       | varchar(500)  | NO   |     | NULL    |                |
| /content     | text          | YES  |     | NULL    |                |
| /image_url   | varchar(1000) | YES  |     | NULL    |                |
| /create_date | datetime      | YES  |     | NULL    |                |
| /update_date | datetime      | YES  |     | NULL    |                |
| /active      | tinyint(4)    | NO   |     | 1       |                |
+-------------+---------------+------+-----+---------+----------------+ */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;

	private String content;
	
	private boolean active;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDateTime createdDate;

	@Column(name = "update_date")
	@UpdateTimestamp
	private LocalDateTime updatedDate;



	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy="post")
	private List<Comment> comments;

	
	@ManyToOne
	@JoinColumn(name="topic_id")
	private Topic topic;
	
	@JsonIgnore
	@ManyToMany(mappedBy="posts")
	private List<Hashtag> hashtags;

	public Post() {
		super();
	}

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

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

 
	public boolean getActive() {
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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	public List<Hashtag> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<Hashtag> hashtags) {
		this.hashtags = hashtags;
	}
	
	
	public void addHashtag(Hashtag hashtag) {
		if (hashtags == null)
			hashtags = new ArrayList<>();

		if (!hashtags.contains(hashtag)) {
			hashtags.add(hashtag);
			hashtag.addPost(this);
		}
	}

	public void removeHashtag(Hashtag hashtag) {
		if (hashtags != null && hashtags.contains(hashtag)) {
			hashtags.remove(hashtag);
			hashtag.removePost(this);
		}
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
		Post other = (Post) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content + ", imageUrl=" + imageUrl
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", active=" + active + "]";
	}

}
