package com.skilldistillery.plantdaddyapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.plantdaddyapp.entities.PlantPhoto;
import com.skilldistillery.plantdaddyapp.entities.User;
import com.skilldistillery.plantdaddyapp.repositories.PlantPhotoRepository;
import com.skilldistillery.plantdaddyapp.repositories.UserRepository;



@Service
public class PlantPhotoServiceImpl implements PlantPhotoServices {
	
	
	
	@Autowired
	private PlantPhotoRepository photoRepo;

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<PlantPhoto> index() {
		
		return photoRepo.findAll();
	}
	

	@Override
	public List<PlantPhoto> findByUserPlantId(int userPlantId) {
		
		return photoRepo.findByUserPlant_Id(userPlantId);
	}


	
	@Override
	public List<PlantPhoto> findByUsername(String username) {
	
		
		return photoRepo.findByUserPlant_User_Username(username);
	}

	
}
