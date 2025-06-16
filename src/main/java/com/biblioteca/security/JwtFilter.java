package com.biblioteca.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
		String cabeceraAuth = request.getHeader("Authorization");
		
		String token = null;
		String nombreUsuario = null;
		
		System.out.println("URI: " + request.getRequestURI());
		
		if (!request.getRequestURI().equalsIgnoreCase("/api/auth/login")) {
			if (cabeceraAuth != null && cabeceraAuth.startsWith("Bearer ")) {
				token = cabeceraAuth.substring(7);
				System.out.println("Validando token: " + token);
				if (jwtProvider.validarToken(token)) {
					System.out.println("Token validado correctamente");
				}
				else {
					System.out.println("Token inválido");
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
					return;
				}
			}
			else {
				System.out.println("Token no proporcionado");
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token no proporcionado");
				return;
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
