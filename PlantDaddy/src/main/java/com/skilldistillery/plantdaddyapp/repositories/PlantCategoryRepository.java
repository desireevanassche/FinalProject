package com.skilldistillery.plantdaddyapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.plantdaddyapp.entities.PlantCategory;

public interface PlantCategoryRepository extends JpaRepository<PlantCategory, Integer> {

	List<PlantCategory> findPlantCategoryByNameLikeOrDescriptionLike(String keyword, String keyword2);

}
