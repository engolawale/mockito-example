package com.leadspring.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import com.leadspring.data.api.TodoService;

@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplMockitoInjectMocksTest {
	
	//Use Rule instead of Runner because Rule allow for multiple rules
	@Rule
	//public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Mock
	TodoService todoServiceMock;
	
	@InjectMocks
	TodBusinessImpl todBusinessImpl;
	
	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;

	@Test
	public void retrieveTodosRealtedToSpring_UsingAMock_ArraySize() {

		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

		List<String> filteredTods = todBusinessImpl.retrieveTodosRealtedToSpring("Dummy");

		assertEquals(2, filteredTods.size());
	}
	
	@Test
	public void retrieveTodosRealtedToSpring_UsingAMock_ArraySize_WithEmptyList() {

		
		List<String> todos = Arrays.asList();

		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

		List<String> filteredTods = todBusinessImpl.retrieveTodosRealtedToSpring("Dummy");

		assertEquals(0, filteredTods.size());
	}
	
	@Test
	public void retrieveTodosRealtedToSpring_UsingBDD() {

		
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

				
		//When
		List<String> filteredTods = todBusinessImpl.retrieveTodosRealtedToSpring("Dummy");

		
		//Then
		assertThat(filteredTods.size(), is(2));
	}

	@Test
	public void deleteTodosNotRealtedToSpring_UsingBDD() {

		
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

			
		//When
		 todBusinessImpl.deleteTodosNotRealtedToSpring("Dummy");

		
		//Then
		 verify(todoServiceMock).deleteTodos("Learn to Dance");
		 
		 verify(todoServiceMock, times(1)).deleteTodos("Learn to Dance");
		 
		 verify(todoServiceMock, never()).deleteTodos("Learn Spring MVC");
		 
		 verify(todoServiceMock, never()).deleteTodos("Learn Spring");
		
	}
	
	@Test
	public void deleteTodosNotRealtedToSpring_UsingBDD_Argument_Capture() {
		
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
	
		//When
		 todBusinessImpl.deleteTodosNotRealtedToSpring("Dummy");

		
		//Then
		//Define Argument Captor on specific method
		 verify(todoServiceMock).deleteTodos(stringArgumentCaptor.capture());
		 
		//Capture argument
		assertThat(stringArgumentCaptor.getValue(), is("Learn to Dance"));
	}

}
