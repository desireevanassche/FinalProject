package com.skilldistillery.plantdaddyapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.plantdaddyapp.entities.Hashtag;

public interface HashtagRepository extends JpaRepository<Hashtag, Integer> {
	
	

}
