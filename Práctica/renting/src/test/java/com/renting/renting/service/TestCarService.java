package com.renting.renting.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.renting.renting.entity.CarEntity;
import com.renting.renting.entity.RentEntity;
import com.renting.renting.entity.UserEntity;
import com.renting.renting.exception.ValidationException;
import com.renting.renting.repository.CarRepository;

public class TestCarService {
		
	UserEntity user = new UserEntity();
	Integer id = 1;
	String model = "ford";
	String brand = "mustang";
	List<RentEntity> rents = new ArrayList<RentEntity>();
	CarEntity car = new CarEntity(id, model, brand, user, rents);
	
	@InjectMocks private CarService carService = new CarServiceImpl();
	
	@Mock private CarRepository carRepository;
	
	@Test
	public void testBuscarTodosFound() throws ValidationException {
		carService.buscarTodo(null);
		Mockito.verify(carRepository).findAll();
		Assert.assertNotNull(car);
		Assert.assertNotNull(car.getId());
		Assert.assertEquals(id, car.getId());
		Assert.assertNotNull(car.getUser());
		Assert.assertEquals(user, car.getUser());
		Assert.assertNotNull(car.getModel());
		Assert.assertEquals(model, car.getModel());
		Assert.assertNotNull(car.getBrand());
		Assert.assertEquals(brand, car.getBrand());
		Assert.assertNotNull(car.getRent());
		Assert.assertEquals(rents, car.getRent());
	}
	
	@Test
	public void testBuscarTodosNotFound() {
		Mockito.when(carRepository.findById(id)).thenReturn(Optional.empty());
		final Optional<CarEntity> car = carService.buscar(id);
		Assert.assertNotNull(car);
		Assert.assertEquals(car, Optional.empty());
		Assert.assertFalse(car.isPresent());
	}
	
	@Test
	public void buscarIdFound() throws ValidationException {
		carService.guardar(car);
		Mockito.verify(carRepository).save(car);
		Assert.assertNotNull(car);
		Assert.assertNotNull(car.getId());
		Assert.assertEquals(id, car.getId());
		Assert.assertNotNull(car.getUser());
		Assert.assertEquals(user, car.getUser());
		Assert.assertNotNull(car.getModel());
		Assert.assertEquals(model, car.getModel());
		Assert.assertNotNull(car.getBrand());
		Assert.assertEquals(brand, car.getBrand());
		Assert.assertNotNull(car.getRent());
		Assert.assertEquals(rents, car.getRent());
	}
	
	@Test
	public void buscarIdNotFound() {
		Mockito.when(carRepository.findById(id)).thenReturn(Optional.empty());
		final Optional<CarEntity> car = carService.buscar(id);
		Assert.assertNotNull(car);
		Assert.assertEquals(car, Optional.empty());
		Assert.assertFalse(car.isPresent());
	}
	
	@Test
	public void testGuardarOk() throws ValidationException {
		carService.guardar(car);
		Mockito.verify(carRepository).save(car);
		Assert.assertNotNull(car);
		Assert.assertNotNull(car.getId());
		Assert.assertEquals(id, car.getId());
		Assert.assertNotNull(car.getUser());
		Assert.assertEquals(user, car.getUser());
		Assert.assertNotNull(car.getModel());
		Assert.assertEquals(model, car.getModel());
		Assert.assertNotNull(car.getBrand());
		Assert.assertEquals(brand, car.getBrand());
		Assert.assertNotNull(car.getRent());
		Assert.assertEquals(rents, car.getRent());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGuardarKo() throws ValidationException {
		CarEntity b = null;
		carService.guardar(b);
		Mockito.verify(carRepository).save(b);
	}
	
	@Test
	public void testActualizarOk() throws ValidationException {
		carService.guardar(car);
		car.setModel("focus");
		Mockito.when(carRepository.saveAndFlush(car)).thenReturn(car);
		carService.actualizar(car);
		Assert.assertNotNull(car);
		Assert.assertEquals(car.getId(), id);
		Assert.assertEquals(car.getUser(), user);
		Assert.assertEquals(car.getModel(), "focus");
		Assert.assertEquals(car.getBrand(), brand);
		Assert.assertEquals(car.getRent(), rents);
	}
	
	@Test
	public void testActualizarKo() throws ValidationException {
		carService.guardar(car);
		car.setModel("focus");
		Mockito.when(carRepository.saveAndFlush(car)).thenReturn(car);
		carService.actualizar(car);
		Assert.assertNotNull(car);
		Assert.assertEquals(car.getId(), id);
		Assert.assertEquals(car.getUser(), user);
		Assert.assertEquals(car.getModel(), model);
		Assert.assertEquals(car.getBrand(), brand);
		Assert.assertEquals(car.getRent(), rents);
	}
	
	@Test
	public void eliminarOk() throws ValidationException {
		carService.guardar(car);
		carService.eliminar(id);
		Mockito.verify(carRepository).delete(car);
	}
	
	@Test
	public void eliminarKo() throws ValidationException {
		carService.guardar(car);
		carService.eliminar(id);
		Mockito.verify(carRepository).delete(car);
	}
}
