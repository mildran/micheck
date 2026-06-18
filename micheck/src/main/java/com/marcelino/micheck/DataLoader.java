package com.marcelino.micheck;

import com.marcelino.micheck.model.Categoria;
import com.marcelino.micheck.model.Entrada;
import com.marcelino.micheck.model.Usuario;
import com.marcelino.micheck.service.CategoriaService;
import com.marcelino.micheck.service.EntradaService;
import com.marcelino.micheck.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoriaService categoriaService;
    private final EntradaService entradaService;
    private final UsuarioService usuarioService;

    public DataLoader(CategoriaService categoriaService, EntradaService entradaService, UsuarioService usuarioService) {
        this.categoriaService = categoriaService;
        this.entradaService = entradaService;
        this.usuarioService = usuarioService;
    }

    @Override
    @Transactional
    public void run(String... args) {
        // Crear admin por defecto si no existe
        if (usuarioService.getByEmail("admin@micheck.com") == null) {
            usuarioService.crearAdmin("Admin", "admin@micheck.com", "admin123");
        }

        Usuario admin = usuarioService.getByEmail("admin@micheck.com");

        if (!categoriaService.getTodos(admin).isEmpty()) {
            return;
        }

        // Crear categorías asignadas al admin
        Categoria serie = new Categoria("Serie", true, "Episodio");
        Categoria comic = new Categoria("Comic", false, "Tomo");
        Categoria libro = new Categoria("Libro", false, "Capítulo");
        categoriaService.agregar(serie, admin);
        categoriaService.agregar(comic, admin);
        categoriaService.agregar(libro, admin);

        // Crear entradas asignadas al admin
        Entrada entrada1 = new Entrada("Breaking Bad", serie, "Muy buena");
        entradaService.agregar(entrada1, 1, 7, admin);
        entradaService.agregarTemporada(entrada1.getId(), 13);

        Entrada entrada2 = new Entrada("One Piece", comic, "Manga japonés");
        entradaService.agregar(entrada2, 1, 10, admin);
    }
}