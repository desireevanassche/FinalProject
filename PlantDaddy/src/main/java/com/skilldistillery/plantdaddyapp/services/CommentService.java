package com.skilldistillery.plantdaddyapp.services;

import java.util.List;

import com.skilldistillery.plantdaddyapp.entities.Comment;
import com.skilldistillery.plantdaddyapp.entities.User;

public interface CommentService {

	
	public List<Comment> findAllByPostId(int postId);
	
	public Comment createComment(Comment comment,int inReplyToId, int postId, String username);
	
	public Comment disablePost(String username, Comment comment, int postId, int commentId);
	
	public User findUserByCommentId(String username, int commentId);
	
	
}
