package com.renting.renting.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.renting.renting.entity.UserEntity;
import com.renting.renting.exception.NotFoundException;
import com.renting.renting.exception.ValidationException;


public interface UserService {
	/**
	 * Método para guardar un UserEntity en la base de datos
	 * @param u
	 * @return Devuelve un UserEntity
	 * @throws ValidationException 
	 */
	public UserEntity guardar(UserEntity u) throws ValidationException;
	
	/**
	 * Método para guardar un coche dentro de un UserEntity
	 * @param u
	 * @param idCar
	 * @return Devuelve el UserEntity modificado
	 * @throws NotFoundException
	 */
	public UserEntity guardarCar(UserEntity u, Integer idCar) throws NotFoundException;
	
	/**
	 * Método para guardar un alquiler dentro de un UserEntity
	 * @param u
	 * @param idRent
	 * @return Devuelve el UserEntity modificado
	 * @throws NotFoundException
	 */
	public UserEntity guardarRent(UserEntity u, Integer idRent) throws NotFoundException;
	
	/**
	 * Método para buscar un UserEntity por id en la base de datos
	 * @param id
	 * @return Devuelve un Optional<UserEntity>
	 */
	public Optional<UserEntity> buscar(Integer id);
	
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
	
	/**
	 * Método que busca a todos los usuarios de la base de datos y recibe un objeto Pageable
	 * @param page
	 * @return Devuelve una paginación con los usuarios de la base de datos
	 */
	public Page<UserEntity> buscarTodo(Pageable page);
}
