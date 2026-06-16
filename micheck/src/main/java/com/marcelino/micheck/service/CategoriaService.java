package com.marcelino.micheck.service;

import com.marcelino.micheck.model.Categoria;
import com.marcelino.micheck.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> getTodos() {
        return categoriaRepository.findAll();
    }

    public void agregar(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    public void eliminar(Long id) {
        categoriaRepository.deleteById(id);
    }

    public Categoria getById(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }
}