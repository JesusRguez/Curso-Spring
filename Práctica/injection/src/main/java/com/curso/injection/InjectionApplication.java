package com.curso.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InjectionApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(InjectionApplication.class, args);
	}
	
	@Autowired
	public calculator calc;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//esto es lo que ejecuta y donde se llama a la calculadora
		System.out.println(calc.numerito(1.4, 1.2));
	}

}
