package com.marcelino.micheck;

import com.marcelino.micheck.model.Categoria;
import com.marcelino.micheck.service.EntradaService;
import com.marcelino.micheck.service.CategoriaService;
import com.marcelino.micheck.model.Entrada;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoriaService categoriaService;
    private final EntradaService entradaService;

    public DataLoader(CategoriaService categoriaService, EntradaService entradaService) {
        this.categoriaService = categoriaService;
        this.entradaService = entradaService;
    }

    @Override
    public void run(String... args) {
        // Crear categorías
        Categoria serie = new Categoria("Serie", true, "Episodio");
        Categoria comic = new Categoria("Comic", false, "Tomo");
        Categoria libro = new Categoria("Libro", false, "Capítulo");
        categoriaService.agregar(serie);
        categoriaService.agregar(comic);
        categoriaService.agregar(libro);

        // Crear entradas
        Entrada entrada1 = new Entrada("Breaking Bad", serie, 2, 12, "Muy buena");
        Entrada entrada2 = new Entrada("One Piece", comic, 0, 10, "Manga japonés");
        entradaService.agregar(entrada1);
        entradaService.agregar(entrada2);
    }
}