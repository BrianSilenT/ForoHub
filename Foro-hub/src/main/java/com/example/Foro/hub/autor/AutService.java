package com.example.Foro.hub.autor;

import com.auth0.jwt.JWT;
import com.example.Foro.hub.seguridad.JWTUtils;
import com.example.Foro.hub.usuario.Usuario;
import com.example.Foro.hub.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AutService {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String login(AutRequest request) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword());

        authManager.authenticate(authToken);

        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return jwtUtils.generarToken(usuario);
    }
}