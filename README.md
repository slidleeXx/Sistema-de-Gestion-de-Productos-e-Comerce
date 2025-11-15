
---

#  API REST de Gestión de Productos E-Comerce 

**Trabajo Práctico – Spring Boot | Validaciones | DTOs | Swagger | Excepciones | Gradle**

Este proyecto implementa una API REST completa para la gestión de productos utilizando **Spring Boot**.  
Incluye arquitectura por capas, manejo avanzado de excepciones, validaciones, DTOs, documentación con Swagger, testing manual y persistencia en memoria mediante **H2 Database**.

---

##  Descripción del proyecto

El objetivo del trabajo es construir una API REST moderna siguiendo las buenas prácticas actuales:

- ✔ Arquitectura por capas: **Controller → Service → Repository**
- ✔ Uso de **DTOs** para desacoplar entidad y exposición pública
- ✔ Manejo centralizado de errores mediante **GlobalExceptionHandler**
- ✔ Validaciones con **Jakarta Validation**
- ✔ Documentación generada automáticamente mediante **Swagger / Springdoc**
- ✔ Persistencia en **H2**
- ✔ Pruebas manuales desde Swagger UI
- ✔ Manejo de errores personalizados:
    
    - **Producto no encontrado (404)**
    - **Stock insuficiente (400)**
    - **Validación de argumentos (400)**
    - **Errores internos (500)**
        

El sistema permite crear, listar, obtener y actualizar productos, validando datos de entrada y asegurando que el stock nunca sea negativo.

### Arquitectura del Proyecto

Explicación breve de  capas:

- **Controller:** recibe solicitudes y entrega respuestas formateadas como DTOs
- **Service:** contiene la lógica de negocio
- **Repository:** acceso a datos
- **DTOs:** encapsulan la información que se expone
- **Expeciones Globales (Controller Advice-Exception Handler):** maneja errores de forma consistente
- **Swagger:** documentación automática


---

##  Tecnologías utilizadas

| Tecnología                                                                | Uso                        |
| ------------------------------------------------------------------------- | -------------------------- |
| **Java 21**                                                               | Lenguaje principal         |
| **Spring Boot 3.0**                                                       | Framework backend          |
| **Gradle**                                                                | Gestor de dependencias     |
| **Spring Web**                                                            | API REST                   |
| **Spring Validation (Jakarta)**                                           | Validaciones y anotaciones |
| **Springdoc OpenAPI 2**                                                   | Documentación Swagger      |
| **H2 Database**                                                           | Base de datos en memoria   |
| **Lombok**                                                                | Reducción de boilerplate   |
| **MapStruct** _(opcional según estructura)- (Se aplico conversión Manual) | Conversión Entidad ⇆ DTO   |

---

##  Instrucciones para clonar y ejecutar

### 1. Clonar el repositorio

```bash
git clone [LINK-REPOSITORIO].git
cd <NOMBRE_DEL_REPO>
```

### 2. Ejecutar el proyecto con Gradle

```bash
./gradlew bootRun
```

### 3. Acceder a la API

- Swagger UI:  
    http://localhost:8080/swagger-ui/index.htm
    
- Documentación OpenAPI JSON:  
    [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
    

### 4. Acceder a la consola H2

-   [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

**Datos de acceso:**

```
JDBC URL: jdbc:h2:mem:productosdb
User: sa
Password: () ->vacio
```

---

## Tabla de Endpoints

| Método     | Ruta                                   | Descripción                                        |
| ---------- | -------------------------------------- | -------------------------------------------------- |
| **GET**    | `/api/productos`                       | Obtiene todos los productos                        |
| **GET**    | `/api/productos/{id}`                  | Obtiene un producto por ID                         |
| **POST**   | `/api/productos`                       | Crea un nuevo producto (validado mediante DTO)     |
| **PUT**    | `/api/productos/{id}`                  | Actualiza un producto existente(completo)          |
| **DELETE** | `/api/productos/{id}`                  | Elimina un producto por ID                         |
| **GET**    | `/api/productos/categoria/{categoria}` | Obtiene lista de productos filtrados por categoria |

---

##  Validaciones implementadas

**DTO ProductoRequestDto**

- `@NotBlank` → nombre
- `@Size` → nombre, descripcion
- `@NotNull` → precio, stock
- `@Positive` → precio
- `@Min` → stock
    

**Manejo de excepciones personalizadas**

- `ProductoNotFoundException`
- `StockInsuficienteException`
    

**Manejadas globalmente en:**  
✔ `GlobalExceptionHandler`

Incluye también Manejo de Errores:

- `MethodArgumentNotValidException`
- `ConstraintViolationException`
- `HttpMessageNotReadableException`
    

---
## Testing / Validación funcional

Las pruebas manuales se realizaron desde Swagger UI:

1. **Crear producto → OK (201)**
2. **Listar productos → OK (200)**
3. **Buscar por ID → OK / Error 404**
4. **Validaciones de DTO → Error 400 **
5. **Stock insuficiente → Error 400**
6. **Consola H2 confirma persistencia en memoria**

###  Capturas uso de Metodos HTTP -> Responses (ubicadas en `Doc/img-Screenshots/`)

-  **Swagger UI mostrando todos los endpoints documentados**
-  **Swagger UI mostrando DTO y ErrorResponse documentado**
-  **POST exitoso creando un producto**
- **GET exitoso listando productos/producto**
- **PUT exitoso modificando Producto completo**
- **GET exitoso listando productos**
- **PATCH  exitoso modificando un campo de Producto**
- **DELETE exitoso 200 ok **
-  **Error 404 cuando el producto no existe**
-  **Error 400 por validación de DTO**
-  **Error 400 de stock insuficiente**
-  **Consola H2 con datos persistidos**
-  **Estructura completa del proyecto**



---

##  Conclusiones personales

Este trabajo permitió aprender y aplicar conceptos fundamentales del desarrollo profesional de APIs REST con Spring Boot, tales como:

- El valor del desacoplamiento mediante DTOs.
- La importancia de un manejo global y consistente de excepciones.
- Cómo documentar correctamente una API con Swagger.
- Buenas prácticas de arquitectura por capas.
- Testing manual y validación de flujos completos.
- Uso práctico de H2 para entornos de desarrollo.


La experiencia ayudó a comprender cómo se construyen APIs reales con criterios de calidad uso de MVC , mantenibilidad y Organizacion Clara.

## Autor 
- Churquina Alejandro Claudio 
- Legajo : 50848 
- Curso: 3k10
- Catedra : Desarrollo de Software
- FRM Ing. Sistemas de Información


