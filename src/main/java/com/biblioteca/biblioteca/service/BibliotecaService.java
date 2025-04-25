package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.model.Libro;
import com.biblioteca.biblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BibliotecaService {

    @Autowired
    private LibroRepository repository;

    //Listar todos los libros

    public List<Libro> listar(){
        return repository.findAll();
    }

    //Obtener libro por ID

    public Optional<Libro> buscarLibro(Long id){
        return repository.findById(id);
    }

    //Obtener libro por titulo
    public Optional<Libro> buscarLibroPorTitulo(String titulo){
        return repository.findByTitulo(titulo);
    }

    //Añadir un libro nuevo

    public Libro anadirLibro(Libro libro){
        return repository.save(libro);
    }

    //Eliminar libro por ID

    public boolean eliminar(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }



}
