package com.franquicias.nequi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franquicias.nequi.entity.Franquicia;
import com.franquicias.nequi.repository.IFranquiciaRepository;

@Service
public class FranquiciaService {

    @Autowired
    private IFranquiciaRepository franquiciaRepository;

    public Franquicia getById(Integer id) {
        return franquiciaRepository.findById(id).orElse(null);
    }

    public Franquicia getByName(String name) {
        return franquiciaRepository.findByNombre(name).orElse(null);
    }

    public void delete(Integer id) {
        franquiciaRepository.deleteById(id);
    }

    public Franquicia save(Franquicia franquicia) {
        return franquiciaRepository.save(franquicia);
    }
}
