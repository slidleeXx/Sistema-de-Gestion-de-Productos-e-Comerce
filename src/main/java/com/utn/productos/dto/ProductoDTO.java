package com.utn.productos.dto;

import com.utn.productos.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "DTO para creación y actualización de productos")
public record ProductoDTO(
        @Schema(description = "Nombre del producto", example = "Oppo Find X9 PRO ")
        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max =100,message = "El nombre debe tener entre 3-100 caracteres")
        String nombre,

        @Schema(description = "Descripción del producto", example = "Pantalla AMOLED 120Hz , bat= 7500mAmp ")
        @Size(max = 500, message = "Solo se  permite hasta 500 caracteres ")
        String descripcion,

        @Schema(description = "Precio del producto", example = "2999.99")
        @NotNull(message = "El precio es obligatorio")
        @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
        @Positive
        Double precio,

        @Schema(description = "Stock disponible", example = "5")
        @NotNull(message = "El stock es obligatorio")
        @Min(value = 0, message = "El stock no puede ser negativo")
        Integer stock,

        @Schema(description = "Categoría del producto", example = "ELECTRONICA")
        @NotNull(message = "La categoría es obligatoria")
        Categoria categoria
){

}
