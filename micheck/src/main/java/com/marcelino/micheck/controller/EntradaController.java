package com.marcelino.micheck.controller;

import com.marcelino.micheck.model.Entrada;
import com.marcelino.micheck.model.Categoria;
import com.marcelino.micheck.model.Episodio;
import com.marcelino.micheck.model.Usuario;
import com.marcelino.micheck.service.EntradaService;
import com.marcelino.micheck.service.CategoriaService;
import com.marcelino.micheck.service.UsuarioService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/entradas")
public class EntradaController {

    private final EntradaService entradaService;
    private final CategoriaService categoriaService;
    private final UsuarioService usuarioService;

    public EntradaController(EntradaService entradaService, CategoriaService categoriaService, UsuarioService usuarioService) {
        this.entradaService = entradaService;
        this.categoriaService = categoriaService;
        this.usuarioService = usuarioService;
    }

    private Usuario getUsuarioActual(UserDetails userDetails) {
        return usuarioService.getByEmail(userDetails.getUsername());
    }

    @Transactional
    @GetMapping
    public String listar(@RequestParam(required = false) Integer categoriaIndice,
                         @AuthenticationPrincipal UserDetails userDetails,
                         Model model) {
        Usuario usuario = getUsuarioActual(userDetails);
        List<Categoria> categorias = categoriaService.getTodos(usuario);


        if (categoriaIndice == null) {
            if (categorias.isEmpty()) {
                categoriaIndice = -1;
            } else {
                categoriaIndice = 0;
            }
        }

        model.addAttribute("categorias", categorias);
        model.addAttribute("categoriaIndice", categoriaIndice);
        model.addAttribute("categoria", new Categoria());

        Entrada nuevaEntrada = new Entrada();
        if (categoriaIndice >= 0 && categoriaIndice < categorias.size()) {
            nuevaEntrada.setTipo(categorias.get(categoriaIndice));
        }
        model.addAttribute("entrada", nuevaEntrada);

        if (categoriaIndice >= 0 && categoriaIndice < categorias.size()) {
            Categoria categoriaSeleccionada = categorias.get(categoriaIndice);
            model.addAttribute("entradas", entradaService.getActivas(categoriaSeleccionada, usuario));
            model.addAttribute("categoriaSeleccionada", categoriaSeleccionada);
        } else if (categoriaIndice == -2) {
            model.addAttribute("entradas", entradaService.getArchivadas(usuario));
            model.addAttribute("categoriaSeleccionada", null);
        } else {
            model.addAttribute("entradas", new ArrayList<>());
            model.addAttribute("categoriaSeleccionada", null);
        }

        return "entradas";
    }

    @PostMapping
    public String agregar(@ModelAttribute Entrada entrada,
                          @RequestParam int numeroTemporada,
                          @RequestParam int numeroUnidades,
                          @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = getUsuarioActual(userDetails);
        entradaService.agregar(entrada, numeroTemporada, numeroUnidades, usuario);
        return "redirect:/entradas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        entradaService.eliminar(id);
        return "redirect:/entradas";
    }

    @Transactional
    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id,
                          @RequestParam(required = false) Integer temporada,
                          Model model) {
        Entrada entrada = entradaService.getEntrada(id);
        model.addAttribute("entrada", entrada);
        model.addAttribute("id", id);

        int tIndice = (temporada != null) ? temporada : entrada.getTemporadas().size() - 1;
        model.addAttribute("tIndice", tIndice);
        model.addAttribute("temporadaActual", entrada.getTemporadas().get(tIndice));
        List<Episodio> episodiosInvertidos = new ArrayList<>(entrada.getTemporadas().get(tIndice).getEpisodios());
        Collections.reverse(episodiosInvertidos);
        model.addAttribute("episodios", episodiosInvertidos);

        return "detalle";
    }

    @PostMapping("/{id}/temporadas/{tIndice}/episodios/{eIndice}")
    public String marcarEpisodio(@PathVariable Long id, @PathVariable int tIndice, @PathVariable int eIndice) {
        entradaService.marcarEpisodio(id, tIndice, eIndice);
        return "redirect:/entradas/" + id;
    }

    @PostMapping("/{id}/temporadas")
    public String agregarTemporada(@PathVariable Long id, @RequestParam int numeroEpisodios) {
        entradaService.agregarTemporada(id, numeroEpisodios);
        return "redirect:/entradas/" + id;
    }

    @PostMapping("/{id}/temporadas/{tIndice}/episodios")
    public String agregarEpisodios(@PathVariable Long id, @PathVariable int tIndice, @RequestParam int cantidad) {
        entradaService.agregarEpisodios(id, tIndice, cantidad);
        return "redirect:/entradas/" + id;
    }

    @PostMapping("/{id}/temporadas/{tIndice}/episodios/eliminar")
    public String eliminarEpisodios(@PathVariable Long id, @PathVariable int tIndice, @RequestParam int cantidad) {
        entradaService.eliminarEpisodios(id, tIndice, cantidad);
        return "redirect:/entradas/" + id + "?temporada=" + tIndice;
    }

    @PostMapping("/{id}/temporadas/eliminar")
    public String eliminarUltimaTemporada(@PathVariable Long id) {
        entradaService.eliminarUltimaTemporada(id);
        return "redirect:/entradas/" + id;
    }

    @PostMapping("/{id}/temporadas/{tIndice}/marcar-todos")
    public String marcarTodos(@PathVariable Long id, @PathVariable int tIndice, @RequestParam boolean visto) {
        entradaService.marcarTodos(id, tIndice, visto);
        return "redirect:/entradas/" + id + "?temporada=" + tIndice;
    }

    @PostMapping("/categorias")
    public String agregarCategoria(@ModelAttribute("categoria") Categoria categoria,
                                   @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = getUsuarioActual(userDetails);
        categoriaService.agregar(categoria, usuario);
        return "redirect:/entradas";
    }

    @PostMapping("/{id}/archivar")
    public String archivar(@PathVariable Long id) {
        entradaService.archivar(id);
        return "redirect:/entradas";
    }

    @PostMapping("/{id}/restaurar")
    public String restaurar(@PathVariable Long id) {
        entradaService.restaurar(id);
        return "redirect:/entradas?categoriaIndice=-2";
    }
}