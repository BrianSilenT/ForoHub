package com.example.Foro.hub.seguridad;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.Foro.hub.usuario.Usuario;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JWTUtils {

    private final String SECRET = "clave_super_secreta";
    private final long EXPIRACION = 86400000; // 1 día en ms

    public String generarToken(Usuario usuario) {
        List<String> roles = usuario.getRoles().stream()
                .map(role -> role.getName().name()) // ROLE_ADMIN, ROLE_USER, etc.
                .collect(Collectors.toList());

        return JWT.create()
                .withSubject(usuario.getUsername())
                .withClaim("roles", roles)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRACION))
                .sign(Algorithm.HMAC256(SECRET));
    }
}