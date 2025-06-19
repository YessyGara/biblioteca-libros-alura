package com.aluracursos.bibliotecalibrosalura.repositorio;


import com.aluracursos.bibliotecalibrosalura.modelos.Idiomas;
import com.aluracursos.bibliotecalibrosalura.modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface LibroRepository extends JpaRepository <Libro, Long>{
    Optional<Libro> findBytituloContainsIgnoreCase(String nombreLibro);


   @Query("SELECT l FROM Libro l WHERE l.idiomas=:idiomas")
   List<Libro> obtenerLibrosPorIdioma(Idiomas idiomas);


}
