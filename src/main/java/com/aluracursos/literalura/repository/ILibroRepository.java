package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ILibroRepository extends JpaRepository<Libro, Long> {
    // Busca un libro por su título ignorando mayúsculas/minúsculas
    Optional<Libro> findByTituloContainsIgnoreCase(String titulo);
    // Buscar por Autor
    List<Libro> findByAutorNombreContainsIgnoreCase(String nombreAutor);
    // Top 5
    List<Libro> findTop5ByOrderByNumeroDescargasDesc();

    /** Listar autores vivos en determinado año (version 1)
    @Query("SELECT a FROM Autor a WHERE :anio BETWEEN a.birthYear AND a.deathYear")
    List<Autor> listarAutoresVivosSegunAnio(@Param("anio") Integer anio);**/

    // Lista de todos los autores
    @Query("SELECT a FROM Autor a")
    List<Autor> buscarTodosLosAutores();
    //Exhibir cantidad de libros en ciertos idiomas
    Integer countByLenguaje(String lenguaje);
}

