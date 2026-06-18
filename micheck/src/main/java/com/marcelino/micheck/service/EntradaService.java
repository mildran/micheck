package com.marcelino.micheck.service;

import com.marcelino.micheck.model.*;
import com.marcelino.micheck.repository.EntradaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntradaService {

    private final EntradaRepository entradaRepository;

    public EntradaService(EntradaRepository entradaRepository) {
        this.entradaRepository = entradaRepository;
    }

    public void agregar(Entrada entrada, int numeroTemporada, int numeroUnidades, Usuario usuario) {
        Temporada temporada = new Temporada(numeroTemporada, numeroUnidades);
        entrada.getTemporadas().add(temporada);
        entrada.setUsuario(usuario);
        entradaRepository.save(entrada);
    }

    public void eliminar(Long id) {
        entradaRepository.deleteById(id);
    }

    public Entrada getEntrada(Long id) {
        return entradaRepository.findById(id).orElse(null);
    }

    public List<Entrada> getActivas(Categoria categoria, Usuario usuario) {
        return entradaRepository.findByTipoAndArchivadaFalseAndUsuario(categoria, usuario);
    }

    public List<Entrada> getArchivadas(Usuario usuario) {
        return entradaRepository.findByArchivadaTrueAndUsuario(usuario);
    }

    public void agregarTemporada(Long idEntrada, int numeroEpisodios) {
        Entrada entrada = getEntrada(idEntrada);
        int siguienteNumero = entrada.getTemporadas().size() + 1;
        entrada.getTemporadas().add(new Temporada(siguienteNumero, numeroEpisodios));
        entradaRepository.save(entrada);
    }

    public void eliminarEpisodios(Long idEntrada, int indiceTemporada, int cantidad) {
        Entrada entrada = getEntrada(idEntrada);
        Temporada temporada = entrada.getTemporadas().get(indiceTemporada);
        List<Episodio> episodios = temporada.getEpisodios();
        for (int i = 0; i < cantidad && !episodios.isEmpty(); i++) {
            episodios.remove(episodios.size() - 1);
        }
        entradaRepository.save(entrada);
    }

    public void agregarEpisodios(Long idEntrada, int indiceTemporada, int cantidad) {
        Entrada entrada = getEntrada(idEntrada);
        Temporada temporada = entrada.getTemporadas().get(indiceTemporada);
        int siguienteNumero = temporada.getEpisodios().size() + 1;
        for (int i = 0; i < cantidad; i++) {
            temporada.getEpisodios().add(new Episodio(siguienteNumero + i, false));
        }
        entradaRepository.save(entrada);
    }

    public void marcarTodos(Long idEntrada, int indiceTemporada, boolean visto) {
        Entrada entrada = getEntrada(idEntrada);
        Temporada temporada = entrada.getTemporadas().get(indiceTemporada);
        for (Episodio episodio : temporada.getEpisodios()) {
            episodio.setVisto(visto);
        }
        entradaRepository.save(entrada);
    }

    public void marcarEpisodio(Long idEntrada, int indiceTemporada, int indiceEpisodio) {
        Entrada entrada = getEntrada(idEntrada);
        Episodio episodio = entrada.getTemporadas().get(indiceTemporada).getEpisodios().get(indiceEpisodio);
        episodio.setVisto(!episodio.isVisto());
        entradaRepository.save(entrada);
    }

    public void eliminarUltimaTemporada(Long idEntrada) {
        Entrada entrada = getEntrada(idEntrada);
        List<Temporada> temporadas = entrada.getTemporadas();
        if (temporadas.size() > 1) {
            temporadas.remove(temporadas.size() - 1);
        }
        entradaRepository.save(entrada);
    }

    public void archivar(Long id) {
        Entrada entrada = getEntrada(id);
        entrada.setArchivada(true);
        entradaRepository.save(entrada);
    }

    public void restaurar(Long id) {
        Entrada entrada = getEntrada(id);
        entrada.setArchivada(false);
        entradaRepository.save(entrada);
    }
}