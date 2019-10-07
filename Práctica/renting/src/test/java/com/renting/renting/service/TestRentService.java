package com.renting.renting.service;

import java.util.Date;
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
import com.renting.renting.repository.RentRepository;

public class TestRentService {
	
	UserEntity user = new UserEntity();
	CarEntity car = new CarEntity();
	Integer idRent = 1;
	Date initDate = null;
	Date finalDate = null;
	Double price = 345.0;
	RentEntity rent = new RentEntity(idRent, user, car, initDate, finalDate, price);
	
	@InjectMocks private RentService rentService = new RentServiceImpl();
	
	@Mock private RentRepository rentRepository;
	
	@Test
	public void testBuscarTodosFound() throws ValidationException {
		rentService.buscarTodo(null);
		Mockito.verify(rentRepository).findAll();
		Assert.assertNotNull(rent);
		Assert.assertNotNull(rent.getIdRent());
		Assert.assertEquals(idRent, rent.getIdRent());
		Assert.assertNotNull(rent.getUser());
		Assert.assertEquals(user, rent.getUser());
		Assert.assertNotNull(rent.getCar());
		Assert.assertEquals(car, rent.getCar());
		Assert.assertNotNull(rent.getInitDate());
		Assert.assertEquals(initDate, rent.getInitDate());
		Assert.assertNotNull(rent.getFinalDate());
		Assert.assertEquals(finalDate, rent.getFinalDate());
		Assert.assertNotNull(rent.getPrice());
		Assert.assertEquals(price, rent.getPrice());
	}
	
	@Test
	public void testBuscarTodosNotFound() {
		Mockito.when(rentRepository.findById(idRent)).thenReturn(Optional.empty());
		final Optional<RentEntity> rent = rentService.buscar(idRent);
		Assert.assertNotNull(rent);
		Assert.assertEquals(rent, Optional.empty());
		Assert.assertFalse(rent.isPresent());
	}
	
	@Test
	public void buscarIdFound() throws ValidationException {
		rentService.guardar(rent);
		Mockito.verify(rentRepository).save(rent);
		Assert.assertNotNull(rent);
		Assert.assertNotNull(rent.getIdRent());
		Assert.assertEquals(idRent, rent.getIdRent());
		Assert.assertNotNull(rent.getUser());
		Assert.assertEquals(user, rent.getUser());
		Assert.assertNotNull(rent.getCar());
		Assert.assertEquals(car, rent.getCar());
		Assert.assertNotNull(rent.getInitDate());
		Assert.assertEquals(initDate, rent.getInitDate());
		Assert.assertNotNull(rent.getFinalDate());
		Assert.assertEquals(finalDate, rent.getFinalDate());
		Assert.assertNotNull(rent.getPrice());
		Assert.assertEquals(price, rent.getPrice());
	}
	
	@Test
	public void buscarIdNotFound() {
		Mockito.when(rentRepository.findById(idRent)).thenReturn(Optional.empty());
		final Optional<RentEntity> rent = rentService.buscar(idRent);
		Assert.assertNotNull(rent);
		Assert.assertEquals(rent, Optional.empty());
		Assert.assertFalse(rent.isPresent());
	}
	
	@Test
	public void testGuardarOk() throws ValidationException {
		rentService.guardar(rent);
		Mockito.verify(rentRepository).save(rent);
		Assert.assertNotNull(rent);
		Assert.assertNotNull(rent.getIdRent());
		Assert.assertEquals(idRent, rent.getIdRent());
		Assert.assertNotNull(rent.getUser());
		Assert.assertEquals(user, rent.getUser());
		Assert.assertNotNull(rent.getCar());
		Assert.assertEquals(car, rent.getCar());
		Assert.assertNotNull(rent.getInitDate());
		Assert.assertEquals(initDate, rent.getInitDate());
		Assert.assertNotNull(rent.getFinalDate());
		Assert.assertEquals(finalDate, rent.getFinalDate());
		Assert.assertNotNull(rent.getPrice());
		Assert.assertEquals(price, rent.getPrice());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGuardarKo() throws ValidationException {
		RentEntity b = null;
		rentService.guardar(b);
		Mockito.verify(rentRepository).save(b);
	}
	
	@Test
	public void testActualizarOk() throws ValidationException {
		rentService.guardar(rent);
		rent.setPrice(654.8);
		Mockito.when(rentRepository.saveAndFlush(rent)).thenReturn(rent);
		rentService.actualizar(rent);
		Assert.assertNotNull(rent);
		Assert.assertEquals(rent.getIdRent(), idRent);
		Assert.assertEquals(rent.getUser(), user);
		Assert.assertEquals(rent.getCar(), car);
		Assert.assertEquals(rent.getInitDate(), initDate);
		Assert.assertEquals(rent.getFinalDate(), finalDate);
		Assert.assertEquals(rent.getPrice(), 654,8);
	}
	
	@Test
	public void testActualizarKo() throws ValidationException {
		rentService.guardar(rent);
		rent.setPrice(654.8);
		Mockito.when(rentRepository.saveAndFlush(rent)).thenReturn(rent);
		rentService.actualizar(rent);
		Assert.assertNotNull(rent);
		Assert.assertEquals(rent.getIdRent(), idRent);
		Assert.assertEquals(rent.getUser(), user);
		Assert.assertEquals(rent.getCar(), car);
		Assert.assertEquals(rent.getInitDate(), initDate);
		Assert.assertEquals(rent.getFinalDate(), finalDate);
		Assert.assertEquals(rent.getPrice(), price);
	}
	
	@Test
	public void eliminarOk() throws ValidationException {
		rentService.guardar(rent);
		rentService.eliminar(idRent);
		Mockito.verify(rentRepository).delete(rent);
	}
	
	@Test
	public void eliminarKo() throws ValidationException {
		rentService.guardar(rent);
		rentService.eliminar(idRent);
		Mockito.verify(rentRepository).delete(rent);
	}
}
