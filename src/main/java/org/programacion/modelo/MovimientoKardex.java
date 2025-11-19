package org.programacion.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MovimientoKardex implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum TipoMovimiento {
        ENTRADA("ENTRADA"),
        SALIDA("SALIDA"),
        AJUSTE("AJUSTE");

        private final String descripcion;

        TipoMovimiento(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    private String codigo;
    private String producto;
    private TipoMovimiento tipo;
    private int cantidad;
    private int stockAnterior;
    private int stockNuevo;
    private LocalDateTime fecha;
    private String referencia; // ID de venta, compra, etc
    private String usuario;
    private double precioUnitario;

    public MovimientoKardex(String codigo, String producto, TipoMovimiento tipo, int cantidad,
                           int stockAnterior, int stockNuevo, String referencia, String usuario, double precioUnitario) {
        this.codigo = codigo;
        this.producto = producto;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.stockAnterior = stockAnterior;
        this.stockNuevo = stockNuevo;
        this.fecha = LocalDateTime.now();
        this.referencia = referencia;
        this.usuario = usuario;
        this.precioUnitario = precioUnitario;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getProducto() {
        return producto;
    }

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getStockAnterior() {
        return stockAnterior;
    }

    public int getStockNuevo() {
        return stockNuevo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getUsuario() {
        return usuario;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("[%s] %s - %s x%d (Stock: %d → %d) - $%.2f",
                codigo, producto, tipo.getDescripcion(), cantidad, stockAnterior, stockNuevo, precioUnitario);
    }
}

