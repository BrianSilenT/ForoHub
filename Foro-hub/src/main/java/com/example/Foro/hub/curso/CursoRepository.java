package com.example.Foro.hub.curso;


import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    // Puedes agregar métodos de búsqueda personalizados si lo necesitas
}