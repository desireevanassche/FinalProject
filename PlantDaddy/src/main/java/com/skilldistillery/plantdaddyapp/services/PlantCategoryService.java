package com.skilldistillery.plantdaddyapp.services;

import java.util.List;

import com.skilldistillery.plantdaddyapp.entities.PlantCategory;

public interface PlantCategoryService {
	
	List<PlantCategory> index();
	
	PlantCategory findById(int id);

	List<PlantCategory> listPlantCategoryByKeyword(String keyword);
	
	
}
