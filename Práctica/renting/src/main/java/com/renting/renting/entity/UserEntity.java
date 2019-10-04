package com.renting.renting.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@OneToMany(fetch = FetchType.LAZY)
	private CarEntity car;
	@OneToMany(fetch = FetchType.LAZY)
	private RentEntity rent;

	/**
	 * Constructor vacío de UserEntity
	 */
	public UserEntity() {}
	
	/**
	 * Constructor de UserEntity
	 * @param id
	 * @param name
	 * @param car
	 * @param rent
	 */
	public UserEntity(Integer id, String name, CarEntity car, RentEntity rent) {
		this.id = id;
		this.name = name;
		this.car = car;
		this.rent = rent;
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
	 * Método para obtener el coche
	 * @return Devuelve el coche de un usuario
	 */
	public CarEntity getCar() {
		return car;
	}

	/**
	 * Método para establecer el coche de un usuario
	 * @param car
	 */
	public void setCar(CarEntity car) {
		this.car = car;
	}
	
	/**
	 * Método para obtener el alquiler
	 * @return Devuelve el alquiler de un usuario
	 */
	public RentEntity getRent() {
		return rent;
	}

	/**
	 * Método para establecer el alquiler de un usuario
	 * @param rent
	 */
	public void setRent(RentEntity rent) {
		this.rent = rent;
	}
}
