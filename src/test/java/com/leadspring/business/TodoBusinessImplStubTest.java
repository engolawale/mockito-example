package com.leadspring.business;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.leadspring.data.api.TodoService;
import com.leadspring.data.api.TodoServiceStub;

public class TodoBusinessImplStubTest {

	@Test
	public void retrieveTodosRealtedToSpring_UsingAStub_ArraySize() {
        
		TodoService todoServiceStub = new TodoServiceStub();
		TodBusinessImpl todBusinessImpl = new TodBusinessImpl(todoServiceStub);
         List<String> filteredTods = todBusinessImpl.retrieveTodosRealtedToSpring("dummyUser");
         assertEquals(2, filteredTods.size());
	}
	
	@Test
	public void retrieveTodosRealtedToSpring_UsingAStub_ArrayContent() {
        
		TodoService todoServiceStub = new TodoServiceStub();
		TodBusinessImpl todBusinessImpl = new TodBusinessImpl(todoServiceStub);
         List<String> filteredTods = todBusinessImpl.retrieveTodosRealtedToSpring("dummyUser");
         assertEquals("Learn Spring MVC", filteredTods.get(0));
         assertEquals("Learn Spring", filteredTods.get(1));
         
	}

}
