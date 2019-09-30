package com.curso.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.api.exception.NotFoundException;
import com.curso.api.exception.ValidationException;
import com.curso.api.model.dto.UserDto;
import com.curso.api.model.entity.Role;
import com.curso.api.model.entity.UserEntity;
import com.curso.api.repository.RoleRepository;
import com.curso.api.service.UserService;
import com.curso.api.service.mapper.MapperService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired private RoleRepository roleRepository;
	@Autowired private UserService userService;
	@Autowired private MapperService<UserEntity, UserDto> userEntityMapper;
	@Autowired private MapperService<UserDto, UserEntity> userDtoMapper;
	
	/**
	 * Método para buscar a todos los usuarios en la base de datos
	 * @return Devuevle una lista pagina da de UserDto con todos los usuarios de la base de datos
	 */
	//@SuppressWarnings("null")
	@GetMapping
	public Page<UserDto> findAll (@RequestParam(name = "page", required = false, defaultValue="0")Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "15") Integer size,
			@RequestParam(name ="name", required = false) String name){
		final Pageable pageable = PageRequest.of(page, size);
		return userService.buscarTodo(pageable).map(x -> userEntityMapper.map(x));
	}
	
	@GetMapping("/rol")
	public List<Role> findAllRole (){
		return roleRepository.findAll();
	}
	
	/**
	 * Método para buscar un usuario por id
	 * @param id
	 * @return Devuelve el usuario de la base de datos cuyo id es el parámetro id
	 * @throws NotFoundException 
	 */
	@GetMapping("/{id}")
	public UserDto findOne(@PathVariable("id") Integer id) throws NotFoundException {
		UserEntity u = userService.buscar(id).orElseThrow(() -> new NotFoundException("Usuario con ID "+id+" no encontrado"));
		UserDto d = new UserDto(u.getId(), u.getName(), u.getAge());
		return d;
	}
	
	/**
	 * Método para actualizar un usuario dentro de la base de datos
	 * @param id
	 * @param UserDto userDto
	 */
	@PutMapping("/{id}")
	public void update(@PathVariable("id") Integer id, @RequestBody UserDto userDto) {
		UserEntity u = userDtoMapper.map(userDto);
		u.setId(id);
		userService.actualizar(u);
	}
	
	/**
	 * Método para crear un usuario en la base de datos
	 * @param userDto
	 * @return Devuelve el UserDto creado
	 * @throws ValidationException 
	 */
	@PostMapping
	public UserDto create(@RequestBody @Valid UserDto userDto) throws ValidationException {
		UserEntity u = userDtoMapper.map(userDto);
		UserDto d = userEntityMapper.map(u);
		userService.guardar(u);
		return d;
	}
	
	/**
	 * Método para borrar un usuario de la base de datos por id
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		userService.eliminar(id);
	}
	/*
	@PostMapping("/{id}/role/{idRole}")
	public RoleDto createRole(@RequestBody @Valid RoleDto roleDto) {
		RoleEntity r = roleDtoMapper.map(roleDto);
		RoleDto d = roleEntityMapper.map(r);
		roleService.guardar(r);
		return d;
	}
	
	@DeleteMapping("/{id}/role")
	public void deleteRole(@PathVariable("id") Role r) {
		roleService.eliminar(r);
	}
	*/
}
