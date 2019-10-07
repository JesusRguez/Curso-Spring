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

import com.renting.renting.dto.CarDto;
import com.renting.renting.dto.UserDto;
import com.renting.renting.entity.CarEntity;
import com.renting.renting.entity.RentEntity;
import com.renting.renting.entity.UserEntity;
import com.renting.renting.exception.NotFoundException;
import com.renting.renting.service.CarService;
import com.renting.renting.service.UserService;
import com.renting.renting.service.mapper.MapperService;

@RestController
@RequestMapping("/car/{idCar}/rent")
public class CarUserController {

	@Autowired private CarService carService;
	@Autowired private MapperService<CarEntity, CarDto> carEntityToDtoMapper;
	@Autowired private MapperService<CarDto, CarEntity> carDtoToEntityMapper;
	
	@Autowired private UserService userService;
	@Autowired private MapperService<UserEntity, UserDto> userEntityToDtoMapper;
	@Autowired private MapperService<UserDto, UserEntity> userDtoToEntityMapper;
	
	/**
	 * Método para buscar el usuario de un coche
	 * @param idCar
	 * @return Devuelve el usuario al que pertenece dicho coche
	 * @throws NotFoundException
	 */
	/*@GetMapping
	public UserDto findAll(@PathVariable("idCar") Integer idCar) throws NotFoundException{
		CarEntity c = carService.buscar(idCar).orElseThrow(() -> new NotFoundException("Coche con ID "+idCar+" no encontrado"));
		return userEntityToDtoMapper.map(c.getUser());
	}*/
	
	/**
	 * Método para buscar el usuario dentro de un coche
	 * @param idCar
	 * @param idUser
	 * @return Devuelve el usuario con ID idUser del coche con ID idCar
	 * @throws NotFoundException
	 */
	@GetMapping("/{idUser}")
	public UserDto findOne(@PathVariable("idCar") Integer idCar, @PathVariable("idUser") Integer idUser) throws NotFoundException{
		CarEntity c = carService.buscar(idCar).orElseThrow(() -> new NotFoundException("Coche con ID "+idCar+" no encontrado"));
		userService.buscar(idUser).orElseThrow(() -> new NotFoundException("Usuario con ID "+idUser+" no encontrado"));
		CarDto carDto = carEntityToDtoMapper.map(c);
		UserEntity user = carDto.getUser();
		return userEntityToDtoMapper.map(user);
	}
	
	/**
	 * Método para actualizar el usuario de un coche
	 * @param idCar
	 * @param idUser
	 * @param userDto
	 * @throws NotFoundException
	 */
	@PutMapping("/{idUser}")
	public void update(@PathVariable("idCar") Integer idCar, @PathVariable("idUser") Integer idUser, @RequestBody UserDto userDto) throws NotFoundException{
		CarEntity c = carService.buscar(idCar).orElseThrow(() -> new NotFoundException("Coche con ID "+idCar+" no encontrado"));
		CarDto carDto = carEntityToDtoMapper.map(c);
		UserEntity userEntity = userDtoToEntityMapper.map(userDto);
		carDto.setUser(userEntity);
		userEntity.setId(idUser);
		userService.actualizar(userEntity);
	}
	
	/**
	 * Método para crear un usuario relacionado con un coche
	 * @param idCar
	 * @param userDto
	 * @return Devuelve el usuario modificado
	 * @throws NotFoundException
	 */
	/*@PostMapping
	public UserDto create(@PathVariable("idCar") Integer idCar, @RequestBody UserDto userDto) throws NotFoundException{
		return userEntityToDtoMapper.map(userService.guardarCar(userDtoToEntityMapper.map(userDto), idCar));
	}*/
	
	/**
	 * Método para borrar un usuario relacionado con un coche
	 * @param idCar
	 * @param idUser
	 * @throws NotFoundException
	 */
	@DeleteMapping("/{idUser}")
	public void delete(@PathVariable("idCar") Integer idCar, @PathVariable("idUser") Integer idUser) throws NotFoundException{
		CarEntity c = carService.buscar(idCar).orElseThrow(() -> new NotFoundException("Coche con ID "+idCar+" no encontrado"));
		userService.buscar(idUser).orElseThrow(() -> new NotFoundException("Usuario con ID "+idUser+" no encontrado"));
		c.setUser(new UserEntity());
		userService.eliminar(idUser);
	}
}
