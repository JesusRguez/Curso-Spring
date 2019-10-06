package com.renting.renting.dto;

import java.util.ArrayList;
import java.util.List;

import com.renting.renting.entity.CarEntity;
import com.renting.renting.entity.RentEntity;

public class UserDto {
	
	private Integer id;
	private String name;
	private Integer age;
	private List<CarEntity> cars = new ArrayList<>();
	private List<RentEntity> rents = new ArrayList<>();

	/**
	 * Constructor vacío de UserDto
	 */
	public UserDto() {}
	
	/**
	 * Constructor de UserDto
	 * @param id
	 * @param name
	 * @param car
	 * @param rent
	 */
	public UserDto(Integer id, String name, Integer age, List<CarEntity> cars, List<RentEntity> rents) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.cars = cars;
		this.rents = rents;
	}

	/**
	 * Método para obtener el id
	 * @return Devuelve el id de un usuario
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Método para establecer un id a un usuario
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Método para obtener el nombre
	 * @return Devuelve el nombre de un usuario
	 */
	public String getName() {
		return name;
	}

	/**
	 * Método para establecer el nombre de un usuario
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Método para devolver la edad
	 * @return Devuelve la edad de un usuario
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * Método para establecer la edad de un usuario
	 * @param age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * Método para obtener el coche
	 * @return Devuelve el coche de un usuario
	 */
	public List<CarEntity> getCar() {
		return cars;
	}

	/**
	 * Método para establecer el coche de un usuario
	 * @param car
	 */
	public void setCar(List<CarEntity> cars) {
		this.cars = cars;
	}
	
	/**
	 * Método para obtener el alquiler
	 * @return Devuelve el alquiler de un usuario
	 */
	public List<RentEntity> getRent() {
		return rents;
	}
	
	/**
	 * Método para establecer el alquiler de un usuario
	 * @param rent
	 */
	public void setRent(List<RentEntity> rents) {
		this.rents = rents;
	}
}
