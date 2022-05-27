package com.skilldistillery.plantdaddyapp.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Set;

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

import com.skilldistillery.plantdaddyapp.entities.Blog;
import com.skilldistillery.plantdaddyapp.entities.Todo;
import com.skilldistillery.plantdaddyapp.services.BlogService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4295" })
public class BlogController {

	@Autowired
	BlogService blogServ; 
	
	@GetMapping("blogs")
	public List<Blog> index(Principal principal) {
		return blogServ.allBlogArticles(principal.getName());
	}
	
	@GetMapping("blogs/{id}")
	public Blog show(HttpServletResponse resp, @PathVariable("id") int id, Principal principal) {
		Blog blog = blogServ.findById(principal.getName(), id);
		if (blog == null) {
			resp.setStatus(404);
		}
		return blog;
	}
	
	@PostMapping("blogs")
	public Blog create(@RequestBody Blog blog, Principal principal) {
		return blogServ.create(principal.getName(), blog);
	}

	@PutMapping("blogs/{tid}")
	public Blog update(Principal principal, @RequestBody Blog blog, @PathVariable int id) {
		return blogServ.update(principal.getName(), blog, id);
	}

//	@DeleteMapping("todos/{tid}")
//	public boolean delete(HttpServletResponse resp, @PathVariable("tid") int todoId, Principal principal) {
//		if (blogServ.destroy(principal.getName(), todoId)) {
//			resp.setStatus(204);
//		} else {
//			resp.setStatus(404);
//		}
//	}

}
