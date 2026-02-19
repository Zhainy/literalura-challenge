package com.aluracursos.literalura.main;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.DatosLibro;
import com.aluracursos.literalura.model.DatosResultados;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.repository.IAutorRepository;
import com.aluracursos.literalura.repository.ILibroRepository;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static Scanner input = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private ILibroRepository repositorio;
    private IAutorRepository autorRepositorio;

    public Main(ILibroRepository repositorio, IAutorRepository autorRepositorio) {
        this.repositorio = repositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void showMenu(){
        var option = -1;
        while(option != 0){
            var menu = """
              1 - Buscar libro por titulo
              2 - Buscar Autor
              3 - Top 5 libros más descargados
              4 - Lista de todos los libros
              5 - Listar autores vivos en determinado año
              6 - Lista de todos los autores
              7 - Exhibir cantidad de libros en Italiano y Español
              
              0 - Salir
              """;
            System.out.println(menu);

            option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    buscarPorAutor();
                    break;
                case 3:
                    top5LibrosMasDescargados();
                    break;
                case 4:
                    listarTodosLosLibros();
                    break;
                case 5:
                    listarAutoresVivosSegunAnio();
                    break;
                case 6:
                    listarTodosLosAutores();
                    break;
                case 7:
                    estadisticasDeIdiomas();
                case 0:
                    System.out.println("Saliendo de la aplicación");
                    break;
                default:
                    System.out.println("Opción no inválida, digite una opción válida");
            }
        }
    }
    private void buscarLibro() {
        DatosLibro datos = getDatosLibro();

        if (datos != null) {
            Optional<Libro> libroExistente = repositorio.findByTituloContainsIgnoreCase(datos.titulo());

            if (libroExistente.isPresent()) {
                System.out.println("\n--------------------------------------");
                System.out.println("No se puede registrar el mismo libro más de una vez.");
                System.out.println("El libro '" + datos.titulo() + "' ya existe en nuestra base de datos.");
                System.out.println("Aquí tienes su información:");
                System.out.println(libroExistente.get());
                System.out.println("--------------------------------------\n");
            } else {
                Libro libro = new Libro(datos);
                repositorio.save(libro);
                System.out.println("¡Libro guardado en la base de datos con éxito!");
                System.out.println(libro);
            }
        }
    }
    private DatosLibro getDatosLibro(){
        System.out.println("--------------------------------------\n");
        System.out.print("Ingrese el titulo del libro: ");
        String tituloLibro = input.nextLine();
        System.out.println("--------------------------------------\n");

        var json = consumoAPI.getData(URL_BASE + tituloLibro.replace(" ", "%20"));
        DatosResultados datos = conversor.obtenerDatos(json, DatosResultados.class);

        if (!datos.resultados().isEmpty()) {
            DatosLibro libroEncontrado = datos.resultados().get(0);
            System.out.println("Libro encontrado en la base de datos!");
            System.out.println("Libro: " +libroEncontrado.titulo());
            System.out.println("Sinopsis: "+ libroEncontrado.sinopsis());
            System.out.println("--------------------------------------\n");

            return libroEncontrado;
        } else {
            System.out.println("No se encontró el libro.");
            System.out.println("--------------------------------------\n");

            return null;
        }
    }
    private void buscarPorAutor(){
        System.out.println("--------------------------------------");
        System.out.print("Ingrese en este orden, apellido y nombre del autor que desea buscar : ");
        var autorBuscado = input.nextLine();
        System.out.println("--------------------------------------\n");
        List<Libro> librosEncontradosPorAutor = repositorio.findByAutorNombreContainsIgnoreCase(autorBuscado);
        if (librosEncontradosPorAutor.isEmpty()) {
            System.out.println("No se encontraron libros para el autor: " + autorBuscado);
        } else {
            System.out.println("-------------------------------------");
            System.out.println("     LIBROS ENCONTRADOS POR AUTOR    ");
            System.out.println("--------------------------------------");
            librosEncontradosPorAutor.forEach(System.out::println);
        }
    }
    private void top5LibrosMasDescargados(){
        System.out.println("--------------------------------------");
        System.out.println("     TOP 5 LIBROS MÁS DESCARGADOS     ");
        System.out.println("--------------------------------------");
        List<Libro> top5 = repositorio.findTop5ByOrderByNumeroDescargasDesc();
        if (top5.isEmpty()) {
            System.out.println("Aún no hay libros en la base de datos para armar un Top 5.");
        } else {
            top5.forEach(System.out::println);
        }
    }
    private void listarAutoresVivosSegunAnio() {
        System.out.println("--------------------------------------");
        System.out.print("Ingrese el año en que desea buscar autores vivos: ");
        try {
            Integer anioBuscado = Integer.valueOf(input.nextLine());

            List<Autor> autoresVivos = autorRepositorio.findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(anioBuscado, anioBuscado);

            if (autoresVivos.isEmpty()) {
                System.out.println("No se encontraron autores vivos durante el año " + anioBuscado + " en la base de datos.");
            } else {
                System.out.println("-------------------------------------");
                System.out.println("    LISTA AUTORES VIVOS EN " + anioBuscado);
                System.out.println("--------------------------------------");
                autoresVivos.forEach(a -> System.out.printf("""
                
                --------------------------------------
                Nombre: %s
                Año de Nacimiento: %s
                Año de Defunción: %s
                --------------------------------------
                """,
                        a.getNombre(),
                        a.getBirthYear(),
                        a.getDeathYear() != null ? a.getDeathYear() : "Desconocido"));
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un año válido numérico (Ejemplo: 1600).");
            System.out.println("--------------------------------------\n");
        }
    }
    private void listarTodosLosAutores(){

        List<Autor> autores = repositorio.buscarTodosLosAutores();

        if (autores.isEmpty()) {
            System.out.println("----------------------------------------------");
            System.out.println("No se encontraron autores en la base de datos.");
            System.out.println("----------------------------------------------");
        } else  {
            System.out.println("-------------------------------------");
            System.out.println("      LISTA DE TODOS LOS AUTORES     ");
            System.out.println("--------------------------------------");

            autores.forEach(a -> System.out.printf("""
                    
                    --------------------------------------
                    Nombre: %s
                    Año de Nacimiento: %s
                    Año de Defunción: %s
                    --------------------------------------
                    """,
                    a.getNombre() !=null ? a.getNombre() : "Anónimo",
                    a.getBirthYear() != null ? a.getBirthYear() : "Desconocido",
                    a.getDeathYear() != null ? a.getDeathYear() : "Desconocido"));
        }
    }
    private void estadisticasDeIdiomas() {
        System.out.println("--------------------------------------");
        System.out.println("   ESTADÍSTICAS: ITALIANO Y ESPAÑOL   ");
        System.out.println("--------------------------------------");

        Integer cantidadEspanol = repositorio.countByLenguaje("es");
        Integer cantidadItaliano = repositorio.countByLenguaje("it");
        Integer cantidadIngles = repositorio.countByLenguaje("en");

        System.out.println("Idioma: Español | Cantidad de libros: " + cantidadEspanol);
        System.out.println("Idioma: Italiano | Cantidad de libros: " + cantidadItaliano);
        System.out.println("Idioma: Ingles | Cantidad de libros: " + cantidadIngles);
        System.out.println("--------------------------------------\n");
    }

    private void listarTodosLosLibros(){
        List<Libro> libros = new ArrayList<>();
        libros = repositorio.findAll();
        if(libros.isEmpty()) {
            System.out.println("--------------------------------------------");
            System.out.println("No se encontro el libro en la base de datos.");
            System.out.println("--------------------------------------------");
        } else {
            libros.forEach(System.out::println);
        }
    }

}
