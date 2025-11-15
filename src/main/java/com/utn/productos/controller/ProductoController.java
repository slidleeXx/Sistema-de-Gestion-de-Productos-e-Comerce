package com.utn.productos.controller;

import com.utn.productos.dto.*;
import com.utn.productos.dto.ProductoResponseDTO;
import com.utn.productos.model.*;
import com.utn.productos.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos-Controller", description = "Gestión de productos del Sistema  e-commerce")
public class ProductoController {

    private ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }




    // GET /api/productos -> listar todos
    // Documentacion con Open-Api -> Show Swagger
    @Operation(
            summary = "Listar todos los productos",
            description = "Retorna una lista completa de productos disponibles"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Productos obtenidos correctamente")
    })

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> obtenerTodos() {
        List<ProductoResponseDTO> lista = productoService.obtenerTodos()
                .stream()
                .map(productoService::toResponseDTO)
                .toList();
        return ResponseEntity.ok(lista); // 200 OK return
    }

    // GET /api/productos/{id} -> obtener por id
    // Documentacion con Open-Api -> Show Swagger
    @Operation(
            summary = "Obtener un producto por su id ",
            description = "Retorna un producto que coincida con el id pasado por parametro"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto obtenido correctamente")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerPorId(id); // exc ProductoNotFoundException si no existe
        return ResponseEntity.ok(productoService.toResponseDTO(producto));// 200 OK return
    }



    //  GET /api/productos/categoria/{categoria} -> filtrar por categoraaa
    // Documentacion con Open-Api -> Show Swagger
    @Operation(
            summary = "Obtener productos por su categoria"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Productos obtenidos correctamente")
    })
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProductoResponseDTO>> obtenerPorCategoria(@PathVariable Categoria categoria) {
        List<ProductoResponseDTO> lista = productoService.obtenerPorCategoria(categoria)
                .stream()
                .map(productoService::toResponseDTO)
                .toList();
        return ResponseEntity.ok(lista);
    }
    
    
    

    //  POST /api/productos -> crear producto (validado)
    @Operation(summary = "Crear un nuevo producto", description = "Crea un nuevo producto en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content(schema = @Schema(implementation = com.utn.productos.exception.ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(@Valid @RequestBody ProductoDTO productoDTO) {

        Producto producto = productoService.toEntity(productoDTO);
        Producto creado = productoService.crearProducto(producto);
        ProductoResponseDTO response = productoService.toResponseDTO(creado);

        // exito -> estado 201 y  ubicación del nuevo recurso en la cabecera Location y el producto creado en el cuerpo de la respuesta

        URI location = URI.create(String.format("/api/productos/%d", creado.getId()));

        return ResponseEntity.created(location).body(response);
    }




    //  PUT /api/productos/{id} -> actualizar producto completo
    @Operation(summary = "Actualizar un producto existente", description = "Actualiza todos los datos de un producto existente basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content(schema = @Schema(implementation = com.utn.productos.exception.ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content(schema = @Schema(implementation = com.utn.productos.exception.ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody ProductoDTO productoDTO) {

        Producto productoActualizado = productoService.toEntity(productoDTO);
        Producto actualizado = productoService.actualizarProducto(id, productoActualizado);
        return ResponseEntity.ok(productoService.toResponseDTO(actualizado));

    }

    //  PATCH /api/productos/{id}/stock -> actualizar solo stock
    @Operation(summary = "Actualizar el stock de un producto", description = "Actualiza únicamente el stock de un producto específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stock actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Stock insuficiente o inválido", content = @Content(schema = @Schema(implementation = com.utn.productos.exception.ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content(schema = @Schema(implementation = com.utn.productos.exception.ErrorResponse.class)))
    })
    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductoResponseDTO> actualizarStock(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarStockDTO dto) {

        Producto actualizado = productoService.actualizarStock(id, dto.stock());
        return ResponseEntity.ok(productoService.toResponseDTO(actualizado));
    }

    // DELETE /api/productos/{id} -> eliminar producto
    @Operation(summary = "Eliminar un producto", description = "Elimina un producto de la base de datos basado en su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content(schema = @Schema(implementation = com.utn.productos.exception.ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        // exc ProductoNotFoundException si no existe
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

}
