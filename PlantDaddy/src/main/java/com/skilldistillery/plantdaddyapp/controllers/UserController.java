package com.skilldistillery.plantdaddyapp.controllers;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.plantdaddyapp.entities.User;
import com.skilldistillery.plantdaddyapp.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4295" })
public class UserController {

	@Autowired
	private UserService userService;


	@GetMapping("users/{keyword}")
	public Set<User> findUserByKeyword(@PathVariable String keyword, HttpServletResponse res, HttpServletRequest req) {
		Set<User> users = userService.findUserWithUsernameLike(keyword);

		if (users == null) {
			res.setStatus(404);
		} else {
			res.setStatus(201); 
		} return users;
	}
	

	
	@PutMapping("users/{userId}")
	public User updateUser (@RequestBody User user, @PathVariable Integer userId, HttpServletResponse res, HttpServletRequest req) {
		User updated = userService.updateUser(user, userId);
		if (updated == null) {
			res.setStatus(404); 
		} else {
			res.setStatus(201); 
			StringBuffer url = req.getRequestURL();
			url.append("/").append(user.getId()); 
			res.setHeader("Location", url.toString()); 
		} return updated; 
	}

}