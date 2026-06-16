package com.marcelino.micheck.repository;

import com.marcelino.micheck.model.Entrada;
import com.marcelino.micheck.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EntradaRepository extends JpaRepository<Entrada, Long> {
    List<Entrada> findByTipoAndArchivadaFalse(Categoria tipo);
    List<Entrada> findByArchivadaTrue();
}