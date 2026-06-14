package com.marcelino.micheck.controller;

import com.marcelino.micheck.model.Categoria;
import com.marcelino.micheck.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", categoriaService.getTodos());
        model.addAttribute("categoria", new Categoria());
        return "categorias";
    }

    @PostMapping
    public String agregar(@ModelAttribute Categoria categoria) {
        categoriaService.agregar(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/eliminar/{indice}")
    public String eliminar(@PathVariable int indice) {
        categoriaService.eliminar(indice);
        return "redirect:/categorias";
    }

}