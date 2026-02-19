package com.aluracursos.literalura.main;

import com.aluracursos.literalura.model.DatosLibro;
import com.aluracursos.literalura.model.Libro;
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
    private List<DatosLibro> datosLibros = new ArrayList<>();
    private List<Libro> libros = new ArrayList<>();
    private Optional<Libro> libroBuscado;

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
                case 0:
                    System.out.println("Saliendo de la aplicación");
                    break;
                default:
                    System.out.println("Opción no inválida, digite una opción válida");
            }
        }
    }
    private void buscarLibro(){
        DatosLibro datos = getDatosLibro();
        Libro libro = new Libro(datos);
        datosLibros.add(datos);

        System.out.println(datos);
    }
    private DatosLibro getDatosLibro(){
        System.out.println("--------------------------------------");
        System.out.print("Ingrese el titulo del libro: ");
        String tituloLibro = input.nextLine();
        var json = consumoAPI.getData(URL_BASE + tituloLibro.replace(" ", "%20"));
        DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class);
        System.out.println(datos.sinopsis());
        return datos;
    }
    private void buscarPorAutor(){

    }
    private void top5LibrosMasDescargados(){}
    private void listarTodosLosAutores(){}
    private void listarAutoresVivosSegunAnio(){}
    private void listarTodosLosLibros(){}

}
