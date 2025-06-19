package com.aluracursos.bibliotecalibrosalura.repositorio;

import com.aluracursos.bibliotecalibrosalura.modelos.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {


    @Query("SELECT a FROM Autor a WHERE :anio>=a.fechaNacimiento AND :anio<a.fechaFallecimiento")
    List<Autor> obtenerAutoresVivos (int anio);

}
