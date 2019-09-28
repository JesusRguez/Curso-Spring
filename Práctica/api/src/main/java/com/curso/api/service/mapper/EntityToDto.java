package com.curso.api.service.mapper;

import org.springframework.stereotype.Service;

import com.curso.api.model.dto.UserDto;
import com.curso.api.model.entity.UserEntity;

@Service

public class EntityToDto implements MapperService<UserEntity, UserDto>{

	@Override
	public UserDto map(UserEntity in) {
		UserDto u = new UserDto(in.getId(), in.getName(), in.getAge());
		/*
		int edad = 0;
		String nacimiento = in.getfNacimiento();
		String[] parts = nacimiento.split("/");
		int ano = Integer.parseInt(parts[2]);
		
		java.util.Date fecha = new Date();
		edad = ano - fecha.getYear(); 
		u.setEdad(edad);
		*/
		return u;
	}
}
