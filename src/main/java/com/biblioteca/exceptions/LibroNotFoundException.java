package com.biblioteca.exceptions;

public class LibroNotFoundException extends RuntimeException {
	
	public LibroNotFoundException() {
		super("No existe ese libro");
	}

}
