package com.marcelino.micheck.controller;

import com.marcelino.micheck.model.Usuario;
import com.marcelino.micheck.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(@ModelAttribute Usuario usuario) {
        usuarioService.registrar(usuario);
        return "redirect:/login?registrado";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("pendientes", usuarioService.getPendientes());
        model.addAttribute("aprobados", usuarioService.getAprobados());
        model.addAttribute("totalUsuarios", usuarioService.getTotalUsuarios());
        return "admin";
    }

    @PostMapping("/admin/aprobar/{id}")
    public String aprobar(@PathVariable Long id) {
        usuarioService.aprobar(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/rechazar/{id}")
    public String rechazar(@PathVariable Long id) {
        usuarioService.rechazar(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/hacer-admin/{id}")
    public String hacerAdmin(@PathVariable Long id) {
        usuarioService.hacerAdmin(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/dar-de-baja/{id}")
    public String darDeBaja(@PathVariable Long id) {
        usuarioService.rechazar(id);
        return "redirect:/admin";
    }
}