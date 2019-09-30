package com.curso.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.api.model.dto.RoleDto;
import com.curso.api.model.entity.RoleEntity;
import com.curso.api.service.RoleService;
import com.curso.api.service.mapper.MapperService;

@RestController
@RequestMapping("/rol")
public class RoleController {

	@Autowired private RoleService roleService;
	@Autowired private MapperService<RoleEntity, RoleDto> roleEntityMapper;
	@Autowired private MapperService<RoleDto, RoleEntity> roleDtoMapper;
	
	/**
	 * Método para byscar a todos los roles en la base de datos
	 * @param page
	 * @param size
	 * @param name
	 * @return Devuelve una lista paginada de RoleDto con todos los roles de la base de datos
	 */
	@GetMapping
	public Page<RoleDto> findAll(@RequestParam(name = "page", required = false, defaultValue="0")Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "15") Integer size,
			@RequestParam(name ="name", required = false) String name){
		final Pageable pageable = PageRequest.of(page, size);
		return roleService.buscarTodo(pageable).map(x -> roleEntityMapper.map(x));
	}
	
	/**
	 * Método para actualizar un rol en la base de datos
	 * @param id
	 * @param roleDto
	 */
	@GetMapping("/{role}")
	public void update(@PathVariable("role") Integer id, @RequestBody RoleDto roleDto) {
		RoleEntity r = roleDtoMapper.map(roleDto);
		r.setId(id);
		roleService.actualizar(r);
	}
	
	/**
	 * Método para crear un rol en la base de datos
	 * @param roleDto
	 * @return Devuelve el RoleDto creado
	 */
	@PostMapping
	public RoleDto create(@RequestBody @Valid RoleDto roleDto) {
		RoleEntity r = roleDtoMapper.map(roleDto);
		RoleDto d = roleEntityMapper.map(r);
		roleService.guardar(r);
		return d;
	}
	
	/**
	 * Método para borrar un rol de la base de datos por id
	 * @param id
	 */
	@DeleteMapping("/{role}")
	public void delete(@PathVariable("role") Integer id) {
		roleService.eliminar(id);
	}
}
