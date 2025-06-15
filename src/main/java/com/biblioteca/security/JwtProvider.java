package com.biblioteca.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtProvider {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private long expiration;
	
	public String generarToken(String usuario) {
		return Jwts.builder()
				.setSubject(usuario)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
	
	public boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		}
		catch (Exception e) {
			System.out.println("Error en validar token: " + e.getMessage());
			return false;
		}
	}
	
	public String sacarUsuarioDelToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
}
