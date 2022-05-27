package com.skilldistillery.plantdaddyapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.plantdaddyapp.entities.PlantPhoto;
import com.skilldistillery.plantdaddyapp.repositories.PlantPhotoRepository;


@Service
public class PlantPhotoServiceImpl implements PlantPhotoServices {
	
	
	@Autowired
	private PlantPhotoRepository photoRepo;

	@Override
	public List<PlantPhoto> index() {
		
		return photoRepo.findAll();
	}

	@Override
	public List<PlantPhoto> showByUserPlantId(int userPlantId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
