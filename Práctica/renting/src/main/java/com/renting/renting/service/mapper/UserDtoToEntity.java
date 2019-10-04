package com.renting.renting.service.mapper;

import org.springframework.stereotype.Service;

import com.renting.renting.dto.UserDto;
import com.renting.renting.entity.UserEntity;

@Service
public class UserDtoToEntity implements MapperService<UserDto, UserEntity>{

	@Override
	public UserEntity map(UserDto in) {
		UserEntity u = new UserEntity(in.getId(), in.getName(), in.getCar(), in.getRent());
		return u;
	}

}
