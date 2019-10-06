package com.renting.renting.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.renting.renting.entity.CarEntity;
import com.renting.renting.entity.RentEntity;
import com.renting.renting.exception.NotFoundException;
import com.renting.renting.repository.CarRepository;
import com.renting.renting.repository.RentRepository;

@Service
public class RentServiceImpl implements RentService{
	
	@Autowired RentRepository rentRepository;
	@Autowired CarRepository carRepository;

	@Override
	public RentEntity guardar(RentEntity r) {
		RentEntity rent = rentRepository.save(r);
		return rent;
	}

	@Override
	public Optional<RentEntity> buscar(Integer id) {
		Optional<RentEntity> rent = rentRepository.findById(id);
		return rent;
	}

	@Override
	public void actualizar(RentEntity r) {
		rentRepository.saveAndFlush(r);
	}

	@Override
	public void eliminar(Integer id) {
		rentRepository.deleteById(id);
	}

	@Override
	public Page<RentEntity> buscarTodo(Pageable page) {
		return rentRepository.findAll(page);
	}

	@Override
	public RentEntity guardarRentCar(RentEntity r, Integer idCar) throws NotFoundException {
		CarEntity car = carRepository.findById(idCar).orElseThrow(() -> new NotFoundException("Coche con ID "+idCar+" no encontrado"));
		r.setCar(car);
		car.getRent().add(r);
		carRepository.save(car);
		return rentRepository.save(r);
	}

	@Override
	public RentEntity guardarRentUser(RentEntity r, Integer idUser) {
		// TODO Auto-generated method stub
		return null;
	}
}
