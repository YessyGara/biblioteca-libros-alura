package com.aluracursos.bibliotecalibrosalura;

import com.aluracursos.bibliotecalibrosalura.principal.Principal;
import com.aluracursos.bibliotecalibrosalura.repositorio.AutorRepository;
import com.aluracursos.bibliotecalibrosalura.repositorio.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecalibrosaluraApplication implements CommandLineRunner {

	@Autowired
	private AutorRepository autorRepository;
	@Autowired
	private LibroRepository libroRepository;

	public static void main(String[] args) {
		SpringApplication.run(BibliotecalibrosaluraApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Principal principal= new Principal(autorRepository,libroRepository);
		principal.muestraMenu();
	}
}
