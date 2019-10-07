package com.renting.renting.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.renting.renting.entity.UserEntity;
import com.renting.renting.exception.NotFoundException;
import com.renting.renting.service.CarService;
import com.renting.renting.service.UserService;
import com.renting.renting.service.mapper.MapperService;

@RestController
@RequestMapping("/user/{idUser}/car")
public class UserCarController {
	@Autowired private CarService carService;
	@Autowired private MapperService<CarEntity, CarDto> carEntityToDtoMapper;
	@Autowired private MapperService<CarDto, CarEntity> carDtoToEntityMapper;
	
	@Autowired private UserService userService;
	@Autowired private MapperService<UserEntity, UserDto> userEntityToDtoMapper;
	@Autowired private MapperService<UserDto, UserEntity> userDtoToEntityMapper;
	
	/**
	 * Método para buscar los coches de un usuario
	 * @param idUser
	 * @return Devuelve una lista de los coches que tiene alquiados el usuario con ID idUser
	 * @throws NotFoundException
	 */
	@GetMapping
	public List<CarDto> findAll(@PathVariable("idUser") Integer idUser) throws NotFoundException{
		UserEntity u = userService.buscar(idUser).orElseThrow(() -> new NotFoundException("Usuario con ID "+idUser+" no encontrado"));
		List<CarDto> coches = new ArrayList<CarDto>();
		for(int i=0; i<u.getCar().size(); ++i) {
			coches.add(carEntityToDtoMapper.map(u.getCar().get(i)));
		}
		return coches;
	}
	
	/**
	 * Método para buscar el usuario de un coche por ID
	 * @param idUser
	 * @param idCar
	 * @return Devuelve el coche con ID idCar, del usuario con ID idUser
	 * @throws NotFoundException
	 */
	@GetMapping("/{idCar}")
	public CarDto findOne(@PathVariable("idUser") Integer idUser, @PathVariable("idCar") Integer idCar) throws NotFoundException{
		UserEntity u = userService.buscar(idUser).orElseThrow(() -> new NotFoundException("Usuario con ID "+idUser+" no encontrado"));
		carService.buscar(idCar).orElseThrow(() -> new NotFoundException("Coche con ID "+idCar+" no encontrado"));
		UserDto userDto = userEntityToDtoMapper.map(u);
		CarEntity car = userDto.getCar().get(idUser);
		return carEntityToDtoMapper.map(car);
	}
	
	/**
	 * Método para actualizar el coche de un usuario
	 * @param idUser
	 * @param idCar
	 * @param carDto
	 * @throws NotFoundException
	 */
	@PutMapping("/{idCar}")
	public void updae(@PathVariable("idUser") Integer idUser, @PathVariable("idCar") Integer idCar, @RequestBody CarDto carDto) throws NotFoundException{
		UserEntity u = userService.buscar(idUser).orElseThrow(() -> new NotFoundException("Usuario con ID "+idUser+" no encontrado"));
		UserDto userDto = userEntityToDtoMapper.map(u);
		CarEntity carEntity = carDtoToEntityMapper.map(carDto);
		userDto.getCar().add(carEntity);
		carEntity.setId(idCar);
		carService.actualizar(carEntity);
	}
	
	/**
	 * Método par crear un coche relacionado con un usuario
	 * @param idUser
	 * @param carDto
	 * @return Devuelve el coche modificado
	 * @throws NotFoundException
	 */
	@PostMapping
	public CarDto create(@PathVariable("idUser") Integer idUser, @RequestBody CarDto carDto) throws NotFoundException{
		return carEntityToDtoMapper.map(carService.guardarUser(carDtoToEntityMapper.map(carDto), idUser));
	}
	
	/**
	 * Método para borrar un coche relacionado con un usuario
	 * @param idUser
	 * @param idCar
	 * @throws NotFoundException
	 */
	@DeleteMapping("/{idCar")
	public void delete(@PathVariable("idUser") Integer idUser, @PathVariable("IdCar") Integer idCar) throws NotFoundException{
		UserEntity u = userService.buscar(idUser).orElseThrow(() -> new NotFoundException("Usuario con ID "+idUser+" no encontrado"));
		CarEntity car = carService.buscar(idCar).orElseThrow(() -> new NotFoundException("Coche con ID "+idCar+" no encontrado"));
		u.getCar().remove(car);
		carService.eliminar(idCar);
	}
}
