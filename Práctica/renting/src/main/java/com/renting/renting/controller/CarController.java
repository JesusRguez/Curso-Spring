package com.renting.renting.controller;

import java.util.Optional;

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

import com.renting.renting.dto.CarDto;
import com.renting.renting.dto.UserDto;
import com.renting.renting.entity.CarEntity;
import com.renting.renting.entity.UserEntity;
import com.renting.renting.service.CarService;
import com.renting.renting.service.mapper.MapperService;

@RestController
@RequestMapping("/car")
public class CarController {

	@Autowired private CarService carService;
	@Autowired private MapperService<CarEntity, CarDto> carEntityToDtoMapper;
	@Autowired private MapperService<CarDto, CarEntity> carDtoToEntityMapper;
	
	/**
	 * Método para buscar a todos los usuarios en la base de datos
	 * @param page
	 * @param size
	 * @param name
	 * @return Devuelve una lista paginada de todos los usuarios de la base de datos
	 */
	@GetMapping
	public Page<CarDto> findAll (@RequestParam(name = "page", required = false, defaultValue="0")Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "15") Integer size,
			@RequestParam(name ="name", required = false) String name){
		final Pageable pageable = PageRequest.of(page, size);
		return carService.buscarTodo(pageable).map(x -> carEntityToDtoMapper.map(x));
	}
	
	/**
	 * Método para buscar un usuario por id
	 * @param id
	 * @return Devuelve el usuario de la base de datos cuyo id es el parámetro id
	 * @throws NotFoundException 
	 */
	@GetMapping("/{id}")
	public CarDto findOne(@PathVariable("id") Integer id) {
		//UserEntity u = userService.buscar(id).orElseThrow(() -> new NotFoundException("Usuario con ID "+id+" no encontrado"));
		Optional<CarEntity> car = carService.buscar(id);
		CarEntity c = car.get();
		CarDto d = new CarDto(/*ponerle los datitos para que se cree el cochecito dto*/);
		return d;
	}
	
	/**
	 * Método para actualizar un usuario dentro de la base de datos
	 * @param id
	 * @param UserDto userDto
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
	public UserDto create(@RequestBody @Valid UserDto userDto){
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
