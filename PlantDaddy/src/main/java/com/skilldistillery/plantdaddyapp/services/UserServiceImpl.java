package com.skilldistillery.plantdaddyapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.plantdaddyapp.entities.User;
import com.skilldistillery.plantdaddyapp.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public User getUserById(int userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		if(userOpt.isPresent()) {
			
			return userOpt.get();
		}
		return null;
	}

}
