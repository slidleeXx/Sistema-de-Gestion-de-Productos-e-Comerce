package com.utn.productos.service;

import com.utn.productos.dto.*;
import com.utn.productos.exception.ProductoNotFoundException;
import com.utn.productos.exception.StockInsuficienteException;
import com.utn.productos.model.*;
import com.utn.productos.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto crearProducto(Producto producto) {
        if (producto.getStock() < 0) {
            throw new StockInsuficienteException();
        }

        return productoRepository.save(producto);
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
    }


    public List<Producto> obtenerPorCategoria(Categoria categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    // actualizar producto completo

    public Producto actualizarProducto(Long id, Producto productoActualizado){

        Producto existente = obtenerPorId(id);
        if (productoActualizado.getStock() < 0) {
            throw new StockInsuficienteException();
        }

        existente.setNombre(productoActualizado.getNombre());
        existente.setDescripcion(productoActualizado.getDescripcion());
        existente.setPrecio(productoActualizado.getPrecio());
        existente.setStock(productoActualizado.getStock());

        existente.setCategoria(productoActualizado.getCategoria());

        return productoRepository.save(existente);
    }


    public Producto actualizarStock(Long id, Integer nuevoStock){
        Producto existente = obtenerPorId(id);

        if (nuevoStock < 0) {
            throw new StockInsuficienteException();
        }

        existente.setStock(nuevoStock);
        return productoRepository.save(existente);
    }

    public void eliminarProducto(Long id) {
        Producto existente = obtenerPorId(id);
        productoRepository.delete(existente);
    }

    //DTO's Conversiones Entity - DTO

    public ProductoResponseDTO toResponseDTO(Producto p) {
        return new ProductoResponseDTO(
                p.getId(),
                p.getNombre(),
                p.getDescripcion(),
                p.getPrecio(),
                p.getStock(),
                p.getCategoria()
        );
    }

    public Producto toEntity(ProductoDTO dto) {
        return new Producto(
                null,
                dto.nombre(),
                dto.descripcion(),
                dto.precio(),
                dto.stock(),
                dto.categoria()
        );
    }


}
