package com.marcelino.micheck.model;

import java.util.ArrayList;
import java.util.List;

public class Entrada {
    private String nombre;
    private Categoria tipo;
    private String observaciones;
    private List<Temporada> temporadas = new ArrayList<>();

    public Entrada() {
    }

    public Entrada(String nombre, Categoria tipo, String observaciones) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.observaciones = observaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getTipo() {
        return tipo;
    }

    public void setTipo(Categoria tipo) {
        this.tipo = tipo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(List<Temporada> temporadas) {
        this.temporadas = temporadas;
    }

    public int getTotalUnidades() {
        int total = 0;
        for (Temporada temporada : temporadas) {
            total += temporada.getEpisodios().size();
        }
        return total;
    }
}