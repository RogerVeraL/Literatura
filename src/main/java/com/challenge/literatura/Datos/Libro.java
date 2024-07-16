package com.challenge.literatura.Datos;

import jakarta.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @ManyToOne
    private Autor autor;

    private String idiomas;
    private Integer descargas;

    public Libro() {}

    public Libro(LibroDatos libroRecord) {
        this.titulo = libroRecord.titulo();
        this.idiomas = String.join(", ", libroRecord.idiomas());
        this.descargas = libroRecord.descargas();
        if (!libroRecord.autores().isEmpty()) {
            this.autor = new Autor(libroRecord.autores().get(0));
        }
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

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return "Libro{" +
                ", titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", idiomas='" + idiomas + '\'' +
                ", descargas=" + descargas +
                '}';
    }
}
