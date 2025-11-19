package org.programacion.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Venta implements Serializable {
    private String id;
    private LocalDateTime fecha;
    private List<ItemVenta> items;
    private double total;
    private double descuento;

    public Venta() {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.fecha = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.total = 0;
        this.descuento = 0;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public List<ItemVenta> getItems() {
        return items;
    }

    public void agregarItem(ItemVenta item) {
        items.add(item);
        calcularTotal();
    }

    public void removerItem(int indice) {
        if (indice >= 0 && indice < items.size()) {
            items.remove(indice);
            calcularTotal();
        }
    }

    public double getTotal() {
        return total;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
        calcularTotal();
    }

    private void calcularTotal() {
        double subtotal = items.stream().mapToDouble(ItemVenta::getSubtotal).sum();
        this.total = subtotal - descuento;
    }

    public double getSubtotal() {
        return items.stream().mapToDouble(ItemVenta::getSubtotal).sum();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Venta [" + id + "] - " + fecha.format(formatter) + " - Total: $" + String.format("%.2f", total);
    }
}

