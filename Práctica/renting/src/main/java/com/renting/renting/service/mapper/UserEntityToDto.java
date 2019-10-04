package com.renting.renting.service.mapper;

import org.springframework.stereotype.Service;

import com.renting.renting.dto.UserDto;
import com.renting.renting.entity.UserEntity;

@Service
public class UserEntityToDto implements MapperService<UserEntity, UserDto>{

	@Override
	public UserDto map(UserEntity in) {
		UserDto u = new UserDto(in.getId(), in.getName(), in.getAge(), in.getCar(), in.getRent());
		return u;
	}

}
