package com.renting.renting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renting.renting.dto.RentDto;
import com.renting.renting.dto.UserDto;
import com.renting.renting.entity.RentEntity;
import com.renting.renting.entity.UserEntity;
import com.renting.renting.exception.NotFoundException;
import com.renting.renting.service.RentService;
import com.renting.renting.service.UserService;
import com.renting.renting.service.mapper.MapperService;

@RestController
@RequestMapping("/rent/{idRent}/user")
public class RentUserController {
	
	@Autowired private UserService userService;
	@Autowired private MapperService<UserEntity, UserDto> userEntityToDtoMapper;
	@Autowired private MapperService<UserDto, UserEntity> userDtoToEntityMapper;
	
	@Autowired private RentService rentService;
	@Autowired private MapperService<RentEntity, RentDto> rentEntityToDtoMapper;
	@Autowired private MapperService<RentDto, RentEntity> rentDtoToEntityMapper;
	
	/**
	 * Método para buscar el usuario de un alquiler
	 * @param idRent
	 * @return Devuelve el usuario al que pertenece el alquiler
	 * @throws NotFoundException
	 */
	@GetMapping
	public UserDto findAll(@PathVariable("idRent") Integer idRent) throws NotFoundException{
		RentEntity r = rentService.buscar(idRent).orElseThrow(() -> new NotFoundException("Alquiler con ID "+idRent+" no encontrado"));
		return userEntityToDtoMapper.map(r.getUser());
	}
	
	/**
	 * Método para buscar el usuario dentro de un alquiler
	 * @param idRent
	 * @param idUser
	 * @return Devuelve el usuario con ID idUser, del alquiler con id idRent
	 * @throws NotFoundException
	 */
	@GetMapping("/{idUser}")
	public UserDto findOne(@PathVariable("idRent") Integer idRent, @PathVariable("idUser") Integer idUser) throws NotFoundException{
		RentEntity r = rentService.buscar(idRent).orElseThrow(() -> new NotFoundException("Alquiler con ID "+idRent+" no encontrado"));
		userService.buscar(idUser).orElseThrow(() -> new NotFoundException("Usuario con ID "+idUser+" no encontrado"));
		RentDto rentDto = rentEntityToDtoMapper.map(r);
		UserEntity user = rentDto.getUser();
		return userEntityToDtoMapper.map(user);
	}
	
	/**
	 * Método para actualizar el usuario de un alquiler
	 * @param idRent
	 * @param idUser
	 * @param userDto
	 * @throws NotFoundException
	 */
	@PutMapping("/{idUser}")
	public void update(@PathVariable("idRent") Integer idRent, @PathVariable("idUser") Integer idUser, @RequestBody UserDto userDto) throws NotFoundException{
		RentEntity r = rentService.buscar(idRent).orElseThrow(() -> new NotFoundException("Alquiler con ID "+idRent+" no encontrado"));
		RentDto rentDto = rentEntityToDtoMapper.map(r);
		UserEntity userEntity = userDtoToEntityMapper.map(userDto);
		rentDto.setUser(userEntity);
		userEntity.setId(idUser);
		userService.actualizar(userEntity);
	}
	
	/**
	 * Método para crear un usuario relacionado con un alquiler
	 * @param idRent
	 * @param userDto
	 * @return Devuelve el usuario modificado
	 * @throws NotFoundException
	 */
	@PostMapping
	public UserDto create(@PathVariable("idRent") Integer idRent, @RequestBody UserDto userDto) throws NotFoundException{
		return userEntityToDtoMapper.map(userService.guardarRent(userDtoToEntityMapper.map(userDto), idRent));
	}
	
	/**
	 * Método para borrar un usuario relacionado con un alquiler
	 * @param idRent
	 * @param idUser
	 * @throws NotFoundException
	 */
	@DeleteMapping("/{idUser}")
	public void delete(@PathVariable("idRent") Integer idRent, @PathVariable("idUser") Integer idUser) throws NotFoundException{
		RentEntity r = rentService.buscar(idRent).orElseThrow(() -> new NotFoundException("Alquiler con ID "+idRent+" no encontrado"));
		userService.buscar(idUser).orElseThrow(() -> new NotFoundException("Usuario con ID "+idUser+" no encontrado"));
		r.setUser(new UserEntity());
		userService.eliminar(idUser);
	}
}
