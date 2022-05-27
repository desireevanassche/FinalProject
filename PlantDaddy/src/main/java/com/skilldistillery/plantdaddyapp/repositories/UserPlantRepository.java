package com.skilldistillery.plantdaddyapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.plantdaddyapp.entities.UserPlant;

public interface UserPlantRepository extends JpaRepository<UserPlant, Integer> {

	UserPlant findByUser_UsernameAndId(String username, int plantid);

	List<UserPlant> findByNicknameOrDescription(String keyword, String keyword2);

	List<UserPlant> findByUser_UserPlants(String username);

}
