package com.renting.renting.dto;

import com.renting.renting.entity.RentEntity;
import com.renting.renting.entity.UserEntity;

public class CarDto {
	
	private Integer id;
	private String model;
	private String brand;
	private UserEntity user;
	private RentEntity rent;
	
	/**
	 * Constructor vacío de CarDto
	 */
	public CarDto() {}
	
	/**
	 * Constructor de CarDto
	 * @param id
	 * @param model
	 * @param brand
	 * @param user
	 * @param rent
	 */
	public CarDto(Integer id, String model, String brand, UserEntity user, RentEntity rent) {
		this.id = id;
		this.model = model;
		this.brand = brand;
		this.user = user;
		this.rent = rent;
	}

	/**
	 * Método para obtener el id
	 * @return Devuelve el id de un coche
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Método para establecer el id de un coche
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Método para obtener el modelo
	 * @return Devuelve el modelo de un coche
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Método para establecer el modelo de un coche
	 * @param model
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Método para obtener la marca
	 * @return Devuelve la marca de un coche
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Método para establecer la marca de un coche
	 * @param brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * Método para obtener el usuario
	 * @return Devuelve el usuario de un coche
	 */
	public UserEntity getUser() {
		return user;
	}

	/**
	 * Método para establecer el usuario de un coche
	 * @param user
	 */
	public void setUser(UserEntity user) {
		this.user = user;
	}

	/**
	 * Método para obtener el alquiler
	 * @return Devuelve el alquiler de un coche
	 */
	public RentEntity getRent() {
		return rent;
	}

	/**
	 * Método para establecer el alquiler de un coche
	 * @param rent
	 */
	public void setRent(RentEntity rent) {
		this.rent = rent;
	}
}
