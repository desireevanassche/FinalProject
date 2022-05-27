package com.skilldistillery.plantdaddyapp.services;

import java.util.List;

import com.skilldistillery.plantdaddyapp.entities.PlantPhoto;

public interface PlantPhotoServices {

	public List<PlantPhoto> index();
	
	public List<PlantPhoto> findByUserPlantId(int userPlantId);
	
	public List<PlantPhoto> findByUsername( String username);
 	
	
	
	
	
	
}
