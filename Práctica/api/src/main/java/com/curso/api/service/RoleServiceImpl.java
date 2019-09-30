package com.curso.api.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.curso.api.model.entity.RoleEntity;
import com.curso.api.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired private RoleRepository repo;
	
	@Override
	public Optional<RoleEntity> guardar(RoleEntity r) {
		return Optional.ofNullable(repo.save(r));
	}

	@Override
	public Optional<RoleEntity> buscar(Integer id) {
		Optional<RoleEntity> r = repo.findById(id);
		return r;
	}

	@Override
	public List<RoleEntity> buscarTodo() {
		return repo.findAll();
	}

	@Override
	public RoleEntity findByIdNotSafe(Integer id) {
		try {
			return repo.getOne(id);
		} catch (EntityNotFoundException e) {
			System.out.println("Fallo en findByIdNotSafe");
		}
		return new RoleEntity();
	}

	@Override
	public Optional<RoleEntity> buscaPorNombre(String n) {
		return repo.findByName(n);
	}

	@Override
	public void actualizar(RoleEntity r) {
		repo.saveAndFlush(r);
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public Page<RoleEntity> buscarTodo(Pageable page) {
		return repo.findAll(page);
	}

	@Override
	public void eliminar(RoleEntity r) {
		repo.delete(r);
	}

}
