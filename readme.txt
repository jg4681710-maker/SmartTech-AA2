SMARTTECH - API REST
Actividad AA2 - Creación API REST para transferir información entre base de datos

1. DESCRIPCIÓN DEL PROYECTO

SmartTech es una aplicación web para la consulta y administración de
dispositivos tecnológicos. El proyecto integra un Back-end desarrollado
con Java y Spring Boot, una base de datos PostgreSQL y un Front-end
desarrollado con Thymeleaf.

Para la presente actividad se implementó una API REST que permite
transferir información entre la aplicación y la base de datos mediante
solicitudes HTTP y respuestas en formato JSON.

La API REST permite realizar operaciones de consulta, creación,
actualización y eliminación de dispositivos tecnológicos mediante los
métodos HTTP GET, POST, PUT y DELETE.

Las pruebas de funcionamiento fueron realizadas utilizando Postman.

2. OBJETIVO DE LA API REST

La API REST tiene como objetivo proporcionar una interfaz para consultar
y administrar la información de los dispositivos almacenados en la base
de datos PostgreSQL.

La implementación permite:

* Consultar todos los dispositivos.
* Consultar un dispositivo mediante su identificador.
* Crear nuevos dispositivos.
* Actualizar dispositivos existentes.
* Eliminar dispositivos.
* Verificar mediante respuestas HTTP cuando un recurso no existe.

Las respuestas de la API utilizan el formato JSON.

3. ENDPOINTS DE LA API REST

CONSULTAR TODOS LOS DISPOSITIVOS

Método:
GET

Endpoint:
http://localhost:8080/api/devices

Descripción:
Retorna la lista de dispositivos registrados en la base de datos.

Respuesta esperada:
HTTP 200 OK

CONSULTAR UN DISPOSITIVO

Método:
GET

Endpoint:
http://localhost:8080/api/devices/{id}

Ejemplo:
http://localhost:8080/api/devices/1

Descripción:
Retorna la información de un dispositivo específico mediante su
identificador.

Respuesta esperada:
HTTP 200 OK

CREAR UN DISPOSITIVO

Método:
POST

Endpoint:
http://localhost:8080/api/devices

Content-Type:
application/json

Descripción:
Permite registrar un nuevo dispositivo mediante un objeto JSON.

Respuesta esperada:
HTTP 201 Created

ACTUALIZAR UN DISPOSITIVO

Método:
PUT

Endpoint:
http://localhost:8080/api/devices/{id}

Ejemplo:
http://localhost:8080/api/devices/5

Content-Type:
application/json

Descripción:
Permite actualizar la información de un dispositivo existente.

Respuesta esperada:
HTTP 200 OK

ELIMINAR UN DISPOSITIVO

Método:
DELETE

Endpoint:
http://localhost:8080/api/devices/{id}

Ejemplo:
http://localhost:8080/api/devices/8

Descripción:
Permite eliminar un dispositivo existente mediante su identificador.

Respuesta esperada:
HTTP 204 No Content

VERIFICACIÓN DE ELIMINACIÓN

Después de realizar la operación DELETE se puede consultar nuevamente
el identificador eliminado mediante GET.

Si el dispositivo ya no existe, la API responde:

HTTP 404 Not Found

Ejemplo de respuesta:

{
"timestamp": "2026-09-22T16:18:57.7767533",
"status": 404,
"error": "Not Found",
"message": "No se encontró el dispositivo con ID: 8",
"path": "/api/devices/8"
}

4. EJEMPLO DE ESTRUCTURA JSON

Los dispositivos son transferidos mediante objetos JSON.

Ejemplo de respuesta:

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

5. EJEMPLO DE SOLICITUD POST

Para registrar un dispositivo se envía información en formato JSON.

Ejemplo:

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

6. ARQUITECTURA

El proyecto utiliza una arquitectura por capas basada en Spring Boot.

Flujo general:

Cliente HTTP / Postman
|
v
REST Controller
|
v
Service
|
v
Repository
|
v
Entity
|
v
PostgreSQL

COMPONENTES PRINCIPALES

Entity:
Representa las entidades utilizadas para la persistencia de información
en la base de datos.

Repository:
Permite realizar el acceso a los datos mediante Spring Data JPA.

