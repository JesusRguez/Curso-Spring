package com.curso.api.model.dto;

import javax.validation.constraints.NotNull;

public class RoleDto {
	private Integer id;
	@NotNull
	private String name;
	
	/**
	 * Constructor de RoleDto
	 * @param id
	 * @param name
	 */
	public RoleDto(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	/**
	 * Método para obtener el ID
	 * @return Devuelve el ID de un Role
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Método para establecer el ID de un Role
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Método para obtener el nombre
	 * @return Devuelve el nombre de un Role
	 */
	public String getName() {
		return name;
	}

	/**
	 * Método para establecer el nombre de un Role
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
