package com.marcelino.micheck.model;

import java.util.ArrayList;
import java.util.List;

public class Temporada {

    private int numero;
    private List<Episodio> episodios = new ArrayList<>();

    public Temporada() {
    }

    public Temporada(int numero, int numeroEpisodios) {
        this.numero = numero;
        for (int i = 1; i <= numeroEpisodios; i++) {
            episodios.add(new Episodio(i, false));
        }
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        this.episodios = episodios;
    }
}