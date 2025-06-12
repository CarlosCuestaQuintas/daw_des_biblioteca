package com.biblioteca.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.models.Libro;
import com.biblioteca.repositories.LibroRepository;

@Service
public class LibroService {
	
	@Autowired
	private LibroRepository libroRepository;
	
	public void crearLibro(Libro libro) {
		libroRepository.save(libro);
	}
	
	public List<Libro> getLibros() {
		return libroRepository.findAll();
	}
	
	// TODO: ¿hacer que devuelva un booleano para gestionar el código y el mensaje que se muestra?
	public Libro getLibro(Long id) {
		Optional<Libro> libro = libroRepository.findById(id);
		if (!libro.isEmpty()) return libro.get();
		else return null;
	}
	
	public List<Libro> getLibrosPorTitulo(String titulo) {
		return libroRepository.buscarLibrosPorTitulo(titulo);
	}
	
	public List<Libro> getLibrosPorAutor(String autor) {
		return libroRepository.buscarLibrosPorAutor(autor);
	}
	
	public List<Libro> getLibrosPorTituloYAutor(String titulo, String autor) {
		return libroRepository.buscarLibrosPorTituloYAutor(titulo, autor);
	}
	
	public boolean actualizarLibro(Libro libro) {
		Libro l = buscarLibro(libro.getId());
		if (l != null) {
			l.setTitulo(libro.getTitulo());
			l.setAutor(libro.getAutor());
			l.setIsbn(libro.getIsbn());
			l.setCategoria(libro.getCategoria());
			l.setEjemplaresDisponibles(libro.getEjemplaresDisponibles());
			libroRepository.save(l);
			return true;
		}
		else return false;
	}
	
	public boolean eliminarLibro(Long id) {
		Optional<Libro> libro = libroRepository.findById(id);
		if (!libro.isEmpty()) {
			libroRepository.deleteById(id);
			return true;
		}
		else return false;
	}
	
	// Función para comprobar que el libro existe. Creo que no debería hacer falta
	private Libro buscarLibro(Long id) {
		Optional<Libro> libro = libroRepository.findById(id);
		if (!libro.isEmpty()) return libro.get();
		else return null;
	}
	
	public boolean hayExistencias(Long id) {
		Optional<Libro> libro = libroRepository.findById(id);
		if (!libro.isEmpty()) {
			if (libro.get().getEjemplaresDisponibles() > 0) return true;
			else return false;
		}
		else return false;
	}
	
	public void prestarLibro(Libro libro) {
		// En principio al llegar aquí ya nos hemos asegurado de que el libro existe
		libro.prestarLibro();
		libroRepository.save(libro);
	}
	
	public void devolverLibro(Libro libro) {
		// En principio al llegar aquí ya nos hemos asegurado de que el libro existe
		libro.devolverLibro();
		libroRepository.save(libro);
	}
}
