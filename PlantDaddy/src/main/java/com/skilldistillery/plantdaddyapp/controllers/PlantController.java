package com.skilldistillery.plantdaddyapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.plantdaddyapp.entities.Plant;
import com.skilldistillery.plantdaddyapp.services.PlantService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4295" })
public class PlantController {

	@Autowired
	PlantService plantServ;

	@GetMapping("plant")
	public List<Plant> index() {
		return plantServ.index();
	}

}
