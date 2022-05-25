package com.skilldistillery.plantdaddyapp.services;

import java.util.Set;

import com.skilldistillery.plantdaddyapp.entities.Todo;

public interface TodoService {

	public Set<Todo> index(String username);

	public Todo show(String username, Integer todoId);

	public Todo create(String username, Todo todo);

	public Todo update(String username, Integer todoId, Todo todo);

	public boolean destroy(String username, Integer todoId);


}
