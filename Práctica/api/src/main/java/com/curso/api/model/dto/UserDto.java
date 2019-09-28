package com.curso.api.model.dto;

import javax.validation.constraints.NotNull;

public class UserDto {
	private Integer id;
	@NotNull
	private String name;
	private Integer age;
	
	/**
	 * Constructor de UserDto
	 * @param Integer id
	 * @param String name
	 * @param Integer age
	 */
	public UserDto(Integer id, String name, Integer age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	/**
	 * Método para obtener el id
	 * @return Devuelve el id de un UserDto
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Método para establecer el id de un UserDto
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Método para obtener el nombre
	 * @return Devuelve el nombre de un UserDto
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Método para establecer el nombre de un UserDto
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Método para obtener la edad
	 * @return Devuelve la edad de un UserDto
	 */
	public Integer getAge() {
		return age;
	}
	
	/**
	 * Método para establecer la edad de un UserDto
	 * @param age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
}
