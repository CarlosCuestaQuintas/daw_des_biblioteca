package com.biblioteca;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.biblioteca.models.Libro;
import com.biblioteca.models.Prestamo;
import com.biblioteca.models.Usuario;
import com.biblioteca.repositories.LibroRepository;
import com.biblioteca.repositories.PrestamoRepository;
import com.biblioteca.repositories.UsuarioRepository;

@Component
public class DataLoader implements CommandLineRunner {
	
	private final LibroRepository libroRepository;
	private final UsuarioRepository usuarioRepository;
	private final PrestamoRepository prestamoRepository;
	
	public DataLoader(LibroRepository libroRepository, UsuarioRepository usuarioRepository, PrestamoRepository prestamoRepository) {
		this.libroRepository = libroRepository;
		this.usuarioRepository = usuarioRepository;
		this.prestamoRepository = prestamoRepository;
	}
	
	@Override
	public void run(String... args) throws Exception {
		// Inicilización de libros
		libroRepository.save(new Libro("La Comunidad del Anillo", "JRR Tolkien", "0", "Fantasía", 2));
		libroRepository.save(new Libro("El Elfo Oscuro", "RA Salvatore", "1", "Fantasía", 0));
		libroRepository.save(new Libro("Horus, Señor de la Guerra", "Dan Abnett", "2", "Ciencia ficción", 2));
		libroRepository.save(new Libro("API Rest con Spring para expertos", "Joaquín Precioso", "3", "Tecnología", 1));
		
		// Inicialización de usuarios
		usuarioRepository.save(new Usuario("Carlos", "ccq@ccq.es", "00000000A"));
		usuarioRepository.save(new Usuario("Joaquín Precioso", "jp@ccq.es", "11111111B"));
		
		// Inicialización de préstamos
		prestamoRepository.save(new Prestamo(1L, 1L));
	}

}
