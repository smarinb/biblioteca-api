package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.model.Libro;
import com.biblioteca.biblioteca.service.BibliotecaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private BibliotecaService servicio;


    @GetMapping
    public ResponseEntity<?> listarLibros(){
        List<Libro> libros = servicio.listar();
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerLibroById(@PathVariable Long id){
        var libro = servicio.buscarLibro(id);
        return libro.map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());

    }

    @GetMapping("/buscar")
    public ResponseEntity<?> obtenerLibroByTitulo(@RequestParam String titulo){
        var libro = servicio.buscarLibroPorTitulo(titulo);
        return libro.map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> agregarLibro(@Valid @RequestBody Libro libro){
        Libro l = servicio.anadirLibro(libro);
        return ResponseEntity.status(201).body(l);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarLibro(@PathVariable Long id){
        if(servicio.eliminar(id)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


}
