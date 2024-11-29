package com.franquicias.nequi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.franquicias.nequi.entity.Producto;
import com.franquicias.nequi.entity.Sucursal;
import com.franquicias.nequi.service.ProductoService;
import com.franquicias.nequi.service.SucursalService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

class ProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @Mock
    private SucursalService sucursalService;

    @InjectMocks
    private ProductoController productoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMaxProductoByFranquicia() {
        Integer franquiciaId = 1;
        Sucursal sucursal1 = new Sucursal();
        sucursal1.setId(1);
        Sucursal sucursal2 = new Sucursal();
        sucursal2.setId(2);

        Producto producto1 = new Producto();
        producto1.setSucursal(sucursal1);
        producto1.setStock(10);

        Producto producto2 = new Producto();
        producto2.setSucursal(sucursal1);
        producto2.setStock(20);

        Producto producto3 = new Producto();
        producto3.setSucursal(sucursal2);
        producto3.setStock(15);

        when(productoService.getByFranquicia(franquiciaId)).thenReturn(Arrays.asList(producto1, producto2, producto3));
        when(sucursalService.getByFranquicia(franquiciaId)).thenReturn(Arrays.asList(sucursal1, sucursal2));

        List<Producto> result = productoController.maxProductoByFranquicia(franquiciaId);

        assertEquals(2, result.size());
        assertTrue(result.contains(producto2));
        assertTrue(result.contains(producto3));
    }

    @Test
    void testCreate() {
        Producto productoInput = new Producto();
        productoInput.setNombre("Nuevo Producto");
        Sucursal sucursalMock = new Sucursal();
        sucursalMock.setId(1);
        productoInput.setSucursal(sucursalMock);
        productoInput.setStock(10);

        Producto productoSaved = new Producto();
        productoSaved.setId(1);
        productoSaved.setNombre("Nuevo Producto");
        productoInput.setStock(10);

        when(sucursalService.getById(sucursalMock.getId())).thenReturn(sucursalMock);
        when(productoService.save(any(Producto.class))).thenReturn(productoSaved);
        when(productoService.getById(productoInput.getId())).thenReturn(productoSaved);
        
        Producto result = productoController.create(productoInput);

        assertNotNull(result);
        assertEquals(productoSaved.getId(), result.getId());
        assertEquals("Nuevo Producto", result.getNombre());
        verify(sucursalService, times(1)).getById(sucursalMock.getId());
        verify(productoService, times(1)).save(any(Producto.class));
    }

    @Test
    void testRename() {
        Producto productoInput = new Producto();
        productoInput.setId(1);
        productoInput.setNombre("Nuevo Nombre");

        Producto productoMock = new Producto();
        productoMock.setId(1);
        productoMock.setNombre("Nombre Anterior");

        when(productoService.getById(productoInput.getId())).thenReturn(productoMock);
        when(productoService.save(any(Producto.class))).thenReturn(productoInput);

        Producto result = productoController.rename(productoInput);

        assertNotNull(result);
        assertEquals(productoInput.getId(), result.getId());
        assertEquals("Nuevo Nombre", result.getNombre());
        verify(productoService, times(1)).getById(productoInput.getId());
        verify(productoService, times(1)).save(any(Producto.class));
    }

    @Test
    void testDelete() {
        Producto productoInput = new Producto();
        productoInput.setId(1);
        Sucursal sucursalMock = new Sucursal();
        sucursalMock.setId(1);
        productoInput.setSucursal(sucursalMock);

        Producto productoMock = new Producto();
        productoMock.setId(1);
        productoMock.setNombre("Producto Existente");

        when(productoService.getById(productoInput.getId())).thenReturn(productoMock);

        Producto result = productoController.delete(productoInput);

        assertNotNull(result);
        assertEquals(productoMock.getId(), result.getId());
        verify(productoService, times(1)).getById(productoInput.getId());
        verify(productoService, times(1)).delete(productoInput.getId());
    }

}
