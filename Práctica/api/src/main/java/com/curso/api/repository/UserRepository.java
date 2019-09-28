package com.curso.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.api.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
	/**
	 * Método para buscar un UserEntity por nombre
	 * @param name
	 * @return Devuelve un Optional<UserEntity>
	 */
	Optional<UserEntity> findByName(String name);
}
