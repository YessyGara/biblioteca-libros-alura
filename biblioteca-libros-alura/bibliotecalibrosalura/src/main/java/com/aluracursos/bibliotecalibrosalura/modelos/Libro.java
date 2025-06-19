package com.aluracursos.bibliotecalibrosalura.modelos;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor

@Entity
@Table(name="libros")
public class Libro {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Idiomas idiomas;
    private List<Autor> autores;
    private double numeroDescargas;
    @ManyToOne
    @JoinColumn(name= "autor")
    private Autor autor;


    public Libro(String titulo, Autor autor, Idiomas idiomas, double numeroDescargas) {
        this.titulo = titulo;
        this.autor= autor;
        this.idiomas = idiomas;
        this.numeroDescargas = numeroDescargas;

    }

    @Override
    public String toString() {
        return "------Informacion Libro------" +
                "\n Titulo= " + titulo + '\''+
                "\n Autor=" + autor  +'\''+
                "\n idiomas=" + idiomas + '\''+
                "\n numeroDescargas=" + numeroDescargas + '\''+
                "\n----------------------------\n";
    }
}
