package com.skilldistillery.plantdaddyapp.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.plantdaddyapp.entities.Todo;
import com.skilldistillery.plantdaddyapp.services.TodoService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4295" })
public class TodoController {

	@Autowired
	TodoService todoServ;

	@GetMapping("todos")
	public Set<Todo> index(Principal principal) {
	
		Set<Todo>results= todoServ.index(principal.getName());
		System.out.println(results);
		 return results;
	}

	@GetMapping("todos/{tid}")
	public Todo show(HttpServletResponse resp, @PathVariable("tid") int todoId, Principal principal) {
		Todo todo = todoServ.show(principal.getName(), todoId);
		if (todo == null) {
			resp.setStatus(404);
		}
		return todo;
	}
	
	@GetMapping("todos/userplants/{uPId}")
	public List<Todo> allUserPlantTodos(@PathVariable("uPId") int userPlantId,
			HttpServletResponse res,
			Principal principal){
		
		System.out.println(userPlantId);
		if(userPlantId != 0) {
			List<Todo> userPlantTodos = todoServ.showTodosByUserPlantd(userPlantId, principal.getName());
			return userPlantTodos;
		}
		
		return null;
	}
	
	

//	@PostMapping("todos")
//	public Todo create(@RequestBody Todo todo, Principal principal, HttpServletResponse res, @PathVariable int id) {
//		
//		
//		return todoServ.create(todo, id);
//	}
//	
	@PostMapping("todos/userplants/{userPlantId}")
	public Todo create(@RequestBody Todo todo, Principal principal,
			@PathVariable("") int userPlantId,
			HttpServletResponse res) {
		
		System.out.println(todo);
		
		return todoServ.create(principal.getName(), todo, userPlantId);
	}

	
	@PutMapping("todos/{tid}")
	public Todo update(@PathVariable("tid") int todoId,
			@RequestBody Todo todo,
			Principal principal,
			HttpServletResponse res) {
		System.out.println(todoId);
		return todoServ.update(principal.getName(), todoId, todo);
	}

	
	@DeleteMapping("todos/{tid}")
	public void destroy(HttpServletResponse resp, @PathVariable("tid") int todoId, Principal principal) {
		if (todoServ.destroy(principal.getName(), todoId)) {
			resp.setStatus(204);
		} else {
			resp.setStatus(404);
		}
	}

}
