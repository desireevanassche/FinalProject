package com.skilldistillery.plantdaddyapp.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Hashtag {

	// -------------------FIELDS------------------------

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "hashtag_has_blog", joinColumns = @JoinColumn(name = "hasttag_id"), inverseJoinColumns = @JoinColumn(name = "blog_id"))
	private List<Blog> blogs;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "hashtag_has_topic", joinColumns = @JoinColumn(name = "hashtag_id"), inverseJoinColumns = @JoinColumn(name = "topic_id"))
	private List<Topic> topics;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "hashtag_has_post", joinColumns = @JoinColumn(name = "hashtag_id"), inverseJoinColumns = @JoinColumn(name = "post_id"))
	private List<Post> posts;

	// -------------NO ARG CONSTRUCTOR--------------------

	public Hashtag() {
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

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	// -----------ADD & REMOVE METHODS FOR MANY TO MANY RELATIONSHIP ON
	// BLOG-------------------

	public void addBlog(Blog blog) {
		if (blogs == null)
			blogs = new ArrayList<>();

		if (!blogs.contains(blog)) {
			blogs.add(blog);
			blog.addHashtag(this);
		}
	}

	public void removeBlog(Blog blog) {
		if (blogs != null && blogs.contains(blog)) {
			blogs.remove(blog);
			blog.removeHashtag(this);
		}
	}
	// -----------ADD & REMOVE METHODS FOR MANY TO MANY RELATIONSHIP ON
	// TOPIC-------------------

	public void addTopic(Topic topic) {
		if (topics == null)
			topics = new ArrayList<>();

		if (!topics.contains(topic)) {
			topics.add(topic);
			topic.addHashtag(this);
		}
	}

	public void removeTopic(Topic topic) {
		if (topics != null && topics.contains(topic)) {
			topics.remove(topic);
			topic.removeHashtag(this);
		}
	}

	// -----------------ADD
	public void addPost(Post post) {
		if (posts == null)
			posts = new ArrayList<>();

		if (!posts.contains(post)) {
			posts.add(post);
			post.addHashtag(this);
		}
	}

	public void removePost(Post post) {
		if (posts != null && topics.contains(post)) {
			posts.remove(post);
			post.removeHashtag(this);
		}
	}

	// --------------TO STRING----------------------------
	@Override
	public String toString() {
		return "Hashtag [id=" + id + ", name=" + name + "]";
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
		Hashtag other = (Hashtag) obj;
		return Objects.equals(id, other.id);
	}

}
