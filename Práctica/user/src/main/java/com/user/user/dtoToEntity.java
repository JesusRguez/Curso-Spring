package com.user.user;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service

public class dtoToEntity implements MapperService<userDto, userEntity>{

	@SuppressWarnings("deprecation")
	@Override
	public userEntity map(userDto in) {
		// TODO Auto-generated method stub
		userEntity u = new userEntity();
		u.setNombre(in.getNombre());
		u.setApellidos(in.getApellidos());
		u.setDni(in.getDni());
		
		String fNacimiento = "";
		java.util.Date fecha = new Date();
		fNacimiento = String.valueOf(fecha.getYear() - in.getEdad());
		
		u.setfNacimiento(fNacimiento);
		return u;
	}
}
