package com.marcelino.micheck.service;

import com.marcelino.micheck.model.Entrada;
import com.marcelino.micheck.model.Episodio;
import com.marcelino.micheck.model.Categoria;
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
        List<Episodio> episodios = new ArrayList<>();
        for (int i = 1; i <= entrada.getNumeroUnidades(); i++) {
            episodios.add(new Episodio(i, false));
        }
        entrada.setEpisodios(episodios);
        entradas.add(entrada);
    }

    public void eliminar(int indice) {
        entradas.remove(indice);
    }

    public Entrada getEntrada(int indice) {
        return entradas.get(indice);
    }

    public List<Entrada> getByCategoria(Categoria categoria) {
        List<Entrada> resultado = new ArrayList<>();
        for (Entrada entrada : entradas) {
            if (entrada.getTipo().equals(categoria)) {
                resultado.add(entrada);
            }
        }
        return resultado;
    }

}