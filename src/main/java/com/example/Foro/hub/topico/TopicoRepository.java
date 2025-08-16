package com.example.Foro.hub.topico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    // Puedes agregar métodos personalizados si lo necesitas
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
}