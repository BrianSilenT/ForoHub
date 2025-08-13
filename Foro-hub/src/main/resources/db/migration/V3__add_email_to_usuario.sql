-- Agrega la columna 'email' a la tabla 'usuario'
ALTER TABLE usuario
ADD COLUMN email VARCHAR(255) NOT NULL UNIQUE;
