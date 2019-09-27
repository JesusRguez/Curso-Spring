package com.curso.api.service.mapper;

import org.springframework.stereotype.Service;

import com.curso.api.model.dto.UserDto;
import com.curso.api.model.entity.UserEntity;

@Service

public class DtoToEntity implements MapperService<UserDto, UserEntity>{

	@Override
	public UserEntity map(UserDto in) {
		// TODO Auto-generated method stub
		UserEntity u = new UserEntity();
		u.setName(in.getName());
		u.setAge(in.getAge());
		u.setId(in.getId());
		
		/*
		String fNacimiento = "";
		java.util.Date fecha = new Date();
		fNacimiento = String.valueOf(fecha.getYear() - in.getEdad());
		u.setfNacimiento(fNacimiento);
		*/
		
		return u;
	}
}
