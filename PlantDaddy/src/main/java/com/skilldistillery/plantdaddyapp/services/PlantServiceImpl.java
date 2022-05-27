package com.skilldistillery.plantdaddyapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.plantdaddyapp.entities.Plant;
import com.skilldistillery.plantdaddyapp.entities.User;
import com.skilldistillery.plantdaddyapp.repositories.PlantRepository;
import com.skilldistillery.plantdaddyapp.repositories.UserRepository;

@Service
public class PlantServiceImpl implements PlantService {

	@Autowired
	private PlantRepository plantRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Plant> index() {
		return plantRepo.findAll();
	}


	@Override
	public Plant findById(int id) {
		return plantRepo.findById(id).get();
	}

	@Override
	public Plant addPlant(Plant plant, String username) {
		User user = userRepo.findByUsername(username);
		if(user != null) {
			plant.setUser(user);
		}
		plant = plantRepo.saveAndFlush(plant);
		return plant;
	}

	@Override
	public Plant updatePlant(Plant plant, int plantid, String username) {

		Plant managed = plantRepo.findByUser_UsernameAndId(username, plantid);
		if (managed != null) {
			managed.setCommonName(plant.getCommonName());
			managed.setDescription(plant.getDescription());
			managed.setImageUrl(plant.getImageUrl());
			managed.setBotanicalName(plant.getBotanicalName());
			managed.setCareDifficulty(plant.getCareDifficulty());
			managed.setWaterCycle(plant.getWaterCycle());
			managed.setWaterType(plant.getWaterType());
			managed.setLightReq(plant.getLightReq());
			managed.setActive(plant.isActive());
			plantRepo.saveAndFlush(managed);
		}
		return managed;
	}

	@Override
	public Plant deactivate(Plant plant, int plantid, String username) {
		Plant deactivate = plantRepo.findByUser_UsernameAndId(username, plantid);
		if (deactivate != null) {
			deactivate.setActive(false);
			plantRepo.saveAndFlush(deactivate);
		}
		return deactivate;
	}

	@Override
	public List<Plant> listPlantByKeyword(String keyword) {
		keyword = "%" + keyword + "%";
		return plantRepo.findByCommonNameLikeOrDescriptionLike(keyword, keyword);
	}

	@Override
	public List<Plant> listPlantByDifficulty(String keyword) {
		keyword = "%" + keyword + "%";
		return plantRepo.findByCareDifficultyLike(keyword);
	}

	@Override
	public List<Plant> indexByUsername(String name) {
		return plantRepo.findByUser_Username(name);
	}

}
