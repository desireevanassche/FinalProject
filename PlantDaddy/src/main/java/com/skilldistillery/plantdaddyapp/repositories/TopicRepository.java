package com.skilldistillery.plantdaddyapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.plantdaddyapp.entities.Topic;

public interface TopicRepository extends JpaRepository<Topic, Integer> {

	
		Topic findByName(String name);
}
