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
    @org.springframework.transaction.annotation.Transactional
    public void run(String... args) {
        if (!categoriaService.getTodos().isEmpty()) {
            return;
        }

        // Crear categorías
        Categoria serie = new Categoria("Serie", true, "Episodio");
        Categoria comic = new Categoria("Comic", false, "Tomo");
        Categoria libro = new Categoria("Libro", false, "Capítulo");
        categoriaService.agregar(serie);
        categoriaService.agregar(comic);
        categoriaService.agregar(libro);

        // Crear entradas
        Entrada entrada1 = new Entrada("Breaking Bad", serie, "Muy buena");
        entradaService.agregar(entrada1, 1, 7);
        entradaService.agregarTemporada(entrada1.getId(), 13);

        Entrada entrada2 = new Entrada("One Piece", comic, "Manga japonés");
        entradaService.agregar(entrada2, 1, 10);
    }
}