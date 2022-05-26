package com.skilldistillery.plantdaddyapp.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping("plants")
	public List<Plant> index() {
		return plantServ.index();
	}

	@GetMapping("users/plants")
	public List<Plant> indexByUser(Principal principal, HttpServletResponse res) {
		return plantServ.indexByUsername(principal.getName());
	}

	@PostMapping("users/plants")
	public Plant createPlant(@RequestBody Plant plant, HttpServletResponse res, Principal pricipal) {
		Plant newPlant = plantServ.addPlant(plant, pricipal.getName());
		if (newPlant != null) {
			res.setStatus(201);
		}
		return newPlant;
	}

	@PutMapping("users/plants/{id}")
	public Plant updatePlant(@PathVariable int id, @RequestBody Plant plant, HttpServletResponse res,
			Principal principal) {
		return plantServ.updatePlant(plant, id, principal.getName());

	}

	@PutMapping("users/plants/disable/{id}")
	public Plant disablePlant(@PathVariable("id") int plantId, @RequestBody Plant plant, Principal principal,
			HttpServletResponse res) {
		return plantServ.deactivate(plant, plantId, principal.getName());
	}

	@GetMapping("plants/search/{keyword}")
	public List<Plant> findByNameOrDescription(@PathVariable String keyword) {
		return plantServ.listPlantByKeyword(keyword);
	}

	@GetMapping("plants/search/difficulty/{keyword}")
	public List<Plant> findByDifficulty(@PathVariable String keyword) {
		return plantServ.listPlantByDifficulty(keyword);
	}

	@GetMapping("plants/search/users/{username}")
	public List<Plant> findByUsername(@PathVariable String username) {
		return plantServ.indexByUsername(username);
	}

}
