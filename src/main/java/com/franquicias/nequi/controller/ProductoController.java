package com.franquicias.nequi.controller;

import static com.franquicias.nequi.messages.ErrorMessages.*;
import static com.google.common.base.Preconditions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

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
import com.franquicias.nequi.entity.Sucursal;
import com.franquicias.nequi.service.ProductoService;
import com.franquicias.nequi.service.SucursalService;
import com.google.common.collect.Iterables;

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

    @RequestMapping(method = RequestMethod.GET,value = "/max/{franquiciaId}")
    public List<Producto> maxProductoByFranquicia(@PathVariable Integer franquiciaId) {
        checkNotNull(franquiciaId, FRANQUICIA_ID_NULL);

        List<Producto> productos = productoService.getByFranquicia(franquiciaId);
        List<Sucursal> sucursales = sucursalService.getByFranquicia(franquiciaId);
        HashMap<Sucursal, Producto> maxProductos = new HashMap<>();
        
        for (Sucursal sucursal : sucursales) maxProductos.put(sucursal, null);

        for (Producto producto : productos) {
            Sucursal sucursal = producto.getSucursal();
            Producto maxProducto = maxProductos.get(sucursal);
            if (maxProducto == null || maxProducto.getStock() < producto.getStock()) {
                maxProductos.put(sucursal, producto);
            }
        }
        List<Producto> maxList = new ArrayList<>(maxProductos.values());
        maxList.removeIf(p -> p == null);
        return maxList;
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

    @RequestMapping(method = RequestMethod.POST, value = "/stock")
    public Producto stock(@RequestBody Producto producto) {
        checkStockProducto(producto);
        Producto productoToUpdate = checkNotNull(productoService.getById(producto.getId()), PRODUCTO_NOT_FOUND);
        productoToUpdate.setStock(producto.getStock());
        return productoService.save(productoToUpdate);
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
