package com.user.user;

public interface MapperService<T, R> {
	public R map(T in);
}
