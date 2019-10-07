package com.renting.renting.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.renting.renting.entity.CarEntity;
import com.renting.renting.entity.RentEntity;
import com.renting.renting.entity.UserEntity;
import com.renting.renting.exception.ValidationException;
import com.renting.renting.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class TestUserService {
	
	List<CarEntity> cars = new ArrayList<CarEntity>();
	List<RentEntity> rents = new ArrayList<RentEntity>();
	Integer age = 34;
	Integer id = 1;
	String name = "pepe";
	
	@InjectMocks private UserService userService = new UserServiceImpl();
	
	@Mock private UserRepository userRepository;
	
	@Test
	public void testBuscarTodosFound() throws ValidationException {
		UserEntity user = new UserEntity(id,name, age, cars, rents);
		userService.buscarTodo(null);
		Mockito.verify(userRepository).findAll();
		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getId());
		Assert.assertEquals(id, user.getId());
		Assert.assertNotNull(user.getName());
		Assert.assertEquals(name, user.getName());
		Assert.assertNotNull(user.getAge());
		Assert.assertEquals(age, user.getAge());
		Assert.assertNotNull(user.getCar());
		Assert.assertEquals(cars, user.getCar());
		Assert.assertNotNull(user.getRent());
		Assert.assertEquals(rents, user.getRent());
	}
	
	@Test
	public void testBuscarTodosNotFound() {
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());
		final Optional<UserEntity> user = userService.buscar(id);
		Assert.assertNotNull(user);
		Assert.assertEquals(user, Optional.empty());
		Assert.assertFalse(user.isPresent());
	}
	
	@Test
	public void buscarIdFound() throws ValidationException {
		UserEntity user = new UserEntity(id,name, age, cars, rents);
		userService.guardar(user);
		Mockito.verify(userRepository).save(user);
		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getId());
		Assert.assertEquals(id, user.getId());
		Assert.assertNotNull(user.getName());
		Assert.assertEquals(name, user.getName());
		Assert.assertNotNull(user.getAge());
		Assert.assertEquals(age, user.getAge());
		Assert.assertNotNull(user.getCar());
		Assert.assertEquals(cars, user.getCar());
		Assert.assertNotNull(user.getRent());
		Assert.assertEquals(rents, user.getRent());
	}
	
	@Test
	public void buscarIdNotFound() {
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());
		final Optional<UserEntity> user = userService.buscar(id);
		Assert.assertNotNull(user);
		Assert.assertEquals(user, Optional.empty());
		Assert.assertFalse(user.isPresent());
	}
	
	@Test
	public void testGuardarOk() throws ValidationException {
		UserEntity user = new UserEntity(id,name, age, cars, rents);
		userService.guardar(user);
		Mockito.verify(userRepository).save(user);
		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getId());
		Assert.assertEquals(user.getId(), id);
		Assert.assertNotNull(user.getName());
		Assert.assertEquals(name, user.getName());
		Assert.assertNotNull(user.getAge());
		Assert.assertEquals(age, user.getAge());
		Assert.assertNotNull(user.getCar());
		Assert.assertEquals(cars, user.getCar());
		Assert.assertNotNull(user.getRent());
		Assert.assertEquals(rents, user.getRent());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGuardarKo() throws ValidationException {
		UserEntity b = null;
		userService.guardar(b);
		Mockito.verify(userRepository).save(b);
	}
	
	@Test
	public void testActualizarOk() throws ValidationException {
		UserEntity user = new UserEntity(id,name, age, cars, rents);
		userService.guardar(user);
		user.setName("Manuel");
		user.setAge(35);
		Mockito.when(userRepository.saveAndFlush(user)).thenReturn(user);
		userService.actualizar(user);
		Assert.assertNotNull(user);
		Assert.assertEquals(user.getId(), id);
		Assert.assertEquals(user.getAge(), 35,0);
		Assert.assertEquals(user.getName(), "Manuel");
	}
	
	@Test
	public void testActualizarKo() throws ValidationException {
		UserEntity user = new UserEntity(id,name, age, cars, rents);
		userService.guardar(user);
		user.setName("Manuel");
		user.setAge(35);
		Mockito.when(userRepository.saveAndFlush(user)).thenReturn(user);
		userService.actualizar(user);
		Assert.assertNotNull(user);
		Assert.assertEquals(user.getId(), id);
		Assert.assertNotEquals(user.getAge(), age);
		Assert.assertNotEquals(user.getName(), name);
	}
	
	@Test
	public void eliminarOk() throws ValidationException {
		UserEntity user = new UserEntity(id,name, age, cars, rents);
		userService.guardar(user);
		userService.eliminar(id);
		Mockito.verify(userRepository).delete(user);
	}
	
	@Test
	public void eliminarKo() throws ValidationException {
		UserEntity user = new UserEntity(id,name, age, cars, rents);
		userService.guardar(user);
		userService.eliminar(id);
		Mockito.verify(userRepository).delete(user);
	}
}
