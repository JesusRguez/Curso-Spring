package com.renting.renting.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="Rent")
public class RentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRent;
	@ManyToOne(fetch = FetchType.LAZY)
	private UserEntity user;
	@ManyToOne(fetch = FetchType.LAZY)
	private CarEntity car;
	private Date initDate;
	private Date finalDate;
	private Double price;

	/**
	 * Constructor vacío de RentEntity
	 */
	public RentEntity() {}

	/**
	 * Constructor de RentEntity
	 * @param user
	 * @param car
	 * @param initDate
	 * @param finalDate
	 * @param price
	 */
	public RentEntity(UserEntity user, CarEntity car, Date initDate, Date finalDate, Double price) {
		this.user = user;
		this.car = car;
		this.initDate = initDate;
		this.finalDate = finalDate;
		this.price = price;
	}

	/**
	 * Método para obtener el usuario
	 * @return Devuelve el usuario de un alquiler
	 */
	public UserEntity getUser() {
		return user;
	}

	/**
	 * Método para establecer el usuario de un alquiler
	 * @param user
	 */
	public void setUser(UserEntity user) {
		this.user = user;
	}

	/**
	 * Método para obtener el coche
	 * @return Devuelve el coche de un alquiler
	 */
	public CarEntity getCar() {
		return car;
	}

	/**
	 * Método para establecer el coche de un alquiler
	 * @param car
	 */
	public void setCar(CarEntity car) {
		this.car = car;
	}

	/**
	 * Método para obtener la fecha de inicio
	 * @return Devuelve la feha de inicio de un alquiler
	 */
	public Date getInitDate() {
		return initDate;
	}

	/**
	 * Método para establecer la fecha de inicio de un alquiler
	 * @param initDate
	 */
	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	/**
	 * Método para obtener la fecha de fin
	 * @return Devuelve la fecha de fin de un alquiler
	 */
	public Date getFinalDate() {
		return finalDate;
	}

	/**
	 * Método para establecer la fecha de fin de un alquiler
	 * @param finalDate
	 */
	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

	/**
	 * Métodopara obtener el precio
	 * @return Devuelve el precio de un alquiler
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Método para establecer el precio de un alquiler
	 * @param price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
}
