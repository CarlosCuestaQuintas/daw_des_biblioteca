package com.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biblioteca.models.Prestamo;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

	@Query("SELECT p FROM Prestamo p WHERE idUsuario = :idUsuario")
	List<Prestamo> buscarPrestamosPorUsuario(@Param("idUsuario") Long idUsuario);
	
	@Query("SELECT p FROM Prestamo p WHERE estado LIKE 'ACTIVO'")
	List<Prestamo> buscarPrestamosActivos();
}
