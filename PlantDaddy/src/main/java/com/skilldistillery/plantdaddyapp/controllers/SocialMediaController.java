package com.skilldistillery.plantdaddyapp.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

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

import com.skilldistillery.plantdaddyapp.entities.Post;
import com.skilldistillery.plantdaddyapp.services.PostService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4295" })
public class SocialMediaController {

	
	
	@Autowired
	private PostService postServ;
	
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
	

	
	
}