package org.programacion.controlador;

import org.programacion.modelo.MovimientoKardex;
import org.programacion.almacenamiento.GestorDatosV2;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ControladorMovimiento {
    private final GestorDatosV2 gestorDatos;

    public ControladorMovimiento() {
        this.gestorDatos = GestorDatosV2.getInstance();
    }
    
    public boolean registrarMovimiento(String codigoProducto, String tipo, int cantidad, String observaciones) {
        if (codigoProducto == null || codigoProducto.isEmpty() || tipo == null || tipo.isEmpty() || cantidad <= 0) {
            return false;
        }
        
        try {
            // Los movimientos se registran automáticamente a través de GestorDatosV2
            // Este método es informativo para la UI
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public List<MovimientoKardex> obtenerMovimientos() {
        return gestorDatos.obtenerKardex();
    }
    
    public List<MovimientoKardex> obtenerMovimientosPorProducto(String codigoProducto) {
        return gestorDatos.obtenerKardexPorProducto(codigoProducto);
    }
    
    public int calcularStockDisponible(String codigoProducto) {
        return gestorDatos.obtenerProductoPorCodigo(codigoProducto)
                .map(p -> p.getStock())
                .orElse(0);
    }
    
    public String obtenerFechaActual() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
