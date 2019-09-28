package com.curso.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.api.model.entity.Role;
import com.curso.api.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired private RoleRepository repo;
	
	@Override
	public Optional<Role> guardar(Role r) {
		return Optional.ofNullable(repo.save(r));
	}

}
