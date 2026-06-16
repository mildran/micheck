package com.marcelino.micheck.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Temporada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Episodio> episodios = new ArrayList<>();

    public Temporada() {
    }

    public Temporada(int numero, int numeroEpisodios) {
        this.numero = numero;
        for (int i = 1; i <= numeroEpisodios; i++) {
            episodios.add(new Episodio(i, false));
        }
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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