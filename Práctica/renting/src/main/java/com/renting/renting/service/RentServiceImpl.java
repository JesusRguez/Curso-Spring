package com.renting.renting.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.renting.renting.entity.RentEntity;
import com.renting.renting.repository.RentRepository;

@Service
public class RentServiceImpl implements RentService{
	
	@Autowired RentRepository rentRepository;

	@Override
	public RentEntity guardar(RentEntity r) {
		RentEntity rent = rentRepository.save(r);
		return rent;
	}

	@Override
	public Optional<RentEntity> buscar(Integer id) {
		Optional<RentEntity> rent = rentRepository.findById(id);
		return rent;
	}

	@Override
	public void actualizar(RentEntity r) {
		rentRepository.saveAndFlush(r);
	}

	@Override
	public void eliminar(Integer id) {
		rentRepository.deleteById(id);
	}

	@Override
	public Page<RentEntity> buscarTodo(Pageable page) {
		return rentRepository.findAll(page);
	}
}