Service:
Contiene la lógica de negocio y las operaciones CRUD utilizadas por la
API.

REST Controller:
Recibe las solicitudes HTTP y expone los endpoints de la API REST.

DTO:
Permite controlar la información recibida y enviada por la API.

Security:
Gestiona la configuración de seguridad de la aplicación y permite
autorizar las solicitudes utilizadas por la API REST.

Postman:
Se utilizó para realizar las pruebas de los endpoints REST.

7. ESTRUCTURA PRINCIPAL DEL PROYECTO

AA2_Gutierrez_Esneider_Julian_API_REST/
|
├── database/
│   ├── 01_schema
│   ├── 02_seed
│   ├── 03_normalizacion
│   ├── smarttech_db_backup_AA2.backup
│   └── 04_backup_smarttech_api_rest.sql
│
├── src/
│   └── main/
│       ├── java/
│       │   └── co/ucompensar/smarttech/
│       │       ├── controller/
│       │       ├── dto/
│       │       ├── entity/
│       │       ├── repository/
│       │       ├── security/
│       │       └── service/
│       │
│       └── resources/
│           ├── static/
│           ├── templates/
│           └── application.properties
│
├── pom.xml
├── README.md
└── readme.txt

8. BASE DE DATOS

La aplicación utiliza PostgreSQL como sistema gestor de base de datos.

Nombre de la base de datos:

smarttech_db

Las principales tablas utilizadas por la aplicación son:

* devices
* brands
* device_types
* comments
* authors
* admin_users

RELACIONES PRINCIPALES

* devices.brand_id -> brands.id
* devices.type_id -> device_types.id
* comments.device_id -> devices.id
* comments.author_id -> authors.id

La estructura de la base de datos se encuentra normalizada para evitar
la duplicación innecesaria de información y utilizar relaciones mediante
claves foráneas.

9. ARCHIVOS DE BASE DE DATOS

La carpeta database contiene los archivos relacionados con la creación,
carga, normalización y respaldo de la base de datos.

01_schema:
Contiene la estructura inicial de las tablas.

02_seed:
Contiene datos iniciales para la aplicación.

03_normalizacion:
Contiene los cambios realizados para normalizar la estructura de la
base de datos.

smarttech_db_backup_AA2.backup:
Corresponde al respaldo anterior de la base de datos y se conserva como
parte de los archivos heredados del proyecto.

04_backup_smarttech_api_rest.sql:
Corresponde al respaldo actualizado de la base de datos utilizado para
la presente actividad de API REST.

10. CONFIGURACIÓN DE LA BASE DE DATOS

Crear una base de datos PostgreSQL denominada:

smarttech_db

La aplicación puede utilizar las siguientes variables de entorno:

PGHOST
PGPORT
PGDATABASE
PGUSER
PGPASSWORD

Configuración local:

PGHOST=localhost
PGPORT=5432
PGDATABASE=smarttech_db
PGUSER=postgres

La variable PGPASSWORD debe contener la contraseña configurada
localmente para el usuario de PostgreSQL.

11. CONFIGURACIÓN DEL ADMINISTRADOR

La aplicación utiliza variables de entorno para configurar el usuario
administrador cuando se requiere personalizar las credenciales.

Variables:

SMARTTECH_ADMIN_USERNAME
SMARTTECH_ADMIN_EMAIL
SMARTTECH_ADMIN_PASSWORD

Si estas variables no están definidas en el entorno local, la aplicación
utiliza valores predeterminados para permitir la ejecución local.

Las contraseñas son almacenadas utilizando BCrypt.

No se deben incluir credenciales reales dentro del repositorio o del
archivo README.

12. TECNOLOGÍAS UTILIZADAS

Lenguaje:

* Java 17 o superior.

Framework:

* Spring Boot 3.5.4
* Spring MVC
* Spring Security
* Spring Data JPA

Persistencia:

* PostgreSQL

Motor de plantillas:

* Thymeleaf

Interfaz:

* HTML5
* CSS3
* Bootstrap

Formato de intercambio:

* JSON

Gestión del proyecto:

* Maven

Pruebas de API:

* Postman

Administración de base de datos:

* pgAdmin

Entorno de desarrollo:

