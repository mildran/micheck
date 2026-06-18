package com.marcelino.micheck.repository;

import com.marcelino.micheck.model.Categoria;
import com.marcelino.micheck.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByUsuario(Usuario usuario);
}