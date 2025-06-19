package com.aluracursos.bibliotecalibrosalura.modelos;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor

@Entity
@Table(name="Autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(unique = true)
    private String nombre;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Temporal(TemporalType.DATE)
    private Date fechaFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Libro> libros;



    public Autor(String nombre, Date fechaNacimiento, Date fechaFallecimiento, List<Libro> libros){
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
        this.libros= libros;

    }


    @Override
    public String toString() {
        return "------Informaccion Autor------" +
                "\n Nombre='" + nombre +
                "\n FechaNacimiento='" + fechaNacimiento +
                "\n FechaFallecimiento='" + fechaFallecimiento +
                "\n Libros=" + libros +
                "\n-----------------------------\n";
    }
}
