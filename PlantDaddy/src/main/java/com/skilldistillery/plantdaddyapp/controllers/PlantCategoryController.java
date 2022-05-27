package com.skilldistillery.plantdaddyapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.plantdaddyapp.entities.PlantCategory;
import com.skilldistillery.plantdaddyapp.services.PlantCategoryService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4295" })
public class PlantCategoryController {

	@Autowired
	PlantCategoryService plantCatSvc;
	
	@GetMapping("plantCategories")
	public List<PlantCategory> index(){
		return plantCatSvc.index();
	}
	@GetMapping("plantCategories/{id}")
	public PlantCategory findById(@PathVariable int id) {
		return plantCatSvc.findById(id);
	}
	@GetMapping("plantCategories/search/{keyword}")
	public List<PlantCategory> findByKeyword(@PathVariable String keyword){
		return plantCatSvc.listPlantCategoryByKeyword(keyword);
	}
	
}
