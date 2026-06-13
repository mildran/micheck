package com.marcelino.micheck;

public class Entrada {
    private String nombre;
    private Tipo tipo;
    private int temporada;
    private int numeroUnidades;
    private String observaciones;

    public Entrada(String nombre, Tipo tipo, int temporada, int numeroUnidades, String observaciones) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.temporada = temporada;
        this.numeroUnidades = numeroUnidades;
        this.observaciones = observaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getTemporada() {
        return temporada;
    }

    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }

    public int getNumeroUnidades() {
        return numeroUnidades;
    }

    public void setNumeroUnidades(int numeroUnidades) {
        this.numeroUnidades = numeroUnidades;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
