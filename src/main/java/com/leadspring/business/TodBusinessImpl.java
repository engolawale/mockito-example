package com.leadspring.business;

import java.util.ArrayList;
import java.util.List;

import com.leadspring.data.api.TodoService;

public class TodBusinessImpl {
	
	private TodoService todoService;

	public TodBusinessImpl(TodoService todoService) {
		super();
		this.todoService = todoService;
	}
	
	public List<String> retrieveTodosRealtedToSpring(String user){
		List<String> filteredTodos = new ArrayList<String>();
		List<String> todos = todoService.retrieveTodos(user);
		
		for(String todo:todos)
			if(todo.contains("Spring")) {
				filteredTodos.add(todo);
			}
			
		return filteredTodos;
	}
	
	public void deleteTodosNotRealtedToSpring(String user){
		List<String> todos = todoService.retrieveTodos(user);
		for(String todo:todos)
			if( !todo.contains("Spring")) {
				todoService.deleteTodos(todo);
			}
			
		return ;
	}

}
