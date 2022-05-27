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

import com.skilldistillery.plantdaddyapp.entities.UserPlant;
import com.skilldistillery.plantdaddyapp.services.UserPlantService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4295" })
public class UserPlantController {

	@Autowired
	UserPlantService userPlantServ;

	@GetMapping("userPlants")
	public List<UserPlant> index(Principal principal) {
		return userPlantServ.index(principal.getName());
	}

	@GetMapping("userPlants/{userPlantId}")
	public UserPlant show(HttpServletResponse res, @PathVariable int userPlantId, Principal principal) {
		UserPlant userPlant = userPlantServ.show(principal.getName(), userPlantId);
		if (userPlant == null) {
			res.setStatus(404);
		}
		return userPlant;
	}
	@GetMapping("userPlants/search/{keyword}")
	public List<UserPlant> findByKeyword(@PathVariable String keyword){
		return userPlantServ.listPlantByKeyword(keyword);
	}


	@PostMapping("userPlants")
	public UserPlant addUserPlant(@RequestBody UserPlant userPlant, HttpServletResponse res, Principal principal) {
		UserPlant newUserPlant = userPlantServ.addPlant(userPlant, principal.getName());
		return newUserPlant;
	}

	@PutMapping("userPlants/{userPlantId}")
	public UserPlant updateUserPlant(@RequestBody UserPlant userPlant, @PathVariable int userPlantId,
			Principal principal) {
		return userPlantServ.updatePlant(userPlant, userPlantId, principal.getName());
	}

	@PutMapping("userPlants/deactivate/{userPlantId}")
	public UserPlant deactivate(@RequestBody UserPlant userPlant, HttpServletResponse res, Principal principal,
			@PathVariable int userPlantId) {
		return userPlantServ.deactivate(userPlant, userPlantId, principal.getName());

	}
	

}
