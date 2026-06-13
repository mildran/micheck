package com.marcelino.micheck.service;

import com.marcelino.micheck.model.Tipo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipoService {

    private List<Tipo> tipos = new ArrayList<>();

    public List<Tipo> getTodos() {
        return tipos;
    }

    public void agregar(Tipo tipo) {
        tipos.add(tipo);
    }

    public void eliminar(int indice) {
        tipos.remove(indice);
    }

}