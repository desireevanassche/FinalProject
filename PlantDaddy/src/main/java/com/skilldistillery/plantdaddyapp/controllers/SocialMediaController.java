package com.skilldistillery.plantdaddyapp.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.plantdaddyapp.entities.Comment;
import com.skilldistillery.plantdaddyapp.entities.Post;
import com.skilldistillery.plantdaddyapp.entities.Topic;
import com.skilldistillery.plantdaddyapp.services.CommentService;
import com.skilldistillery.plantdaddyapp.services.PostService;
import com.skilldistillery.plantdaddyapp.services.TopicService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4295" })
public class SocialMediaController {

	
	
	@Autowired
	private PostService postServ;
	
	@Autowired 
	private TopicService topicServ;
	
	@Autowired 
	private CommentService comServ;
	
	
//	----------------- POST CONTROLLERS ----------------------
	
	
	
	//TESTED AND WORKS IN POSTMAN http://localhost:8095/api/posts
	
	@GetMapping("posts")
		public List<Post> indexAll(HttpServletResponse res){
		 
			return postServ.indexAll();
		}
		
	//TESTED AND WORKS IN POSTMAN  http://localhost:8095/api/users/posts
	@GetMapping("users/posts")
		public List<Post> indexByUsername(Principal principal, HttpServletResponse res){
	 
		return postServ.indexByUsername(principal.getName());
	}
	
	
	
	

	//TESTED AND WORKS IN POSTMAN http://localhost:8095/api/users/posts
	@PostMapping("users/posts")
		public Post createPost(@RequestBody Post post,
				HttpServletResponse res,
				Principal principal){
		 Post newPost = postServ.addPost(principal.getName(), post);
		 if(newPost !=null) {
			 res.setStatus(201);
		 }
	 
		return newPost;
	}
	
	//TESTED AND WORKS IN POSTMAN  http://localhost:8095/api/users/posts/7
	@PutMapping("users/posts/{id}") 
		public Post findPostById(@PathVariable("id") int postId, @RequestBody Post post,
				Principal principal, 
				HttpServletResponse res
				) {
		
		return postServ.updatePost(principal.getName(),post, postId);
	}
	
	
	
	
	// TESTED AND WORKING IN POSTMAN http://localhost:8095/api/users/posts/14
	@PutMapping("users/posts/disable/{id}")
	public Post deletePost(@PathVariable("id") int postId,@RequestBody Post post, Principal principal, 
			HttpServletResponse res) {

		return postServ.disablePost(principal.getName(),post, postId);
		
	}
	

//	----------------- TOPIC CONTROLLERS -----------------------
	
	
	// TESTED IN POSTMAN AND PASSES http://localhost:8095/api/topics
	@GetMapping("topics")
	public List<Topic> indexAllTopics(HttpServletResponse res){
		
		return topicServ.findAll();
		
	}
	
	
	// TESTED IN POSTMAN AND PASSES http://localhost:8095/api/topics/Indoor
	@GetMapping("topics/{name}")
	public Topic findTopicByUsername(@PathVariable("name") String name, HttpServletResponse res) {
		
		
		return topicServ.findByName(name);
	}
	
//	------------------- COMMENT CONTROLLERS ------------------
	
	
	
	// TESTED IN POSTMAN AND PASSES http://localhost:8095/api/comments/1
	@GetMapping("comments/{postId}")
	public List<Comment> indexComments(@PathVariable("postId")int postId,
			HttpServletResponse res){
		
		return comServ.findAllByPostId(postId);
	}
	
	
	
	
	// TESTED IN POSTMAN AND PASSES http://localhost:8095/api/posts/1/comments
	@PostMapping("posts/{postId}/comments/{commentId}")
	public Comment createComment(@PathVariable("postId") int postId, 
			@PathVariable("commentId")int inReplyToId,
			@RequestBody Comment comment,
			Principal principal,
			HttpServletResponse res) {
	
		comment = comServ.createComment(comment,inReplyToId, postId, principal.getName());
		
		if(comment != null) {
			res.setStatus(201);
		}
		
		return comment;	
	}
	
	
//	@PutMapping("posts/{postId}/comments/{commentId}")
//	public Comment disableComment
	
	
	
	
//	@DeleteMapping("posts/{postId}/comments/{commentId}")
//	public void deleteComment(@PathVariable("postId") int postId,
//	@PathVariable("commentId") int commentId,
//	HttpServletResponse res,
//	Principal principal) {
//boolean deleted = comServ.deleteComment(postId, commentId, principal.getName());
//try {
//	if(deleted == true) {
//		res.setStatus(200);
//	}
//} catch (Exception e) {
//	e.printStackTrace();
//	res.setStatus(404);
//}
//
//		
//	}
	
	
	
	
	
	
	
	
	
	
}