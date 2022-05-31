package com.skilldistillery.plantdaddyapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.plantdaddyapp.entities.Comment;
import com.skilldistillery.plantdaddyapp.entities.Post;
import com.skilldistillery.plantdaddyapp.entities.User;
import com.skilldistillery.plantdaddyapp.repositories.CommentRepository;
import com.skilldistillery.plantdaddyapp.repositories.PostRepository;
import com.skilldistillery.plantdaddyapp.repositories.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository comRepo;

	@Autowired
	private PostRepository postRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Comment> findAllByPostId(int postId) {
		List<Comment> comments = null;

		if (postRepo.existsById(postId)) {
			comments = comRepo.findByPost_Id(postId);

		}

		return comments;

	}

	@Override
	public Comment createCommentOnComment(Comment comment, int inReplyToId, int postId, String username) {
		Post post = null;
		User user = userRepo.findByUsername(username);
		if (user != null) {
			Optional<Post> postOp = postRepo.findById(postId);
			if (postOp.isPresent()) {
				post = postOp.get();
				if (post != null && inReplyToId > 0) {
					Optional<Comment> op = comRepo.findById(inReplyToId);
					if (op.isPresent()) {
						Comment newComment = op.get();
						
						comment.setComment(newComment);
						comment.setPost(post);
						comment.setUser(user);
					}
				}
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
			comment.setActive(false);
			comRepo.saveAndFlush(comment);
		}
		return comment;
	}

	// ("comments/{commentId}/users/{id}")

	@Override
	public User findUserByCommentId(String username, int commentId, int userId) {
		List<Comment> existing = comRepo.findByUser_UsernameAndId(username, commentId);
		Optional<User> op = userRepo.findById(userId);
		if (existing != null && op.isPresent()) {
			User user = op.get();
			user.setComments(existing);
			return user;
		}

		return null;
	}

	@Override
	public Comment createComment(int postId, Comment comment, String username) {
		User user = userRepo.findByUsername(username);
		System.out.println(user);
		Post post = null;
		if (user != null) {

			Optional<Post> op = postRepo.findById(postId);
			if (op.isPresent()) {
				post = op.get();

				comment.setPost(post);
				comment.setUser(user);

			}
		}

		return comRepo.saveAndFlush(comment);
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
