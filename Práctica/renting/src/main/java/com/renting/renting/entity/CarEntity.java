package com.renting.renting.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name ="Car")
public class CarEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String model;
	private String brand;
	@ManyToOne(fetch = FetchType.LAZY)
	//@JsonBackReference
	@JsonIgnore
	private UserEntity user;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
	//@JsonBackReference
	@JsonIgnore
	private List<RentEntity> rents = new ArrayList<>();
	
	/**
	 * Constructor vacío de CarEntity
	 */
	public CarEntity() {}
	
	/**
	 * Constructor de CarEntity
	 * @param id
	 * @param model
	 * @param brand
	 * @param user
	 * @param rent
	 */
	public CarEntity(Integer id, String model, String brand, UserEntity user, List<RentEntity> rents) {
		this.id = id;
		this.model = model;
		this.brand = brand;
		this.user = user;
		this.rents = rents;
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
	@JsonIgnore
	public List<RentEntity> getRent() {
		return rents;
	}

	/**
	 * Método para establecer el alquiler de un coche
	 * @param rent
	 */
	@JsonProperty
	public void setRent(List<RentEntity> rent) {
		this.rents = rent;
	}
}
