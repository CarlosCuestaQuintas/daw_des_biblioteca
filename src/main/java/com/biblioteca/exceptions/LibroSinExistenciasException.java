package com.biblioteca.exceptions;

public class LibroSinExistenciasException extends RuntimeException {
	
	public LibroSinExistenciasException() {
		super("Libro sin existencias");
	}

}
