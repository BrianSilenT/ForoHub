package com.example.Foro.hub.topico;



import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public Topico crearTopico(Topico topico) {
        return topicoRepository.save(topico);
    }

    public List<Topico> obtenerTodos() {
        return topicoRepository.findAll();
    }

    public Topico obtenerPorId(Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
    }
    public void eliminarPorId(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado con ID: " + id));
        topicoRepository.delete(topico);
    }

    public Topico actualizarTopico(Long id, ActualizarTopicoDTO dto) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado con ID: " + id));

        topico.setTitulo(dto.getTitulo());
        topico.setMensaje(dto.getMensaje());

        return topicoRepository.save(topico);
    }
}
