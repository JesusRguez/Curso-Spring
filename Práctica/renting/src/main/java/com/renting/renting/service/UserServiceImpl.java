package com.renting.renting.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.renting.renting.entity.UserEntity;
import com.renting.renting.exception.ValidationException;
import com.renting.renting.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired UserRepository userRepository;

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
}
