package com.biblioteca.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Prestamo {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private Long idLibro;
	
	@NotNull
	private Long idUsuario;
	
	//@Null // TODO: hacer que se rellene automáticamente
	private LocalDate fechaPrestamo;
	
	//@Null
	private LocalDate fechaDevolucion;
	
	// TODO: tiene sentido que el estado pueda ser null al crearlo porque automáticamente se ponga como activo
	@Pattern(regexp = "ACTIVO|DEVUELTO")
	private String estado;
	
	public Prestamo() {}
	
	public Prestamo(Long idLibro, Long idUsuario) {
		this.idLibro = idLibro;
		this.idUsuario = idUsuario;
		this.activar();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public LocalDate getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(LocalDate fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public void activar() {
		this.estado = "ACTIVO";
		this.fechaPrestamo = LocalDate.now();
	}
	
	public void devolver() {
		this.estado = "DEVUELTO";
		this.fechaDevolucion = LocalDate.now();
	}
	
}
