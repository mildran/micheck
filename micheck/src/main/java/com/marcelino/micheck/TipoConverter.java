package com.marcelino.micheck;

import com.marcelino.micheck.model.Tipo;
import com.marcelino.micheck.service.TipoService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TipoConverter implements Converter<String, Tipo> {

    private final TipoService tipoService;

    public TipoConverter(TipoService tipoService) {
        this.tipoService = tipoService;
    }

    @Override
    public Tipo convert(String indice) {
        return tipoService.getTodos().get(Integer.parseInt(indice));
    }

}
