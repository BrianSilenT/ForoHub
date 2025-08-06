package com.example.Foro.hub.curso;




import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> obtenerTodos() {
        return cursoRepository.findAll();
    }

    public Curso obtenerPorId(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    public Curso crearCurso(Curso curso) {
        return cursoRepository.save(curso);
    }
}