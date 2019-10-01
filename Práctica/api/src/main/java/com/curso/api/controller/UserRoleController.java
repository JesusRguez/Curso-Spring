package com.curso.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.api.model.dto.RoleDto;
import com.curso.api.model.dto.UserDto;
import com.curso.api.model.entity.RoleEntity;
import com.curso.api.model.entity.UserEntity;
import com.curso.api.service.RoleService;
import com.curso.api.service.UserService;
import com.curso.api.service.mapper.MapperService;

@RestController
@RequestMapping("/user/${userId}/Role")
public class UserRoleController {
	@Autowired private RoleService roleService;
	@Autowired private MapperService<RoleEntity, RoleDto> roleEntityMapper;
	@Autowired private MapperService<RoleDto, RoleEntity> roleDtoMapper;
	
	@Autowired private UserService userService;
	@Autowired private MapperService<UserEntity, UserDto> userEntityMapper;
	@Autowired private MapperService<UserDto, UserEntity> userDtoMapper;
	
	//Poner el findAll
	
	
	//Poner el findById
	
	
	@PostMapping("/{idRole}")
	public RoleDto createRole(@RequestBody @Valid RoleDto roleDto) {
		RoleEntity r = roleDtoMapper.map(roleDto);
		RoleDto d = roleEntityMapper.map(r);
		roleService.guardar(r);
		return d;
	}
	
	@DeleteMapping("/{id}")
	public void deleteRole(@PathVariable("id") RoleEntity r) {
		roleService.eliminar(r);
	}
}
