package com.renting.renting.service.mapper;

import org.springframework.stereotype.Service;

import com.renting.renting.dto.RentDto;
import com.renting.renting.entity.RentEntity;

@Service
public class RentEntityToDto implements MapperService<RentEntity, RentDto>{

	@Override
	public RentDto map(RentEntity in) {
		RentDto r = new RentDto(in.getUser(), in.getCar(), in.getInitDate(), in.getFinalDate(), in.getPrice());
		return r;
	}

}
