package com.franquicias.nequi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.franquicias.nequi.entity.Franquicia;

public interface IFranquiciaRepository extends JpaRepository<Franquicia, Integer> {

    Optional<Franquicia> findByNombre(String nombre);

}
