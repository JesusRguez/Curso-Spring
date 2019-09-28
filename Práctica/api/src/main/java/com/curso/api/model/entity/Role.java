package com.curso.api.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	
	/**
	 * Método para obtener el nombre
	 * @return Devuelve el nombrede un Role
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Método para establecer el nombre de un Role
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Método para obtener el id
	 * @return Devuelve el id de un Role
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Métdo para establecer el id de un Role
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
}
