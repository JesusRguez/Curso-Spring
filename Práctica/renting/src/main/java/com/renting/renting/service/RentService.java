package com.renting.renting.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.renting.renting.entity.RentEntity;
import com.renting.renting.exception.NotFoundException;

public interface RentService {

	/**
	 * Método para guardar un RentEntity en la base de datos
	 * @param r
	 * @return Devuelve un RentEntity
	 */
	public RentEntity guardar(RentEntity r);
	
	/**
	 * Método para guardar un coche dentro de un RentEntity
	 * @param r
	 * @param idCar
	 * @return Devuelve el RentEntity modificado
	 * @throws NotFoundException
	 */
	public RentEntity guardarRentCar(RentEntity r, Integer idCar) throws NotFoundException;
	
	/**
	 * Método para guardar un usuario dentro de un RentEntity
	 * @param r
	 * @param idUser
	 * @return Devuelve el RentEntity modificado
	 * @throws NotFoundException
	 */
	public RentEntity guardarRentUser(RentEntity r, Integer idUser) throws NotFoundException;
	
	/**
	 * Método para buscar un RentEntity por id en la base de datos
	 * @param id
	 * @return Devuelve un Optional<RentEntity>
	 */
	public Optional<RentEntity> buscar(Integer id);
	
	/**
	 * Método para actualizar un RentEntity en la base de datos
	 * @param r
	 */
	public void actualizar(RentEntity r);
	
	/**
	 * Método para eliminar un RentEntity de la base de datos por su id
	 * @param id
	 */
	public void eliminar(Integer id);
	
	/**
	 * Método que busca a todos los usuarios de la base de datos y recibe un objeto Pageable
	 * @param page
	 * @return Devuelve una paginación con los alquieleres de la base de datos
	 */
	public Page<RentEntity> buscarTodo(Pageable page);
	
	/**
	 * Método para calcular las ganancias de un alquiler
	 * @param idCar
	 * @param init
	 * @param end
	 * @return
	 * @throws NotFoundException
	 */
	public double profit(Integer idCar, Date init, Date end) throws NotFoundException;
}
