package com.curso.api.service.mapper;

import org.springframework.stereotype.Service;

import com.curso.api.model.dto.RoleDto;
import com.curso.api.model.entity.RoleEntity;

@Service

public class RoleDtoToEntity implements MapperService<RoleDto, RoleEntity>{

	@Override
	public RoleEntity map(RoleDto in) {
		RoleEntity r = new RoleEntity();
		r.setName(in.getName());
		r.setId(in.getId());
		return r;
	}
}
