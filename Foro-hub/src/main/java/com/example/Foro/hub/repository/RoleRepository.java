package com.example.Foro.hub.repository;


import com.example.Foro.hub.model.Role;
import com.example.Foro.hub.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
    boolean existsByName(RoleName name);
}