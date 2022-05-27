package com.skilldistillery.plantdaddyapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.skilldistillery.plantdaddyapp.entities.Store;

public interface StoreRepository extends JpaRepository<Store, Integer>{

	
	List<Store> findByNameLike(@Param("k") String keyword);
	
	Store findByPlants_User_UsernameAndId(String username, int postId);
	
}
