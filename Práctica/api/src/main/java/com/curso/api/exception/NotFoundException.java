package com.curso.api.exception;


public class NotFoundException extends Exception {
	/**
	 * serialVersionUID por defecto a 1L
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de NotFoundException
	 */
	public NotFoundException() {
		super();
	}
	
	/**
	 * Constructor de NotFoundException que recibe un mensaje
	 * @param message
	 */
	public NotFoundException(String message) {
		super(message);
	}
}
