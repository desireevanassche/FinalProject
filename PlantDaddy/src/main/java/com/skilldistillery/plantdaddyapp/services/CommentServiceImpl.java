package com.skilldistillery.plantdaddyapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.plantdaddyapp.entities.Comment;
import com.skilldistillery.plantdaddyapp.entities.Post;
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

		if (postRepo.existsById(postId)) {
			comments = comRepo.findByPost_Id(postId);

		}

		return comments;

	}

	@Override
	public Comment createComment(Comment comment, int inReplyToId, int postId, String username) {

		Post post = postRepo.findByUser_UsernameAndId(username, postId);

		if (post != null && inReplyToId > 0) {
			Optional<Comment> op = comRepo.findById(inReplyToId);
			if (op.isPresent()) {
				Comment newComment = op.get();
				comment.setComment(newComment);
			}
			 
		}

		if (post != null && inReplyToId == 0) {
			comment.setPost(post);
			
		}

		return comRepo.saveAndFlush(comment);
	}

	@Override
	public Comment disablePost(String username, Comment comment, int postId, int commentId) {

		Post existing = postRepo.findByUser_UsernameAndId(username, postId);
		Optional<Comment> op = comRepo.findById(commentId);

		if (existing != null && op.isPresent()) {
			comment = op.get();
			comment.setActive(comment.isActive());
			comRepo.saveAndFlush(comment);
		}

		return comment;
	}

//	@Override
//	public boolean deleteComment(int postId, int commentId, String username) {
//		boolean deleted = false;
//		
//		Post post = postRepo.findByUser_UsernameAndId(username, postId);
//		Optional<Comment> op = comRepo.findById(commentId);
//		
//		System.out.println(post);
//		System.out.println(op);
//		if(post != null && op.isPresent()) {
//			
//			
//			comRepo.deleteById(commentId);
//			deleted = true;
//		}
//				
//		
//		return deleted;
//	}

}
