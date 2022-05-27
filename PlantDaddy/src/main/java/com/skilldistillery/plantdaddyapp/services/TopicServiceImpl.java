package com.skilldistillery.plantdaddyapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.plantdaddyapp.entities.Topic;
import com.skilldistillery.plantdaddyapp.repositories.TopicRepository;

@Service
public class TopicServiceImpl implements TopicService {

	
	@Autowired
	private TopicRepository topicRepo;

	@Override
	public List<Topic> findAll() {
		
		return topicRepo.findAll();
	}

	@Override
	public Topic findByName(String name) {
		 
		return topicRepo.findByName(name); 
	}
	
	
	
}
