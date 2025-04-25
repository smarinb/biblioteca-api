package com.biblioteca.biblioteca;


import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.containsString;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LibroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //Test listar todos los libros
    @Test
    void listarLibros_devuelve200() throws Exception {
        mockMvc.perform(get("/libros"))
                .andExpect(status().isOk());
    }

    @Test
    void agregarLibro_devuelve201ConJson() throws Exception {
        String libro = """
                {
                    "titulo": "El Hobbit",
                    "autor": "Tolkien",
                    "isbn": "13245678-567",
                    "anioPublicacion": 1950
                }
                
                
                """;

        mockMvc.perform(post("/libros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(libro))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("Hobbit"))
                );
    }

    @Test
    void agregarLibroSinTitulo_devuelve400() throws Exception {
        String libro = """
                {
                    "autor": "Tolkien",
                    "isbn": "13245678-567",
                    "anioPublicacion": 1950
                }
                
                
                """;

        mockMvc.perform(post("/libros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(libro))
                .andExpect(status().isBadRequest());


    }

    @Test
    void eliminarLibro_existente_devuelve204() throws Exception {


        String libro = """
                {
                    "titulo": "El Hobbit",
                    "autor": "Tolkien",
                    "isbn": "13245678-567",
                    "anioPublicacion": 1950
                }
                
                
                """;

        String response = mockMvc.perform(post("/libros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(libro))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Number idNum = JsonPath.read(response, "$.id");
        long id = idNum.longValue();

        mockMvc.perform(delete("/libros/" + id))
                .andExpect(status().isNoContent());


    }

    @Test
    void eliminarLibro_noExistente_devuelve404() throws Exception {
        mockMvc.perform(delete("/libros/8575757575"))
                .andExpect(status().isNotFound());
    }

    @Test
    void buscarLibro_existente_devuelve200ConContenido() throws Exception{
        String libro = """
                {
                    "titulo": "El Hobbit",
                    "autor": "Tolkien",
                    "isbn": "13245678-567",
                    "anioPublicacion": 1950
                }
                
                
                """;

        String response = mockMvc.perform(post("/libros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(libro))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String titulo = JsonPath.read(response, "$.titulo");


        mockMvc.perform(get("/libros/buscar")
                        .param("titulo", titulo))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("El Hobbit")));
    }

    @Test
    void buscarLibro_noExistente_devuelve404() throws Exception {
        mockMvc.perform(get("/libros/buscar")
                        .param("titulo", "zzzzzzzzzzzz")) // Título inventado
                .andExpect(status().isNotFound());
    }


}
