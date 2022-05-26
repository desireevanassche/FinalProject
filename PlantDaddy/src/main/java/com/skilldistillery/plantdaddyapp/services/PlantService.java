package com.skilldistillery.plantdaddyapp.services;

import java.util.List;

import com.skilldistillery.plantdaddyapp.entities.Plant;

public interface PlantService {

	List<Plant> index();

	Plant findById(int id);

	Plant addPlant(Plant plant, String username);

	Plant updatePlant(Plant plant, int id, String username);
	
	Plant deactivate(Plant plant, int id, String username);

	List<Plant> listPlantByKeyword(String keyword);

	List<Plant> listPlantByDifficulty(String keyword);

	List<Plant> indexByUsername(String name);

}
