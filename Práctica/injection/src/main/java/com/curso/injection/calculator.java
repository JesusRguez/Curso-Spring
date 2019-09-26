package com.curso.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("calc")
public class calculator {
	//autowired de operator
	@Autowired
	public operator ope;
	
	//método que llama a operator
	public Double numerito(Double a, Double b) {
		return ope.suma(a, b);
	}
	
}
