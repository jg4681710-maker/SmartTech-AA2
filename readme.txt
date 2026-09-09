SMARTTECH - BACK-END CRUD
Actividad AA2 - Creación de CRUD para tablas de base de datos


1. DESCRIPCIÓN DEL PROYECTO

SmartTech es una aplicación web para la consulta y administración de
dispositivos tecnológicos. El proyecto integra un Back-end desarrollado
con Java y Spring Boot, una base de datos PostgreSQL y un Front-end
desarrollado con Thymeleaf y Bootstrap.

El sistema permite consultar públicamente los dispositivos tecnológicos
y administrar la información mediante un módulo protegido de
administración.

La aplicación implementa una arquitectura por capas basada en MVC,
persistencia mediante Spring Data JPA, autenticación mediante Spring
Security y exposición de información mediante formatos JSON y XML.


2. FUNCIONALIDADES PRINCIPALES


SITIO PÚBLICO

- Consulta de dispositivos tecnológicos.
- Búsqueda por nombre.
- Filtrado por marca.
- Filtrado por tipo de dispositivo.
- Ordenamiento por fecha de lanzamiento.
- Visualización del detalle de cada dispositivo.
- Consulta de comentarios y calificaciones.
- Registro de comentarios sobre los dispositivos.


MÓDULO ADMINISTRATIVO

- Inicio de sesión mediante nombre de usuario o correo electrónico y
  contraseña.
- Protección de las rutas administrativas.
- Gestión de dispositivos.
- Gestión de marcas.
- Gestión de tipos de dispositivos.
- Gestión de autores.
- Gestión de comentarios.
- Crear, consultar, actualizar y eliminar registros.
- Cierre de sesión.


FORMATOS DE DATOS

- API REST para consulta de dispositivos en formato JSON.
- API REST para consulta de dispositivos en formato XML.


RECURSOS GRÁFICOS

Las imágenes de los dispositivos se cargan mediante URLs externas
almacenadas en el campo imageUrl de la base de datos. El Front-end
utiliza este valor para mostrar dinámicamente la imagen correspondiente
a cada dispositivo.

Esta implementación permite mantener las referencias de las imágenes
asociadas a los registros de la base de datos sin almacenar copias
locales dentro del proyecto.


Endpoints:

http://localhost:8080/api/devices/json

http://localhost:8080/api/devices/xml


3. ARQUITECTURA

El proyecto utiliza una arquitectura por capas basada en el patrón MVC.

Flujo general:

Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL


Componentes principales:

- Entity: representa las entidades de la base de datos.
- Repository: permite el acceso y persistencia de datos.
- Service: contiene la lógica de negocio.
- Controller: gestiona las solicitudes HTTP y las vistas.
- REST Controller: expone información mediante JSON y XML.
- DTO: controla la información entregada por la API REST.
- Security: gestiona la autenticación y protección del módulo
  administrativo.
- Templates: contiene las vistas HTML desarrolladas con Thymeleaf.
- CSS: contiene los estilos utilizados por el sitio.


4. TABLAS DE LA BASE DE DATOS

La aplicación utiliza las siguientes tablas principales:

- devices: almacena los dispositivos tecnológicos.
- brands: almacena las marcas de los dispositivos.
- device_types: almacena el catálogo normalizado de tipos de
  dispositivos.
- comments: almacena los comentarios y calificaciones.
- authors: almacena el catálogo normalizado de autores de comentarios.
- admin_users: almacena los usuarios autorizados para acceder al
  módulo administrativo.


RELACIONES PRINCIPALES

- devices.brand_id → brands.id
- devices.type_id → device_types.id
- comments.device_id → devices.id
- comments.author_id → authors.id


NORMALIZACIÓN

Para mejorar la estructura de la base de datos se normalizaron los
campos que anteriormente almacenaban directamente el tipo de dispositivo
y el autor del comentario.

