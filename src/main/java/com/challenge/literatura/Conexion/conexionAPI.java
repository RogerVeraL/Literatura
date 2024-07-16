package com.challenge.literatura.Conexion;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class conexionAPI {

    public String consulta(String title) {
        try {
            URI direccion = URI.create("https://gutendex.com/books/?search=" + title);
            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(direccion)
                    .build();
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            return "Error en la consulta: " + e.getMessage();
        }
    }
}
