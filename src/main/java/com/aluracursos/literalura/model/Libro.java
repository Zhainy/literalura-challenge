package com.aluracursos.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name="libros")

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
    private String lenguaje;
    private String sinopsis;
    private String tematica;
    private int numeroDescargas;


    public Libro() {}

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.autor = datosLibro.autor();
        this.lenguaje = datosLibro.lenguaje();
        this.sinopsis = datosLibro.sinopsis();
        this.tematica = datosLibro.tematica();
        this.numeroDescargas = datosLibro.numeroDescargas();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public int getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(int numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }
    @Override
    public String toString() {
        return """
                --------------------------------------
                                Libro
                --------------------------------------
               """ +
                "Titulo: " + titulo +
                        " | Autor: " + autor +
                        " | Sinopsis: " + sinopsis +
                        " | Idioma: " + lenguaje +
                        " | Temática: " + tematica +
                        " | Descargas: " + numeroDescargas +
                """
                 --------------------------------------
                """;
    }
}
