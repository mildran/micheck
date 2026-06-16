package com.marcelino.micheck.repository;

import com.marcelino.micheck.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}