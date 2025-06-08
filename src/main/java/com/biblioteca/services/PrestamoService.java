package com.biblioteca.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.models.Libro;
import com.biblioteca.models.Prestamo;
import com.biblioteca.repositories.PrestamoRepository;

@Service
public class PrestamoService {

	@Autowired
	private PrestamoRepository prestamoRepository;
	
	@Autowired
	private LibroService libroService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	/*public boolean crearPrestamo(Prestamo prestamo) {
		if (libroService.hayExistencias(prestamo.getIdLibro())) {
			prestamo.activar();
			prestamoRepository.save(prestamo);
			return true;
		}
		else return false;
	}*/
	
	public int crearPrestamo(Prestamo prestamo) {
		if (usuarioService.getUsuario(prestamo.getIdUsuario()) == null) return -1; // El usuario no existe
		else {
			Libro libro = libroService.getLibro(prestamo.getIdLibro());
			if (libro == null) return -2; // No existe el libro
			else {
				if (libro.getEjemplaresDisponibles() <= 0) return -3; // No hay existencias de ese libro
				else {
					prestamo.activar();
					libro.prestarLibro();
					prestamoRepository.save(prestamo);
					return 0; // Préstamo correcto
				}
			}
		}
	}
	
	public List<Prestamo> getPrestamos() {
		return prestamoRepository.findAll();
	}
	
	// TODO: ¿hacer que devuelva un booleano para gestionar el código y el mensaje que se muestra?
	public Prestamo getPrestamo(Long id) {
		Optional<Prestamo> prestamo = prestamoRepository.findById(id);
		if (!prestamo.isEmpty()) return prestamo.get();
		else return null;
	}
	
	// TODO: ¿qué pasa si no existe en usuario?
	public List<Prestamo> getPrestamosPorUsuario(Long idUsuario) {
		return prestamoRepository.buscarPrestamosPorUsuario(idUsuario);
	}
	
	public List<Prestamo> getPrestamosActivos() {
		return prestamoRepository.buscarPrestamosActivos();
	}
	
	public boolean devolverPrestamo(Long id) {
		Optional<Prestamo> prestamo = prestamoRepository.findById(id);
		if (!prestamo.isEmpty()) {
			Prestamo pres = prestamo.get();
			pres.devolver();
			Libro libro = libroService.getLibro(pres.getIdLibro());
			libroService.devolverLibro(libro);
			prestamoRepository.save(pres);
			return true;
		}
		else return false;
	}
	
	/*
	private Prestamo buscarPrestamo(Long id) {
		Optional<Prestamo> prestamo = prestamoRepository.findById(id);
		if (!prestamo.isEmpty()) return prestamo.get();
		else return null;
	}
	*/
	
}
