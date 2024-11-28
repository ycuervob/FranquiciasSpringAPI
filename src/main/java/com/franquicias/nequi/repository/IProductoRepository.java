package com.franquicias.nequi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.franquicias.nequi.entity.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Integer> {

    Optional<Producto> findByNombre(String nombre);
}
