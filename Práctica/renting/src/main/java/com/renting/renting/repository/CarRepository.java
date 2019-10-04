package com.renting.renting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renting.renting.entity.CarEntity;

public interface CarRepository extends JpaRepository<CarEntity, Integer>{

}
