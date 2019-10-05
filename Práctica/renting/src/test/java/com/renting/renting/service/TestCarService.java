package com.renting.renting.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.renting.renting.entity.CarEntity;
import com.renting.renting.repository.CarRepository;

public class TestCarService {

private final static Integer CAR_ID = 1;
	
	@InjectMocks private CarService carService = new CarServiceImpl();
	
	@Mock private CarRepository carRepository;
	
	//@Test
	public void testBuscarTodosFound() {
		
	}
	
	//@Test
	public void testBuscarTodosNotFound() {
		
	}
	
	//@Test
	public void buscarIdFound() {
		final CarEntity carEmtity = new CarEntity();
		Mockito.when(carRepository.findById(CAR_ID)).thenReturn(Optional.ofNullable(carEmtity));
		final Optional<CarEntity> car = carService.buscar(CAR_ID);
		Assert.assertNotNull(car);
		Assert.assertNotNull(car.get());
	}
	
	//@Test
	public void buscarIdNotFound() {
		Mockito.when(carRepository.findById(CAR_ID)).thenReturn(Optional.empty());
		final Optional<CarEntity> car = carService.buscar(CAR_ID);
		Assert.assertNotNull(car);
		Assert.assertEquals(car, Optional.empty());
		Assert.assertFalse(car.isPresent());
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
