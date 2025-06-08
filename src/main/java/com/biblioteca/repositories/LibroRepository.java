package com.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biblioteca.models.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
	
	@Query("SELECT l FROM Libro l WHERE titulo LIKE :titulo")
	List<Libro> buscarLibrosPorTitulo(@Param("titulo") String titulo);
	
	@Query("SELECT l FROM Libro l WHERE autor LIKE :autor")
	List<Libro> buscarLibrosPorAutor(@Param("autor") String autor);
	
	@Query("SELECT l FROM Libro l WHERE titulo LIKE :titulo AND autor LIKE :autor")
	List<Libro> buscarLibrosPorTituloYAutor(@Param("titulo") String titulo, @Param("autor") String autor);

}
