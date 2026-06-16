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
    public Categoria convert(String id) {
        if (id.isEmpty()) {
            return null;
        }
        return categoriaService.getById(Long.parseLong(id));
    }

}