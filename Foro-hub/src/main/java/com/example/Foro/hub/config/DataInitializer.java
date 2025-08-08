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
    CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            for (RoleName roleName : RoleName.values()) {
                if (!roleRepository.existsByName(roleName)) {
                    roleRepository.save(new Role(roleName));
                    System.out.println("Rol creado: " + roleName);
                }
            }
        };
    }

    @Bean
    CommandLineRunner initUser(RoleRepository roleRepository, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!usuarioRepository.existsByEmail("user@forohub.com")) {
                Role userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow();
                Usuario user = new Usuario();
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
