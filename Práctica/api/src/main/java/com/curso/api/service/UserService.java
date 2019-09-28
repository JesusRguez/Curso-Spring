package com.curso.api.service;

import java.util.List;
import java.util.Optional;

import com.curso.api.model.entity.UserEntity;

public interface UserService {
	
	/**
	 * Método para guardar un UserEntity en la base de datos
	 * @param u
	 * @return Devuelve un UserEntity
	 */
	public UserEntity guardar(UserEntity u);
	
	/**
	 * Método para buscar un UserEntity por id en la base de datos
	 * @param id
	 * @return Devuelve un Optional<UserEntity>
	 */
	public Optional<UserEntity> buscar(Integer id);
	
	/**
	 * Método para buscar todos los objetos de tipo UserEntity en la base de daatos
	 * @return Devuelve una lista de UserEntity
	 */
	public List<UserEntity> buscarTodo();
	
	/**
	 * Método para buscar un UserEntity que puede que no esté en la base de datos
	 * @param id
	 * @return Devuelve el UserEntity si está y si no, devuelve un UserEntity vacío
	 */
	public UserEntity findByIdNotSafe(Integer id);
	
	/**
	 * Método para buscar un UserEntity por nombre
	 * @param n
	 * @return Devuelve un Optional<UserEntity>
	 */
	public Optional<UserEntity> buscaPorNombre(String n);
	
	/**
	 * Método para actualizar un UserEntity en la base de datos
	 * @param u
	 */
	public void actualizar(UserEntity u);
	
	/**
	 * Método para eliminar un UserEntity de la base de datos por su id
	 * @param id
	 */
	public void eliminar(Integer id);
}
