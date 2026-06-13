package com.marcelino.micheck;

public class Tipo {
    private String nombre;
    private boolean tieneTemporadas;
    private String nombreUnidad;

    public Tipo(String nombre, boolean tieneTemporadas, String nombreUnidad) {
        this.nombre = nombre;
        this.tieneTemporadas = tieneTemporadas;
        this.nombreUnidad = nombreUnidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isTieneTemporadas() {
        return tieneTemporadas;
    }

    public void setTieneTemporadas(boolean tieneTemporadas) {
        this.tieneTemporadas = tieneTemporadas;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }
}
