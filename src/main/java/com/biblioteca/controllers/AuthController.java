package com.biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.services.AuthService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("api/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<?> autenticarUsuario(@RequestBody String usuario) {
		try {
			String token = authService.autenticar(usuario);
			return ResponseEntity.ok(token);
		}
		catch (Exception e) {
			// Esta excepción que se captura es la declarada en AuthService
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

}
