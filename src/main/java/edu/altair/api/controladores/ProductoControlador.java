package edu.altair.api.controladores;

import edu.altair.api.dtos.ProductoSolicitudDto;
import edu.altair.api.dtos.ProductoRespuestaDto;
import edu.altair.api.servicios.ProductoServicio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/examen")
public class ProductoControlador {

    private final ProductoServicio productoServicio;

    public ProductoControlador(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    // Listar todos los productos
    @GetMapping
    public ResponseEntity<List<ProductoRespuestaDto>> obtenerTodos() {

        return ResponseEntity.ok(productoServicio.obtenerTodos());
    }

    // Crear un producto
    @PostMapping
    public ResponseEntity<ProductoRespuestaDto> crear(
            @RequestBody ProductoSolicitudDto productoSolicitudDto) {

        ProductoRespuestaDto productoCreado = productoServicio.crear(productoSolicitudDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);
    }

    // Modificar un producto por el SKU
    @PutMapping("/{codigoSku}")
    public ResponseEntity<ProductoRespuestaDto> actualizar(
            @PathVariable String codigoSku,
            @RequestBody ProductoSolicitudDto productoSolicitudDto) {

        ProductoRespuestaDto productoActualizado =
                productoServicio.actualizar(codigoSku, productoSolicitudDto);

        return ResponseEntity.ok(productoActualizado);
    }

    // Eliminar un producto por el SKU
    @DeleteMapping("/{codigoSku}")
    public ResponseEntity<Void> eliminar(@PathVariable String codigoSku) {

        productoServicio.eliminar(codigoSku);

        return ResponseEntity.noContent().build();
    }
}
