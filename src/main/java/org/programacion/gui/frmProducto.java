package org.programacion.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import org.programacion.utils.JsonManager;

public class frmProducto extends JFrame {
    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private JTable tblProductos;
    private JButton btnGuardar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnCancelar;
    private DefaultTableModel modeloTabla;
    private JsonManager manager;

    public frmProducto() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Productos");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        
        manager = JsonManager.getInstance();
        initializeComponents();
        setContentPane(contentPane);
        cargarProductos();
        configuraEventos();
    }
    
    private void initializeComponents() {
        contentPane = new JPanel();
        modeloTabla = new DefaultTableModel(new String[]{"Código", "Nombre", "Precio", "Stock"}, 0);
        
        // Panel principal
        JPanel panelMain = new JPanel(new java.awt.BorderLayout());
        
        // Panel superior con título
        JPanel panelTop = new JPanel(new java.awt.BorderLayout());
        JLabel lblTitulo = new JLabel("Gestión de Productos");
        panelTop.add(lblTitulo, java.awt.BorderLayout.WEST);
        panelMain.add(panelTop, java.awt.BorderLayout.NORTH);
        
        // Panel central con forma y tabla
        JPanel panelCenter = new JPanel(new java.awt.BorderLayout());
        
        // Panel izquierdo (Formulario)
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new javax.swing.BoxLayout(panelForm, javax.swing.BoxLayout.Y_AXIS));
        panelForm.setPreferredSize(new java.awt.Dimension(280, 0));
        
        panelForm.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        panelForm.add(txtCodigo);
        
        panelForm.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelForm.add(txtNombre);
        
        panelForm.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panelForm.add(txtPrecio);
        
        panelForm.add(new JLabel("Stock:"));
        txtStock = new JTextField();
        panelForm.add(txtStock);
        
        panelForm.add(javax.swing.Box.createVerticalStrut(20));
        
        JPanel panelBotones = new JPanel(new java.awt.GridLayout(2, 2, 5, 5));
        btnGuardar = new JButton("Guardar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnCancelar = new JButton("Cancelar");
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        panelForm.add(panelBotones);
        panelForm.add(javax.swing.Box.createVerticalGlue());
        
        panelCenter.add(panelForm, java.awt.BorderLayout.WEST);
        
        // Panel derecho (Tabla)
        tblProductos = new JTable(modeloTabla);
        tblProductos.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(tblProductos);
        panelCenter.add(scrollPane, java.awt.BorderLayout.CENTER);
        
        tblProductos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblProductos.getSelectedRow() != -1) {
                int row = tblProductos.getSelectedRow();
                txtCodigo.setText(tblProductos.getValueAt(row, 0).toString());
                txtNombre.setText(tblProductos.getValueAt(row, 1).toString());
                txtPrecio.setText(tblProductos.getValueAt(row, 2).toString());
                txtStock.setText(tblProductos.getValueAt(row, 3).toString());
            }
        });
        
        panelMain.add(panelCenter, java.awt.BorderLayout.CENTER);
        contentPane = panelMain;
    }
    
    private void cargarProductos() {
        modeloTabla.setRowCount(0);
        for (Map<String, Object> p : manager.getProductos()) {
            modeloTabla.addRow(new Object[]{
                p.get("codigo"),
                p.get("nombre"),
                p.get("precio"),
                p.get("stock")
            });
        }
    }
    
    private void configuraEventos() {
        btnGuardar.addActionListener(e -> guardarProducto());
        btnActualizar.addActionListener(e -> actualizarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());
        btnCancelar.addActionListener(e -> limpiarFormulario());
    }
    
    private void guardarProducto() {
        if (validarCampos()) {
            try {
                String codigo = txtCodigo.getText().trim();
                String nombre = txtNombre.getText().trim();
                double precio = Double.parseDouble(txtPrecio.getText().trim());
                int stock = Integer.parseInt(txtStock.getText().trim());
                
                if (manager.obtenerProducto(codigo) != null) {
                    JOptionPane.showMessageDialog(this, "El código ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                manager.agregarProducto(codigo, nombre, precio, stock);
                cargarProductos();
                limpiarFormulario();
                JOptionPane.showMessageDialog(this, "Producto guardado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Precio y Stock deben ser números", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void actualizarProducto() {
        if (validarCampos()) {
            try {
                String codigo = txtCodigo.getText().trim();
                String nombre = txtNombre.getText().trim();
                double precio = Double.parseDouble(txtPrecio.getText().trim());
                int stock = Integer.parseInt(txtStock.getText().trim());
                
                if (manager.obtenerProducto(codigo) == null) {
                    JOptionPane.showMessageDialog(this, "El producto no existe", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                manager.actualizarProducto(codigo, nombre, precio, stock);
                cargarProductos();
                limpiarFormulario();
                JOptionPane.showMessageDialog(this, "Producto actualizado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Precio y Stock deben ser números", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void eliminarProducto() {
        if (txtCodigo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona un producto", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String codigo = txtCodigo.getText().trim();
        if (manager.obtenerProducto(codigo) == null) {
            JOptionPane.showMessageDialog(this, "El producto no existe", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int respuesta = JOptionPane.showConfirmDialog(this, 
            "¿Estás seguro de que deseas eliminar este producto?", 
            "Confirmación", JOptionPane.YES_NO_OPTION);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            manager.eliminarProducto(codigo);
            cargarProductos();
            limpiarFormulario();
            JOptionPane.showMessageDialog(this, "Producto eliminado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void limpiarFormulario() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        if (txtBuscar != null) txtBuscar.setText("");
    }
    
    private boolean validarCampos() {
        if (txtCodigo.getText().trim().isEmpty() || 
            txtNombre.getText().trim().isEmpty() || 
            txtPrecio.getText().trim().isEmpty() || 
            txtStock.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son requeridos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
