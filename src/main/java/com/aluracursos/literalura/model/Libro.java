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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autor autor;
    private String lenguaje;
    @Column(columnDefinition = "TEXT")
    private String sinopsis;
    private String tematica;
    private int numeroDescargas;


    public Libro() {}

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        if (datosLibro.autor() != null && !datosLibro.autor().isEmpty()) {
            this.autor = new Autor(datosLibro.autor().get(0));
        }
        if (datosLibro.lenguaje() != null && !datosLibro.lenguaje().isEmpty()) {
            this.lenguaje = datosLibro.lenguaje().get(0);
        }
        if (datosLibro.sinopsis() != null && !datosLibro.sinopsis().isEmpty()) {
            this.sinopsis = datosLibro.sinopsis().get(0);
        } else {
            this.sinopsis = "Sin sinopsis disponible";
        }
        if (datosLibro.tematica() != null && !datosLibro.tematica().isEmpty()) {
            this.tematica = datosLibro.tematica().get(0);
        }
        this.numeroDescargas = datosLibro.numeroDescargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
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
