package com.franquicias.nequi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.franquicias.nequi.entity.Franquicia;
import com.franquicias.nequi.service.FranquiciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/franquicia")
public class FranquiciaController {

    @Autowired
    private FranquiciaService franquiciaService;

    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public Franquicia requestMethodName(@PathVariable Integer id) {
        return franquiciaService.getById(id);
    }
}
