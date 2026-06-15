package com.marcelino.micheck;

import com.marcelino.micheck.model.Categoria;
import com.marcelino.micheck.service.CategoriaService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoriaConverter implements Converter<String, Categoria> {

    private final CategoriaService categoriaService;

    public CategoriaConverter(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Override
    public Categoria convert(String indice) {
        if (indice.isEmpty()) {
            return null;
        }
        return categoriaService.getTodos().get(Integer.parseInt(indice));
    }

}