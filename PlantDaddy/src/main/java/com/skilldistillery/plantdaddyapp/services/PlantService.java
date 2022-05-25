package com.skilldistillery.plantdaddyapp.services;

import java.util.List;

import com.skilldistillery.plantdaddyapp.entities.Plant;

public interface PlantService {

	List<Plant> index();

	Plant findById(int id);

	Plant addPlant(Plant plant);

	Plant updatePlant(Plant plant, int id);
	
	Plant deactivate(Plant plant, int id);

	List<Plant> listAllPlantByType(String keyword);

	List<Plant> listPlantByKeyword(String keyword);

	List<Plant> listPlantByDifficulty(String keyword);

}
