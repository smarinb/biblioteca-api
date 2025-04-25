package com.biblioteca.biblioteca.repository;

import com.biblioteca.biblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro,Long> {

    Optional<Libro> findByTitulo(String titulo);

}
