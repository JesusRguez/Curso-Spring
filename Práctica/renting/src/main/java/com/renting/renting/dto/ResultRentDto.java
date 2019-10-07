package com.renting.renting.dto;

import java.util.Date;

public class ResultRentDto {

	private String title;
	private Date initDate;
	private Date endDate;
	private Double price;
	
	/**
	 * Constructor vacío de ResultRentDto
	 */
	public ResultRentDto() {}
	
	/**
	 * Constructor de ResultRentDto
	 * @param title
	 * @param init
	 * @param end
	 * @param price
	 */
	public ResultRentDto(String title, Date initDate, Date endDate, Double price) {
		super();
		this.title = title;
		this.initDate = initDate;
		this.endDate = endDate;
		this.price = price;
	}

	/**
	 * Método para obtener el título
	 * @return Devuelve el título de un ResultRentDto
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Método para establecer el título de un ResultRentDto
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Método para obtener la fecha de inicio
	 * @return Devuelve la fecha de inicio de un ResultRentDto
	 */
	public Date getInitDate() {
		return initDate;
	}

	/**
	 * Método para establecer la fecha de inicio de un ResultRentDto
	 * @param init
	 */
	public void setInitDate(Date init) {
		this.initDate = init;
	}

	/**
	 * Método para obterner la fecha de fin
	 * @return Devuelve la fecha de fin de un ResultRentDto
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Método para establecer la fechade fin de un ResultRentDto
	 * @param end
	 */
	public void setEndDate(Date end) {
		this.endDate = end;
	}

	/**
	 * Método paraobtener el precio
	 * @return Devuelve el precio de un ResultRentDto
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Método para establecer el precio de un ResultRentDto
	 * @param price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
