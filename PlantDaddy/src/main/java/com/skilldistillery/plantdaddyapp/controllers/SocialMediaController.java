package com.skilldistillery.plantdaddyapp.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("posts")
		public List<Post> indexAll(HttpServletResponse res){
		 
			return postServ.indexAll();
		}
		
	
	
	@GetMapping("users/posts")
		public List<Post> indexByUsername(Principal principal, HttpServletResponse res){
	 
		return postServ.indexByUsername(principal.getName());
	}

	
	@PutMapping("users/posts/{id}") 
		public Post findPostById(@PathVariable("id") int postId,
				Principal principal, 
				HttpServletResponse res
				) {
		
		
		return postServ.findById(principal.getName(), postId);
	}
	
	
	
//	@PutMapping("updatepost")
//		public Post updatePost
	
	
}
