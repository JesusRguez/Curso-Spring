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

import com.renting.renting.dto.RentDto;
import com.renting.renting.entity.RentEntity;
import com.renting.renting.exception.NotFoundException;
import com.renting.renting.service.RentService;
import com.renting.renting.service.mapper.MapperService;

@RestController
@RequestMapping("rent")
public class RentController {
	
	@Autowired private RentService rentService;
	@Autowired private MapperService<RentEntity, RentDto> rentEntityToDtoMapper;
	@Autowired private MapperService<RentDto, RentEntity> rentDtoToEntityMapper;
	
	/**
	 * Método para buscar a todos los alquileres en la base de datos
	 * @param page
	 * @param size
	 * @param name
	 * @return Devuelve una lista paginada de todos los alquileres de la base de datos
	 */
	@GetMapping
	public Page<RentDto> findAll (@RequestParam(name = "page", required = false, defaultValue="0")Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "15") Integer size,
			@RequestParam(name ="name", required = false) String name){
		final Pageable pageable = PageRequest.of(page, size);
		return rentService.buscarTodo(pageable).map(x -> rentEntityToDtoMapper.map(x));
	}
	
	/**
	 * Método para buscar un alquiler por id
	 * @param id
	 * @return Devuelve el alquiler de la base de datos con ID id
	 * @throws NotFoundException 
	 */
	@GetMapping("/{id}")
	public RentDto findOne(@PathVariable("id") Integer id) throws NotFoundException {
		RentEntity r = rentService.buscar(id).orElseThrow(() -> new NotFoundException("Alquiler con ID "+id+" no encontrado"));
		return new RentDto(r.getIdRent(), r.getUser(), r.getCar(), r.getInitDate(), r.getFinalDate(), r.getPrice());
	}
	
	/**
	 * Método para actualizar un alquiler dentro de la base de datos
	 * @param id
	 * @param rentDto
	 */
	@PutMapping("/{id}")
	public void update(@PathVariable("id") Integer id, @RequestBody RentDto rentDto) {
		RentEntity r = rentDtoToEntityMapper.map(rentDto);
		r.setIdRent(id);
		rentService.actualizar(r);
	}
	
	/**
	 * Método para crear un alquiler en la base de datos
	 * @param rentDto
	 * @return Devuelve el RentDto creado 
	 */
	@PostMapping
	public RentDto create(@RequestBody RentDto rentDto) {
		RentEntity r = rentDtoToEntityMapper.map(rentDto);
		RentDto d = rentEntityToDtoMapper.map(r);
		rentService.guardar(r);
		return d;
	}
	
	/**
	 * Método para borrar un alquiler de la base de datos por id
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		rentService.eliminar(id);
	}
}
