package com.skilldistillery.plantdaddyapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.plantdaddyapp.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
