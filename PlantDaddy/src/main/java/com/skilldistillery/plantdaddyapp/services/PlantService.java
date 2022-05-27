package com.skilldistillery.plantdaddyapp.services;

import java.util.List;

import com.skilldistillery.plantdaddyapp.entities.Plant;

public interface PlantService {

	public List<Plant> index();

	public Plant findById(int id);

	public Plant addPlant(Plant plant, String username);

	public Plant updatePlant(Plant plant, int id, String username);

	public Plant deactivate(Plant plant, int id, String username);

	public List<Plant> listPlantByKeyword(String keyword);

	public List<Plant> listPlantByDifficulty(String keyword);

	public List<Plant> indexByUsername(String name);

}
