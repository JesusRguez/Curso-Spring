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
import com.renting.renting.dto.RentDto;
import com.renting.renting.entity.CarEntity;
import com.renting.renting.entity.RentEntity;
import com.renting.renting.exception.NotFoundException;
import com.renting.renting.service.CarService;
import com.renting.renting.service.RentService;
import com.renting.renting.service.mapper.MapperService;

@RestController
@RequestMapping("/car/{idCar}/rent")
public class CarRentController {
	
	@Autowired private CarService carService;
	@Autowired private MapperService<CarEntity, CarDto> carEntityToDtoMapper;
	@Autowired private MapperService<CarDto, CarEntity> carDtoToEntityMapper;
	
	@Autowired private RentService rentService;
	@Autowired private MapperService<RentEntity, RentDto> rentEntityToDtoMapper;
	@Autowired private MapperService<RentDto, RentEntity> rentDtoToEntityMapper;
	
	/**
	 * Métodopara buscar todos los alquileres de un coche
	 * @param idCar
	 * @return Devuelve una lista con todos los alquileres del coche con ID idCar
	 * @throws NotFoundException
	 */
	@GetMapping
	public List<RentDto> findAll (@PathVariable("idCar") Integer idCar) throws NotFoundException{
		CarEntity c = carService.buscar(idCar).orElseThrow(() -> new NotFoundException("Coche con ID "+idCar+" no encontrado"));
		List<RentDto> alquileres = new ArrayList<>();
		for(int i=0; i<c.getRent().size(); ++i) {
			alquileres.add(rentEntityToDtoMapper.map(c.getRent().get(i)));
		}
		return alquileres;
	}
	
	/**
	 * Método para buscar el alquiler de un coche por id
	 * @param idCar
	 * @param idRent
	 * @return Devuelve el alquiler con ID idRent, del coche con id idCar
	 * @throws NotFoundException
	 */
	@GetMapping("/{idRent}")
	public RentDto findOne(@PathVariable("idCar") Integer idCar, @PathVariable("idRent") Integer idRent) throws NotFoundException {
		CarEntity c = carService.buscar(idCar).orElseThrow(() -> new NotFoundException("Coche con ID "+idCar+" no encontrado"));
		rentService.buscar(idRent).orElseThrow(() -> new NotFoundException("Alquiler con ID "+idRent+" no encontrado"));
		CarDto carDto = new CarDto(c.getId(), c.getModel(), c.getBrand(), c.getUser(), c.getRent());
		RentEntity rent = carDto.getRent().get(idCar);
		return new RentDto(rent.getIdRent(), rent.getUser(), rent.getCar(), rent.getInitDate(), rent.getFinalDate(), rent.getPrice());
	}
	
	/**
	 * Método para actualizar un alquiler dentro de la base de datos
	 * @param idCar
	 * @param idRent
	 * @param rentDto
	 * @throws NotFoundException 
	 */
	@PutMapping("/{idRent}")
	public void update(@PathVariable("idCar") Integer idCar, @PathVariable("idRent") Integer idRent, @RequestBody RentDto rentDto) throws NotFoundException {
		CarEntity c = carService.buscar(idCar).orElseThrow(() -> new NotFoundException("Coche con ID "+idCar+" no encontrado"));
		CarDto carDto = new CarDto(c.getId(), c.getModel(), c.getBrand(), c.getUser(), c.getRent());
		RentEntity rent = carDto.getRent().get(idCar);
		rent.setIdRent(idRent);
		rentService.actualizar(rent);
	}
	
	/**
	 * Método para crear un alquiler en la base de datos
	 * @param idCar
	 * @param rentDto
	 * @return Devuelve el CarDto actualizado 
	 * @throws NotFoundException 
	 */
	@PostMapping
	public RentDto create(@PathVariable("idCar") Integer idCar, @RequestBody RentDto rentDto) throws NotFoundException {
		//carService.buscar(idCar).orElseThrow(() -> new NotFoundException("Coche con ID "+idCar+" no encontrado"));
		return rentEntityToDtoMapper.map(rentService.guardarRentCar(rentDtoToEntityMapper.map(rentDto), idCar));
	}
	
	/**
	 * Método para borrar un alquiler de la base de datos por id
	 * @param idCar
	 * @param idRent
	 * @throws NotFoundException 
	 */
	@DeleteMapping("/{idRent}")
	public void delete(@PathVariable("idCar") Integer idCar, @PathVariable("idRent") Integer idRent) throws NotFoundException {
		CarEntity c = carService.buscar(idCar).orElseThrow(() -> new NotFoundException("Coche con ID "+idCar+" no encontrado"));
		RentEntity rent = rentService.buscar(idRent).orElseThrow(() -> new NotFoundException("Alquiler con ID "+idRent+" no encontrado"));
		c.getRent().remove(rent);
		rentService.eliminar(idRent);
	}
}
