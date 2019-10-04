package com.renting.renting.service.mapper;

import org.springframework.stereotype.Service;

import com.renting.renting.dto.CarDto;
import com.renting.renting.entity.CarEntity;

@Service
public class CarEntityToDto implements MapperService<CarEntity, CarDto>{

	@Override
	public CarDto map(CarEntity in) {
		CarDto c= new CarDto(in.getId(), in.getModel(), in.getBrand(), in.getUser(), in.getRent());
		return c;
	}

}
