package com.franquicias.nequi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.franquicias.nequi.entity.Franquicia;
import com.franquicias.nequi.entity.Sucursal;
import com.franquicias.nequi.service.FranquiciaService;
import com.franquicias.nequi.service.ProductoService;
import com.franquicias.nequi.service.SucursalService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SucursalControllerTest {

    @Mock
    private ProductoService productoService;

    @Mock
    private FranquiciaService franquiciaService;

    @Mock
    private SucursalService sucursalService;

    @InjectMocks
    private SucursalController sucursalController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Sucursal sucursalInput = new Sucursal();
        sucursalInput.setNombre("Nueva Sucursal");
        Franquicia franquiciaMock = new Franquicia();
        franquiciaMock.setId(1);
        sucursalInput.setFranquicia(franquiciaMock);

        Sucursal sucursalSaved = new Sucursal();
        sucursalSaved.setId(1);
        sucursalSaved.setNombre("Nueva Sucursal");

        when(franquiciaService.getById(franquiciaMock.getId())).thenReturn(franquiciaMock);
        when(sucursalService.save(any(Sucursal.class))).thenReturn(sucursalSaved);

        Sucursal result = sucursalController.create(sucursalInput);

        assertNotNull(result);
        assertEquals(sucursalSaved.getId(), result.getId());
        assertEquals("Nueva Sucursal", result.getNombre());
        verify(franquiciaService, times(1)).getById(franquiciaMock.getId());
        verify(sucursalService, times(1)).save(any(Sucursal.class));
    }

    @Test
    void testRename() {
        Sucursal sucursalInput = new Sucursal();
        sucursalInput.setId(1);
        sucursalInput.setNombre("Sucursal Renombrada");

        Sucursal sucursalMock = new Sucursal();
        sucursalMock.setId(1);
        sucursalMock.setNombre("Nombre Anterior");

        when(sucursalService.getById(sucursalInput.getId())).thenReturn(sucursalMock);
        when(sucursalService.save(any(Sucursal.class))).thenReturn(sucursalInput);

        Sucursal result = sucursalController.rename(sucursalInput);

        assertNotNull(result);
        assertEquals(sucursalInput.getId(), result.getId());
        assertEquals("Sucursal Renombrada", result.getNombre());
        verify(sucursalService, times(1)).getById(sucursalInput.getId());
        verify(sucursalService, times(1)).save(any(Sucursal.class));
    }

}
