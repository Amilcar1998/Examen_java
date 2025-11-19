package org.programacion.controlador;

import org.programacion.modelo.Venta;
import org.programacion.almacenamiento.GestorDatosV2;
import java.util.List;

public class ControladorVenta {
    private final GestorDatosV2 gestorDatos;

    public ControladorVenta() {
        this.gestorDatos = GestorDatosV2.getInstance();
    }
    
    public boolean guardarVenta(Venta venta) {
        if (venta == null || venta.getItems().isEmpty()) {
            return false;
        }
        gestorDatos.registrarVenta(venta);
        return true;
    }
    
    public List<Venta> obtenerVentas() {
        return gestorDatos.obtenerVentas();
    }
    
    public double calcularTotal(double subtotal, double descuento) {
        if (descuento < 0 || descuento > 100) {
            descuento = 0;
        }
        double descuentoMonto = (subtotal * descuento) / 100;
        return subtotal - descuentoMonto;
    }
}
