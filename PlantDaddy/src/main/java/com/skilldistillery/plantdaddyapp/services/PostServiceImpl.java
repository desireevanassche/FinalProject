package com.skilldistillery.plantdaddyapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.plantdaddyapp.entities.Post;
import com.skilldistillery.plantdaddyapp.entities.User;
import com.skilldistillery.plantdaddyapp.repositories.PostRepository;
import com.skilldistillery.plantdaddyapp.repositories.UserRepository;

@Service
public class PostServiceImpl implements PostService {

	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private UserRepository userRepo;
	  
	
	@Override
	public List<Post> indexAll(){
		return postRepo.findAll();
	}
	
	
	@Override
	public List<Post> indexByUsername(String username) {
		
		return postRepo.findByUser_Username(username);
		
	}

	
	@Override
	public Post findById(String username, int postId) {
		Optional<Post> postOP = postRepo.findById(postId);
		User user = userRepo.findByUsername(username);
		Post post = null;
		if(user != null) {
			if(postOP.isPresent()) {
				post = postOP.get();
				post.setUser(user);
			}
		}
		
		
		return post;
	}

	@Override
	public Post addPost(String username, Post post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post updatePost(String username, int postId, Post post) {
		
		Post existing = postRepo.findByUser_UsernameAndId(username, postId); 
	 
		if(existing != null) {
			existing.setContent(post.getContent());
			existing.setContent(post.getTitle());
			existing.setContent(post.getImageUrl());
			
		}
		
		
		return existing;
	}

	@Override
	public boolean deletePost(String username, int postId) {
		// TODO Auto-generated method stub
		return false;
	}



}
