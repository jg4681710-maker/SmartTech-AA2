# SmartTech - AA2

Proyecto académico desarrollado para la actividad **“Creación API REST transferir información entre base de datos”** de la asignatura **Desarrollo de Software Web Back-End**.

SmartTech es una aplicación web orientada a la consulta y administración de dispositivos tecnológicos. El proyecto integra el desarrollo realizado en actividades anteriores y amplía la solución mediante una **API REST** que permite transferir información entre la aplicación y la base de datos PostgreSQL utilizando respuestas en formato **JSON**.

La API implementa las operaciones principales **GET, POST, PUT y DELETE**, y permite realizar pruebas mediante **Postman**.

## 1. Objetivo del proyecto

Implementar una API REST para el sistema SmartTech que permita consultar, registrar, actualizar y eliminar dispositivos tecnológicos almacenados en una base de datos PostgreSQL.

La solución utiliza una arquitectura por capas para separar la presentación, los controladores, la lógica de negocio, el acceso a datos y la persistencia.

## 2. Funcionalidades

### 2.1 Sitio público

* Consulta de dispositivos tecnológicos.
* Búsqueda por nombre.
* Filtrado por marca.
* Filtrado por tipo de dispositivo.
* Ordenamiento por fecha de lanzamiento.
* Visualización del detalle de cada dispositivo.
* Consulta de comentarios y calificaciones.
* Registro de comentarios.

### 2.2 Módulo administrativo

* Inicio de sesión mediante nombre de usuario o correo electrónico.
* Autenticación mediante contraseña.
* Protección de las rutas administrativas.
* Gestión de dispositivos.
* Gestión de marcas.
* Gestión de tipos de dispositivos.
* Gestión de autores.
* Gestión de comentarios.
* Operaciones CRUD.
* Cierre de sesión.

### 2.3 API REST

La API REST permite realizar operaciones sobre la información de los dispositivos mediante solicitudes HTTP.

Operaciones implementadas:

* **GET:** consulta de dispositivos.
* **POST:** creación de dispositivos.
* **PUT:** actualización de dispositivos.
* **DELETE:** eliminación de dispositivos.

## 3. Endpoints de la API REST

### GET - Consultar todos los dispositivos

```text
GET http://localhost:8080/api/devices
```

Retorna la lista de dispositivos registrados en formato JSON.

### GET - Consultar un dispositivo por ID

```text
GET http://localhost:8080/api/devices/{id}
```

Ejemplo:

```text
GET http://localhost:8080/api/devices/1
```

### POST - Crear un dispositivo

```text
POST http://localhost:8080/api/devices
```

Content-Type:

```text
application/json
```

Ejemplo de solicitud:

```json
{
    "name": "Galaxy A56",
    "brandId": 1,
    "typeId": 1,
    "releaseDate": "2025-03-28",
    "processor": "Exynos 1580",
    "memory": "8 GB RAM",
    "storage": "256 GB",
    "screen": "6.7 pulgadas AMOLED",
    "description": "Smartphone de gama media alta diseñado para rendimiento, productividad y entretenimiento.",
    "imageUrl": "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?auto=format&fit=crop&w=900&q=80",
    "price": 2199000
}
```

### PUT - Actualizar un dispositivo

```text
PUT http://localhost:8080/api/devices/{id}
```

Ejemplo:

```text
PUT http://localhost:8080/api/devices/5
```

Content-Type:

```text
application/json
```

La operación permite modificar la información de un dispositivo existente.

### DELETE - Eliminar un dispositivo

```text
DELETE http://localhost:8080/api/devices/{id}
```

Ejemplo utilizado durante las pruebas:

```text
DELETE http://localhost:8080/api/devices/8
```

La operación elimina el registro correspondiente.

Posteriormente se verificó la eliminación mediante:

```text
GET http://localhost:8080/api/devices/8
```

La API respondió con:

