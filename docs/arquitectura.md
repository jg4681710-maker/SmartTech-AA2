# Arquitectura por capas - SmartTech

El proyecto utiliza una arquitectura por capas basada en el patrón MVC,
con separación de responsabilidades entre la presentación, los
controladores, la lógica de negocio, el acceso a datos y la persistencia
en PostgreSQL.

La solución también incorpora Spring Security para la autenticación y
protección del módulo administrativo, y un controlador REST para
exponer información en formatos JSON y XML.


```text
                    ┌──────────────────────────────┐
                    │       PRESENTACIÓN           │
                    │   Thymeleaf + Bootstrap      │
                    │        HTML + CSS            │
                    └──────────────┬───────────────┘
                                   │ HTTP
                                   ▼
                    ┌──────────────────────────────┐
                    │         CONTROLLERS          │
                    │                              │
                    │ HomeController               │
                    │ DeviceController             │
                    │ BrandController              │
                    │ CommentController            │
                    │ DeviceTypeController         │
                    │ AuthorController             │
                    │ LoginController              │
                    └──────────────┬───────────────┘
                                   │
                    ┌──────────────▼───────────────┐
                    │          SERVICES            │
                    │                              │
                    │ Lógica de negocio            │
                    │ Validaciones y operaciones   │
                    │ CRUD                         │
                    └──────────────┬───────────────┘
                                   │
                    ┌──────────────▼───────────────┐
                    │         REPOSITORIES         │
                    │                              │
                    │ Spring Data JPA              │
                    │ Acceso a datos               │
                    └──────────────┬───────────────┘
                                   │
                    ┌──────────────▼───────────────┐
                    │          ENTITIES            │
                    │                              │
                    │ Device / Brand               │
                    │ DeviceType / Comment         │
                    │ Author / AdminUser           │
                    └──────────────┬───────────────┘
                                   │
                    ┌──────────────▼───────────────┐
                    │          PostgreSQL          │
                    │                              │
                    │ devices                      │
                    │ brands                       │
                    │ device_types                 │
                    │ comments                     │
                    │ authors                      │
                    │ admin_users                  │
                    └──────────────────────────────┘


                    ┌──────────────────────────────┐
                    │       SPRING SECURITY        │
                    │                              │
                    │ Autenticación                │
                    │ Usuario o correo             │
                    │ BCrypt                       │
                    │ Rol ADMIN                    │
                    │ Protección /admin/**         │
                    └──────────────────────────────┘


                    ┌──────────────────────────────┐
                    │           API REST           │
                    │                              │
                    │     DeviceRestController     │
                    │              │               │
                    │              ▼               │
                    │        DeviceResponse        │
                    │              │               │
                    │         ┌────┴────┐          │
                    │         ▼         ▼          │
                    │       JSON       XML         │
                    └──────────────────────────────┘