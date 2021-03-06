package com.skilldistillery.plantdaddyapp.services;

import java.util.List;

import com.skilldistillery.plantdaddyapp.entities.PlantGrowth;

public interface PlantGrowthService {

	
	public List<PlantGrowth> indexGrowthByUserPlantId(int userPlantId, String Username);
	
	public PlantGrowth findGrowthById(int userPlantId, int growthId, String username);
	
	public PlantGrowth addGrowth(int userPlantId, String username, PlantGrowth growth);
	
	public PlantGrowth updateGrowth(String username, int growthId, PlantGrowth growth); 
	
	public boolean deleteGrowth( int growthId, int userPlantId, String username);
	
	
	
	
	
	
	
}
