package com.skilldistillery.plantdaddyapp.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.plantdaddyapp.entities.Todo;
import com.skilldistillery.plantdaddyapp.entities.User;
import com.skilldistillery.plantdaddyapp.entities.UserPlant;
import com.skilldistillery.plantdaddyapp.repositories.TodoRepository;
import com.skilldistillery.plantdaddyapp.repositories.UserPlantRepository;
import com.skilldistillery.plantdaddyapp.repositories.UserRepository;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoRepository todoRepo;
	
	@Autowired 
	private UserRepository userRepo; 
	
	@Autowired
	private UserPlantRepository plantRepo;

	@Override
	public Set<Todo> index(String username) {
		System.out.println(username);
	  return todoRepo.findByUserPlant_User_Username(username);
	}
	

	@Override
	public Todo show(String username, int todoId) {
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
	public Todo create(String username, Todo todo, int userPlantId) {
		System.out.println("inside impl: " + todo);
		
		User user = userRepo.findByUsername(username);
		Optional<UserPlant> plantOp = plantRepo.findById(userPlantId);
		System.out.println(plantOp);
		if (plantOp.isPresent() &&	user != null) {
		todo.setUserPlant(plantOp.get());
		}
		return todoRepo.saveAndFlush(todo);
	}

	
	@Override
	public Todo update(String username, int todoId, Todo todo) {
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
	public boolean destroy(String username, int todoId) {
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


	@Override
	public List<Todo> showTodosByUserPlantd(int userPlantId, String username) {
			System.out.println(userPlantId);
			
			List<Todo> resultsList = todoRepo.findByUserPlant_Id(userPlantId);
				System.out.println(resultsList);
		return  resultsList;
	}
	





}
