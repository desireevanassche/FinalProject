package com.skilldistillery.plantdaddyapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.plantdaddyapp.entities.Comment;
import com.skilldistillery.plantdaddyapp.entities.Post;
import com.skilldistillery.plantdaddyapp.entities.User;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	List<Comment> findByPost_Id(int postId);
	
	
	
	
	List<Comment> findByUser_UsernameAndId(String username, int commentId);
}
