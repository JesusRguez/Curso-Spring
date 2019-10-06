package com.renting.renting.controller;

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
import com.renting.renting.entity.CarEntity;
import com.renting.renting.exception.NotFoundException;
import com.renting.renting.service.CarService;
import com.renting.renting.service.mapper.MapperService;

@RestController
@RequestMapping("/car")
public class CarController {

	@Autowired private CarService carService;
	@Autowired private MapperService<CarEntity, CarDto> carEntityToDtoMapper;
	@Autowired private MapperService<CarDto, CarEntity> carDtoToEntityMapper;
	
	/**
	 * Método para buscar a todos los coches en la base de datos
	 * @param page
	 * @param size
	 * @param name
	 * @return Devuelve una lista paginada de todos los coches de la base de datos
	 */
	@GetMapping
	public Page<CarDto> findAll (@RequestParam(name = "page", required = false, defaultValue="0")Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "15") Integer size,
			@RequestParam(name ="name", required = false) String name){
		final Pageable pageable = PageRequest.of(page, size);
		return carService.buscarTodo(pageable).map(x -> carEntityToDtoMapper.map(x));
	}
	
	/**
	 * Método para buscar un cohce por id
	 * @param id
	 * @return Devuelve el coche de la base de datos con ID id
	 * @throws NotFoundException 
	 */
	@GetMapping("/{id}")
	public CarDto findOne(@PathVariable("id") Integer id) throws NotFoundException {
		CarEntity c = carService.buscar(id).orElseThrow(() -> new NotFoundException("Coche con ID "+id+" no encontrado"));
		return new CarDto(c.getId(), c.getModel(), c.getBrand(), c.getUser(), c.getRent());
	}
	
	/**
	 * Método para actualizar un coche dentro de la base de datos
	 * @param id
	 * @param carDto
	 */
	@PutMapping("/{id}")
	public void update(@PathVariable("id") Integer id, @RequestBody CarDto carDto) {
		CarEntity c = carDtoToEntityMapper.map(carDto);
		c.setId(id);
		carService.actualizar(c);
	}
	
	/**
	 * Método para crear un coche en la base de datos
	 * @param carDto
	 * @return Devuelve el CarDto creado
	 */
	@PostMapping
	public CarDto create(@RequestBody CarDto carDto) {
		CarEntity c = carDtoToEntityMapper.map(carDto);
		CarDto d = carEntityToDtoMapper.map(c);
		carService.guardar(c);
		return d;
	}
	
	/**
	 * Método para borrar un coche de la base de datos por id
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		carService.eliminar(id);
	}
}
