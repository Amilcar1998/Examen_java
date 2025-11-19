package org.programacion.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.*;
import org.programacion.utils.JsonManager;

public class frmMovimiento extends JFrame {
    private JPanel contentPane;
    private JTextField txtBuscar;
    private JComboBox cmbProducto;
    private JSpinner spnCantidad;
    private JComboBox cmbTipo;
    private JTextField txtMotivo;
    private JTable tblMovimientos;
    private JButton btnGuardar;
    private JButton btnEliminar;
    private JButton btnCancelar;
    private JTextField txtCantidad;
    private JTextField txtFecha;
    private JTextArea txtObservaciones;
    private JTable tblHistorial;
    private JLabel lblStockValue;
    private JButton btnRegistrar;
    private JButton btnExportar;
    private JButton btnLimpiar;
    private JButton btnActualizar;
    private DefaultTableModel modeloTabla;
    private DefaultTableModel modeloHistorial;
    private JsonManager manager;

    public frmMovimiento() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Movimientos de Almacén");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        
        manager = JsonManager.getInstance();
        initializeComponents();
        setContentPane(contentPane);
        cargarMovimientos();
        configuraEventos();
    }
    
    private void initializeComponents() {
        contentPane = new JPanel(new java.awt.BorderLayout());
        modeloTabla = new DefaultTableModel(
            new String[]{"Producto", "Cantidad", "Tipo", "Motivo", "Fecha"}, 0
        );
        modeloHistorial = new DefaultTableModel(
            new String[]{"Producto", "Cantidad", "Tipo", "Motivo", "Fecha"}, 0
        );
        
        // Panel principal
        JPanel panelMain = new JPanel(new java.awt.BorderLayout());
        
        // Panel superior con título
        JPanel panelTop = new JPanel(new java.awt.BorderLayout());
        JLabel lblTitulo = new JLabel("KARDEX - MOVIMIENTOS DE INVENTARIO");
        lblTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        panelTop.add(lblTitulo, java.awt.BorderLayout.WEST);
        panelMain.add(panelTop, java.awt.BorderLayout.NORTH);
        
        // Panel central
        JPanel panelCenter = new JPanel(new java.awt.BorderLayout());
        
        // Panel izquierdo (Formulario)
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new javax.swing.BoxLayout(panelForm, javax.swing.BoxLayout.Y_AXIS));
        panelForm.setPreferredSize(new java.awt.Dimension(300, 0));
        
        panelForm.add(new JLabel("Producto:"));
        cmbProducto = new JComboBox();
        cargarProductosEnCombo();
        panelForm.add(cmbProducto);
        
        panelForm.add(new JLabel("Tipo de Movimiento:"));
        cmbTipo = new JComboBox(new String[]{"Entrada", "Salida", "Ajuste", "Devolución"});
        panelForm.add(cmbTipo);
        
        panelForm.add(new JLabel("Cantidad:"));
        txtCantidad = new JTextField();
        panelForm.add(txtCantidad);
        
        panelForm.add(new JLabel("Fecha:"));
        txtFecha = new JTextField();
        txtFecha.setEditable(false);
        txtFecha.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        panelForm.add(txtFecha);
        
        panelForm.add(new JLabel("Observaciones:"));
        txtObservaciones = new JTextArea(3, 20);
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setWrapStyleWord(true);
        panelForm.add(new JScrollPane(txtObservaciones));
        
        panelForm.add(javax.swing.Box.createVerticalStrut(10));
        panelForm.add(new JLabel("Stock Disponible:"));
        lblStockValue = new JLabel("0 unidades");
        lblStockValue.setForeground(new java.awt.Color(0, 128, 0));
        panelForm.add(lblStockValue);
        
        panelForm.add(javax.swing.Box.createVerticalStrut(15));
        
        JPanel panelBotones = new JPanel(new java.awt.GridLayout(3, 2, 5, 5));
        btnRegistrar = new JButton("Registrar");
        btnExportar = new JButton("Exportar");
        btnLimpiar = new JButton("Limpiar");
        btnActualizar = new JButton("Refrescar");
        btnCancelar = new JButton("Cancelar");
        btnGuardar = btnRegistrar;
        btnEliminar = new JButton("Eliminar");
        
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnExportar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        panelForm.add(panelBotones);
        panelForm.add(javax.swing.Box.createVerticalGlue());
        
        panelCenter.add(panelForm, java.awt.BorderLayout.WEST);
        
        // Panel derecho (Tabla)
        tblHistorial = new JTable(modeloHistorial);
        tblHistorial.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(tblHistorial);
        panelCenter.add(scrollPane, java.awt.BorderLayout.CENTER);
        
        panelMain.add(panelCenter, java.awt.BorderLayout.CENTER);
        contentPane = panelMain;
    }
    
    private void cargarProductosEnCombo() {
        cmbProducto.removeAllItems();
        for (Map<String, Object> p : manager.getProductos()) {
            cmbProducto.addItem(p.get("codigo") + " - " + p.get("nombre"));
        }
    }
    
    private void cargarMovimientos() {
        modeloHistorial.setRowCount(0);
        for (Map<String, Object> m : manager.getMovimientos()) {
            modeloHistorial.addRow(new Object[]{
                m.get("producto"),
                m.get("cantidad"),
                m.get("tipo"),
                m.get("motivo"),
                m.get("fecha")
            });
        }
    }
    
    private void configuraEventos() {
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                guardarMovimiento();
            }
        });
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                eliminarMovimiento();
            }
        });
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                limpiarFormulario();
            }
        });
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                limpiarFormulario();
            }
        });
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                cargarMovimientos();
            }
        });
        
        cmbProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                actualizarStock();
            }
        });
    }
    
    private void actualizarStock() {
        if (cmbProducto.getSelectedIndex() >= 0) {
            String selected = cmbProducto.getSelectedItem().toString();
            String codigo = selected.split(" - ")[0];
            Map<String, Object> p = manager.obtenerProducto(codigo);
            if (p != null) {
                int stock = ((Number) p.get("stock")).intValue();
                lblStockValue.setText(stock + " unidades");
            }
        }
    }
    
    private void guardarMovimiento() {
        if (validarCampos()) {
            try {
                String selected = cmbProducto.getSelectedItem().toString();
                String producto = selected.split(" - ")[0];
                int cantidad = Integer.parseInt(txtCantidad.getText().trim());
                String tipo = cmbTipo.getSelectedItem().toString();
                String motivo = txtObservaciones.getText().trim();
                
                if (manager.obtenerProducto(producto) == null) {
                    JOptionPane.showMessageDialog(this, "El producto no existe", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                manager.agregarMovimiento(producto, cantidad, tipo, motivo);
                cargarMovimientos();
                limpiarFormulario();
                JOptionPane.showMessageDialog(this, "Movimiento registrado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void eliminarMovimiento() {
        if (tblHistorial.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un movimiento", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int respuesta = JOptionPane.showConfirmDialog(this, 
            "¿Estás seguro de que deseas eliminar este movimiento?", 
            "Confirmación", JOptionPane.YES_NO_OPTION);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            int row = tblHistorial.getSelectedRow();
            modeloHistorial.removeRow(row);
            limpiarFormulario();
            JOptionPane.showMessageDialog(this, "Movimiento eliminado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void limpiarFormulario() {
        cmbProducto.setSelectedIndex(0);
        txtCantidad.setText("");
        cmbTipo.setSelectedIndex(0);
        txtObservaciones.setText("");
        lblStockValue.setText("0 unidades");
    }
    
    private boolean validarCampos() {
        if (cmbProducto.getSelectedIndex() < 0 || 
            txtCantidad.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Producto y Cantidad son requeridos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
