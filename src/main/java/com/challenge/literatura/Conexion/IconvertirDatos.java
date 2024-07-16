package com.challenge.literatura.Conexion;

public interface IconvertirDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
