package com.marcelino.micheck.repository;

import com.marcelino.micheck.model.Entrada;
import com.marcelino.micheck.model.Categoria;
import com.marcelino.micheck.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EntradaRepository extends JpaRepository<Entrada, Long> {
    List<Entrada> findByTipoAndArchivadaFalseAndUsuario(Categoria tipo, Usuario usuario);
    List<Entrada> findByArchivadaTrueAndUsuario(Usuario usuario);
}