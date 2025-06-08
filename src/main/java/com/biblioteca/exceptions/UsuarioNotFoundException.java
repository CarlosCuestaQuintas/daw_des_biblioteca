package com.biblioteca.exceptions;

public class UsuarioNotFoundException extends RuntimeException {

	public UsuarioNotFoundException() {
		super("No existe ese usuario");
	}
	
}
