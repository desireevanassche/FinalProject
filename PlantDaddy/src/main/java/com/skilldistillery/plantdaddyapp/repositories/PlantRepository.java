package com.skilldistillery.plantdaddyapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.skilldistillery.plantdaddyapp.entities.Plant;

public interface PlantRepository extends JpaRepository<Plant, Integer> {
	
	
	List<Plant> findByCommonNameOrDescriptionLike(@Param("k") String keyword, @Param("k")String keyword2);
	
	List<Plant> findByCareDifficultyLike(@Param("k") String keyword);

	Plant findByUser_UsernameAndId(String username, int postId);
	
}
