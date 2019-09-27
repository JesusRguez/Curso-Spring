package com.curso.api.service;

import java.util.List;
import java.util.Optional;

import com.curso.api.model.entity.UserEntity;

public interface UserService {
	//guardar
	public UserEntity guardar(UserEntity u);
	
	//buscar
	public Optional<UserEntity> buscar(Integer id);
	public List<UserEntity> buscarTodo();
	public UserEntity findByIdNotSafe(Integer id);
	public Optional<UserEntity> buscaPorNombre(String n);
	
	//actualizar
	public void actualizar(UserEntity u);
	
	//eliminar
	public void eliminar(Integer id);
}
