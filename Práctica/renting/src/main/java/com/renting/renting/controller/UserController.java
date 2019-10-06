package com.renting.renting.controller;

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

import com.renting.renting.dto.UserDto;
import com.renting.renting.entity.UserEntity;
import com.renting.renting.exception.NotFoundException;
import com.renting.renting.exception.ValidationException;
import com.renting.renting.service.UserService;
import com.renting.renting.service.mapper.MapperService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired private UserService userService;
	@Autowired private MapperService<UserEntity, UserDto> userEntityToDtoMapper;
	@Autowired private MapperService<UserDto, UserEntity> userDtoToEntityMapper;
	
	/**
	 * Método para buscar a todos los usuarios en la base de datos
	 * @param page
	 * @param size
	 * @param name
	 * @return Devuelve una lista paginada de todos los usuarios de la base de datos
	 */
	@GetMapping
	public Page<UserDto> findAll (@RequestParam(name = "page", required = false, defaultValue="0")Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "15") Integer size,
			@RequestParam(name ="name", required = false) String name){
		final Pageable pageable = PageRequest.of(page, size);
		return userService.buscarTodo(pageable).map(x -> userEntityToDtoMapper.map(x));
	}
	
	/**
	 * Método para buscar un usuario por id
	 * @param id
	 * @return Devuelve el usuario de la base de datos con ID id
	 * @throws NotFoundException 
	 */
	@GetMapping("/{id}")
	public UserDto findOne(@PathVariable("id") Integer id) throws NotFoundException {
		UserEntity u = userService.buscar(id).orElseThrow(() -> new NotFoundException("Usuario con ID "+id+" no encontrado"));
		return new UserDto(u.getId(), u.getName(), u.getAge(), u.getCar(), u.getRent());
	}
	
	/**
	 * Método para actualizar un usuario de la base de datos
	 * @param id
	 * @param userDto
	 */
	@PutMapping("/{id}")
	public void update(@PathVariable("id") Integer id, @RequestBody UserDto userDto) {
		UserEntity u = userDtoToEntityMapper.map(userDto);
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
	public UserDto create(@RequestBody @Valid UserDto userDto) throws ValidationException{
		UserEntity u = userDtoToEntityMapper.map(userDto);
		UserDto d = userEntityToDtoMapper.map(u);
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
}
