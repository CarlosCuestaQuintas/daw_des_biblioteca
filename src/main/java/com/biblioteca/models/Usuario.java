package com.biblioteca.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Usuario {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String nombre;
	
	@NotNull @Email
	private String email;
	
	@NotNull @Size(min = 9, max = 9) // TODO: implementar la exclusividad
	private String dni;
	
	public Usuario() {}
	
	public Usuario(String nombre, String email, String dni) {
		this.nombre = nombre;
		this.email = email;
		this.dni = dni;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
}
