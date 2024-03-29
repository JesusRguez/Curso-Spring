package com.renting.renting.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.renting.renting.entity.CarEntity;
import com.renting.renting.exception.NotFoundException;

public interface CarService {

	/**
	 * Método para guardar un CarEntity en la base de datos
	 * @param c
	 * @return Devuelve un CarEntity
	 */
	public CarEntity guardar(CarEntity c);
	
	/**
	 * Método para guardar un alquiler dentro de un CarEntity
	 * @param c
	 * @param idRent
	 * @return Devuelve el CarEntity modificado
	 * @throws NotFoundException
	 */
	public CarEntity guardarRent(CarEntity c, Integer idRent) throws NotFoundException;
	
	/**
	 * Método para guardar un usuario dentro de un CarEntity
	 * @param c
	 * @param idUser
	 * @return Devuelve el CarEntity modificado
	 * @throws NotFoundException
	 */
	public CarEntity guardarUser(CarEntity c, Integer idUser) throws NotFoundException;
	
	/**
	 * Método para buscar un CarEntity por id en la base de datos
	 * @param id
	 * @return Devuelve un Optional<CarEntity>
	 */
	public Optional<CarEntity> buscar(Integer id);
	
	/**
	 * Método para actualizar un CarEntity en la base de datos
	 * @param c
	 */
	public void actualizar(CarEntity c);
	
	/**
	 * Método para eliminar un CarEntity de la base de datos por su id
	 * @param id
	 */
	public void eliminar(Integer id);
	
	/**
	 * Método que busca a todos los usuarios de la base de datos y recibe un objeto Pageable
	 * @param page
	 * @return Devuelve una paginación con los coches de la base de datos
	 */
	public Page<CarEntity> buscarTodo(Pageable page);
}
