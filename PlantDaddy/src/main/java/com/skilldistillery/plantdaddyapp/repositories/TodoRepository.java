package com.skilldistillery.plantdaddyapp.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.plantdaddyapp.entities.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
	
	List<Todo> findByUserPlant_Id(int userPlantId);
	
	Set<Todo> findByUserPlant_User_Username(String username);
}
