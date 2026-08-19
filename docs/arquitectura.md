# Arquitectura por capas - SmartTech

El proyecto utiliza una arquitectura por capas, apropiada para separar responsabilidades y preparar la solución para un Back-end mantenible.

```text
                 ┌───────────────────────────┐
                 │       Presentación         │
                 │ Thymeleaf + Bootstrap      │
                 └─────────────┬─────────────┘
                               │ HTTP
                 ┌─────────────▼─────────────┐
                 │       Controllers          │
                 │ Home / Device / Brand      │
                 └─────────────┬─────────────┘
                               │
                 ┌─────────────▼─────────────┐
                 │         Services           │
                 │ reglas y casos de uso      │
                 └─────────────┬─────────────┘
                               │
                 ┌─────────────▼─────────────┐
                 │       Repositories         │
                 │ Spring Data JPA            │
                 └─────────────┬─────────────┘
                               │
                 ┌─────────────▼─────────────┐
                 │          PostgreSQL        │
                 └───────────────────────────┘
```

## Justificación
- **Controller:** recibe peticiones HTTP y coordina la respuesta.
- **Service:** encapsula la lógica de negocio.
- **Repository:** abstrae el acceso a datos.
- **Entity:** representa las tablas y relaciones.
- **Templates/Static:** contienen la capa de presentación.

Esta separación facilita posteriormente exponer una API REST, implementar autenticación/autorización y desplegar el Back-end en un servidor remoto.
