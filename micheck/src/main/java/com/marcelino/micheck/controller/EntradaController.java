package com.marcelino.micheck.controller;

import com.marcelino.micheck.model.Entrada;
import com.marcelino.micheck.model.Episodio;
import com.marcelino.micheck.model.Categoria;
import com.marcelino.micheck.service.EntradaService;
import com.marcelino.micheck.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/entradas")
public class EntradaController {

    private final EntradaService entradaService;
    private final CategoriaService categoriaService;

    public EntradaController(EntradaService entradaService, CategoriaService categoriaService) {
        this.entradaService = entradaService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) Integer categoriaIndice, Model model) {
        model.addAttribute("categorias", categoriaService.getTodos());
        model.addAttribute("entrada", new Entrada());
        model.addAttribute("categoriaIndice", categoriaIndice);

        if (categoriaIndice != null && categoriaIndice >= 0) {
            Categoria categoriaSeleccionada = categoriaService.getTodos().get(categoriaIndice);
            model.addAttribute("entradas", entradaService.getByCategoria(categoriaSeleccionada));
            model.addAttribute("categoriaSeleccionada", categoriaSeleccionada);
        } else {
            model.addAttribute("entradas", entradaService.getTodas());
            model.addAttribute("categoriaSeleccionada", null);
        }

        return "entradas";
    }

    @PostMapping
    public String agregar(@ModelAttribute Entrada entrada) {
        entradaService.agregar(entrada);
        return "redirect:/entradas";
    }

    @GetMapping("/eliminar/{indice}")
    public String eliminar(@PathVariable int indice) {
        entradaService.eliminar(indice);
        return "redirect:/entradas";
    }

    @GetMapping("/{indice}")
    public String detalle(@PathVariable int indice, Model model) {
        model.addAttribute("entrada", entradaService.getEntrada(indice));
        model.addAttribute("indice", indice);
        return "detalle";
    }

    @PostMapping("/{indice}/episodios/{eIndice}")
    public String marcarEpisodio(@PathVariable int indice, @PathVariable int eIndice) {
        Entrada entrada = entradaService.getEntrada(indice);
        Episodio episodio = entrada.getEpisodios().get(eIndice);
        episodio.setVisto(!episodio.isVisto());
        return "redirect:/entradas/" + indice;
    }

}