package com.skilldistillery.plantdaddyapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.plantdaddyapp.entities.Hashtag;
import com.skilldistillery.plantdaddyapp.entities.User;
import com.skilldistillery.plantdaddyapp.repositories.HashtagRepository;
import com.skilldistillery.plantdaddyapp.repositories.UserRepository;

@Service
public class HashtagServiceImpl implements HashtagService {

	
	@Autowired
	private HashtagRepository hashRep;

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<Hashtag> index(String username) {
		User user = userRepo.findByUsername(username);
				if(user !=null) {
					return hashRep.findAll();
				}
		return null;
	}
	
	

//	@Override
//	public Hashtag createHashtag(Hashtag hashtag, String username) {
//		User user = userRepo.findByUsername(username);
//		hashtag = null;
//		if(user != null) {
//			hashtag.set
//		}
//				
//		return null;
//	}
	
	
	
	
	
}
