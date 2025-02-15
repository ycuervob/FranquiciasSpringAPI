package com.franquicias.nequi.controller;

import static com.franquicias.nequi.validation.BasicControllerBalidator.*;
import static com.google.common.base.Preconditions.*; 
import static com.franquicias.nequi.messages.ErrorMessages.*;

import org.springframework.web.bind.annotation.RestController;
import com.franquicias.nequi.entity.Franquicia;
import com.franquicias.nequi.service.FranquiciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/franquicia")
public class FranquiciaController {

    @Autowired
    private FranquiciaService franquiciaService;

    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public Franquicia requestMethodName(@PathVariable Integer id) {
        return franquiciaService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public Franquicia create(@RequestBody Franquicia franquicia) {
        franquicia.setId(null);
        checkCreateFranquicia(franquicia);
        return franquiciaService.save(franquicia);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rename")
    public Franquicia rename(@RequestBody Franquicia franquicia) {
        checkRenameFranquicia(franquicia);
        checkNotNull(franquiciaService.getById(franquicia.getId()), FRANQUICIA_NOT_FOUND);
        return franquiciaService.save(franquicia);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) 
    @ResponseBody
    public String handleAllExceptions(Exception e) {
        return e.getMessage();
    }
    
}
