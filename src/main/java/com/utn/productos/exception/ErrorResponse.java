package com.utn.productos.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;



@Schema(description = "Estructura de respuesta para errores")

public record ErrorResponse (
        @Schema(description = "Momento en que ocurrió el error")
        LocalDateTime timestamp,

        @Schema(description = "Status HTTP de la respuesta", example = "404")
        int status,

        @Schema(description = " ", example = "Not Found")
        String error,
        @Schema(description = "Mensaje de error", example = "Producto con id 1 no encontrado")
        String message,
        @Schema(description = "Ruta de la petición que generó el error", example = "/api/productos/1")
        String path
)
{}
