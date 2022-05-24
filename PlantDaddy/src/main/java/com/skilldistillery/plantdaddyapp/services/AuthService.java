package com.skilldistillery.plantdaddyapp.services;

import com.skilldistillery.plantdaddyapp.entities.User;

public interface AuthService {
	public User register(User user);
	public User getUserByUsername(String username);

}
