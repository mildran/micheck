package com.marcelino.micheck.service;

import com.marcelino.micheck.model.Entrada;
import org.springframework.stereotype.Service;
import com.marcelino.micheck.model.Episodio;
import java.util.ArrayList;
import java.util.List;
import com.marcelino.micheck.model.Tipo;

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

    public List<Entrada> getByTipo(Tipo tipo) {
        List<Entrada> resultado = new ArrayList<>();
        for (Entrada entrada : entradas) {
            if (entrada.getTipo().equals(tipo)) {
                resultado.add(entrada);
            }
        }
        return resultado;
    }

}