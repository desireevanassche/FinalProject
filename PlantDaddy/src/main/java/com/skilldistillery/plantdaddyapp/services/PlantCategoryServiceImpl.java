package com.skilldistillery.plantdaddyapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.plantdaddyapp.entities.PlantCategory;
import com.skilldistillery.plantdaddyapp.repositories.PlantCategoryRepository;

@Service
public class PlantCategoryServiceImpl implements PlantCategoryService {
	
	@Autowired
	private PlantCategoryRepository plantCatRepo;

	@Override
	public List<PlantCategory> index() {
		return plantCatRepo.findAll();
	}

	@Override
	public List<PlantCategory> listPlantCategoryByKeyword(String keyword) {
		keyword = "%" + keyword + "%";
		return plantCatRepo.findPlantCategoryByNameLikeOrDescriptionLike(keyword, keyword);
	}

	@Override
	public PlantCategory findById(int id) {
		return plantCatRepo.findById(id).get();
	}

}
