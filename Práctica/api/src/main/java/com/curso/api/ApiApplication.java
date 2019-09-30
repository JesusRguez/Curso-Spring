package com.curso.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.curso.api.model.entity.Role;
import com.curso.api.model.entity.UserEntity;
import com.curso.api.service.RoleService;
import com.curso.api.service.UserService;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner{

	@Autowired UserService userService;
	@Autowired RoleService roleService;
	
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Role r = new Role();
		r.setName("admin");
		roleService.guardar(r);
		
		Role t = new Role();
		t.setName("user");
		roleService.guardar(t);
		
		UserEntity pepe = new UserEntity();
		pepe.setName("Pepe");
		pepe.setAge(35);
		pepe.setRole(t);
		
		userService.guardar(pepe);
		//log.info("Usuario guardado: {}", pepeStored);
		
		
		//Optional<UserEntity> u = userService.buscar(1);
		
		String nuevo = userService.buscar(1).map(x->x.getName()).map(x->x.toUpperCase()).orElse("");
		System.out.println(nuevo);
		
		String nuevoPepe = userService.buscaPorNombre("Pepe").map(x->x.getName()).map(x->x.toUpperCase()).orElse("");
		System.out.println(nuevoPepe);
		
		UserEntity juan = new UserEntity();
		juan.setName("Juan");
		juan.setAge(25);
		juan.setRole(r);
		
		userService.guardar(juan);
	}

}
