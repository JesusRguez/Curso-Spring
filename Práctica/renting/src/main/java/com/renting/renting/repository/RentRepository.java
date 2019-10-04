package com.renting.renting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renting.renting.entity.RentEntity;

public interface RentRepository extends JpaRepository<RentEntity, Integer>{

}
