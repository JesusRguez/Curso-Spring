package com.curso.api.service.mapper;

public interface MapperService<T, R> {
	public R map(T in);
}
