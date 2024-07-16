package com.challenge.literatura.Conexion;

import com.challenge.literatura.Datos.Autor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BDAutor extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.muerte > :fecha AND a.nacimiento < :fecha")
    List<Autor> busquedaAutorVivo(@Param("fecha") Integer fecha);
}
