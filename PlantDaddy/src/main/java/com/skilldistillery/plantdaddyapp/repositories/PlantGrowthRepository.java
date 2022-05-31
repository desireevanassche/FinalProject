package com.skilldistillery.plantdaddyapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.plantdaddyapp.entities.PlantGrowth;

public interface PlantGrowthRepository extends JpaRepository<PlantGrowth, Integer> {

	public List<PlantGrowth> findByUserPlant_Id(int userPlantId);
	
}
