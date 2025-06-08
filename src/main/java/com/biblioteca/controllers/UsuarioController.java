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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<?> crearUsuario(@RequestBody @Valid Usuario usuario) {
		usuarioService.crearUsuario(usuario);
		return ResponseEntity.ok("Usuario creado correctamente: " + usuario.getNombre());
	}
	
	@GetMapping
	public ResponseEntity<?> getUsuarios() {
		List<Usuario> usuarios = usuarioService.getUsuarios();
		if (usuarios.size() > 0) return ResponseEntity.ok(usuarios);
		else return ResponseEntity.ok("No hay usuarios");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUsuario(@PathVariable Long id) {
		Usuario usuario = usuarioService.getUsuario(id);
		if (usuario != null) return ResponseEntity.ok(usuario);
		else return ResponseEntity.ok("Usuario no encontrado");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarUsuario(@RequestBody @Valid Usuario usuario) {
		if (usuarioService.actualizarUsuario(usuario)) return ResponseEntity.ok("Usuario actualizado correctamente: " + usuario.getNombre());
		else return ResponseEntity.ok("Usuario no encontrado");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
		if (usuarioService.eliminarUsuario(id)) return ResponseEntity.ok("Usuario eliminado");
		else return ResponseEntity.ok("Usuario no encontrado");
	}
	
}
