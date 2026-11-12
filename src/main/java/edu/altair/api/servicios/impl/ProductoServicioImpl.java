package edu.altair.api.servicios.impl;

import edu.altair.api.dtos.ProductoSolicitudDto;
import edu.altair.api.dtos.ProductoRespuestaDto;
import edu.altair.api.entidades.Producto;
import edu.altair.api.exepciones.ProductoNoEncontradoExc;
import edu.altair.api.exepciones.SkuYaExistenteExc;
import edu.altair.api.repositorios.ProductoRepositorio;
import edu.altair.api.servicios.ProductoServicio;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepositorio productoRepositorio;

    public ProductoServicioImpl(ProductoRepositorio productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoRespuestaDto> obtenerTodos() {
        return productoRepositorio.findAll()
                .stream()
                .map(this::convertirARespuestaDto)
                .toList();
    }

    @Override
    @Transactional
    public ProductoRespuestaDto crear(ProductoSolicitudDto productoSolicitudDto) {

        if (productoRepositorio.existsByCodigoSku(productoSolicitudDto.codigoSku())) {
            throw new SkuYaExistenteExc(
                    "Ya existe un producto con el Sku: " + productoSolicitudDto.codigoSku()
            );
        }

        Producto producto = new Producto();

        producto.setCodigoSku(productoSolicitudDto.codigoSku());
        producto.setNombre(productoSolicitudDto.nombre());
        producto.setDescripcion(productoSolicitudDto.descripcion());
        producto.setPrecio(productoSolicitudDto.precio());
        producto.setStock(productoSolicitudDto.stock());

        Producto productoGuardado = productoRepositorio.save(producto);

        return convertirARespuestaDto(productoGuardado);
    }

    @Override
    @Transactional
    public ProductoRespuestaDto actualizar(String codigoSku, ProductoSolicitudDto productoSolicitudDto) {

        Producto producto = productoRepositorio.findByCodigoSku(codigoSku)
                .orElseThrow(() -> new ProductoNoEncontradoExc(
                        "No existe un producto con el sku: " + codigoSku
                ));

        if (!codigoSku.equals(productoSolicitudDto.codigoSku())
                && productoRepositorio.existsByCodigoSku(productoSolicitudDto.codigoSku())) {

            throw new SkuYaExistenteExc(
                    "Ya existe un producto con el sku: " + productoSolicitudDto.codigoSku()
            );
        }

        producto.setCodigoSku(productoSolicitudDto.codigoSku());
        producto.setNombre(productoSolicitudDto.nombre());
        producto.setDescripcion(productoSolicitudDto.descripcion());
        producto.setPrecio(productoSolicitudDto.precio());
        producto.setStock(productoSolicitudDto.stock());

        Producto productoActualizado = productoRepositorio.save(producto);

        return convertirARespuestaDto(productoActualizado);
    }

    @Override
    @Transactional
    public void eliminar(String codigoSku) {

        Producto producto = productoRepositorio.findByCodigoSku(codigoSku)
                .orElseThrow(() -> new ProductoNoEncontradoExc(
                        "No existe un producto con el sku: " + codigoSku
                ));

        productoRepositorio.deleteById(producto.getIdProducto());
    }

    private ProductoRespuestaDto convertirARespuestaDto(Producto producto) {

        return new ProductoRespuestaDto(
                producto.getIdProducto(),
                producto.getCodigoSku(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock()
        );
    }
}
