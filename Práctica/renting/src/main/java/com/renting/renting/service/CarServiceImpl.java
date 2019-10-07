package com.renting.renting.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.renting.renting.entity.CarEntity;
import com.renting.renting.entity.RentEntity;
import com.renting.renting.entity.UserEntity;
import com.renting.renting.exception.NotFoundException;
import com.renting.renting.repository.CarRepository;
import com.renting.renting.repository.RentRepository;
import com.renting.renting.repository.UserRepository;

@Service
public class CarServiceImpl implements CarService{
	
	@Autowired CarRepository carRepository;
	@Autowired RentRepository rentRepository;
	@Autowired UserRepository userRepository;

	@Override
	public CarEntity guardar(CarEntity c) {
		CarEntity car = carRepository.save(c);
		return car;
	}

	@Override
	public Optional<CarEntity> buscar(Integer id) {
		Optional<CarEntity> car = carRepository.findById(id);
		return car;
	}

	@Override
	public void actualizar(CarEntity c) {
		carRepository.saveAndFlush(c);
	}

	@Override
	public void eliminar(Integer id) {
		carRepository.deleteById(id);
	}

	@Override
	public Page<CarEntity> buscarTodo(Pageable page) {
		return carRepository.findAll(page);
	}

	@Override
	public CarEntity guardarRent(CarEntity c, Integer idRent) throws NotFoundException {
		RentEntity rent = rentRepository.findById(idRent).orElseThrow(() -> new NotFoundException("Alquiler con ID "+idRent+" no encontrado"));
		c.getRent().add(rent);
		rent.setCar(c);
		rentRepository.save(rent);
		return carRepository.save(c);
	}

	@Override
	public CarEntity guardarUser(CarEntity c, Integer idUser) throws NotFoundException {
		UserEntity user = userRepository.findById(idUser).orElseThrow(() -> new NotFoundException("Usuario con ID "+idUser+" no encontrado"));
		c.setUser(user);
		user.getCar().add(c);
		userRepository.save(user);
		return carRepository.save(c);
	}
}
