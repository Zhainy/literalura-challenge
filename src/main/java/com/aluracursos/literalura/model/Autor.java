package com.aluracursos.literalura.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int Id;
    private String nombre;
    private LocalDate birthYear;
    private LocalDate deathYear;
    @ManyToMany(fetch = FetchType.EAGER)
    private String libro;

    public Autor() {}
    public Autor(String nombre, LocalDate birthYear, LocalDate deathYear, String libro) {
        this.nombre = nombre;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.libro = libro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(LocalDate birthYear) {
        this.birthYear = birthYear;
    }

    public LocalDate getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(LocalDate deathYear) {
        this.deathYear = deathYear;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }
    @Override
    public String toString() {
        return """
                --------------------------------------
                                Autor
                --------------------------------------
               """ +
                "Nombre: " + nombre +
                " | Año de nacimiento: " + birthYear +
                " | Año de defunción: " + deathYear +
                " | Libros: " + libro +
                """
                 --------------------------------------
                """;
    }
}
