package com.in28minutes.rest.webservices.todo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class TodoResource {
	
	@Autowired
	private TodoHardcodedService service;
	
	//GET /users/{username}/todos
	@GetMapping("/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username){
		return service.findAll();
	}
	
	//GET /users/{username}/todos/{id}
	@GetMapping("/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username, @PathVariable long id){
		return service.findById(id);
	}
	
	//DELETE /delete/{username}/todos/{id}
	@DeleteMapping("delete/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id){
		Todo todo = service.deleteTodo(id);
		if(todo != null){
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	//EDIT/UPDATE a Todo
	//PUT /users/{username}/todos/{todo_id}
	@PutMapping("/users/{username}/todos/{todo_id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long todo_id,
			@RequestBody Todo todo){
		Todo updatedTodo = service.save(todo);
		return new ResponseEntity<Todo>(updatedTodo, HttpStatus.OK);
	}
	
	//SAVE
	//POST /users/{username}/todos/
	@PostMapping("/users/{username}/todos/")
	public ResponseEntity<Void> saveTodo(@PathVariable String username, @PathVariable long todo_id,
			@RequestBody Todo todo){
		Todo createdTodo = service.save(todo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("{id}").buildAndExpand(createdTodo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
