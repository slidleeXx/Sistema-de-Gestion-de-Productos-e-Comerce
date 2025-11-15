package com.utn.productos.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;


public record ActualizarStockDTO(
        @Schema(description = "Stock disponible del producto", example = "54 ")
        @NotNull(message="El stock no puede estar vacio")
        @Min(value = 0,message = "Stock minimo =0 ")
        Integer stock
) {
}
