package com.renting.renting.service.mapper;

import org.springframework.stereotype.Service;

import com.renting.renting.dto.CarDto;
import com.renting.renting.entity.CarEntity;

@Service
public class CarDtoToEntity implements MapperService<CarDto, CarEntity>{

	@Override
	public CarEntity map(CarDto in) {
		CarEntity c= new CarEntity(in.getId(), in.getModel(), in.getBrand(), in.getUser(), in.getRent());
		return c;
	}

}
