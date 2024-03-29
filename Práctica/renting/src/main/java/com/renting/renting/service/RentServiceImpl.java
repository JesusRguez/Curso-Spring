package com.renting.renting.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class RentServiceImpl implements RentService{
	
	@Autowired RentRepository rentRepository;
	@Autowired CarRepository carRepository;
	@Autowired UserRepository userRepository;

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
	public RentEntity guardarRentUser(RentEntity r, Integer idUser) throws NotFoundException {
		UserEntity user = userRepository.findById(idUser).orElseThrow(() -> new NotFoundException("Usuario con ID "+idUser+" no encontrado"));
		r.setUser(user);
		user.getRent().add(r);
		userRepository.save(user);
		return rentRepository.save(r);
	}

	@SuppressWarnings("unchecked")
	@Override
	public double profit(Integer idCar, Date init, Date end) throws NotFoundException {
		carRepository.findById(idCar).orElseThrow(() -> new NotFoundException("Coche con id "+idCar+" no enconctrado"));
		Optional<RentEntity> alquileres = rentRepository.findById(idCar);
		List<RentEntity> rents = new ArrayList<>();
		rents = (List<RentEntity>) alquileres.get();
		Double profit = 0.0;
		for(RentEntity entity : rents) 
			if(entity.getInitDate().after(init) && entity.getFinalDate().before(end)) {
				profit += entity.getPrice();
			}
		return profit;
	}
}
