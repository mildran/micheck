package com.marcelino.micheck.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Entrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToOne
    private Categoria tipo;
    private String observaciones;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Temporada> temporadas = new ArrayList<>();
    private boolean archivada;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @ManyToOne
    private Usuario usuario;

    public Entrada() {
    }

    public Entrada(String nombre, Categoria tipo, String observaciones) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.observaciones = observaciones;
    }
    public int getTotalUnidades() {
        int total = 0;
        for (Temporada temporada : temporadas) {
            total += temporada.getEpisodios().size();
        }
        return total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isArchivada() {
        return archivada;
    }

    public void setArchivada(boolean archivada) {
        this.archivada = archivada;
    }

    public Categoria getTipo() {
        return tipo;
    }

    public void setTipo(Categoria tipo) {
        this.tipo = tipo;
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(List<Temporada> temporadas) {
        this.temporadas = temporadas;
    }
}