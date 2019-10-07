package com.renting.renting.controller;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.ast.Not;
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
@RequestMapping("/user/{idUser}/rent")
public class UserRentController {

	@Autowired private UserService userService;
	@Autowired private MapperService<UserEntity, UserDto> userEntityToDtoMapper;
	@Autowired private MapperService<UserDto, UserEntity> userDtoToEntityMapper;
	
	@Autowired private RentService rentService;
	@Autowired private MapperService<RentEntity, RentDto> rentEntityToDtoMapper;
	@Autowired private MapperService<RentDto, RentEntity> rentDtoToEntityMapper;
	
	/**
	 * Método para buscar los alquileres de un usuario
	 * @param idUser
	 * @return Devuelve una lista de los alquileres que tiene el usuario
	 * @throws NotFoundException
	 */
	@GetMapping
	public List<RentDto> findAll(@PathVariable("idUser") Integer idUser) throws NotFoundException{
		UserEntity u = userService.buscar(idUser).orElseThrow(() -> new NotFoundException("Usuario con ID "+idUser+" no encontrado"));
		List<RentDto> alquileres = new ArrayList<RentDto>();
		for(int i=0; i<u.getRent().size(); ++i) {
			alquileres.add(rentEntityToDtoMapper.map(u.getRent().get(i)));
		}
		return alquileres;
	}
	
	/**
	 * Método para buscar un alquiler dentro de un usuario
	 * @param idUser
	 * @param idRent
	 * @return Devuelve el alquiler con ID idRent, del usuario con ID idUser
	 * @throws NotFoundException
	 */
	@GetMapping("/{idRent}")
	public RentDto findOne(@PathVariable("idUser") Integer idUser, @PathVariable("idRent") Integer idRent) throws NotFoundException{
		UserEntity u = userService.buscar(idUser).orElseThrow(() -> new NotFoundException("Usuario con ID "+idUser+" no encontrado"));
		rentService.buscar(idRent).orElseThrow(() -> new NotFoundException("Alquiler con ID "+idRent+" no encontrado"));
		UserDto userDto = userEntityToDtoMapper.map(u);
		RentEntity rent = userDto.getRent().get(idRent);
		return rentEntityToDtoMapper.map(rent);
	}
	
	/**
	 * Método para actualizar el alquiler de un usuario
	 * @param idUser
	 * @param idRent
	 * @param rentDto
	 * @throws NotFoundException
	 */
	@PutMapping("/{idRent}")
	public void update(@PathVariable("idUser") Integer idUser, @PathVariable("idRent") Integer idRent, @RequestBody RentDto rentDto) throws NotFoundException{
		UserEntity u = userService.buscar(idUser).orElseThrow(() -> new NotFoundException("Usuario con ID "+idUser+" no encontrado"));
		UserDto userDto = userEntityToDtoMapper.map(u);
		RentEntity rentEntity = rentDtoToEntityMapper.map(rentDto);
		userDto.getRent().add(rentEntity);
		rentEntity.setIdRent(idRent);
		rentService.actualizar(rentEntity);
	}
	
	/**
	 * Método paracrear un alquiler relacionado con un usuario
	 * @param idUser
	 * @param rentDto
	 * @return Devuelve el alquiler modificado
	 * @throws NotFoundException
	 */
	@PostMapping
	public RentDto create(@PathVariable("idUser") Integer idUser, @RequestBody RentDto rentDto) throws NotFoundException{
		return rentEntityToDtoMapper.map(rentService.guardarRentUser(rentDtoToEntityMapper.map(rentDto), idUser));
	}
	
	/**
	 * Método para borrar un alquiler relacionado con un usuario
	 * @param idUser
	 * @param idRent
	 * @throws NotFoundException
	 */
	@DeleteMapping("/{idRent}")
	public void delete(@PathVariable("idUser") Integer idUser, @PathVariable("idRent") Integer idRent) throws NotFoundException{
		UserEntity u = userService.buscar(idUser).orElseThrow(() -> new NotFoundException("Usuario con ID "+idUser+" no encontrado"));
		RentEntity rent = rentService.buscar(idRent).orElseThrow(() -> new NotFoundException("Alquiler con ID "+idRent+" no encontrado"));
		u.getRent().remove(rent);
		rentService.eliminar(idRent);
	}
}
