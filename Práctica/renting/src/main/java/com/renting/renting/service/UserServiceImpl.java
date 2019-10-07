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
import com.renting.renting.exception.ValidationException;
import com.renting.renting.repository.CarRepository;
import com.renting.renting.repository.RentRepository;
import com.renting.renting.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired UserRepository userRepository;
	@Autowired CarRepository carRepository;
	@Autowired RentRepository rentRepository;

	@Override
	public UserEntity guardar(UserEntity u) throws ValidationException{
		if (u.getAge()<18) {
			throw new ValidationException("Tienes que ser mayor de 18 años para registrarte en el sistema");
		} else {
			return userRepository.save(u);
		}
	}

	@Override
	public Optional<UserEntity> buscar(Integer id) {
		Optional<UserEntity> user = userRepository.findById(id);
		return user;
	}

	@Override
	public void actualizar(UserEntity u) {
		userRepository.saveAndFlush(u);
	}

	@Override
	public void eliminar(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	public Page<UserEntity> buscarTodo(Pageable page) {
		return userRepository.findAll(page);
	}

	@Override
	public UserEntity guardarCar(UserEntity u, Integer idCar) throws NotFoundException {
		CarEntity car = carRepository.findById(idCar).orElseThrow(() -> new NotFoundException("Coche con ID "+idCar+" no encontrado"));
		u.getCar().add(car);
		car.setUser(u);
		carRepository.save(car);
		return userRepository.save(u);
	}

	@Override
	public UserEntity guardarRent(UserEntity u, Integer idRent) throws NotFoundException {
		RentEntity rent = rentRepository.findById(idRent).orElseThrow(() -> new NotFoundException("Alquiler con ID "+idRent+" no encontrado"));
		u.getRent().add(rent);
		rent.setUser(u);
		rentRepository.save(rent);
		return userRepository.save(u);
	}
}
