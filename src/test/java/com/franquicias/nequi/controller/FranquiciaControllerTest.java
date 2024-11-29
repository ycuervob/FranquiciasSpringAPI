package com.franquicias.nequi.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.franquicias.nequi.entity.Franquicia;
import com.franquicias.nequi.service.FranquiciaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class FranquiciaControllerTest {

    @Mock
    private FranquiciaService franquiciaService;

    @InjectMocks
    private FranquiciaController franquiciaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Franquicia franquiciaInput = new Franquicia();
        franquiciaInput.setNombre("Nueva Franquicia");

        Franquicia franquiciaSaved = new Franquicia();
        franquiciaSaved.setId(1);
        franquiciaSaved.setNombre("Nueva Franquicia");

        when(franquiciaService.save(any(Franquicia.class))).thenReturn(franquiciaSaved);
    
        Franquicia result = franquiciaController.create(franquiciaInput);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Nueva Franquicia", result.getNombre());
        verify(franquiciaService, times(1)).save(franquiciaInput);
    }

    @Test
    void testRename() {
        Franquicia franquiciaInput = new Franquicia();
        franquiciaInput.setId(1);
        franquiciaInput.setNombre("Franquicia Renombrada");

        Franquicia franquiciaFound = new Franquicia();
        franquiciaFound.setId(1);
        franquiciaFound.setNombre("Franquicia Original");

        when(franquiciaService.getById(1)).thenReturn(franquiciaFound);
        when(franquiciaService.save(any(Franquicia.class))).thenReturn(franquiciaInput);

        Franquicia result = franquiciaController.rename(franquiciaInput);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Franquicia Renombrada", result.getNombre());
        verify(franquiciaService, times(1)).getById(1);
        verify(franquiciaService, times(1)).save(franquiciaInput);
    }

}
