package com.skilldistillery.plantdaddyapp.services;

import java.util.List;

import com.skilldistillery.plantdaddyapp.entities.Post;
import com.skilldistillery.plantdaddyapp.entities.User;

public interface PostService {

	public List<Post> indexAll();
	
	public List<Post> indexByUsername(String username);
	
	public Post findById(String username, int postId);

	public Post addPost(String username, Post post);
	
	public Post updatePost(String username, int postId, Post post);
	
	public boolean deletePost(String username, int postId);
	
	
}

