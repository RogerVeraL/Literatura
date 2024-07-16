package com.challenge.literatura.Datos;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record Resultados(
        @JsonAlias("results") List<LibroDatos> resultados) {
}
