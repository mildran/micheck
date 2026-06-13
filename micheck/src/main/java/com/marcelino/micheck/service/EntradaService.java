package com.marcelino.micheck.service;

import com.marcelino.micheck.model.Entrada;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntradaService {

    private List<Entrada> entradas = new ArrayList<>();

    public List<Entrada> getTodas() {
        return entradas;
    }

    public void agregar(Entrada entrada) {
        entradas.add(entrada);
    }

    public void eliminar(int indice) {
        entradas.remove(indice);
    }

    public Entrada getEntrada(int indice) {
        return entradas.get(indice);
    }

}