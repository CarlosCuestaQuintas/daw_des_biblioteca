package com.biblioteca.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NoResourceFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleNoResourceFoundException(NoResourceFoundException ex) {
		//return new ResponseEntity<>("Error", HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no encontrado: " + ex.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<String> handleBadRequest(MethodArgumentNotValidException e) {
		String m = "";
		for (Object o : e.getDetailMessageArguments()) {
			if (!o.toString().isEmpty()) {
				m += o.toString();
			}
		}
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body("La petición se ha realizado de manera incorrecta:"
						+ "\n" + m);
	}
	
	@ExceptionHandler(UsuarioNotFoundException.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> handleUsuarioNotFoundException(UsuarioNotFoundException ex) {
		return ResponseEntity.ok(ex.getMessage());
	}
	
	@ExceptionHandler(LibroNotFoundException.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> handleLibroNotFoundException(LibroNotFoundException ex) {
		return ResponseEntity.ok(ex.getMessage());
	}
	
	@ExceptionHandler(LibroSinExistenciasException.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> handleLibroSinExistenciasException(LibroSinExistenciasException ex) {
		return ResponseEntity.ok(ex.getMessage());
	}

}
