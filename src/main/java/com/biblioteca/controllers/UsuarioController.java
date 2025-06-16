package com.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.models.Usuario;
import com.biblioteca.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Operation(summary = "Agregar un usuario")
	@PostMapping
	public ResponseEntity<?> crearUsuario(@RequestBody @Valid Usuario usuario) {
		usuarioService.crearUsuario(usuario);
		return ResponseEntity.ok("Usuario creado correctamente: " + usuario.getNombre());
	}
	
	@Operation(summary = "Listar todos los usuarios")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuarios encontrados", content = { @Content(mediaType = "application/list") })
	})
	@GetMapping
	public ResponseEntity<?> getUsuarios() {
		List<Usuario> usuarios = usuarioService.getUsuarios();
		if (usuarios.size() > 0) return ResponseEntity.ok(usuarios);
		else return ResponseEntity.ok("No hay usuarios");
	}
	
	@Operation(summary = "Buscar un usuario por ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuario encontrado", content = { @Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "200", description = "Usuario no encontrado")
	})
	@GetMapping("/{id}")
	public ResponseEntity<?> getUsuario(@PathVariable Long id) {
		Usuario usuario = usuarioService.getUsuario(id);
		if (usuario != null) return ResponseEntity.ok(usuario);
		else return ResponseEntity.ok("Usuario no encontrado");
	}
	
	@Operation(summary = "Actualizar un usuario")
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarUsuario(@RequestBody @Valid Usuario usuario) {
		if (usuarioService.actualizarUsuario(usuario)) return ResponseEntity.ok("Usuario actualizado correctamente: " + usuario.getNombre());
		else return ResponseEntity.ok("Usuario no encontrado");
	}
	
	@Operation(summary = "Eliminar un usuario")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
		if (usuarioService.eliminarUsuario(id)) return ResponseEntity.ok("Usuario eliminado");
		else return ResponseEntity.ok("Usuario no encontrado");
	}
	
}
