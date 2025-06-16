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

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {
	
	@Autowired
	private PrestamoService prestamoService;
	
	@Operation(summary = "Agregar un préstamo")
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
	
	@Operation(summary = "Listar todos los préstamos")
	@GetMapping
	public ResponseEntity<?> getPrestamos() {
		// TODO: considerar que no hay préstamos
		//return ResponseEntity.ok(prestamoService.getPrestamos());
		List<Prestamo> prestamos = prestamoService.getPrestamos();
		if (prestamos.size() > 0) return ResponseEntity.ok(prestamoService.getPrestamos());
		else return ResponseEntity.ok("No hay préstamos registrados");
	}
	
	@Operation(summary = "Buscar un préstamo por ID")
	@GetMapping("/{id}")
	public ResponseEntity<?> getPrestamo(@PathVariable Long id) {
		// TODO: considerar que no existe el préstamo y devolver otra cosa
		//return ResponseEntity.ok(prestamoService.getPrestamo(id));
		Prestamo prestamo = prestamoService.getPrestamo(id);
		if (prestamo != null) return ResponseEntity.ok(prestamoService.getPrestamo(id));
		else return ResponseEntity.ok("Préstamo no encontrado");
	}
	
	@Operation(summary = "Listar préstamos de un usuario por su ID")
	@GetMapping("/usuario/{id}")
	public ResponseEntity<?> getPrestamosPorUsuario(@PathVariable Long id) {
		// TODO: utilizar la id para comprobar si existe el usuario primero (probablemente sea cosa de tocar el servicio)
		//return ResponseEntity.ok(prestamoService.getPrestamosPorUsuario(id));
		List<Prestamo> prestamos = prestamoService.getPrestamosPorUsuario(id);
		if (prestamos.size() > 0) return ResponseEntity.ok(prestamoService.getPrestamosPorUsuario(id));
		else return ResponseEntity.ok("Este usuario no tiene préstamos");
	}
	
	@Operation(summary = "Listar los préstamos activos")
	@GetMapping("/activos")
	public ResponseEntity<?> getPrestamosActivos() {
		List<Prestamo> prestamos = prestamoService.getPrestamosActivos();
		if (prestamos.size() > 0) return ResponseEntity.ok(prestamos);
		else return ResponseEntity.ok("No hay préstamos activos");
	}
	
	@Operation(summary = "Marcar un préstamo como devuelto")
	@PutMapping("/{id}/devolver")
	public ResponseEntity<?> devolverPrestamo(@PathVariable Long id) {
		if (prestamoService.devolverPrestamo(id)) return ResponseEntity.ok("Préstamo devuelto");
		else return ResponseEntity.ok("Préstamo no encontrado");
	}
	

}
