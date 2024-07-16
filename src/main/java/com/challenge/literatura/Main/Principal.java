package com.challenge.literatura.Main;

import com.challenge.literatura.Conexion.BDAutor;
import com.challenge.literatura.Conexion.BDLibro;
import com.challenge.literatura.Conexion.conexionAPI;
import com.challenge.literatura.Conexion.convertirDatos;
import com.challenge.literatura.Datos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;
import java.util.Scanner;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Principal implements CommandLineRunner {

    @Autowired
    private BDLibro libroRepo;
    @Autowired
    private BDAutor autorRepo;

    private final conexionAPI conexion = new conexionAPI();
    private final Scanner scanner = new Scanner(System.in);
    private final convertirDatos conversor = new convertirDatos();
    private int opcion;

    public static void main(String[] args) {
        SpringApplication.run(Principal.class, args);
    }

    @Override
    public void run(String... args) {
        mostrarMenu();
    }
    public Principal(BDLibro libroRepo, BDAutor autorRepo) {
        this.libroRepo = libroRepo;
        this.autorRepo = autorRepo;
    }

    public void mostrarMenu() {
        String menuOpciones = """
                Elige una de las opciones:
                1. Buscar libro por título
                2. Listar libros registrados
                3. Listar autores registrados
                4. Listar autores en determinado año
                5. Listar libros por idioma
                0. Salir
                """;
        do {
            System.out.println(menuOpciones);
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1 -> buscarLibro();
                    case 2 -> listarLibros();
                    case 3 -> listarAutores();
                    case 4 -> listarAutoresPorAno();
                    case 5 -> listarLibrosPorIdioma();
                    case 0 -> System.out.println("Gracias por usar el programa! Hasta luego!");
                    default -> System.out.println("Opción no válida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private void buscarLibro() {
        System.out.println("Escribe el título del libro a buscar:");
        String libroBusqueda = scanner.nextLine();
        String json = conexion.consulta(libroBusqueda.replace(" ", "+"));
        Resultados resultados = conversor.obtenerDatos(json, Resultados.class);

        if (resultados != null && !resultados.resultados().isEmpty()) {
            LibroDatos datosLibro = resultados.resultados().get(0);
            Libro libro = new Libro(datosLibro);
            libroRepo.save(libro);
            System.out.println("Libro encontrado y guardado: " + libro);
        } else {
            System.out.println("No se encontraron resultados.");
        }
    }

    private void listarLibros() {
        List<Libro> libros = libroRepo.findAll();
        libros.forEach(System.out::println);
    }

    private void listarAutores() {
        List<Autor> autores = autorRepo.findAll();
        autores.forEach(System.out::println);
    }

    private void listarAutoresPorAno() {
        System.out.println("Año a buscar:");
        int fecha = Integer.parseInt(scanner.nextLine());
        List<Autor> autores = autorRepo.busquedaAutorVivo(fecha);
        autores.forEach(a -> System.out.printf("Nombre: %s, Edad: %d%n", a.getNombre(), fecha - a.getNacimiento()));
    }

    private void listarLibrosPorIdioma() {
        List<String> idiomas = libroRepo.consulta();
        System.out.println("Idiomas disponibles: " + String.join(", ", idiomas));
        System.out.println("Escribe la abreviatura del idioma:");
        String idioma = scanner.nextLine();
        List<Libro> libros = libroRepo.busquedaIdioma(idioma);
        libros.forEach(System.out::println);
    }
}
