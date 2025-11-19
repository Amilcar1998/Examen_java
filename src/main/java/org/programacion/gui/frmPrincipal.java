package org.programacion.gui;

import javax.swing.*;
import java.util.*;
import org.programacion.utils.JsonManager;

public class frmPrincipal extends JFrame {
    private JPanel contentPane;
    private JButton btnGrafico;
    private JButton btnUsuario;
    private JButton btnAlmacen;
    private JButton btnVenta;
    private JButton btnMovimiento;
    private JButton btnAcceso;
    private JButton btnInfo;
    private JButton btnSalir;
    private JPanel panelContenido;
    private JsonManager manager;

    public frmPrincipal() {
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Dashboard - Sistema de Punto de Venta");
        setSize(1400, 800);
        setLocationRelativeTo(null);
        
        manager = JsonManager.getInstance();
        configuraEventos();
        mostrarDashboard();
    }
    
    private void configuraEventos() {
        btnGrafico.addActionListener(e -> mostrarDashboard());
        btnUsuario.addActionListener(e -> mostrarUsuarios());
        btnAlmacen.addActionListener(e -> mostrarProductos());
        btnVenta.addActionListener(e -> mostrarVentas());
        btnMovimiento.addActionListener(e -> mostrarMovimientos());
        btnAcceso.addActionListener(e -> mostrarAcceso());
        btnInfo.addActionListener(e -> mostrarInfo());
        btnSalir.addActionListener(e -> System.exit(0));
    }
    
    private void mostrarDashboard() {
        // TODO: Implementar lógica
    }
    
    private void mostrarProductos() {
        frmProducto producto = new frmProducto();
        producto.setVisible(true);
    }
    
    private void mostrarUsuarios() {
        // TODO: Implementar lógica
    }
    
    private void mostrarVentas() {
        // TODO: Implementar lógica
    }
    
    private void mostrarMovimientos() {
        // TODO: Implementar lógica
    }
    
    private void mostrarAcceso() {
        // TODO: Implementar lógica
    }
    
    private void mostrarInfo() {
        // TODO: Implementar lógica
    }
}
