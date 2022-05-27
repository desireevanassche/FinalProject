package com.skilldistillery.plantdaddyapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.plantdaddyapp.entities.Post;
import com.skilldistillery.plantdaddyapp.entities.Store;
import com.skilldistillery.plantdaddyapp.entities.User;
import com.skilldistillery.plantdaddyapp.repositories.StoreRepository;
import com.skilldistillery.plantdaddyapp.repositories.UserRepository;

@Service
public class StoreServiceImpl implements StoreService {

	
	@Autowired
	private StoreRepository storeRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Store> index(String username) {
		
		return storeRepo.findAll();
	}

	@Override
	public List<Store> findByKeyword(String keyword) {
		keyword = "%" + keyword + "%";
		return storeRepo.findByNameLike(keyword);
	}

	@Override
	public Store findById( int storeId) {
		Optional <Store> op = storeRepo.findById(storeId);
		Store result = null;
		if(op.isPresent()) {
			result = op.get();
			return result;
			}
		
		return null ;
	}

	@Override
	public Store addStore(String username, Store store) {
		
		return storeRepo.saveAndFlush(store);
	}

	@Override
	public Store updateStore(String username, Store store, int storeId) {
		
		Store existing = storeRepo.findByPlants_User_UsernameAndId(username, storeId); 
		 
		if(existing != null) {
			existing.setName(store.getName());
			existing.setImageUrl(store.getImageUrl());
			existing.setAddress(store.getAddress());
			existing.setPlants(store.getPlants());
			storeRepo.saveAndFlush(existing);
		}
		
		
		return existing;
	}
	
	
	
	
}
