package com.curso.api.service;

import java.util.Optional;

import com.curso.api.model.entity.UserEntity;

public interface UserService {
	public UserEntity guardar(UserEntity u);
	public Optional<UserEntity> buscar(Integer id);
	public UserEntity findByIdNotSafe(Integer id);
	public Optional<UserEntity> buscaPorNombre(String n);
}
