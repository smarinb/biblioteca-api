# 📚 Biblioteca API

Una API RESTful para la gestión de libros en una biblioteca digital.  
Desarrollada con **Spring Boot**, **Spring Data JPA**, **H2** y probada con **JUnit** y **MockMvc**.

---

## 🚀 Tecnologías utilizadas

- Java 17  
- Spring Boot 3  
- Spring Web  
- Spring Data JPA  
- Bean Validation (Jakarta)  
- Base de datos en memoria H2  
- JUnit 5 + MockMvc + JsonPath  

---

## 📦 Funcionalidades

- 📖 Listar todos los libros  
- 🔍 Buscar un libro por su ID o título  
- ➕ Añadir un nuevo libro con validaciones  
- ❌ Eliminar un libro por su ID  
- ✅ Validación automática con mensajes personalizados  
- 🧪 Tests integrados con MockMvc  

---

## 🧩 Modelo `Libro`

```java
Long id;
String titulo;
String autor;
String isbn;
int anioPublicacion;
```

### 📋 Validaciones:

- `titulo`, `autor`, `isbn`: **obligatorios** (no vacíos)  
- `isbn`: **único**  
- `anioPublicacion`: entre **1900** y **2025**  

---

## 🌐 Endpoints

| Método | Endpoint                         | Descripción                    |
|--------|----------------------------------|--------------------------------|
| GET    | `/libros`                        | Lista todos los libros         |
| GET    | `/libros/{id}`                   | Busca un libro por ID          |
| GET    | `/libros/buscar?titulo=xxx`      | Busca un libro por título      |
| POST   | `/libros`                        | Añade un nuevo libro           |
| DELETE | `/libros/{id}`                   | Elimina un libro por su ID     |

---

## 🧪 Pruebas automatizadas

El proyecto incluye pruebas que validan:

- ✅ Creación de libro correctamente  
- ❌ Validaciones de campos vacíos o erróneos  
- ✅ Eliminación correcta  
- 🔎 Búsqueda por ID y título  
- ❌ Casos negativos (no encontrados, errores de validación)

---

## 🛠 Cómo ejecutar el proyecto

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tuusuario/biblioteca-api.git
   ```
2. Entra al proyecto:
   ```bash
   cd biblioteca-api
   ```
3. Ejecuta la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Accede a la API:
   ```
   http://localhost:8080/libros
   ```

---

## 🧠 Autor

Desarrollado por **Sergio Marín** como parte de su proceso de aprendizaje backend con Java y Spring Boot.  
Este proyecto forma parte de su porfolio profesional y demuestra conocimientos en desarrollo de API REST, validación de datos y pruebas automatizadas.

---
