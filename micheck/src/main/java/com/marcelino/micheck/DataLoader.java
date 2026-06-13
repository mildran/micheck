package com.marcelino.micheck;

import com.marcelino.micheck.model.Tipo;
import com.marcelino.micheck.service.EntradaService;
import com.marcelino.micheck.service.TipoService;
import com.marcelino.micheck.model.Entrada;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final TipoService tipoService;
    private final EntradaService entradaService;

    public DataLoader(TipoService tipoService, EntradaService entradaService) {
        this.tipoService = tipoService;
        this.entradaService = entradaService;
    }

    @Override
    public void run(String... args) {
        // Crear tipos
        Tipo serie = new Tipo("Serie", true, "Episodio");
        Tipo comic = new Tipo("Comic", false, "Tomo");
        Tipo libro = new Tipo("Libro", false, "Capítulo");
        tipoService.agregar(serie);
        tipoService.agregar(comic);
        tipoService.agregar(libro);

        // Crear entradas
        Entrada entrada1 = new Entrada("Breaking Bad", serie, 2, 12, "Muy buena");
        Entrada entrada2 = new Entrada("One Piece", comic, 0, 10, "Manga japonés");
        entradaService.agregar(entrada1);
        entradaService.agregar(entrada2);
    }
}