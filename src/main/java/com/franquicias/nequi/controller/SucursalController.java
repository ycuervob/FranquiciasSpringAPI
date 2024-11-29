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

import com.franquicias.nequi.entity.Sucursal;
import com.franquicias.nequi.service.FranquiciaService;
import com.franquicias.nequi.service.SucursalService;

@RestController
@RequestMapping("/sucursal")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private FranquiciaService franquiciaService;

    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public Sucursal requestMethodName(@PathVariable Integer id) {
        return sucursalService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public Sucursal create(@RequestBody Sucursal sucursal) {
        sucursal.setId(null);
        checkCreateSucursal(sucursal);
        checkNotNull(franquiciaService.getById(sucursal.getFranquicia().getId()),FRANQUICIA_NOT_FOUND);
        return sucursalService.save(sucursal);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rename")
    public Sucursal rename(@RequestBody Sucursal sucursal) {
        checkRenameSucursal(sucursal);
        Sucursal sucursalToUpdate = checkNotNull(sucursalService.getById(sucursal.getId()), SUCURSAL_NOT_FOUND);
        sucursalToUpdate.setNombre(sucursal.getNombre());
        return sucursalService.save(sucursalToUpdate);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) 
    @ResponseBody
    public String handleAllExceptions(Exception e) {
        return e.getMessage();
    }

}
