package com.aluracursos.bibliotecalibrosalura.servicios;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json,Class <T> clase );
}
