package com.skilldistillery.plantdaddyapp.services;

import java.util.Set;

import com.skilldistillery.plantdaddyapp.entities.User;

import antlr.collections.List;

public interface UserService {

	User getUserById(int userId);
	
	public Set<User> findUserWithUsernameLike(String keyword);
	
	public User updateUser(User user, int userId); 
	
	
}
