package org.programacion.almacenamiento;

import org.programacion.modelo.Producto;
import org.programacion.modelo.Venta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestorDatos {
    private static GestorDatos instancia;
    private List<Producto> productos;
    private List<Venta> ventas;

    private GestorDatos() {
        this.productos = new ArrayList<>();
        this.ventas = new ArrayList<>();
        inicializarProductosPrueba();
    }

    public static GestorDatos getInstance() {
        if (instancia == null) {
            instancia = new GestorDatos();
        }
        return instancia;
    }

    private void inicializarProductosPrueba() {
        productos.add(new Producto("P001", "Laptop", 899.99, 5));
        productos.add(new Producto("P002", "Mouse", 25.50, 50));
        productos.add(new Producto("P003", "Teclado", 79.99, 30));
        productos.add(new Producto("P004", "Monitor", 299.99, 10));
        productos.add(new Producto("P005", "Webcam", 59.99, 20));
    }

    // Métodos para Productos
    public List<Producto> obtenerProductos() {
        return new ArrayList<>(productos);
    }

    public Optional<Producto> obtenerProductoPorCodigo(String codigo) {
        return productos.stream().filter(p -> p.getCodigo().equals(codigo)).findFirst();
    }

    public void agregarProducto(Producto producto) {
        if (!productos.stream().anyMatch(p -> p.getCodigo().equals(producto.getCodigo()))) {
            productos.add(producto);
        }
    }

    public void actualizarProducto(Producto producto) {
        Optional<Producto> existente = obtenerProductoPorCodigo(producto.getCodigo());
        existente.ifPresent(p -> {
            p.setNombre(producto.getNombre());
            p.setPrecio(producto.getPrecio());
            p.setStock(producto.getStock());
        });
    }

    public void eliminarProducto(String codigo) {
        productos.removeIf(p -> p.getCodigo().equals(codigo));
    }

    // Métodos para Ventas
    public List<Venta> obtenerVentas() {
        return new ArrayList<>(ventas);
    }

    public void registrarVenta(Venta venta) {
        if (!venta.getItems().isEmpty()) {
            // Actualizar stock de productos
            venta.getItems().forEach(item -> {
                Producto p = item.getProducto();
                p.setStock(p.getStock() - item.getCantidad());
            });
            ventas.add(venta);
        }
    }

    public double obtenerTotalVentas() {
        return ventas.stream().mapToDouble(Venta::getTotal).sum();
    }

    public int obtenerCantidadVentas() {
        return ventas.size();
    }
}

