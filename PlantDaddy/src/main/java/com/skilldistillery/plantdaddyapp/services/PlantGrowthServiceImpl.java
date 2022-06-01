package com.skilldistillery.plantdaddyapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.plantdaddyapp.entities.PlantGrowth;
import com.skilldistillery.plantdaddyapp.entities.User;
import com.skilldistillery.plantdaddyapp.entities.UserPlant;
import com.skilldistillery.plantdaddyapp.repositories.PlantGrowthRepository;
import com.skilldistillery.plantdaddyapp.repositories.UserPlantRepository;
import com.skilldistillery.plantdaddyapp.repositories.UserRepository;

@Service
public class PlantGrowthServiceImpl implements PlantGrowthService {

	
	@Autowired
	private PlantGrowthRepository growRepo;

	@Autowired
	private UserPlantRepository plantRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<PlantGrowth> indexGrowthByUserPlantId(int userPlantId, String username) {
		System.out.println(userPlantId);
	User user = userRepo.findByUsername(username);
	
	if(user!=null) {
	 Optional<UserPlant> plantOp = plantRepo.findById(userPlantId);
	 
	 
	 if(plantOp.isPresent()) {
		 UserPlant plant = plantOp.get();
		 List<PlantGrowth> growth = growRepo.findByUserPlant_Id(plant.getId());
		 
		 return growth;
	 }
	}
		return null;
	}

	@Override
	public PlantGrowth findGrowthById(int userPlantId, int growthId, String username) {
		User user = userRepo.findByUsername(username);
		
		if(user!=null) {
			
			 Optional<UserPlant> plantOp = plantRepo.findById(userPlantId);
			 
			 if(plantOp.isPresent()) {
				 
				 UserPlant plant = plantOp.get();
				 plant.setUser(user);
				 Optional<PlantGrowth> op = growRepo.findById(growthId);
				 
				 if(op.isPresent()) {
					 PlantGrowth growth = op.get();
					 growth.setUserPlant(plant);
				
				 
				 return growth;
				 }
			 }
			 
			}
		
		return null;
	}

	@Override
	public PlantGrowth addGrowth(int growthId, int userPlantId, String uesrname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteGrowth(int growthId, int userPlantId, String username) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
}
