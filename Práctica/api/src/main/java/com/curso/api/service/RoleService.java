package com.curso.api.service;

import java.util.Optional;

import com.curso.api.model.entity.Role;

public interface RoleService {
	
	/**
	 * Método para guardar un Role en la base de datos
	 * @param r
	 * @return Devuelve un Optional<Role>
	 */
	Optional<Role> guardar(Role r);
}
