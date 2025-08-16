package com.example.Foro.hub.topico;

import com.example.Foro.hub.curso.CursoRepository;
import com.example.Foro.hub.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    @PostMapping
    @Operation(summary = "Crear un nuevo tópico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tópico creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud"),
            @ApiResponse(responseCode = "401", description = "No autenticado"),
            @ApiResponse(responseCode = "403", description = "No autorizado para crear tópicos"),
            @ApiResponse(responseCode = "404", description = "Curso o autor no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public Topico crearTopico(@Valid @RequestBody CrearTopicoRequest request) {
        Topico topico = new Topico();
        topico.setTitulo(request.getTitulo());
        topico.setMensaje(request.getMensaje());
        topico.setCurso(cursoRepository.findById(request.getCursoId()).orElseThrow());
        topico.setAutor(usuarioRepository.findById(request.getAutorId()).orElseThrow());
        return topicoService.crearTopico(topico);
    }

    @PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'ADMIN')")
    @GetMapping
    @Operation(summary = "Obtener todos los tópicos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de tópicos obtenida"),
            @ApiResponse(responseCode = "401", description = "No autenticado"),
            @ApiResponse(responseCode = "403", description = "No autorizado para ver tópicos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    public List<Topico> obtenerTopicos() {
        return topicoService.obtenerTodos();
    }

    @PreAuthorize("hasAnyRole('USER', 'MODERATOR', 'ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "Obtener tópico por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tópico obtenido exitosamente"),
            @ApiResponse(responseCode = "401", description = "No autenticado"),
            @ApiResponse(responseCode = "403", description = "No autorizado para ver el tópico"),
            @ApiResponse(responseCode = "404", description = "Tópico no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    public Topico obtenerTopicoPorId(@PathVariable Long id) {
        return topicoService.obtenerPorId(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar tópico por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tópico eliminado exitosamente"),
            @ApiResponse(responseCode = "401", description = "No autenticado"),
            @ApiResponse(responseCode = "403", description = "No autorizado para eliminar tópicos"),
            @ApiResponse(responseCode = "404", description = "Tópico no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarPorId(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> actualizarTopico(@PathVariable Long id, @RequestBody ActualizarTopicoDTO dto) {
        Topico actualizado = topicoService.actualizarTopico(id, dto);
        return ResponseEntity.ok(actualizado);
    }
}