```json
{
    "status": 404,
    "error": "Not Found",
    "message": "No se encontró el dispositivo con ID: 8"
}
```

Esto permite comprobar que el registro ya no se encuentra disponible.

## 4. Formato JSON

La API utiliza JSON para el intercambio de información entre el cliente y el servidor.

Ejemplo de respuesta:

```json
{
    "id": 1,
    "name": "Galaxy S25",
    "brand": "Samsung",
    "type": "Celular",
    "releaseDate": "2025-01-22",
    "processor": "Snapdragon 8 Elite",
    "memory": "12 GB RAM",
    "storage": "256 GB",
    "screen": "6.2 pulgadas AMOLED",
    "description": "Celular de alto rendimiento orientado a productividad, fotografía y entretenimiento.",
    "imageUrl": "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?auto=format&fit=crop&w=900&q=80",
    "price": 3299000.00
}
```

## 5. Arquitectura

El proyecto utiliza una arquitectura por capas basada en el patrón MVC y en la separación de responsabilidades.

La estructura principal es:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Entity
    ↓
PostgreSQL
```

### Controller

Recibe y procesa las solicitudes HTTP.

El controlador principal de la API REST es:

```text
DeviceRestController
```

Ubicación:

```text
src/main/java/co/ucompensar/smarttech/controller
```

### Service

Contiene la lógica de negocio y las operaciones relacionadas con los dispositivos.

```text
DeviceService
```

### Repository

Gestiona el acceso a los datos mediante Spring Data JPA.

```text
DeviceRepository
```

### Entity

Representa las entidades de la base de datos mediante JPA.

Entre las principales entidades se encuentran:

```text
Device
Brand
DeviceType
Comment
Author
AdminUser
```

## 6. Estructura del proyecto

```text
SmartTech
│
├── BD
├── database
├── docs
│
├── src
│   └── main
│       ├── java
│       │   └── co.ucompensar.smarttech
│       │       ├── controller
│       │       ├── dto
│       │       ├── entity
│       │       ├── exception
│       │       ├── repository
│       │       ├── security
│       │       └── service
│       │
│       └── resources
│           ├── static
│           ├── templates
│           └── application.properties
│
├── pom.xml
├── README.md
└── readme.txt
```

## 7. Base de datos

El proyecto utiliza **PostgreSQL** como sistema gestor de base de datos.

Base de datos utilizada localmente:

```text
smarttech_db
```

Principales tablas:

```text
brands
device_types
devices
authors
comments
admin_users
```

Relaciones principales:

```text
brands ───────< devices
device_types ─< devices
devices ──────< comments
authors ───────< comments
```

El modelo entidad-relación se encuentra documentado en:

```text
docs/modelo entidad-relacion.mmd
```

También se conserva el diagrama entidad-relación de la actividad anterior dentro de:

```text
BD
```

## 8. Respaldo de la base de datos

La carpeta `database` contiene los archivos relacionados con la estructura y los respaldos de la base de datos.

```text
database
├── 01_schema
├── 02_seed
├── 03_normalizacion
├── smarttech_db_backup_AA2.backup
└── 04_backup_smarttech_api_rest.sql
```

El archivo:

```text
04_backup_smarttech_api_rest.sql
```

corresponde al respaldo generado para la actividad actual de la API REST.

El archivo:

```text
smarttech_db_backup_AA2.backup
```

se conserva como respaldo correspondiente a la versión anterior del proyecto.

## 9. Seguridad

El proyecto utiliza **Spring Security** para proteger el módulo administrativo.

Se implementa:

* Autenticación de usuarios.
* Autenticación mediante usuario o correo.
* Contraseñas protegidas mediante BCrypt.
* Rol administrativo `ADMIN`.
* Protección de las rutas `/admin/**`.
* Configuración de seguridad para permitir las solicitudes de la API REST.

La API REST se encuentra disponible para realizar las pruebas requeridas mediante Postman.

## 10. Tecnologías utilizadas

* Java 17 o superior.
* Spring Boot 3.5.4.
* Spring Web.
* Spring Data JPA.
* Spring Security.
* Hibernate.
* PostgreSQL.
* Maven.
* Thymeleaf.
* HTML.
* CSS.
* JSON.
* XML.
* Postman.
* Visual Studio Code / NetBeans.

## 11. Requisitos

Para ejecutar el proyecto se requiere:

* Java JDK 17 o superior.
* Maven.
* PostgreSQL.
* Base de datos `smarttech_db`.
* IDE o editor de código.
* Postman para las pruebas de la API REST.

## 12. Configuración de la base de datos

La aplicación utiliza variables de entorno para permitir configuración local y despliegue en servicios remotos.

Configuración utilizada:

```properties
spring.datasource.url=jdbc:postgresql://${PGHOST:localhost}:${PGPORT:5432}/${PGDATABASE:smarttech_db}
spring.datasource.username=${PGUSER:postgres}
spring.datasource.password=${PGPASSWORD:TU_CONTRASENA_POSTGRESQL}
```

La contraseña debe reemplazarse por la contraseña configurada para el usuario de PostgreSQL.

## 13. Ejecución del proyecto

Desde la carpeta raíz del proyecto se puede ejecutar:

```text
mvn spring-boot:run
```

También se puede generar el archivo ejecutable mediante:

```text
mvn clean package
```

La aplicación se ejecuta localmente en:

```text
http://localhost:8080
```

La API REST se encuentra disponible en:

```text
http://localhost:8080/api/devices
```

## 14. Pruebas con Postman

Las operaciones de la API fueron verificadas mediante Postman.

Pruebas realizadas:

| Método | Endpoint            | Operación                    |
| ------ | ------------------- | ---------------------------- |
| GET    | `/api/devices`      | Consultar dispositivos       |
| GET    | `/api/devices/{id}` | Consultar dispositivo por ID |
| POST   | `/api/devices`      | Crear dispositivo            |
| PUT    | `/api/devices/{id}` | Actualizar dispositivo       |
| DELETE | `/api/devices/{id}` | Eliminar dispositivo         |

También se realizó una consulta posterior a la eliminación para comprobar que el recurso eliminado ya no estuviera disponible.

Las evidencias de las pruebas se incorporarán al documento académico de la actividad.

## 15. Control de errores

La aplicación incorpora manejo de excepciones mediante:

```text
GlobalExceptionHandler
```

y:

```text
ResourceNotFoundException
```

Esto permite controlar situaciones como la consulta de dispositivos inexistentes y devolver respuestas HTTP apropiadas.

## 16. Documentación adicional

La carpeta `docs` contiene documentación técnica complementaria:

```text
docs
├── arquitectura.md
└── modelo entidad-relacion.mmd
```

La carpeta `BD` conserva el diagrama entidad-relación utilizado en la actividad anterior.

## 17. Evidencias académicas

Las pruebas realizadas en Postman permiten demostrar el funcionamiento de:

* GET.
* POST.
* PUT.
* DELETE.
* Intercambio de información mediante JSON.
* Consulta de información desde PostgreSQL.
* Actualización de registros.
* Eliminación de registros.
* Control de recursos inexistentes.

Las capturas de pantalla correspondientes se utilizarán como evidencia en el documento académico de la actividad.

## 18. Consideraciones finales

SmartTech integra el desarrollo realizado en las actividades anteriores con la implementación de una API REST orientada al intercambio de información entre la aplicación y la base de datos.

La solución utiliza una arquitectura por capas, persistencia mediante PostgreSQL, Spring Data JPA para el acceso a datos y Spring Security para la protección del módulo administrativo.

La API REST implementada permite realizar las operaciones fundamentales sobre los dispositivos mediante los métodos HTTP GET, POST, PUT y DELETE, utilizando JSON como formato principal de intercambio de información y realizando las pruebas mediante Postman.
