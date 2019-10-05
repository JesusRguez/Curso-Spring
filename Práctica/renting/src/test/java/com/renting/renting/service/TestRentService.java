package com.renting.renting.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.renting.renting.entity.RentEntity;
import com.renting.renting.repository.RentRepository;

public class TestRentService {
	
private final static Integer RENT_ID = 1;
	
	@InjectMocks private RentService rentService = new RentServiceImpl();
	
	@Mock private RentRepository rentRepository;
	
	//@Test
	public void testBuscarTodosFound() {
		
	}
	
	//@Test
	public void testBuscarTodosNotFound() {
		
	}
	
	//@Test
	public void buscarIdFound() {
		final RentEntity rentEmtity = new RentEntity();
		Mockito.when(rentRepository.findById(RENT_ID)).thenReturn(Optional.ofNullable(rentEmtity));
		final Optional<RentEntity> rent = rentService.buscar(RENT_ID);
		Assert.assertNotNull(rent);
		Assert.assertNotNull(rent.get());
	}
	
	//@Test
	public void buscarIdNotFound() {
		Mockito.when(rentRepository.findById(RENT_ID)).thenReturn(Optional.empty());
		final Optional<RentEntity> rent = rentService.buscar(RENT_ID);
		Assert.assertNotNull(rent);
		Assert.assertEquals(rent, Optional.empty());
		Assert.assertFalse(rent.isPresent());
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
