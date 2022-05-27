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

import com.skilldistillery.plantdaddyapp.entities.Store;
import com.skilldistillery.plantdaddyapp.services.StoreService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4295" })
public class StoreController {

	@Autowired
	private StoreService storeServ;
	
//	Tested in postman and passes  http://localhost:8095/api/stores
	@GetMapping("stores")
	public List<Store> index(Principal principal, HttpServletResponse res){
		
		return storeServ.index(principal.getName());
		
	}

//	Tested in postman and passes  http://localhost:8095/api/stores/flower
	@GetMapping("stores/{keyword}")
	public List<Store> searchStoreByKeyword(@PathVariable("keyword")String keyword, HttpServletResponse res){
		List<Store> stores = storeServ.findByKeyword(keyword);
		return stores;
		
	}
	
	@PostMapping("stores")
	public Store createStore(@RequestBody Store store, Principal principal, HttpServletResponse res ) {
		
	return	storeServ.addStore(principal.getName(), store);
		
	}
	
	@PutMapping("stores/{id}")
	public Store editStore(@PathVariable("id") int storeId,@RequestBody Store store, Principal principal, HttpServletResponse res) {
		
		return storeServ.updateStore(principal.getName(), store, storeId);
		
	}
	
	
	
	
	
}
