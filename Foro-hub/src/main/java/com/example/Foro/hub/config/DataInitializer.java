package com.example.Foro.hub.config;

import com.example.Foro.hub.model.Role;
import com.example.Foro.hub.model.RoleName;
import com.example.Foro.hub.repository.RoleRepository;
import com.example.Foro.hub.usuario.Usuario;
import com.example.Foro.hub.usuario.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(RoleRepository roleRepository, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Crear roles
            for (RoleName roleName : RoleName.values()) {
                if (!roleRepository.existsByName(roleName)) {
                    roleRepository.save(new Role(roleName));
                    System.out.println("Rol creado: " + roleName);
                }
            }

            // Crear usuario
            // Crear usuario
            if (!usuarioRepository.existsByEmail("user@forohub.com")) {
                Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                        .orElseThrow(() -> new IllegalStateException("ROLE_USER no encontrado"));
                Usuario user = new Usuario();
                user.setUsername("usuario123"); // ← Esta línea es clave
                user.setNombre("Usuario");
                user.setEmail("user@forohub.com");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setRoles(Set.of(userRole));
                usuarioRepository.save(user);
                System.out.println("Usuario USER creado");
            }
        };
    }

}