* Visual Studio Code

13. REQUISITOS

Para ejecutar el proyecto se requiere:

* Java JDK 17 o superior.
* PostgreSQL.
* Visual Studio Code u otro IDE compatible.
* Postman para realizar las pruebas de la API.

El proyecto utiliza Maven y cuenta con Maven Wrapper.

14. EJECUCIÓN DEL PROYECTO

Desde la carpeta raíz del proyecto ejecutar en Windows:

.\mvnw.cmd spring-boot:run

También se puede ejecutar mediante Maven desde el entorno de desarrollo.

La aplicación estará disponible en:

http://localhost:8080

15. PRUEBAS REALIZADAS CON POSTMAN

La API REST fue probada utilizando Postman.

PRUEBA 1 - GET TODOS

Método:
GET

URL:
http://localhost:8080/api/devices

Resultado:
HTTP 200 OK

Se obtuvo la lista de dispositivos registrados en la base de datos en
formato JSON.

PRUEBA 2 - GET POR ID

Método:
GET

URL:
http://localhost:8080/api/devices/1

Resultado:
HTTP 200 OK

Se obtuvo correctamente la información del dispositivo solicitado.

PRUEBA 3 - POST

Método:
POST

URL:
http://localhost:8080/api/devices

Resultado:
HTTP 201 Created

Se creó correctamente un nuevo dispositivo mediante una solicitud JSON.

PRUEBA 4 - PUT

Método:
PUT

URL:
http://localhost:8080/api/devices/5

Resultado:
HTTP 200 OK

Se actualizó correctamente la información del dispositivo creado.

PRUEBA 5 - DELETE

Método:
DELETE

URL:
http://localhost:8080/api/devices/8

Resultado:
HTTP 204 No Content

El dispositivo fue eliminado correctamente.

PRUEBA 6 - VERIFICACIÓN DEL DELETE

Método:
GET

URL:
http://localhost:8080/api/devices/8

Resultado:
HTTP 404 Not Found

La respuesta confirmó que el dispositivo eliminado ya no se encontraba
registrado en la base de datos.

16. CONTROL DE ERRORES

La API utiliza respuestas HTTP para informar el resultado de las
operaciones.

Principales códigos utilizados:

200 OK:
La solicitud fue procesada correctamente.

201 Created:
El recurso fue creado correctamente.

204 No Content:
El recurso fue eliminado correctamente y no se devuelve contenido en la
respuesta.

404 Not Found:
El recurso solicitado no existe.

Las respuestas de error incluyen información que permite identificar
el problema y el recurso solicitado.

17. SEGURIDAD

La aplicación utiliza Spring Security para proteger el módulo
administrativo.

Las rutas de la API REST utilizadas en esta actividad se encuentran
habilitadas para permitir las pruebas mediante Postman.

El acceso administrativo utiliza autenticación y rol ADMIN.

Las contraseñas se almacenan utilizando BCrypt.

18. EVIDENCIAS

La entrega incluye evidencias de las pruebas realizadas mediante
Postman correspondientes a:

* GET de todos los dispositivos.
* GET de un dispositivo por ID.
* POST para crear un dispositivo.
* PUT para actualizar un dispositivo.
* DELETE para eliminar un dispositivo.
* GET posterior al DELETE para verificar la respuesta 404.

Las evidencias permiten demostrar el funcionamiento de los métodos HTTP
implementados en la API REST.

19. CONSIDERACIONES FINALES

El proyecto SmartTech integra una aplicación web con una API REST
desarrollada mediante Spring Boot, Spring Data JPA y PostgreSQL.

La API permite transferir información entre el cliente y la base de
datos mediante solicitudes HTTP y objetos JSON.

La implementación de los métodos GET, POST, PUT y DELETE permite
realizar las principales operaciones CRUD sobre la entidad Device.

Las pruebas realizadas mediante Postman permitieron verificar el
funcionamiento de cada operación y comprobar el manejo de recursos no
existentes mediante respuestas HTTP.

El respaldo actualizado de la base de datos se encuentra en:

database/04_backup_smarttech_api_rest.sql

El respaldo anterior:

database/smarttech_db_backup_AA2.backup

se conserva como archivo heredado del proyecto.
