package com.skilldistillery.plantdaddyapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.skilldistillery.plantdaddyapp.entities.Plant;

public interface PlantRepository extends JpaRepository<Plant, Integer> {
	
	List<Plant> findByTypeLike(@Param("k") String keyword);
	
	List<Plant> findByKeywordLike(@Param("k") String keyword);
	
	List<Plant> findByDifficultyLike(@Param("k") String keyword);
	
}
