package com.skilldistillery.plantdaddyapp.services;

import java.util.ArrayList;
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
			System.out.println(user);
		if (user != null) {
			Optional<UserPlant> plantOp = plantRepo.findById(userPlantId);
				System.out.println(plantOp);
			if (plantOp.isPresent()) {
				
				UserPlant plant = plantOp.get();
				System.out.println(plant.getId());
				
				List<PlantGrowth> growth = growRepo.findByUserPlant_Id(userPlantId);
				
//				if(growth == null) {
//					List<PlantGrowth> newGrowth = new ArrayList<PlantGrowth>();
					
//				}
				
				System.out.println(growth);

				return growth;
			}
		}
		return null;
	}

	@Override
	public PlantGrowth findGrowthById(int userPlantId, int growthId, String username) {
		User user = userRepo.findByUsername(username);

		if (user != null) {

			Optional<UserPlant> plantOp = plantRepo.findById(userPlantId);

			if (plantOp.isPresent()) {

				UserPlant plant = plantOp.get();
				plant.setUser(user);
				Optional<PlantGrowth> op = growRepo.findById(growthId);

				if (op.isPresent()) {
					PlantGrowth growth = op.get();
					growth.setUserPlant(plant);

					return growth;
				}
			}

		}

		return null;
	}

	@Override
	public PlantGrowth addGrowth(int userPlantId, String username, PlantGrowth growth) {

		User user = userRepo.findByUsername(username);

		if (user != null) {

			Optional<UserPlant> plantOp = plantRepo.findById(userPlantId);

			if (plantOp.isPresent()) {
				UserPlant plant = plantOp.get();
				plant.setUser(user);
				growth.setUserPlant(plant);

			}
		}
		return growRepo.saveAndFlush(growth);
	}
	
	
	
	
	@Override
	public PlantGrowth updateGrowth( String username, int growthId, PlantGrowth growth) {
		
		PlantGrowth managed = growRepo.findByUserPlant_User_UsernameAndId(username, growthId);
		
		if(managed != null) {
			managed.setHeight(growth.getHeight());
			managed.setSpread(growth.getSpread());
			managed.setPotDiameter(growth.getPotDiameter());
			managed.setGrowthDescription(growth.getGrowthDescription());
			managed.setGrowthImage(growth.getGrowthImage());
			
			growRepo.saveAndFlush(managed);
		}
		return managed;
	}
	
	

	@Override
	public boolean deleteGrowth(int growthId, int userPlantId, String username) {
		boolean deleted = false;

		PlantGrowth existing = growRepo.findByUserPlant_User_UsernameAndId(username, growthId);

		if (existing != null) {
			growRepo.delete(existing);
			deleted = true;

		}

		return deleted;
	}

	

}
