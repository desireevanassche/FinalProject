package com.skilldistillery.plantdaddyapp.services;

import java.util.List;

import com.skilldistillery.plantdaddyapp.entities.Comment;

public interface CommentService {

	
	public List<Comment> findAllByPostId(int postId);
	
	public Comment createComment(Comment comment, int postId, String username);
	
	public boolean deleteComment(int postId, int commentId, String username);
	
	
	
	
}
