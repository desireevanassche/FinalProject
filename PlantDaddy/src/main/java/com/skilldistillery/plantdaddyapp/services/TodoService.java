package com.skilldistillery.plantdaddyapp.services;

import java.util.List;
import java.util.Set;

import com.skilldistillery.plantdaddyapp.entities.Todo;

public interface TodoService {

	public Set<Todo> index(String username);

	public Todo show(String username, int todoId);
	
	public List<Todo> showTodosByUserPlantd(int userPlantId, String username);

	public Todo create(String username, Todo todo, int userPlantId);

	public Todo update(String username, int todoId, Todo todo);

	public boolean destroy(String username, int todoId);


}