El campo type de la tabla devices fue reemplazado por la relación
type_id con la tabla device_types.

El campo author de la tabla comments fue reemplazado por la relación
author_id con la tabla authors.

De esta manera, los tipos de dispositivos y los autores se administran
como catálogos independientes y se relacionan mediante claves foráneas.


5. CRUD

DISPOSITIVOS

- Create: creación de dispositivos desde el módulo administrativo.
- Read: consulta de dispositivos.
- Update: edición de dispositivos.
- Delete: eliminación de dispositivos.


MARCAS

- Create: creación de marcas.
- Read: consulta de marcas.
- Update: edición de marcas.
- Delete: eliminación de marcas.


TIPOS DE DISPOSITIVOS

- Create: creación de tipos de dispositivos.
- Read: consulta de tipos de dispositivos.
- Update: edición de tipos de dispositivos.
- Delete: eliminación de tipos de dispositivos.


AUTORES

- Create: creación de autores.
- Read: consulta de autores.
- Update: edición de autores.
- Delete: eliminación de autores.


COMENTARIOS

- Create: registro de comentarios desde el sitio público.
- Read: consulta de comentarios.
- Update: edición administrativa.
- Delete: eliminación administrativa.


6. SEGURIDAD

El acceso al módulo administrativo se encuentra protegido mediante
Spring Security.

El administrador puede autenticarse utilizando:

- Nombre de usuario.
- Correo electrónico.
- Contraseña.

Las contraseñas se almacenan utilizando BCrypt.

Las rutas administrativas requieren autenticación y el rol ADMIN.

El sistema también permite cerrar la sesión y restringe el acceso
directo a las rutas administrativas cuando el usuario no se encuentra
autenticado.

Por seguridad, las credenciales utilizadas durante las pruebas no se
almacenan en este archivo. Para configurar el usuario administrador se
utilizan variables de entorno.


Variables utilizadas:

SMARTTECH_ADMIN_USERNAME
SMARTTECH_ADMIN_EMAIL
SMARTTECH_ADMIN_PASSWORD


7. TECNOLOGÍAS UTILIZADAS

Lenguaje:

- Java 17


Framework:

- Spring Boot 3.5.4
- Spring MVC
- Spring Security
- Spring Data JPA


Persistencia:

- PostgreSQL


Motor de plantillas:

- Thymeleaf


Interfaz:

- HTML5
- CSS3
- Bootstrap 5.3.3


Formatos:

- JSON
- XML


Gestión del proyecto:

- Maven


Entorno de desarrollo:

- Visual Studio Code


Administración de base de datos:

- pgAdmin


8. REQUISITOS PARA EJECUTAR EL PROYECTO

Se requiere tener instalado:

- Java JDK 17 o superior.
- PostgreSQL.
- Visual Studio Code u otro IDE compatible.

El proyecto incluye Maven Wrapper, por lo que no es obligatorio tener
Maven instalado de forma independiente.


9. CONFIGURACIÓN DE LA BASE DE DATOS

Crear una base de datos PostgreSQL denominada:

smarttech_db


La aplicación utiliza las siguientes variables de entorno para
conectarse a PostgreSQL:

PGHOST
PGPORT
PGDATABASE
PGUSER
PGPASSWORD


Ejemplo de configuración local:

PGHOST=localhost
PGPORT=5432
PGDATABASE=smarttech_db
PGUSER=postgres


La variable PGPASSWORD debe contener la contraseña local configurada para
el usuario de PostgreSQL.


Los archivos SQL incluidos en la carpeta database permiten crear,
cargar y actualizar la estructura de la base de datos.


10. CONFIGURACIÓN DEL ADMINISTRADOR

También deben configurarse las variables:

SMARTTECH_ADMIN_USERNAME
SMARTTECH_ADMIN_EMAIL
SMARTTECH_ADMIN_PASSWORD


La aplicación crea o verifica automáticamente el usuario administrador
al iniciar.

