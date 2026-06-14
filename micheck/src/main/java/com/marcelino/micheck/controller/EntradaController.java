package com.marcelino.micheck.controller;

import com.marcelino.micheck.model.Entrada;
import com.marcelino.micheck.model.Episodio;
import com.marcelino.micheck.model.Categoria;
import com.marcelino.micheck.service.EntradaService;
import com.marcelino.micheck.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
    public String agregar(@ModelAttribute Entrada entrada,
                          @RequestParam int numeroTemporada,
                          @RequestParam int numeroUnidades) {
        entradaService.agregar(entrada, numeroTemporada, numeroUnidades);
        return "redirect:/entradas";
    }

    @GetMapping("/eliminar/{indice}")
    public String eliminar(@PathVariable int indice) {
        entradaService.eliminar(indice);
        return "redirect:/entradas";
    }

    @GetMapping("/{indice}")
    public String detalle(@PathVariable int indice,
                          @RequestParam(required = false) Integer temporada,
                          Model model) {
        Entrada entrada = entradaService.getEntrada(indice);
        model.addAttribute("entrada", entrada);
        model.addAttribute("indice", indice);

        int tIndice = (temporada != null) ? temporada : entrada.getTemporadas().size() - 1;
        model.addAttribute("tIndice", tIndice);
        model.addAttribute("temporadaActual", entrada.getTemporadas().get(tIndice));
        List<Episodio> episodiosInvertidos = new ArrayList<>(entrada.getTemporadas().get(tIndice).getEpisodios());
        Collections.reverse(episodiosInvertidos);
        model.addAttribute("episodios", episodiosInvertidos);

        return "detalle";
    }

    @PostMapping("/{indice}/temporadas/{tIndice}/episodios/{eIndice}")
    public String marcarEpisodio(@PathVariable int indice, @PathVariable int tIndice, @PathVariable int eIndice) {
        Entrada entrada = entradaService.getEntrada(indice);
        Episodio episodio = entrada.getTemporadas().get(tIndice).getEpisodios().get(eIndice);
        episodio.setVisto(!episodio.isVisto());
        return "redirect:/entradas/" + indice;
    }

    @PostMapping("/{indice}/temporadas")
    public String agregarTemporada(@PathVariable int indice, @RequestParam int numeroEpisodios) {
        entradaService.agregarTemporada(indice, numeroEpisodios);
        return "redirect:/entradas/" + indice;
    }

    @PostMapping("/{indice}/temporadas/{tIndice}/episodios")
    public String agregarEpisodios(@PathVariable int indice, @PathVariable int tIndice, @RequestParam int cantidad) {
        entradaService.agregarEpisodios(indice, tIndice, cantidad);
        return "redirect:/entradas/" + indice;
    }

    @PostMapping("/{indice}/temporadas/{tIndice}/episodios/eliminar")
    public String eliminarUltimoEpisodio(@PathVariable int indice, @PathVariable int tIndice) {
        entradaService.eliminarUltimoEpisodio(indice, tIndice);
        return "redirect:/entradas/" + indice + "?temporada=" + tIndice;
    }

    @PostMapping("/{indice}/temporadas/eliminar")
    public String eliminarUltimaTemporada(@PathVariable int indice) {
        entradaService.eliminarUltimaTemporada(indice);
        return "redirect:/entradas/" + indice;
    }

    @PostMapping("/{indice}/temporadas/{tIndice}/marcar-todos")
    public String marcarTodos(@PathVariable int indice, @PathVariable int tIndice, @RequestParam boolean visto) {
        entradaService.marcarTodos(indice, tIndice, visto);
        return "redirect:/entradas/" + indice + "?temporada=" + tIndice;
    }

}