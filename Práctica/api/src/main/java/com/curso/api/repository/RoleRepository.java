package com.curso.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.api.model.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer>{

	/**
	 * Método para buscar un RoleEntity por nombre
	 * @param n
	 * @return Devuelve un Optional<RoleEntity>
	 */
	Optional<RoleEntity> findByName(String n);

}
