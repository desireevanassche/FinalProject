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
		User user = userRepo.findByUsername(username);
		if(user !=null) {
			post.setUser(user);
		
		}
		
		return postRepo.saveAndFlush(post);
	}

	@Override
	public Post updatePost(String username,Post post, int postId) {
		
		Post existing = postRepo.findByUser_UsernameAndId(username, postId); 
	 
		if(existing != null) {
			existing.setContent(post.getContent());
			existing.setTitle(post.getTitle());
			existing.setImageUrl(post.getImageUrl());
			existing.setActive(post.getActive());
			postRepo.saveAndFlush(existing);
		}
		
		
		return existing;
	}

	
	@Override
	public boolean deletePost(String username, int postId) {
		boolean deleted = false;
		Post deletedPost = postRepo.findByUser_UsernameAndId(username, postId);
		if(deletedPost != null) {
			postRepo.deleteById(postId);;
			deleted = true;
		}
		
		return deleted;
	}



}
