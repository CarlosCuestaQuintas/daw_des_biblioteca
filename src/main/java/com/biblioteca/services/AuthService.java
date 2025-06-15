package com.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.models.Usuario;
import com.biblioteca.repositories.UsuarioRepository;
import com.biblioteca.security.JwtProvider;

@Service
public class AuthService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	public String autenticar(String nombre) {
		Usuario usuario = usuarioRepository.buscarUsuarioPorNombre(nombre);
		
		if (usuario != null) {
			System.out.println("Generando token para el usuario " + nombre);
			String token = jwtProvider.generarToken(nombre);
			System.out.println("Token: " + token);
			return token;
		}
		else {
			System.out.println("No generando token para el usuario " + nombre + " porque no existe");
			throw new RuntimeException("Error al generar token: el usuario no existe");
		}
	}

}
