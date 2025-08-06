package com.example.Foro.hub.topico;

import com.example.Foro.hub.curso.CursoRepository;
import com.example.Foro.hub.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Operation(summary = "Crear un nuevo tópico")
    @ApiResponse(responseCode = "200", description = "Tópico creado exitosamente")
    public Topico crearTopico(@Valid @RequestBody CrearTopicoRequest request) {
        Topico topico = new Topico();
        topico.setTitulo(request.getTitulo());
        topico.setMensaje(request.getMensaje());
        topico.setCurso(cursoRepository.findById(request.getCursoId()).orElseThrow());
        topico.setAutor(usuarioRepository.findById(request.getAutorId()).orElseThrow());
        return topicoService.crearTopico(topico);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los tópicos")
    @ApiResponse(responseCode = "200", description = "Lista de tópicos obtenida")
    public List<Topico> obtenerTopicos() {
        return topicoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener tópico por ID")
    @ApiResponse(responseCode = "200", description = "Tópico obtenido exitosamente")
    public Topico obtenerTopicoPorId(@PathVariable Long id) {
        return topicoService.obtenerPorId(id);
    }
}