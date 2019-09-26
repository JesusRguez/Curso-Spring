package com.curso.injection;

import org.springframework.stereotype.Service;

@Service("ope")
public class operator {
	//aqui la suma
	public Double suma(Double a, Double b) {
		return a+b;
	}
}
