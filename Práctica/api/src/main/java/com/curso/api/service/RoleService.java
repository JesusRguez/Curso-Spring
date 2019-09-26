package com.curso.api.service;

import java.util.Optional;

import com.curso.api.model.entity.Role;

public interface RoleService {
	Optional<Role> guardar(Role r);
}
