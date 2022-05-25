package com.skilldistillery.plantdaddyapp.services;

import java.util.Optional;
import java.util.Set;

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

	@Override
	public Set<User> findUserWithUsernameLike(String keyword) {
		String usernameLike = "%" + keyword + "%";
		return userRepo.findByUsernameLike(usernameLike);
	}

	@Override
	public User updateUser(User user, int userId) {
		user.setId(userId);

		if (userRepo.existsById(userId)) {
			return userRepo.save(user);
		}
		return null;

	}
	

}












