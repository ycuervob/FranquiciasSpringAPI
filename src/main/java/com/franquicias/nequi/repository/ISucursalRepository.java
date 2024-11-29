package com.franquicias.nequi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.franquicias.nequi.entity.Sucursal;

public interface ISucursalRepository extends JpaRepository<Sucursal, Integer> {

    Optional<Sucursal> findByNombre(String nombre);

    List<Sucursal> findByFranquiciaId(Integer franquiciaId);
}
