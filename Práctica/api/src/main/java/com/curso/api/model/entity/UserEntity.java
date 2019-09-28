package com.curso.api.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="User")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(unique = true)
	private String name;
	private Integer age;
	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;
	
	/**
	 * Método para devolver el rol
	 * @return Devuelve el rol de un UserEntity
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Método para establecer el rol de un UserEntity
	 * @param role
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * Método para obtener el id
	 * @return Devuelve el id de un UserEntity
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Método para establecer el id de un UserEntity
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Método para obtener el nombre
	 * @return Devuelve el nombre de un UserEntity
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Método para establecer el nombre de un UserEntity
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Método para obtener la edad
	 * @return Devuelve la edad de un UserEntity
	 */
	public Integer getAge() {
		return age;
	}
	
	/**
	 * Método para establecer la edad de un UserEntity
	 * @param age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
}
