package com.marcelino.micheck.controller;

import com.marcelino.micheck.model.Categoria;
import com.marcelino.micheck.model.Usuario;
import com.marcelino.micheck.service.CategoriaService;
import com.marcelino.micheck.service.UsuarioService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final UsuarioService usuarioService;

    public CategoriaController(CategoriaService categoriaService, UsuarioService usuarioService) {
        this.categoriaService = categoriaService;
        this.usuarioService = usuarioService;
    }

    private Usuario getUsuarioActual(UserDetails userDetails) {
        return usuarioService.getByEmail(userDetails.getUsername());
    }

    @GetMapping
    public String listar(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Usuario usuario = getUsuarioActual(userDetails);
        model.addAttribute("categorias", categoriaService.getTodos(usuario));
        model.addAttribute("categoria", new Categoria());
        return "categorias";
    }

    @PostMapping
    public String agregar(@ModelAttribute Categoria categoria,
                          @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = getUsuarioActual(userDetails);
        categoriaService.agregar(categoria, usuario);
        return "redirect:/categorias";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        categoriaService.eliminar(id);
        return "redirect:/entradas?categoriaIndice=-1";
    }
}