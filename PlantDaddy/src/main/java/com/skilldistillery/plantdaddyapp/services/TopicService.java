package com.skilldistillery.plantdaddyapp.services;

import java.util.List;

import com.skilldistillery.plantdaddyapp.entities.Topic;

public interface TopicService {

	public List<Topic> findAll();
	
	public Topic findByName(String name);
	
	
}
