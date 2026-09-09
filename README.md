# SmartTech - AA2

Proyecto académico desarrollado para la actividad **“Creación de CRUD para tablas de base de datos”** de la asignatura Desarrollo de Software Web Back-end.

SmartTech es una aplicación web orientada a la consulta y administración de dispositivos tecnológicos. El sistema permite visualizar un catálogo de dispositivos, realizar búsquedas y filtros, consultar información detallada, registrar comentarios y calificaciones, y administrar la información mediante un módulo administrativo protegido.

## Funcionalidades

### Sitio público

- Consulta de dispositivos tecnológicos.
- Búsqueda por nombre.
- Filtrado por marca.
- Filtrado por tipo de dispositivo.
- Ordenamiento por fecha de lanzamiento.
- Visualización del detalle de cada dispositivo.
- Consulta de comentarios y calificaciones.
- Registro de comentarios.

### Módulo administrativo

- Inicio de sesión mediante nombre de usuario o correo electrónico.
- Autenticación mediante contraseña.
- Protección de las rutas administrativas.
- Gestión de dispositivos.
- Gestión de marcas.
- Gestión de tipos de dispositivos.
- Gestión de autores.
- Gestión de comentarios.
- Operaciones CRUD de creación, consulta, actualización y eliminación.
- Cierre de sesión.

### API REST

- Consulta de dispositivos en formato JSON.
- Consulta de dispositivos en formato XML.

Endpoints:

```text
http://localhost:8080/api/devices/json
http://localhost:8080/api/devices/xml