package com.curso.api.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.curso.api.exception.ValidationException;
import com.curso.api.model.entity.UserEntity;
import com.curso.api.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired private UserRepository repo;	
	
	@Override
	public UserEntity guardar(UserEntity u) throws ValidationException {
		if (u.getAge()<18) {
			throw new ValidationException("Eres muy peque");
		} else {
			return repo.save(u);
		}
		
	}

	@Override
	public Optional<UserEntity> buscar(Integer id) {
		Optional<UserEntity> u = repo.findById(id);
		return u;
	}

	@Override
	public UserEntity findByIdNotSafe(Integer id) {
		try {
			return repo.getOne(id);
		} catch (EntityNotFoundException e) {
			System.out.println("Fallo en findByIdNotSafe");
		}
		return new UserEntity();
	}

	@Override
	public Optional<UserEntity> buscaPorNombre(String n) {
		return repo.findByName(n);
	}

	@Override
	public void actualizar(UserEntity u) {
		repo.saveAndFlush(u);
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public List<UserEntity> buscarTodo() {
		return repo.findAll();
	}

	@Override
	public Page<UserEntity> buscarTodo(Pageable page) {
		return repo.findAll(page);
	}
}
