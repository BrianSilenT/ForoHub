-- Crea la tabla 'roles'
CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- (Opcional) Inserta roles iniciales
INSERT INTO roles (name) VALUES ('ADMIN'), ('USER'), ('MODERATOR');