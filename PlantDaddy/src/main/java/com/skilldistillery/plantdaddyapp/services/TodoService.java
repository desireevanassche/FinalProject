package com.skilldistillery.plantdaddyapp.services;

import java.util.Set;

import com.skilldistillery.plantdaddyapp.entities.Todo;

public interface TodoService {

	public Set<Todo> index(Integer userId);

	public Todo show(Integer userId, Integer taskId);

	public Todo create(Integer userId, Todo todo);

	public Todo update(Integer userId, Integer taskId, Todo todo);

	public boolean destroy(Integer userId, Integer taskId);
}
