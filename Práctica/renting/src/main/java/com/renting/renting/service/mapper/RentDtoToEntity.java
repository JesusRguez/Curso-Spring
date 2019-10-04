package com.renting.renting.service.mapper;

import org.springframework.stereotype.Service;

import com.renting.renting.dto.RentDto;
import com.renting.renting.entity.RentEntity;

@Service
public class RentDtoToEntity implements MapperService<RentDto, RentEntity>{

	@Override
	public RentEntity map(RentDto in) {
		RentEntity r = new RentEntity(in.getUser(), in.getCar(), in.getInitDate(), in.getFinalDate(), in.getPrice());
		return r;
	}

}
