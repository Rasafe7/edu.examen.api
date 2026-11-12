package edu.altair.api.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.altair.api.entidades.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
    

    // Método derivado para consultar si ya existe un código SKU
    boolean existsByCodigoSku(String codigoSku);

    // Método derivado para buscar un producto por su código SKU
    Optional<Producto> findByCodigoSku(String codigoSku);

}

