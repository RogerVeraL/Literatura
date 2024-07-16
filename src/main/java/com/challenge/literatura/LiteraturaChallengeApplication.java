package com.challenge.literatura;

import com.challenge.literatura.Conexion.BDAutor;
import com.challenge.literatura.Conexion.BDLibro;
import com.challenge.literatura.Main.Principal;
import com.sun.tools.javac.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaChallengeApplication implements CommandLineRunner {

    @Autowired
    private BDAutor AutorRepo;

    @Autowired
    private BDLibro LibroRepo;

    public static void main(String[] args) {
        SpringApplication.run(LiteraturaChallengeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(LibroRepo, AutorRepo);
        principal.mostrarMenu();
    }
}

