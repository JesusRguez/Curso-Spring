package com.renting.renting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.renting.renting.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

}
