package com.marcelino.micheck.controller;

import com.marcelino.micheck.model.Entrada;
import com.marcelino.micheck.service.EntradaService;
import com.marcelino.micheck.service.TipoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String listar(Model model) {
        model.addAttribute("entradas", entradaService.getTodas());
        model.addAttribute("entrada", new Entrada());
        model.addAttribute("tipos", tipoService.getTodos());
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

}
