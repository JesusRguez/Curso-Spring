package com.renting.renting.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.renting.renting.entity.CarEntity;
import com.renting.renting.entity.RentEntity;
import com.renting.renting.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService{
	
	@Autowired CarRepository carRepository;

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
	public void guardarRent(CarEntity c, RentEntity r) {
		
	}
}
