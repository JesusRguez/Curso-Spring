package com.user.user;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service

public class entityToDto implements MapperService<userEntity, userDto>{

	@SuppressWarnings("deprecation")
	@Override
	public userDto map(userEntity in) {
		// TODO Auto-generated method stub
		userDto u = new userDto();
		u.setNombre(in.getNombre());
		u.setApellidos(in.getApellidos());
		u.setDni(in.getDni());
		
		int edad = 0;
		String nacimiento = in.getfNacimiento();
		String[] parts = nacimiento.split("/");
		int ano = Integer.parseInt(parts[2]);
		
		java.util.Date fecha = new Date();
		edad = ano - fecha.getYear(); 
		
		
		u.setEdad(edad);
		return u;
	}
}
