package com.curso.api.service.mapper;

import org.springframework.stereotype.Service;

import com.curso.api.model.dto.RoleDto;
import com.curso.api.model.entity.RoleEntity;

@Service

public class RoleEntityToDto implements MapperService<RoleEntity, RoleDto>{

	@Override
	public RoleDto map(RoleEntity in) {
		RoleDto r = new RoleDto(in.getId(), in.getName());
		return r;
	}
}