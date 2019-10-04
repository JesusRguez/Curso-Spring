package com.renting.renting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(basePackages = "com.renting.renting.controller")
public class ExceptionController {
	/**
	 * Método que captura la excepción 404 producida cuando no existe un usuario con el ID que se busca
	 * @param e
	 * @return Devuelve la excepción
	 */
	@ResponseBody
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Exception UsuarioNoExiste(NotFoundException e) {
		return e;
	}
	
	/**
	 * Método que captura la excepción 422 producida cuando se intenta crear un usuario menor de edad
	 * @param e
	 * @return Devuelve la excepción
	 */
	@ResponseBody
	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public Exception UsuarioMenorEdad(ValidationException e) {
		return e;
	}
}
