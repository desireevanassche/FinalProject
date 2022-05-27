package com.skilldistillery.plantdaddyapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.skilldistillery.plantdaddyapp.entities.PottingMix;

public interface PottingMixRepository extends JpaRepository<PottingMix, Integer> {

	PottingMix findByUser_UsernameAndId(String username, int id);
	
	List<PottingMix> findByBrandLikeOrNameLikeOrTypeLike(@Param("k") String keyword, @Param("k")String keyword2, @Param("k") String keyword3);

	List<PottingMix> findByUser_Username(String name);

}
