package com.aluracursos.bibliotecalibrosalura.principal;

import com.aluracursos.bibliotecalibrosalura.modelos.*;
import com.aluracursos.bibliotecalibrosalura.repositorio.AutorRepository;
import com.aluracursos.bibliotecalibrosalura.repositorio.LibroRepository;
import com.aluracursos.bibliotecalibrosalura.servicios.ConsumoApi;
import com.aluracursos.bibliotecalibrosalura.servicios.ConvierteDatos;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;

import java.awt.print.Book;
import java.util.*;
import java.util.stream.Collectors;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

public class Principal {
    private Scanner teclado= new Scanner(System.in);
    private ConsumoApi consumoAPI= new ConsumoApi();
    private ConvierteDatos convierteDatos= new ConvierteDatos();
    private final String URL_BASE= "https://gutendex.com/books/";
    private List<LibroRecord> libroRecordList= new ArrayList<>();
    private List<Libro> libros;
    private List<Autor> autor;
    private Idiomas idiomas;

    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(AutorRepository autorRepository, LibroRepository libroRepository) {
        this.autorRepository= autorRepository;
        this.libroRepository= libroRepository;
    }


    public void muestraMenu() {

        var opcion = -1;
        while (opcion != 0) {

            var menu = """
                    ########### MENU ############
                    1) - Buscar libro por titulo
                    2) - Mostrar libros registrados
                    3) - Listar autores registrados
                    4) - Listar autores vivos en determinado año
                    5) - Listar libros por idioma
                    
                    0 - salir
                    \n###########################
                    """;

            System.out.println("\n");
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    mostrarLibrosRegistrados();
                   break;
                case 3:
                    listarautoresregistrados();
                    break;
                case 4:
                    listaautoresvivosendeterminadoano();
                    break;
                case 5:
                    Listaribrosporidioma();
                    break;
                case 0:
                    System.out.println("Cerrando el programa, gracias por usar nuestra APP");
                default:
                    System.out.println("Opcion no valida");

            }


        }

    }

    private LibroRecord getdatosLibros (){
        System.out.println("Escriba el libro que desea buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE+ "?search=" + nombreLibro.replace(" ", "+"));
        System.out.println(json);
        LibroRecord datos= convierteDatos.obtenerDatos(json,LibroRecord.class);
        return getdatosLibros();
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Escriba el nombre del libro que desea buscar ");
        var nombreLibro= teclado.nextLine();
        Optional<Libro> libroBuscado= libroRepository.findBytituloContainsIgnoreCase(nombreLibro);

        if(libroBuscado.isPresent()){
            System.out.println("El libro buscado es= " + libroBuscado.get());
        }else{
            System.out.println("Libro no encontrado");
        }
    }

      private void mostrarLibrosRegistrados() {
         libros = libroRepository.findAll();
         libros.stream()
                .forEach(System.out::println);
   }

      private void listarautoresregistrados(){
        autor= autorRepository.findAll();
        autor.stream().forEach(System.out::println);

     }

      private void listaautoresvivosendeterminadoano(){
          System.out.println("Ingrese el año vivo del autor(es) que desea buscar: ");
          try{
              int ano = teclado.nextInt();

              List<Autor> autor = autorRepository.obtenerAutoresVivos(ano);
              if(autor.isEmpty()){
                  System.out.println("No se encontraron registros de autores vivos durante ese año en la base de datos.");
              }else{
                  autor.forEach(System.out::println);
              }

          }catch (InputMismatchException e){
              System.out.println("Debes ingresar un año válido.");
          }


     }

      private void Listaribrosporidioma(){

          System.out.println("Ingrese el idioma que desea buscar: ");
          System.out.println("""
                      INGLES("en"),
                      CASTELLANO("es"),
                      ALEMAN("de"),
                      FRANCES("fr"),
                      ITALIANO("it"),
                      PORTUGUES("pt")
                """);

          String idioma = teclado.nextLine();

          List<Libro> libros = libroRepository.obtenerLibrosPorIdioma(idiomas);
          if(libros.isEmpty()){
              System.out.println("No se encontraron libros en ese idioma");
          }else{
              libros.forEach(System.out::println);
          }

      }














}
