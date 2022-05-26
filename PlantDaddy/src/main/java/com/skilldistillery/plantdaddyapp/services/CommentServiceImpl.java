package com.skilldistillery.plantdaddyapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.plantdaddyapp.entities.Comment;
import com.skilldistillery.plantdaddyapp.repositories.CommentRepository;
import com.skilldistillery.plantdaddyapp.repositories.PostRepository;

@Service
public class CommentServiceImpl implements CommentService {

	
	@Autowired 
	private CommentRepository comRepo;
	
	@Autowired
	private PostRepository postRepo; 

	@Override
	public List<Comment> findAllByPostId(int postId) {
		List<Comment> comments = null;
		
		if(postRepo.existsById(postId)) {
			comments = comRepo.findByPost_Id(postId);
			
		}
		
		
		return comments;
	}

	@Override
	public Comment createComment(Comment comment, int postId) {
		
		return null;
	}

	
	
	
	@Override
	public boolean deleteComment(int postId, int commentId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
}
