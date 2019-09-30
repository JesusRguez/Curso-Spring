package com.curso.api.exception;

public class ValidationException extends Exception {
	/**
	 * serialVersionUID por defecto a 1L
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de ValidationException
	 */
	public ValidationException() {
		super();
	}
	
	/**
	 * Constructor de ValidationException que recibe un mensaje
	 * @param message
	 */
	public ValidationException(String message) {
		super(message);
	}
}
