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

import com.skilldistillery.plantdaddyapp.entities.Address;
import com.skilldistillery.plantdaddyapp.services.AddressService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4295" })
public class AddressController {
	
	@Autowired
	AddressService addySvc;
	
	@GetMapping("addresses")
	public List<Address> index() {
		return addySvc.index();
	}
	@GetMapping("addresses/{id}")
	public Address findById(@PathVariable int id) {
		return addySvc.findById(id);
	}
	
	@PostMapping("users/addresses")
	public Address addAddress(@RequestBody Address address, HttpServletResponse res, Principal principal) {
		Address newAddress = addySvc.addAddress(address, principal.getName());
		if(newAddress != null) {
			res.setStatus(201);
		}
		return newAddress;
	}
	@PutMapping("users/addresses/{id}")
	public Address updateAddress(@PathVariable int id, @RequestBody Address address, HttpServletResponse res, Principal principal) {
		return addySvc.updateAddress(address, id, principal.getName());
	}
	@GetMapping("addresses/search/{keyword}")
	public List<Address> findByKeyword(@PathVariable String keyword) {
		return addySvc.listByKeyword(keyword);
	}
	
}
