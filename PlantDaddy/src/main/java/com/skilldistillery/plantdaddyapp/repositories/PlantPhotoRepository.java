package com.skilldistillery.plantdaddyapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.skilldistillery.plantdaddyapp.entities.PlantPhoto;

public interface PlantPhotoRepository extends JpaRepositoryImplementation<PlantPhoto, Integer>{

	
	List<PlantPhoto> findByUserPlant_Id(int userPlantId);
	
	List<PlantPhoto> findByUserPlant_User_Username( String username);
	
}
