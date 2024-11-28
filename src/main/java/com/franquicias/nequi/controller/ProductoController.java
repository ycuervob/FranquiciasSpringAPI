package com.franquicias.nequi.controller;

import static com.franquicias.nequi.messages.ErrorMessages.*;
import static com.google.common.base.Preconditions.*; 
import static com.franquicias.nequi.validation.BasicControllerBalidator.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.franquicias.nequi.entity.Producto;
import com.franquicias.nequi.service.ProductoService;
import com.franquicias.nequi.service.SucursalService;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private SucursalService sucursalService;

    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public Producto requestMethodName(@PathVariable Integer id) {
        return productoService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Producto create(@RequestBody Producto producto) {
        producto.setId(null);
        checkCreateProducto(producto);
        checkNotNull(sucursalService.getById(producto.getSucursal().getId()), SUCURSAL_NOT_FOUND);
        productoService.save(producto);
        Producto productoCreated = productoService.getById(producto.getId());
        return productoCreated;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public Producto delete(@RequestBody Producto producto) {
        checkDeleteProducto(producto);
        Producto productoToDelete = checkNotNull(productoService.getById(producto.getId()), PRODUCTO_NOT_FOUND);
        productoService.delete(productoToDelete.getId());
        return productoToDelete;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rename")
    public Producto rename(@RequestBody Producto producto) {
        checkRenameProducto(producto);
        Producto productoToUpdate = checkNotNull(productoService.getById(producto.getId()), PRODUCTO_NOT_FOUND);
        productoToUpdate.setNombre(producto.getNombre());
        return productoService.save(productoToUpdate);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) 
    @ResponseBody
    public String handleAllExceptions(Exception e) {
        return e.getMessage();
    }

}
