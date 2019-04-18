package com.leadspring.mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;

public class ListTest {

	@Test
	public void listSizeMethodMock() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2);
		assertEquals(2, listMock.size());
	}
	
	@Test
	public void listSizeMethodMock_ReturnMultipleValues() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2).thenReturn(3);
		assertEquals(2, listMock.size());
		assertEquals(3, listMock.size());
	}

	@Test
	public void listGetMethodMock() {
		List listMock = mock(List.class);
		when(listMock.get(0)).thenReturn("in28Minutes");
		assertEquals("in28Minutes", listMock.get(0));
		assertEquals(null, listMock.get(1)); //nice mock behaviour
	}
	
	@Test
	public void listGetMethodMock_ArgMatchers() {
		List listMock = mock(List.class);
		when(listMock.get(anyInt())).thenReturn("in28Minutes");
		assertEquals("in28Minutes", listMock.get(0));
		assertEquals("in28Minutes", listMock.get(1));
		
	}
	
	@Test(expected=RuntimeException.class )
	public void listGetMethodMock_throwRuntimeException() {
		List listMock = mock(List.class);
		when(listMock.get(anyInt())).thenThrow(new RuntimeException("Something"));
		listMock.get(0);
		
	}
	
	@Test(expected=RuntimeException.class )
	public void listGetMethodMock_mixingUp() {
		List listMock = mock(List.class);
		when(listMock.subList(anyInt(), 5)).thenThrow(new RuntimeException("Something"));
		listMock.subList(0, 5);
		
	}
	
	@Test
	public void listGetMethodMock_ArgMatchers_BDD() {
		
		//Given
		List<String> listMock = mock(List.class);
		
		given(listMock.get(anyInt())).willReturn("in28Minutes");
		
		//When
		String firstElement = listMock.get(0);
		
		//Then
		assertThat(firstElement, is("in28Minutes"));
		
	}

}
