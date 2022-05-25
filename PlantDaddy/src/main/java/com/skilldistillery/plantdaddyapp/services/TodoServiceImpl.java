package com.skilldistillery.plantdaddyapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.plantdaddyapp.entities.Todo;
import com.skilldistillery.plantdaddyapp.entities.User;
import com.skilldistillery.plantdaddyapp.repositories.TodoRepository;
import com.skilldistillery.plantdaddyapp.repositories.UserRepository;

@Service
public class TodoServiceImpl {
	
	@Autowired
	private TodoRepository todoRepo; 




}
