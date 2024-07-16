package com.challenge.literatura.Datos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroDatos(
        @JsonAlias("id") String id,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") ArrayList<AutorDatos> autores,
        @JsonAlias("languages") ArrayList<String> idiomas,
        @JsonAlias("download_count") Integer descargas
) {}
