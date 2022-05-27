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

import com.skilldistillery.plantdaddyapp.entities.PottingMix;
import com.skilldistillery.plantdaddyapp.services.PottingMixService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4295" })
public class PottingMixController {

	@Autowired
	PottingMixService potSvc;

	@GetMapping("pottingMixes")
	public List<PottingMix> index() {
		return potSvc.index();
	}
	@GetMapping("pottingMixes/search/{keyword}")
	public List<PottingMix> searchByKeyword(@PathVariable String keyword){
		return potSvc.listPottingMixByKeyword(keyword);
	}
	@GetMapping("pottingMixes/users/{name}")
	public List<PottingMix> searchByUser(@PathVariable String name){
		return potSvc.listPottingMixByUsername(name);
	}

	@PostMapping("pottingMixes")
	public PottingMix createPottingMix(@RequestBody PottingMix potMix, HttpServletResponse res, Principal principal) {
		PottingMix newPotMix = potSvc.addPottingMix(potMix, principal.getName());
		if (newPotMix != null) {
			res.setStatus(201);
		}
		return newPotMix;
	}

	@PutMapping("pottingMixes/{id}")
	public PottingMix updatePotMix(@PathVariable int id, @RequestBody PottingMix potMix, HttpServletResponse res,
			Principal principal) {
		return potSvc.updatePottingMix(potMix, id, principal.getName());
	}

	@PutMapping("pottingMixes/deactivate/{id}")
	public PottingMix deactivateMix(@PathVariable int id, @RequestBody PottingMix potMix, HttpServletResponse res,
			Principal principal) {
		return potSvc.deactivate(potMix, id, principal.getName());
	}
}
