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
import com.renting.renting.dto.RentDto;
import com.renting.renting.entity.CarEntity;
import com.renting.renting.entity.RentEntity;
import com.renting.renting.exception.NotFoundException;
import com.renting.renting.service.CarService;
import com.renting.renting.service.RentService;
import com.renting.renting.service.mapper.MapperService;

@RestController
@RequestMapping("/rent/{idRent}/car")
public class RentCarController {
	@Autowired private CarService carService;
	@Autowired private MapperService<CarEntity, CarDto> carEntityToDtoMapper;
	@Autowired private MapperService<CarDto, CarEntity> carDtoToEntityMapper;
	
	@Autowired private RentService rentService;
	@Autowired private MapperService<RentEntity, RentDto> rentEntityToDtoMapper;
	@Autowired private MapperService<RentDto, RentEntity> rentDtoToEntityMapper;
	
	/**
	 * Método para buscar el coche de un alquiler
	 * @param idRent
	 * @param idCar
	 * @return Devuelvee el coche que pertenece a dicho alquiler
	 * @throws NotFoundException
	 */
	@GetMapping
	public CarDto findAll(@PathVariable("idRent") Integer idRent, @PathVariable("idCar") Integer idCar) throws NotFoundException{
		RentEntity r = rentService.buscar(idRent).orElseThrow(() -> new NotFoundException("Alquiler con ID "+idRent+" no encontrado"));
		CarDto car = carEntityToDtoMapper.map(r.getCar());
		return car;
	}
	
	/**
	 * Método para buscar el coche dentro de un alquiler
	 * @param idRent
	 * @param idCar
	 * @return Devuelve el coche con ID idCar del alquiler con ID idRent
	 * @throws NotFoundException
	 */
	@GetMapping("/{idCar}")
	public CarDto findOne(@PathVariable("idRent") Integer idRent, @PathVariable("idCar") Integer idCar) throws NotFoundException{
		RentEntity r = rentService.buscar(idRent).orElseThrow(() -> new NotFoundException("Alquiler con ID "+idRent+" no encontrado"));
		carService.buscar(idCar).orElseThrow(()-> new NotFoundException("Coche con ID "+idCar+" no encontrado"));
		RentDto rentDto = new RentDto(r.getIdRent(), r.getUser(), r.getCar(), r.getInitDate(), r.getFinalDate(), r.getPrice());
		CarEntity car = rentDto.getCar();
		return carEntityToDtoMapper.map(car);
	}
	
	/**
	 * Método para actualizar el coche de un alquiler
	 * @param idRent
	 * @param idCar
	 * @throws NotFoundException
	 */
	@PutMapping("/{idCar}")
	public void update(@PathVariable("idRent") Integer idRent, @PathVariable("idCar") Integer idCar, @RequestBody CarDto carDto) throws NotFoundException {
		RentEntity r = rentService.buscar(idRent).orElseThrow(() -> new NotFoundException("Alquiler con ID "+idRent+" no encontrado"));
		RentDto rentDto = rentEntityToDtoMapper.map(r);
		CarEntity car = carDtoToEntityMapper.map(carDto);
		rentDto.setCar(car);
		car.setId(idCar);
		carService.actualizar(car);
	}
	
	/**
	 * Método para crear un coche relacionado con un alquiler
	 * @param idRent
	 * @param carDto
	 * @return Devuelve el coche creado
	 * @throws NotFoundException
	 */
	@PostMapping
	public CarDto create(@PathVariable("idRent)") Integer idRent, @RequestBody CarDto carDto) throws NotFoundException{
		return carEntityToDtoMapper.map(carService.guardarRent(carDtoToEntityMapper.map(carDto), idRent));
	}
	
	/**
	 * Método para borrar un coche
	 * @param idRent
	 * @param idCar
	 * @throws NotFoundException
	 */
	@DeleteMapping("/{idCar}")
	public void delete(@PathVariable("idRent") Integer idRent, @PathVariable("idCar") Integer idCar) throws NotFoundException{
		RentEntity r = rentService.buscar(idRent).orElseThrow(() -> new NotFoundException("Alquiler con ID "+idRent+" no encontrado"));
		carService.buscar(idCar).orElseThrow(() -> new NotFoundException("Coche con ID "+idCar+" no encontrado"));
		r.setCar(new CarEntity());
		carService.eliminar(idCar);
	}
	
	@GetMapping("/{idCar}/profit")
	public double profit(@PathVariable("idRent") Integer idRent, @PathVariable("idCar") Integer idCar) throws NotFoundException{
		RentEntity r = rentService.buscar(idRent).orElseThrow(() -> new NotFoundException("Alquiler con ID "+idRent+" no encontrado"));
		carService.buscar(idCar).orElseThrow(() -> new NotFoundException("Coche con ID "+idCar+" no encontrado"));
		Double profit = rentService.profit(idCar, r.getInitDate(), r.getFinalDate());
		return profit;
	}
}
