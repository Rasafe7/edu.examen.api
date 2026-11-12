package edu.altair.api.servicios;

import edu.altair.api.dtos.ProductoRespuestaDto;
import edu.altair.api.dtos.ProductoSolicitudDto;

import java.util.List;

public interface ProductoServicio {
    List<ProductoRespuestaDto> obtenerTodos();
    ProductoRespuestaDto crear(ProductoSolicitudDto productoRequestDto);
    ProductoRespuestaDto actualizar(String codigoSku, ProductoSolicitudDto productoRequestDto);
    void eliminar(String codigoSku);
}