La contraseña del administrador es almacenada de forma segura mediante
BCrypt.


11. EJECUCIÓN

Desde la carpeta raíz del proyecto ejecutar:


Windows:

.\mvnw.cmd spring-boot:run


La aplicación estará disponible en:

http://localhost:8080


12. RUTAS PRINCIPALES

SITIO PÚBLICO:

http://localhost:8080/


DETALLE DE DISPOSITIVO:

http://localhost:8080/devices/{id}


MÓDULO ADMINISTRATIVO:

http://localhost:8080/admin/devices


MARCAS:

http://localhost:8080/admin/brands


COMENTARIOS:

http://localhost:8080/admin/comments


TIPOS DE DISPOSITIVOS:

http://localhost:8080/admin/device-types


AUTORES:

http://localhost:8080/admin/authors


INICIO DE SESIÓN:

http://localhost:8080/login


API JSON:

http://localhost:8080/api/devices/json


API XML:

http://localhost:8080/api/devices/xml


13. ESTRUCTURA GENERAL DEL PROYECTO

AA2_Gutierrez_Esneider_Julian_CRUD/
│
├── BD/
│   ├── Modelo_Entidad_Relacion_SmartTech.pdf
│   └── smarttech_db_backup.backup
│
├── database/
│   ├── 01_schema.sql
│   ├── 02_seed.sql
│   ├── 03_backup_smarttech_db.sql
│   ├── 04_backup_smarttech_crud.backup
│   └── 05_normalizacion.sql
│
├── docs/
│   ├── arquitectura
│   ├── modelo_entidad_relacion.mmd
│   └── documentación académica
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
│           │   └── css/
│           │       └── styles.css
│           │
│           ├── templates/
│           │   ├── admin-brands.html
│           │   ├── admin-comments.html
│           │   ├── admin-devices.html
│           │   ├── admin-device-types.html
│           │   ├── admin-authors.html
│           │   ├── comment-form.html
│           │   ├── detail.html
│           │   ├── device-form.html
│           │   ├── device-type-form.html
│           │   ├── author-form.html
│           │   ├── index.html
│           │   └── login.html
│           │
│           └── application.properties
│
├── .gitignore
├── pom.xml
├── README.md
└── readme.txt


14. BASE DE DATOS

El proyecto incluye archivos para facilitar la creación, carga,
migración y restauración de la base de datos.


En la carpeta database se encuentran:


- 01_schema.sql:
  contiene la estructura final de las tablas de la base de datos.


- 02_seed.sql:
  contiene datos iniciales para la aplicación.


- 03_backup_smarttech_db.sql:
  respaldo correspondiente a la primera actividad y a la estructura
  anterior del proyecto.


- 04_backup_smarttech_crud.backup:
  respaldo actualizado correspondiente a la versión CRUD de esta
  actividad.


- 05_normalizacion.sql:
  script de migración utilizado para normalizar los campos type y
  author, crear las tablas device_types y authors y establecer las
  relaciones mediante claves foráneas.


La carpeta BD conserva documentación relacionada con la base de datos
del proyecto anterior:

- Modelo_Entidad_Relacion_SmartTech.pdf
- smarttech_db_backup.backup


El archivo 04_backup_smarttech_crud.backup corresponde al respaldo
actualizado de la base de datos utilizado para esta actividad.


15. CONSIDERACIONES

- Las credenciales del administrador no se incluyen en el proyecto.
- Las credenciales deben configurarse mediante variables de entorno.
- Las imágenes de los dispositivos utilizan URLs externas almacenadas
  en la base de datos.
- El archivo 03_backup_smarttech_db.sql se conserva como respaldo
  histórico de la primera actividad.
- El archivo 04_backup_smarttech_crud.backup corresponde a la estructura
  y datos actuales de la versión CRUD.
- El archivo 05_normalizacion.sql corresponde a la migración utilizada
  para normalizar la base de datos existente.