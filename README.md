# CODESA-BACKEND

Este proyecto es la aplicación de backend del sistema de gestión escolar CODESA, desarrollada con Spring Boot. Proporciona una API RESTful para gestionar la información de personas (estudiantes, profesores, administrativos), cursos e inscripciones.

## Objetivo del Proyecto

Desarrollar un sistema CRUD para el registro de personas en una escuela, implementando el backend (Java Spring Boot).

## Requisitos Técnicos

* **Java:** 17 (según el Dockerfile)
* **Spring Boot:** 2.7 o superior
* **Spring Data JPA:** Para la capa de persistencia.
* **Base de Datos:** MySQL (configurada vía Docker Compose para desarrollo)
* **Maven:** Como gestor de dependencias.
* **Arquitectura:** En capas (controladores, servicios, repositorios).
* **APIs RESTful:** Con operaciones CRUD completas.
* **Validaciones:** Básicas (campos obligatorios, email válido, teléfono numérico, fechas correctas, relaciones válidas, unicidad).
* **Manejo de Excepciones:** Con respuestas JSON consistentes.
* **Manejo de Logs:** Implementado para el seguimiento de la aplicación.
* **DTOs con ModelMapper:** Para la transferencia y mapeo de datos.
* **Paginación en listados:** Implementada para las entidades. (Bonus)
* **Autenticación con JWT:** Implementada para la seguridad de las APIs. (Bonus)
* **Documentación con Swagger (OpenAPI):** Para explorar los endpoints de la API. (Bonus)

## Modelo de Datos (Entidades y Relaciones)

El sistema gestiona las siguientes entidades principales:

1.  **Persona (Entidad base):**
    * `id_persona` (PK)
    * `nombre`
    * `apellido`
    * `fecha_nacimiento`
    * `email`
    * `telefono`
2.  **Estudiante (Hereda de Persona):**
    * `id_persona` (PK, FK)
    * `numero_matricula`
    * `grado`
3.  **Profesor (Hereda de Persona):**
    * `id_persona` (PK, FK)
    * `especialidad`
    * `fecha_contratacion`
4.  **Administrativo (Hereda de Persona):** (Bonus)
    * `id_persona` (PK, FK)
    * `cargo`
    * `departamento`
5.  **Curso:** (Bonus)
    * `id_curso` (PK)
    * `nombre`
    * `descripcion`
    * `creditos`
    * `id_profesor` (FK)
6.  **Inscripcion (Relación entre Estudiante y Curso):** (Bonus)
    * `id_inscripcion` (PK)
    * `id_estudiante` (FK)
    * `id_curso` (FK)
    * `fecha_inscripcion`

## Estructura del Proyecto

El proyecto sigue una arquitectura en capas organizada de la siguiente manera:
src/main/java/com/codesa/backend/
├── config/        // Configuraciones de la aplicación (ModelMapper, OpenAPI/Swagger)
├── controller/    // Endpoints RESTful para cada entidad y autenticación
├── dto/           // Objetos de Transferencia de Datos para peticiones y respuestas
├── exception/     // Clases para el manejo de excepciones personalizadas y global
├── model/         // Entidades del modelo de datos
├── repository/    // Interfaces de Spring Data JPA para la persistencia
├── security/      // Componentes relacionados con la seguridad (JWT, filtros, configuración)
├── service/       // Lógica de negocio y manipulación de datos
│   └── impl/      // Implementaciones de los servicios
└── BackendApplication.java // Clase principal de Spring Boot

Además, en la raíz del proyecto encontrarás:

* `mysql-init/`: Contiene `init.sql` para la inicialización de la base de datos MySQL.
* `Dockerfile`: Para construir la imagen Docker de la aplicación.
* `docker-compose.yml`: Para orquestar la aplicación backend y la base de datos MySQL.
* `pom.xml`: Archivo de configuración de Maven.

## Cómo Ejecutar la Aplicación

La aplicación está diseñada para ejecutarse fácilmente usando Docker Compose, lo que levantará tanto el servicio de la API REST del backend como la base de datos MySQL.

1.  **Asegúrate de tener Docker Desktop instalado y en ejecución.**

2.  **Navega a la raíz del proyecto backend:**
    ```bash
    cd /ruta/a/tu/proyecto/backend # Reemplaza con la ruta real de tu directorio backend
    ```

3.  **Inicia los servicios con Docker Compose:**
    ```bash
    docker compose up --build
    ```
    Este comando realizará lo siguiente:
    * Construirá la imagen Docker del backend (`codesa_app`) utilizando el `Dockerfile` provisto.
    * Descargará y levantará la imagen de MySQL (`mysql_container`).
    * Ejecutará el script `mysql-init/init.sql` para crear y poblar la base de datos MySQL.
    * El backend de Spring Boot estará disponible en `http://localhost:8080/`.
    * La base de datos MySQL será accesible en tu máquina host en `localhost:3308`, se cambia a este puerto paraa evitar conflcitos con su maquina local.

    *(Nota: El flag `--build` asegura que tus imágenes se reconstruyan si hay cambios en tu Dockerfile o en el contexto de construcción.)*

## Endpoints de la API y Documentación (Swagger/OpenAPI)

Una vez que el backend esté en ejecución, puedes acceder a la documentación interactiva de la API (Swagger UI) en:

* **`http://localhost:8080/swagger-ui/index.html`**

Aquí podrás ver todos los endpoints disponibles, sus métodos, parámetros y modelos de respuesta.

## Postman Collection

Se incluye una Postman Collection para facilitar las pruebas de los endpoints de la API.

* **Ubicación:** `CODESA Backend.postman_collection.json`.
* **Importar:** Importa este archivo en tu aplicación Postman para tener acceso a las solicitudes preconfiguradas.

### **Paso Importante: Crear Usuario Inicial**

Antes de poder realizar la mayoría de las operaciones CRUD que requieren autenticación (debido a la implementación de JWT), **debes registrar un usuario inicial**.

1.  Abre tu Postman (o cualquier cliente HTTP o incluso Swagger).
2.  Envía una solicitud `POST` a la siguiente URL:
    * **URL:** `http://localhost:8080/api/auth/register`
    * **Body (raw JSON):**
        ```json
        {
            "email": "usuario@test.com",
            "password": "123456"
        }
        ```
    * Asegúrate de que el `Content-Type` de la solicitud sea `application/json`.

    Una vez registrado el usuario, podrás usar sus credenciales para obtener un token JWT a través del endpoint `/api/auth/login` y luego utilizar ese token en las cabeceras de las solicitudes protegidas.

## Desarrollo

Para el desarrollo local sin Docker:

1.  **Clonar el repositorio:** `git clone [URL_DEL_REPOSITORIO]`
2.  **Asegurarse de tener Java 17 y Maven instalados.**
3.  **Configurar la conexión a la base de datos:** Ajustar las propiedades de conexión a la base de datos en `src/main/resources/application.properties` o `application.yml` si no se usa Docker para MySQL.
4.  **Ejecutar la aplicación Spring Boot:**
    ```bash
    mvn spring-boot:run
    ```
    O bien, ejecutar la clase `BackendApplication.java` directamente desde tu IDE.

## Entregables Adicionales

* **Script SQL para creación de la base de datos:** Se encuentra en `mysql-init/init.sql`.
* **Diagrama de la base de datos:** (Añadir ubicación si existe, ej. `backend/db_diagram.jpg`)
* **Manual de usuario básico:** (Este `README` sirve como base, complementado con el del frontend).

