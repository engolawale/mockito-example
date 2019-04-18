package com.leadspring.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.leadspring.data.api.TodoService;

public class TodoBusinessImplMockTest {

	@Test
	public void retrieveTodosRealtedToSpring_UsingAMock_ArraySize() {

		TodoService todoServiceMock = mock(TodoService.class);

		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

		TodBusinessImpl todBusinessImpl = new TodBusinessImpl(todoServiceMock);

		List<String> filteredTods = todBusinessImpl.retrieveTodosRealtedToSpring("Dummy");

		assertEquals(2, filteredTods.size());
	}
	
	@Test
	public void retrieveTodosRealtedToSpring_UsingAMock_ArraySize_WithEmptyList() {

		TodoService todoServiceMock = mock(TodoService.class);

		List<String> todos = Arrays.asList();

		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

		TodBusinessImpl todBusinessImpl = new TodBusinessImpl(todoServiceMock);

		List<String> filteredTods = todBusinessImpl.retrieveTodosRealtedToSpring("Dummy");

		assertEquals(0, filteredTods.size());
	}
	
	@Test
	public void retrieveTodosRealtedToSpring_UsingBDD() {

		//Given
		TodoService todoServiceMock = mock(TodoService.class);

		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

		TodBusinessImpl todBusinessImpl = new TodBusinessImpl(todoServiceMock);

		
		//When
		List<String> filteredTods = todBusinessImpl.retrieveTodosRealtedToSpring("Dummy");

		
		//Then
		assertThat(filteredTods.size(), is(2));
	}

	@Test
	public void deleteTodosNotRealtedToSpring_UsingBDD() {

		//Given
		TodoService todoServiceMock = mock(TodoService.class);

		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

		TodBusinessImpl todBusinessImpl = new TodBusinessImpl(todoServiceMock);

		
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

		//Declare Argument Captor
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		
		//Given
		TodoService todoServiceMock = mock(TodoService.class);

		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

		TodBusinessImpl todBusinessImpl = new TodBusinessImpl(todoServiceMock);

		
		//When
		 todBusinessImpl.deleteTodosNotRealtedToSpring("Dummy");

		
		//Then
		//Define Argument Captor on specific method
		 verify(todoServiceMock).deleteTodos(stringArgumentCaptor.capture());
		 
		//Capture argument
		assertThat(stringArgumentCaptor.getValue(), is("Learn to Dance"));
	}

}
