package com.biblioteca.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Libro {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String titulo;
	
	@NotNull
	private String autor;
	
	@NotNull // TODO: implementar la exclusividad
	private String isbn;
	
	private String categoria;
	
	@Min(0) // TODO: volver a esto más tarde
	private int ejemplaresDisponibles;
	
	public Libro() {}
	
	public Libro(String titulo, String autor, String isbn, String categoria, int ejemplaresDisponibles) {
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
		this.categoria = categoria;
		this.ejemplaresDisponibles = ejemplaresDisponibles;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getEjemplaresDisponibles() {
		return ejemplaresDisponibles;
	}

	public void setEjemplaresDisponibles(int ejemplaresDisponibles) {
		this.ejemplaresDisponibles = ejemplaresDisponibles;
	}
	
	public void prestarLibro() {
		this.ejemplaresDisponibles--;
	}
	
	public void devolverLibro() {
		this.ejemplaresDisponibles++;
	}
	
}
