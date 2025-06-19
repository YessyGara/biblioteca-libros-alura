package com.aluracursos.bibliotecalibrosalura.modelos;

public enum Idiomas {
    INGLES("en"),
    CASTELLANO("es"),
    ALEMAN("de"),
    FRANCES("fr"),
    ITALIANO("it"),
    PORTUGUES("pt");


    private String idioma;

    Idiomas (String idioma){
        this.idioma = idioma;
    }

    public static Idiomas fromString(String text) {
        for (Idiomas idiomas1 : Idiomas.values()) {
            if (idiomas1.idioma.equalsIgnoreCase(text)) {
                return idiomas1;
            }
        }
        throw new IllegalArgumentException("Ningun idioma encontrado: " + text);
    }
}
