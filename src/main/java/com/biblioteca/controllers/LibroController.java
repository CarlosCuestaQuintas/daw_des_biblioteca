package com.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.models.Libro;
import com.biblioteca.services.LibroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/libros")
public class LibroController {
	
	@Autowired
	private LibroService libroService;
	
	@PostMapping
	public ResponseEntity<?> crearLibro(@RequestBody @Valid Libro libro) {
		libroService.crearLibro(libro);
		return ResponseEntity.ok("Libro creado correctamente: " + libro.getTitulo());
	}
	
	// TODO: revisar y completar esto o quitarlo por completo
	@Operation(summary = "Listar todos los libros")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Libros encontrados", content = { @Content(mediaType = "application/list") })
	})
	@GetMapping
	public ResponseEntity<?> getLibros(@RequestParam(required = false) String titulo, @RequestParam(required = false) String autor) {
		List<Libro> libros;
		// TODO: Creo que el grueso de la lógica debería estar en LibroService
		// Comprobamos si se nos ha pedido título
		if (titulo != null && !titulo.isEmpty()) {
			// Comprobamos si, además, se nos ha pedido un autor
			if (autor != null && !autor.isEmpty()) {
				libros = libroService.getLibrosPorTituloYAutor(titulo, autor);
				if (libros.size() > 0) return ResponseEntity.ok(libros);
				else return ResponseEntity.ok("No hay libros con ese título de ese autor");
			}
			else {
				libros = libroService.getLibrosPorTitulo(titulo);
				if (libros.size() > 0) return ResponseEntity.ok(libros);
				else return ResponseEntity.ok("No hay libros con ese título");
			}
		}
		else {
			// Comprobamos si se nos ha pedido autor
			if (autor != null && !autor.isEmpty()) {
				libros = libroService.getLibrosPorAutor(autor);
				if (libros.size() > 0) return ResponseEntity.ok(libros);
				else return ResponseEntity.ok("No hay libros con ese autor");
			}
			else {
				libros = libroService.getLibros();
				if (libros.size() > 0) return ResponseEntity.ok(libros);
				else return ResponseEntity.ok("No hay libros");
			}
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getLibro(@PathVariable Long id) {
		// TODO: considerar que no existe el usuario y devolver otra cosa
		//return ResponseEntity.ok(libroService.getLibro(id));
		Libro libro = libroService.getLibro(id);
		if (libro != null) return ResponseEntity.ok(libro);
		//else throw new ResourceNotFoundException("Libro no encontrado");
		else return ResponseEntity.ok("Libro no encontrado");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarLibro(@PathVariable Long id, @RequestBody @Valid Libro libro) {
		if (libroService.actualizarLibro(libro)) return ResponseEntity.ok("Libro actualizado correctamente " + libro.getTitulo());
		else return ResponseEntity.ok("Libro no encontrado");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarLibro(@PathVariable Long id) {
		if (libroService.eliminarLibro(id)) return ResponseEntity.ok("Libro eliminado");
		else return ResponseEntity.ok("Libro no encontrado");
	}

}
