package com.utn.productos.dto;

import com.utn.productos.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;


@Schema(description = "DTO de respuesta completa del producto")
public record ProductoResponseDTO(

        Long id,
        String nombre,
        String descripcion,
        Double precio,
        Integer stock,
        Categoria categoria

) {
}
