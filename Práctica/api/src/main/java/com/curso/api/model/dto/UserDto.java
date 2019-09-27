package com.curso.api.model.dto;

import javax.validation.constraints.NotNull;

public class UserDto {
	private Integer id;
	@NotNull
	private String name;
	private Integer age;
	
	//Con todos los parámetros
	public UserDto(Integer id, String name, Integer age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
}
