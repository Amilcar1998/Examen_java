package org.programacion.modelo;

import java.io.Serializable;

public class ItemVenta implements Serializable {
    private Producto producto;
    private int cantidad;
    private double subtotal;

    public ItemVenta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = producto.getPrecio() * cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = producto.getPrecio() * cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return producto.getNombre() + " x" + cantidad + " = $" + String.format("%.2f", subtotal);
    }
}

