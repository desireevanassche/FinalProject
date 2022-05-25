package com.skilldistillery.plantdaddyapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.plantdaddyapp.entities.Todo;
import com.skilldistillery.plantdaddyapp.entities.User;

public interface TodoRepository extends JpaRepository<Todo, Integer> {


}
