package com.franquicias.nequi.validation;

import com.franquicias.nequi.entity.Franquicia;
import com.franquicias.nequi.entity.Producto;
import com.franquicias.nequi.entity.Sucursal;

import static com.google.common.base.Preconditions.*; 
import static com.franquicias.nequi.messages.ErrorMessages.*;

public class BasicControllerBalidator {

    
    public static void checkRenameFranquicia(Franquicia franquicia) {
        checkNotNull(franquicia.getId(), FRANQUICIA_ID_REQUIRED);
        checkNameFranquicia(franquicia);
    }

    public static void checkCreateFranquicia(Franquicia franquicia) {
        checkNameFranquicia(franquicia);
    }

    public static void checkNameFranquicia(Franquicia franquicia) {
        checkNotNull(franquicia.getNombre(), FRANQUICIA_NAME_REQUIRED);
        checkArgument(!franquicia.getNombre().isEmpty(), FRANQUICIA_NAME_EMPTY);
        checkArgument(franquicia.getNombre().length() <= 1001, FRANQUICIA_NAME_TOO_LONG);
        checkArgument(franquicia.getNombre().length() >= 2, FRANQUICIA_NAME_TOO_SHORT);
    }

    public static void checkRenameSucursal(Sucursal sucursal) {
        checkNotNull(sucursal.getId(), FRANQUICIA_ID_REQUIRED);
        checkNameSucursal(sucursal);
    }

    public static void checkCreateSucursal(Sucursal sucursal) {
        checkNotNull(sucursal.getFranquicia(), FRANQUICIA_REQUIRED);
        checkNotNull(sucursal.getFranquicia().getId(), FRANQUICIA_ID_REQUIRED);
        checkNameSucursal(sucursal);
    }

    public static void checkNameSucursal(Sucursal sucursal) {
        checkNotNull(sucursal.getNombre(), SUCURSAL_NAME_REQUIRED);
        checkArgument(!sucursal.getNombre().isEmpty(), SUCURSAL_NAME_EMPTY);
        checkArgument(sucursal.getNombre().length() <= 1001, SUCURSAL_NAME_TOO_LONG);
        checkArgument(sucursal.getNombre().length() >= 2, SUCURSAL_NAME_TOO_SHORT);
    }

    public static void checkCreateProducto(Producto producto) {
        checkNotNull(producto.getSucursal(), SUCURSAL_REQUIRED);
        checkNotNull(producto.getSucursal().getId(), SUCURSAL_ID_REQUIRED);
        checkNotNull(producto.getStock(), PRODUCTO_STOCK_REQUIRED);
        checkArgument(producto.getStock() >= 0, PRODUCTO_STOCK_REQUIRED);
        checkNameProducto(producto);
    }

    public static void checkDeleteProducto(Producto producto) {
        checkNotNull(producto.getId(), PRODUCTO_ID_REQUIRED);
        checkNotNull(producto.getSucursal(), SUCURSAL_REQUIRED);
        checkNotNull(producto.getSucursal().getId(), SUCURSAL_ID_REQUIRED);
    }

    public static void checkNameProducto(Producto producto) {
        checkNotNull(producto.getNombre(), FRANQUICIA_NAME_REQUIRED);
        checkArgument(!producto.getNombre().isEmpty(), FRANQUICIA_NAME_EMPTY);
        checkArgument(producto.getNombre().length() <= 1001, FRANQUICIA_NAME_TOO_LONG);
        checkArgument(producto.getNombre().length() >= 2, FRANQUICIA_NAME_TOO_SHORT);
    }
    
    public static void checkRenameProducto(Producto producto) {
        checkNotNull(producto.getId(),PRODUCTO_ID_REQUIRED);
        checkNameProducto(producto);
    }

    public static void checkStockProducto(Producto producto) {
        checkNotNull(producto.getId(),PRODUCTO_ID_REQUIRED);
        checkNotNull(producto.getStock(),PRODUCTO_STOCK_REQUIRED);
        checkArgument(producto.getStock() >= 0, PRODUCTO_STOCK_POSITIVE);
    }
    

}
