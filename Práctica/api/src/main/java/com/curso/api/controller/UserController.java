package com.curso.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.api.model.dto.UserDto;
import com.curso.api.model.entity.UserEntity;
import com.curso.api.service.UserService;
import com.curso.api.service.mapper.MapperService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired private UserService userService;
	@Autowired private MapperService<UserEntity, UserDto> userEntityMapper;
	@Autowired private MapperService<UserDto, UserEntity> userDtoMapper;
	
	/**
	 * Método para buscar a todos los usuarios en la base de datos
	 * @return Devuevle una lista de UserDto con todos los usuarios de la base de datos
	 */
	@SuppressWarnings("null")
	@GetMapping
	public List<UserDto> findAll() {
		List<UserEntity> lista = userService.buscarTodo();
		List<UserDto> listaD = new ArrayList<>();
		for (UserEntity userEntity : lista) {
			listaD.add(userEntityMapper.map(userEntity));
		}
		return listaD;		
	}
	
	/**
	 * Método para buscar un usuario por id
	 * @param id
	 * @return Devuelve el usuario de la base de datos cuyo id es el parámetro id
	 */
	@GetMapping("/{id}")
	public UserDto findOne(@PathVariable("id") Integer id) {
		java.util.Optional<UserEntity> u = userService.buscar(id);
		UserEntity user = u.get();
		UserDto d = new UserDto(user.getId(), user.getName(), user.getAge());
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
	 */
	@PostMapping
	public UserDto create(@RequestBody @Valid UserDto userDto) {
		UserEntity u = userDtoMapper.map(userDto);
		userService.guardar(u);
		UserDto d = userEntityMapper.map(u);
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
