package com.curso.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.curso.api.model.entity.RoleEntity;

public interface RoleService {
	
	/**
	 * Método para guardar un Role en la base de datos
	 * @param r
	 * @return Devuelve un Optional<Role>
	 */
	Optional<RoleEntity> guardar(RoleEntity r);
	
	/**
	 * Método para buscar un rol por su id
	 * @param id
	 * @return Devuelve el método cuyo identificador es id
	 */
	public Optional<RoleEntity> buscar(Integer id);
	
	/**
	 * Método para buscar todos los objetos de tipo RoleEntity en la base de daatos
	 * @return Devuelve una lista de RoleEntity
	 */
	public List<RoleEntity> buscarTodo();
	
	/**
	 * Método para buscar un RoleEntity que puede que no esté en la base de datos
	 * @param id
	 * @return Devuelve el RoleEntity si está y si no, devuelve un RoleEntity vacío
	 */
	public RoleEntity findByIdNotSafe(Integer id);
	
	/**
	 * Método para buscar un RoleEntity por nombre
	 * @param n
	 * @return Devuelve un Optional<RoleEntity>
	 */
	public Optional<RoleEntity> buscaPorNombre(String n);
	
	/**
	 * Método para actualizar un RoleEntity en la base de datos
	 * @param r
	 */
	public void actualizar(RoleEntity r);
	
	/**
	 * Método para eliminar un RoleEntity de la base de datos por su id
	 * @param id
	 */
	public void eliminar(Integer id);
	
	/**
	 * Método que busca a todos los usuarios de la base de datos y recibe un objeto Pageable
	 * @param page
	 * @return Devuelve una paginación con los roles de la base de datos
	 */
	public Page<RoleEntity> buscarTodo(Pageable page);

	/**
	 * Método para eliminar un rol completo de la base de datos
	 * @param r
	 */
	void eliminar(RoleEntity r);
}
