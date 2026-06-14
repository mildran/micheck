package com.marcelino.micheck.service;

import com.marcelino.micheck.model.Categoria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {

    private List<Categoria> categorias = new ArrayList<>();

    public List<Categoria> getTodos() {
        return categorias;
    }

    public void agregar(Categoria categoria) {
        categorias.add(categoria);
    }

    public void eliminar(int indice) {
        categorias.remove(indice);
    }

}