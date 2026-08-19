# SmartTech - AA2

Proyecto académico para la actividad **Creando con patrón de arquitectura** de Desarrollo de Software Web Back-end.

## Tecnologías
- Java 17 o superior compatible
- Spring Boot 3
- Spring MVC
- Thymeleaf
- Spring Data JPA
- PostgreSQL
- Bootstrap 5
- Maven
- Visual Studio Code

## Funcionalidades implementadas
1. Catálogo de dispositivos.
2. Ordenamiento por fecha de lanzamiento.
3. Búsqueda por nombre.
4. Filtro por marca.
5. Filtro por tipo.
6. Vista de detalle.
7. Sistema de comentarios y calificación.
8. Administración de marcas.
9. Administración CRUD de dispositivos.
10. Persistencia mediante PostgreSQL.
11. Arquitectura por capas.

## Requisitos
- JDK 23
- Maven 3.9+
- PostgreSQL 15+ recomendado

## Base de datos

La aplicación utiliza PostgreSQL y trabaja con una base de datos denominada `smarttech_db`.

La configuración de conexión se encuentra en `application.properties`.

### Modelo de datos

La base de datos está compuesta por las tablas:

- `brands`
- `devices`
- `comments`

Las relaciones principales son:

- Una marca puede tener varios dispositivos.
- Un dispositivo pertenece a una marca.
- Un dispositivo puede tener varios comentarios.
- Cada comentario pertenece a un dispositivo.

### Scripts de base de datos

Dentro de la carpeta `database` se encuentran:

- `01_schema.sql`: creación de la estructura de la base de datos.
- `02_seed.sql`: inserción de datos iniciales.
- `03_backup_smarttech_db.sql`: script de respaldo/restauración de la base de datos.

### Archivos de respaldo y documentación

Dentro de la carpeta `BD` se encuentran:

- `Modelo_Entidad_Relacion_SmartTech.pdf`: modelo entidad-relación de la base de datos.
- `smarttech_db_backup.backup`: respaldo de la base de datos PostgreSQL en formato Custom.

El respaldo `smarttech_db_backup.backup` puede restaurarse mediante la opción **Restore** de pgAdmin sobre la base de datos `smarttech_db`.

## Ejecución

Desde la carpeta raíz del proyecto ejecutar:

```bash
mvn clean spring-boot:run
```

Abrir:

`http://localhost:8080`

### Administración

Gestión de dispositivos:

`http://localhost:8080/admin/devices`

Gestión de marcas:

`http://localhost:8080/admin/brands`

## Evidencias recomendadas
Tomar capturas donde se vea:
- estructura de carpetas por capas;
- ejecución del proyecto;
- catálogo con filtros;
- detalle de un dispositivo;
- registro de comentario;
- administración de dispositivos;
- administración de marcas;
- PostgreSQL con tablas y datos;
- modelo entidad-relación.

## Observación académica
La aplicación es un prototipo funcional y académico. Para una versión productiva se recomienda agregar autenticación, autorización por roles, manejo global de excepciones, DTOs, paginación, protección CSRF y validaciones adicionales.
