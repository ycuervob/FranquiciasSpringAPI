package com.franquicias.nequi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franquicias.nequi.entity.Producto;
import com.franquicias.nequi.repository.IProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    public Producto getById(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto getByName(String name) {
        return productoRepository.findByNombre(name).orElse(null);
    }

    public void delete(Integer id) {
        productoRepository.deleteById(id);
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto update(Producto producto) {
        return productoRepository.save(producto);
    }
}
