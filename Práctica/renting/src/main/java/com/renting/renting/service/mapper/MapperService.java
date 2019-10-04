package com.renting.renting.service.mapper;

public interface MapperService<T, R> {
	
	/**
	 * Método para cambiar de un tipo a otro
	 * @param in
	 * @return Devuelve un R a partir de un T
	 */
	public R map(T in);
}