package com.in28minutes.rest.webservices.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoHardcodedService {
	
	private static List<Todo> todos = new ArrayList<Todo>();
	private static int idCounter = 0;
	
	static{
		todos.add(new Todo(++idCounter, "in28minutes", "Learn Kafka", new Date(), false));
		todos.add(new Todo(++idCounter, "in28minutes", "Learn AWS", new Date(), false));
		todos.add(new Todo(++idCounter, "in28minutes", "Learn MVC", new Date(), false));
	}
	
	public List<Todo> findAll(){
		return todos;
	}

}
