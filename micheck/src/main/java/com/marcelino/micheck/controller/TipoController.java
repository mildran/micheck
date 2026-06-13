package com.marcelino.micheck.controller;

import com.marcelino.micheck.model.Tipo;
import com.marcelino.micheck.service.TipoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tipos")
public class TipoController {

    private final TipoService tipoService;

    public TipoController(TipoService tipoService) {
        this.tipoService = tipoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("tipos", tipoService.getTodos());
        model.addAttribute("tipo", new Tipo());
        return "tipos";
    }

    @PostMapping
    public String agregar(@ModelAttribute Tipo tipo) {
        tipoService.agregar(tipo);
        return "redirect:/tipos";
    }

    @GetMapping("/eliminar/{indice}")
    public String eliminar(@PathVariable int indice) {
        tipoService.eliminar(indice);
        return "redirect:/tipos";
    }

}