package com.skilldistillery.plantdaddyapp.services;

import java.util.List;

import com.skilldistillery.plantdaddyapp.entities.UserPlant;

public interface UserPlantService {
	
	public List<UserPlant> index(String username);
	
	public UserPlant show(String username, int userPlantId);
	
	public UserPlant addPlant(UserPlant userPlant, String username);

	public UserPlant updatePlant(UserPlant userPlant, int id, String username);
	
	public UserPlant deactivate(UserPlant userPlant, int id, String username);

	public List<UserPlant> listPlantByKeyword(String keyword);


}
