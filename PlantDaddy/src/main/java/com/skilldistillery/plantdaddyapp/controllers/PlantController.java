package com.skilldistillery.plantdaddyapp.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping
	public List<Plant> indexByUser(Principal principal, HttpServletResponse res) {
		return plantServ.indexByUsername(principal.getName());
	}
	@PostMapping
	public Plant createPlant(@RequestBody Plant plant, HttpServletResponse res, Principal pricipal) {
		Plant newPlant = plantServ.addPlant(plant, pricipal.getName());
		if(newPlant != null) {
			res.setStatus(201);
		}
		return newPlant;
	}

}
