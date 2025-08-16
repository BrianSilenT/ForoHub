# 🧠 ForoHub

**ForoHub** es una aplicación web desarrollada con **Spring Boot**, **JPA**, **Flyway** y **MySQL**, diseñada para facilitar la creación y gestión de foros temáticos. Incluye autenticación segura con **JWT**, relaciones entre usuarios y roles, migraciones automatizadas, y una arquitectura limpia orientada a buenas prácticas.

---

## 🚀 Funcionalidades principales

- 🔐 Autenticación con JWT y protección de endpoints.
- 👥 Gestión de usuarios con roles (`ROLE_USER`, `ROLE_ADMIN`) mediante relación ManyToMany.
- 📝 Creación, edición y visualización de publicaciones en foros.
- 🗂️ Migraciones automáticas con Flyway para mantener el esquema alineado con las entidades.
- 🧪 Pruebas de endpoints con Insomnia/Postman.
- 📄 Documentación clara y trazabilidad técnica.

---

## 🛠️ Tecnologías utilizadas

| Tecnología     | Propósito                          |
|----------------|------------------------------------|
| Spring Boot    | Backend y lógica de negocio        |
| JPA / Hibernate| Persistencia de datos              |
| Flyway         | Migraciones de base de datos       |
| MySQL          | Base de datos relacional           |
| JWT            | Seguridad y autenticación          |
| Insomnia/Postman| Pruebas de endpoints              |

---

## 📦 Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/BrianSilenT/ForoHub.git
   cd ForoHub

   spring.datasource.url=jdbc:mysql://localhost:3306/forohub
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=none
spring.flyway.enabled=true

./mvnw spring-boot:run

src/
├── main/
│   ├── java/com/forohub/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── model/
│   │   ├── repository/
│   │   └── security/
│   └── resources/
│       ├── db/migration/
│       └── application.properties


✅ Repositorio público: [https://github.com/BrianSilenT/ForoHub](https://github.com/BrianSilenT/ForoHub)
📌 Accesible directamente desde cualquier navegador.
