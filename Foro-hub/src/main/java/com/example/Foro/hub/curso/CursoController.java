package com.example.Foro.hub.curso;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    @Operation(summary = "Crear un nuevo curso")
    @ApiResponse(responseCode = "200", description = "Curso creado exitosamente")
    public Curso crearCurso(@RequestBody CrearCursoRequest request) {
        Curso curso = new Curso();
        curso.setNombre(request.getNombre());
        curso.setCategoria(request.getCategoria());
        return cursoService.crearCurso(curso);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los cursos")
    @ApiResponse(responseCode = "200", description = "Lista de cursos obtenida")
    public List<Curso> obtenerCursos() {
        return cursoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener curso por ID")
    @ApiResponse(responseCode = "200", description = "Curso obtenido exitosamente")
    public Curso obtenerCursoPorId(@PathVariable Long id) {
        return cursoService.obtenerPorId(id);
    }
}