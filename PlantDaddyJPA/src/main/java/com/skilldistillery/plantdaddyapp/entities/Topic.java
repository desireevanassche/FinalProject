package com.skilldistillery.plantdaddyapp.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
/* +-------------+---------------+------+-----+---------+----------------+
| Field       | Type          | Null | Key | Default | Extra          |
+-------------+---------------+------+-----+---------+----------------+
| id          | int(11)       | NO   | PRI | NULL    | auto_increment |
| name        | varchar(100)  | NO   |     | NULL    |                |
| description | text          | YES  |     | NULL    |                |
| image_url   | varchar(1000) | YES  |     | NULL    |                |
+-------------+---------------+------+-----+---------+----------------+ */
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Topic {

	// ----------------FIELDS-------------------------

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String description;

	@Column(name = "image_url")
	private String imageUrl;

	@OneToMany(mappedBy = "topic")
	private List<Post> post;

	@JsonIgnore
	@ManyToMany(mappedBy = "topics")
	private List<Hashtag> hashtags;

	// -------------NO ARG CONSTRUCTOR--------------------

	public Topic() {
		super();
	}

	// ------------GETTERS & SETTERS-----------------------

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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	public List<Hashtag> getHashtag() {
		return hashtags;
	}

	public void setHashtag(List<Hashtag> hashtags) {
		this.hashtags = hashtags;
	}

	// -----------ADD & REMOVE METHODS FOR MANY TO MANY RELATIONSHIP ON
	// HASHTAG-------------------

	public void addHashtag(Hashtag hashtag) {
		if (hashtags == null)
			hashtags = new ArrayList<>();

		if (!hashtags.contains(hashtag)) {
			hashtags.add(hashtag);
			hashtag.addTopic(this);
		}
	}

	public void removeHashtag(Hashtag hashtag) {
		if (hashtags != null && hashtags.contains(hashtag)) {
			hashtags.remove(hashtag);
			hashtag.removeTopic(this);
		}
	}
	// -------------------HASHCODE & EQUALS----------------

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
		Topic other = (Topic) obj;
		return id == other.id;
	}

	// --------------TO STRING----------------------------

	@Override
	public String toString() {
		return "Topic [id=" + id + ", name=" + name + ", description=" + description + ", imageUrl=" + imageUrl + "]";
	}

}
