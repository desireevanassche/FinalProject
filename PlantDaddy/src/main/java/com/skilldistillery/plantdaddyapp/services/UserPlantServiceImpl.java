package com.skilldistillery.plantdaddyapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.plantdaddyapp.entities.UserPlant;
import com.skilldistillery.plantdaddyapp.repositories.UserPlantRepository;

@Service
public class UserPlantServiceImpl implements UserPlantService {

	@Autowired
	private UserPlantRepository userPlantRepo;

	@Override
	public List<UserPlant> index(String username) {
		return userPlantRepo.findByUser_Username(username);
	}

	@Override
	public UserPlant addPlant(UserPlant userPlant, String username) {
		userPlant = userPlantRepo.saveAndFlush(userPlant);
		return userPlant;
	}

	@Override
	public UserPlant updatePlant(UserPlant userPlant, int plantid, String username) {
		UserPlant managed = userPlantRepo.findByUser_UsernameAndId(username, plantid);
		if (managed != null) {
			managed.setHeight(userPlant.getHeight());
			managed.setSpread(userPlant.getSpread());
			managed.setNickname(userPlant.getNickname());
			managed.setPotDiameter(userPlant.getPotDiameter());
			managed.setImageUrl(userPlant.getImageUrl());
			managed.setHomeLocation(userPlant.getHomeLocation());
			managed.setDescription(userPlant.getDescription());
			userPlantRepo.saveAndFlush(managed);
		}
		return managed;
	}

	@Override
	public UserPlant deactivate(UserPlant userPlant, int plantid, String username) {
		UserPlant deactivate = userPlantRepo.findByUser_UsernameAndId(username, plantid);
		if (deactivate != null) {
			deactivate.setActive(false);
			userPlantRepo.saveAndFlush(deactivate);
		}
		return deactivate;
	}

	@Override
	public List<UserPlant> listPlantByKeyword(String keyword) {
		keyword = "%" + keyword + "%";
		return userPlantRepo.findByNicknameOrDescription(keyword, keyword);
	}

	@Override
	public UserPlant show(String username, int userPlantId) {
		Optional<UserPlant> op = userPlantRepo.findById(userPlantId);
		if(op.isPresent()) {
			UserPlant result = op.get();
			if(result.getUser().getUsername().equals(username)) {
				return result;
			}
		}
		return null;
	}

	

}
