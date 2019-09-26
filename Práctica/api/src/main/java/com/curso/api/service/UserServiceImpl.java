package com.curso.api.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.api.model.entity.UserEntity;
import com.curso.api.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired private UserRepository repo;	
	
	@Override
	public UserEntity guardar(UserEntity u) {
		// TODO Auto-generated method stub
		//Mirar que el que llegue no sea nulo
		return repo.save(u);
	}

	@Override
	public Optional<UserEntity> buscar(Integer id) {
		// TODO Auto-generated method stub
		Optional<UserEntity> u = repo.findById(id);
		return u;
	}

	@Override
	public UserEntity findByIdNotSafe(Integer id) {
		// TODO Auto-generated method stub
		try {
			return repo.getOne(id);
		} catch (EntityNotFoundException e) {
			// TODO: handle exception
			System.out.println("Fallo en findByIdNotSafe");
		}
		return new UserEntity();
	}

	@Override
	public Optional<UserEntity> buscaPorNombre(String n) {
		// TODO Auto-generated method stub
		return repo.findByName(n);
	}

	

}
