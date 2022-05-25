package com.skilldistillery.plantdaddyapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.plantdaddyapp.entities.UserPlant;

public interface UserPlantRepository extends JpaRepository<UserPlant, Integer> {

}
