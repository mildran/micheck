package com.marcelino.micheck.controller;

import com.marcelino.micheck.model.Entrada;
import com.marcelino.micheck.service.EntradaService;
import com.marcelino.micheck.service.TipoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.marcelino.micheck.model.Episodio;
import com.marcelino.micheck.model.Tipo;

@Controller
@RequestMapping("/entradas")
public class EntradaController {

    private final EntradaService entradaService;
    private final TipoService tipoService;

    public EntradaController(EntradaService entradaService, TipoService tipoService) {
        this.entradaService = entradaService;
        this.tipoService = tipoService;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) Integer tipoIndice, Model model) {
        model.addAttribute("tipos", tipoService.getTodos());
        model.addAttribute("entrada", new Entrada());
        model.addAttribute("tipoIndice", tipoIndice);

        if (tipoIndice != null && tipoIndice >= 0) {
            Tipo tipoSeleccionado = tipoService.getTodos().get(tipoIndice);
            model.addAttribute("entradas", entradaService.getByTipo(tipoSeleccionado));
            model.addAttribute("tipoSeleccionado", tipoSeleccionado);
        } else {
            model.addAttribute("entradas", entradaService.getTodas());
            model.addAttribute("tipoSeleccionado", null);
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
