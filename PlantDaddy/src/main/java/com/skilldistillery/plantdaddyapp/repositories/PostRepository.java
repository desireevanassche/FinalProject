package com.skilldistillery.plantdaddyapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.plantdaddyapp.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUser_Username(String username);

	Post findByUser_UsernameAndId(String username, int postId);
	 
	
	
}
