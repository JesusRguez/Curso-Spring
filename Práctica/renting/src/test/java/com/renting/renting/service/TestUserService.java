package com.renting.renting.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.renting.renting.entity.UserEntity;
import com.renting.renting.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class TestUserService {
	
	private final static Integer USER_ID = 1;
	
	@InjectMocks private UserService userService = new UserServiceImpl();
	
	@Mock private UserRepository userRepository;
	
	//@Test
	public void testBuscarTodosFound() {
		
	}
	
	//@Test
	public void testBuscarTodosNotFound() {
		
	}
	
	//@Test
	public void buscarIdFound() {
		final UserEntity userEmtity = new UserEntity();
		Mockito.when(userRepository.findById(USER_ID)).thenReturn(Optional.ofNullable(userEmtity));
		final Optional<UserEntity> user = userService.buscar(USER_ID);
		Assert.assertNotNull(user);
		Assert.assertNotNull(user.get());
	}
	
	//@Test
	public void buscarIdNotFound() {
		Mockito.when(userRepository.findById(USER_ID)).thenReturn(Optional.empty());
		final Optional<UserEntity> user = userService.buscar(USER_ID);
		Assert.assertNotNull(user);
		Assert.assertEquals(user, Optional.empty());
		Assert.assertFalse(user.isPresent());
	}
	
	//@Test
	public void testGuardarOk() {
		
	}
	
	//@Test
	public void testGuardarKo() {
		
	}
	
	//@Test
	public void testActualizarOk() {
		
	}
	
	//@Test
	public void testActualizarKo() {
		
	}
	
	//@Test
	public void eliminarOk() {
		
	}
	
	//@Test
	public void eliminarKo() {
		
	}
}
