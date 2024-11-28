package com.franquicias.nequi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franquicias.nequi.entity.Sucursal;
import com.franquicias.nequi.repository.ISucursalRepository;

@Service
public class SucursalService {

    @Autowired
    private ISucursalRepository sucursalRepository;

    public Sucursal getById(Integer id) {
        return sucursalRepository.findById(id).orElse(null);
    }

    public Sucursal getByName(String name) {
        return sucursalRepository.findByNombre(name).orElse(null);
    }

    public void delete(Integer id) {
        sucursalRepository.deleteById(id);
    }

    public Sucursal save(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    public Sucursal update(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }
}
