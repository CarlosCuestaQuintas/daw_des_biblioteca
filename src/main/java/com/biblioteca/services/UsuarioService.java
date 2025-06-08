package com.biblioteca.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.models.Usuario;
import com.biblioteca.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void crearUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public List<Usuario> getUsuarios() {
		return usuarioRepository.findAll();
	}
	
	public Usuario getUsuario(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (!usuario.isEmpty()) return usuario.get();
		else return null;
	}
	
	public boolean actualizarUsuario(Usuario usuario) {
		Usuario u = buscarUsuario(usuario.getId());
		if (u != null) {
			u.setNombre(usuario.getNombre());
			u.setEmail(usuario.getEmail());
			u.setDni(usuario.getDni());
			usuarioRepository.save(u);
			return true;
		}
		else return false;
	}
	
	public boolean eliminarUsuario(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (!usuario.isEmpty()) {
			usuarioRepository.deleteById(id);
			return true;
		}
		else return false;
	}
	
	private Usuario buscarUsuario(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (!usuario.isEmpty()) return usuario.get();
		else return null;
	}

}
