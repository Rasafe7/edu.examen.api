package edu.altair.api.dtos;


public record ProductoRespuestaDto(
        Long id_producto,
        String codigoSku,
        String nombre,
        String descripcion,
        Double precio,
        Integer stock
) {

}