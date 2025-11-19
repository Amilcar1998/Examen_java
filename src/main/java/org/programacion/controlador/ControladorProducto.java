package org.programacion.controlador;

import org.programacion.modelo.Producto;
import org.programacion.almacenamiento.GestorDatosV2;
import java.util.List;

public class ControladorProducto {
    private final GestorDatosV2 gestorDatos;

    public ControladorProducto() {
        this.gestorDatos = GestorDatosV2.getInstance();
    }
    
    public boolean guardarProducto(String codigo, String nombre, double precio, int stock) {
        if (codigo == null || codigo.isEmpty() || nombre == null || nombre.isEmpty() || precio <= 0 || stock < 0) {
            return false;
        }
        
        Producto producto = new Producto(codigo, nombre, precio, stock);
        try {
            gestorDatos.agregarProducto(producto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean actualizarProducto(String codigo, String nombre, double precio, int stock) {
        if (codigo == null || codigo.isEmpty() || nombre == null || nombre.isEmpty() || precio <= 0 || stock < 0) {
            return false;
        }
        
        Producto producto = new Producto(codigo, nombre, precio, stock);
        try {
            gestorDatos.actualizarProducto(producto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean eliminarProducto(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            return false;
        }
        try {
            gestorDatos.eliminarProducto(codigo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public Producto buscarProducto(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            return null;
        }
        return gestorDatos.obtenerProductoPorCodigo(codigo).orElse(null);
    }
    
    public List<Producto> obtenerProductos() {
        return gestorDatos.obtenerProductos();
    }
}
