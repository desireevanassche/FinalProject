package com.skilldistillery.plantdaddyapp.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.plantdaddyapp.entities.User;
import com.skilldistillery.plantdaddyapp.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public User getUserById(int userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		if (userOpt.isPresent()) {

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
	public User updateUser(User user, String username) {
		User existing = userRepo.findByUsername(username);
		if (existing != null) {
			existing.setBiography(user.getBiography());
			existing.setEmail(user.getEmail());
			existing.setFirstName(user.getFirstName());
			existing.setLastName(user.getLastName());
			existing.setImageUrl(user.getImageUrl());
			userRepo.saveAndFlush(existing);
		}
		return existing;

	}

}
