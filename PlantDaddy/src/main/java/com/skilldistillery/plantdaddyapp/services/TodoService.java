package com.skilldistillery.plantdaddyapp.services;

import java.util.Set;

import com.skilldistillery.plantdaddyapp.entities.Todo;
import com.skilldistillery.plantdaddyapp.entities.UserPlant;

public interface TodoService {

	public Set<Todo> index(String username);

	public Todo show(String username, int todoId);

	public Todo create(String username, Todo todo, int userPlantId);

	public Todo update(String username, int todoId, Todo todo);

	public boolean destroy(String username, int todoId);


}
