package com.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.exceptions.LibroNotFoundException;
import com.biblioteca.exceptions.LibroSinExistenciasException;
import com.biblioteca.exceptions.UsuarioNotFoundException;
import com.biblioteca.models.Prestamo;
import com.biblioteca.services.PrestamoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {
	
	@Autowired
	private PrestamoService prestamoService;
	
	/*
	POST /api/prestamos - Crear un préstamo
	GET /api/prestamos - Obtener todos los préstamos
	GET /api/prestamos/{id} - Obtener un préstamo por ID
	GET /api/prestamos/usuario/{idUsuario} - Obtener préstamos de un usuario
	GET /api/prestamos/activos - Obtener préstamos activos
	PUT /api/prestamos/{id}/devolver - Marcar un préstamo como devuelto
	 */
	
	/*@PostMapping
	public ResponseEntity<?> crearPrestamo(@RequestBody @Valid Prestamo prestamo) {
		if (prestamoService.crearPrestamo(prestamo)) return ResponseEntity.ok("Préstamo creado");
		else return ResponseEntity.ok("No hay existencias de ese libro");
	}*/
	
	@PostMapping
	public ResponseEntity<?> crearPrestamo(@RequestBody @Valid Prestamo prestamo) {
		switch (prestamoService.crearPrestamo(prestamo)) {
			case 0:
				return ResponseEntity.ok("Préstamo creado");
			case -1:
				throw new UsuarioNotFoundException();
			case -2:
				throw new LibroNotFoundException();
			case -3:
				throw new LibroSinExistenciasException();
			default:
				return ResponseEntity.ok("Algo ha ido mal");
		}
	}
	
	@GetMapping
	public ResponseEntity<?> getPrestamos() {
		// TODO: considerar que no hay préstamos
		//return ResponseEntity.ok(prestamoService.getPrestamos());
		List<Prestamo> prestamos = prestamoService.getPrestamos();
		if (prestamos.size() > 0) return ResponseEntity.ok(prestamoService.getPrestamos());
		else return ResponseEntity.ok("No hay préstamos registrados");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPrestamo(@PathVariable Long id) {
		// TODO: considerar que no existe el préstamo y devolver otra cosa
		//return ResponseEntity.ok(prestamoService.getPrestamo(id));
		Prestamo prestamo = prestamoService.getPrestamo(id);
		if (prestamo != null) return ResponseEntity.ok(prestamoService.getPrestamo(id));
		else return ResponseEntity.ok("Préstamo no encontrado");
	}
	
	// TODO: ¿qué pasa si no existe el usuario?
	@GetMapping("/usuario/{id}")
	public ResponseEntity<?> getPrestamosPorUsuario(@PathVariable Long id) {
		// TODO: utilizar la id para comprobar si existe el usuario primero (probablemente sea cosa de tocar el servicio)
		//return ResponseEntity.ok(prestamoService.getPrestamosPorUsuario(id));
		List<Prestamo> prestamos = prestamoService.getPrestamosPorUsuario(id);
		if (prestamos.size() > 0) return ResponseEntity.ok(prestamoService.getPrestamosPorUsuario(id));
		else return ResponseEntity.ok("Este usuario no tiene préstamos");
	}
	
	@GetMapping("/activos")
	public ResponseEntity<?> getPrestamosActivos() {
		List<Prestamo> prestamos = prestamoService.getPrestamosActivos();
		if (prestamos.size() > 0) return ResponseEntity.ok(prestamos);
		else return ResponseEntity.ok("No hay préstamos activos");
	}
	
	@PutMapping("/{id}/devolver")
	public ResponseEntity<?> devolverPrestamo(@PathVariable Long id) {
		if (prestamoService.devolverPrestamo(id)) return ResponseEntity.ok("Préstamo devuelto");
		else return ResponseEntity.ok("Préstamo no encontrado");
	}
	

}
