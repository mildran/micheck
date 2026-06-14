package com.marcelino.micheck.service;

import com.marcelino.micheck.model.Entrada;
import com.marcelino.micheck.model.Episodio;
import com.marcelino.micheck.model.Categoria;
import com.marcelino.micheck.model.Temporada;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntradaService {

    private List<Entrada> entradas = new ArrayList<>();

    public List<Entrada> getTodas() {
        return entradas;
    }

    public void agregar(Entrada entrada, int numeroTemporada, int numeroUnidades) {
        Temporada temporada = new Temporada(numeroTemporada, numeroUnidades);
        entrada.getTemporadas().add(temporada);
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

    public void agregarTemporada(int indiceEntrada, int numeroEpisodios) {
        Entrada entrada = entradas.get(indiceEntrada);
        int siguienteNumero = entrada.getTemporadas().size() + 1;
        entrada.getTemporadas().add(new Temporada(siguienteNumero, numeroEpisodios));
    }

    public void agregarEpisodios(int indiceEntrada, int indiceTemporada, int cantidad) {
        Entrada entrada = entradas.get(indiceEntrada);
        Temporada temporada = entrada.getTemporadas().get(indiceTemporada);
        int siguienteNumero = temporada.getEpisodios().size() + 1;
        for (int i = 0; i < cantidad; i++) {
            temporada.getEpisodios().add(new Episodio(siguienteNumero + i, false));
        }
    }

    public void marcarTodos(int indiceEntrada, int indiceTemporada, boolean visto) {
        Entrada entrada = entradas.get(indiceEntrada);
        Temporada temporada = entrada.getTemporadas().get(indiceTemporada);
        for (Episodio episodio : temporada.getEpisodios()) {
            episodio.setVisto(visto);
        }
    }

    public void eliminarUltimoEpisodio(int indiceEntrada, int indiceTemporada) {
        Entrada entrada = entradas.get(indiceEntrada);
        Temporada temporada = entrada.getTemporadas().get(indiceTemporada);
        List<Episodio> episodios = temporada.getEpisodios();
        if (!episodios.isEmpty()) {
            episodios.remove(episodios.size() - 1);
        }
    }

    public void eliminarUltimaTemporada(int indiceEntrada) {
        Entrada entrada = entradas.get(indiceEntrada);
        List<Temporada> temporadas = entrada.getTemporadas();
        if (temporadas.size() > 1) {
            temporadas.remove(temporadas.size() - 1);
        }
    }

}