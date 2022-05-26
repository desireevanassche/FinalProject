package com.skilldistillery.plantdaddyapp.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.plantdaddyapp.entities.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
	
	
	Set<Todo> findByUserPlant_User_Username(String username);
}
