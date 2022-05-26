package com.skilldistillery.plantdaddyapp.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.plantdaddyapp.entities.Todo;
import com.skilldistillery.plantdaddyapp.entities.User;
import com.skilldistillery.plantdaddyapp.repositories.TodoRepository;
import com.skilldistillery.plantdaddyapp.repositories.UserRepository;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoRepository todoRepo;
	
	@Autowired UserRepository userRepo; 

	@Override
	public Set<Todo> index(String username) {
		System.out.println(username);
	  return todoRepo.findByUserPlant_User_Username(username);
	}
	



	@Override
	public Todo show(String username, Integer todoId) {
		Optional<Todo> op = todoRepo.findById(todoId);
		if(op.isPresent()) {
			Todo result = op.get();
			if(result.getUserPlant().getUser().getUsername().equals(username)) {
				return result;
			}
		}
		return null;
	}
	
	@Override
	public Todo create(String username, Todo todo) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			todo.getUserPlant().setUser(user);
			return todoRepo.saveAndFlush(todo);
		}
		return null;
	}

	@Override
	public Todo update(String username, Integer todoId, Todo todo) {
		Optional<Todo> op = todoRepo.findById(todoId);
		if(op.isPresent()) {
			Todo result = op.get();
			if(result.getUserPlant().getUser().getUsername().equals(username)) {
				todo.setId(todoId);
				todo.getUserPlant().setUser(result.getUserPlant().getUser());
				return todoRepo.saveAndFlush(todo);
			}
		}
		 
		  return null;
	}

	@Override
	public boolean destroy(String username, Integer todoId) {
		Optional<Todo> op = todoRepo.findById(todoId);
		if(op.isPresent()) {
			Todo result = op.get();
			if(result.getUserPlant().getUser().getUsername().equals(username)) {
				todoRepo.deleteById(todoId);
				op = todoRepo.findById(todoId);
				return !op.isPresent();
			}
		}
		return false;
	}
	





}
