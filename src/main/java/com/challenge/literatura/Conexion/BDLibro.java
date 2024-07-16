package com.challenge.literatura.Conexion;

import com.challenge.literatura.Datos.Libro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BDLibro extends JpaRepository<Libro, Long> {

    @Query("SELECT DISTINCT l.idiomas FROM Libro l")
    List<String> consulta();

    @Query("SELECT l FROM Libro l WHERE l.idiomas LIKE %:idioma%")
    List<Libro> busquedaIdioma(@Param("idioma") String idioma);
}
