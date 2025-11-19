package org.programacion.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import org.programacion.utils.JsonManager;

public class frmUsuario extends JFrame {
    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JTextField txtNombre;
    private JComboBox cmbRol;
    private JCheckBox chkActivo;
    private JTable tblUsuarios;
    private JButton btnGuardar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JButton btnCancelar;
    private DefaultTableModel modeloTabla;
    private JsonManager manager;

    public frmUsuario() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Usuarios");
        setSize(1050, 700);
        setLocationRelativeTo(null);
        
        manager = JsonManager.getInstance();
        initializeComponents();
        setContentPane(contentPane);
        cargarUsuarios();
        configuraEventos();
    }
    
    private void initializeComponents() {
        contentPane = new JPanel(new java.awt.BorderLayout());
        modeloTabla = new DefaultTableModel(
            new String[]{"Usuario", "Nombre", "Rol", "Activo"}, 0
        );
        
        // Panel principal
        JPanel panelMain = new JPanel(new java.awt.BorderLayout());
        
        // Panel superior con título
        JPanel panelTop = new JPanel(new java.awt.BorderLayout());
        JLabel lblTitulo = new JLabel("Gestión de Usuarios");
        panelTop.add(lblTitulo, java.awt.BorderLayout.WEST);
        panelMain.add(panelTop, java.awt.BorderLayout.NORTH);
        
        // Panel central
        JPanel panelCenter = new JPanel(new java.awt.BorderLayout());
        
        // Panel izquierdo (Formulario)
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new javax.swing.BoxLayout(panelForm, javax.swing.BoxLayout.Y_AXIS));
        panelForm.setPreferredSize(new java.awt.Dimension(300, 0));
        
        panelForm.add(new JLabel("Buscar:"));
        txtBuscar = new JTextField();
        panelForm.add(txtBuscar);
        
        panelForm.add(javax.swing.Box.createVerticalStrut(10));
        panelForm.add(new JLabel("Usuario:"));
        txtUsuario = new JTextField();
        panelForm.add(txtUsuario);
        
        panelForm.add(new JLabel("Contraseña:"));
        txtContrasena = new JPasswordField();
        panelForm.add(txtContrasena);
        
        panelForm.add(new JLabel("Nombre Completo:"));
        txtNombre = new JTextField();
        panelForm.add(txtNombre);
        
        panelForm.add(new JLabel("Rol:"));
        cmbRol = new JComboBox(new String[]{"Admin", "Usuario", "Gerente"});
        panelForm.add(cmbRol);
        
        chkActivo = new JCheckBox("Activo");
        panelForm.add(chkActivo);
        
        panelForm.add(javax.swing.Box.createVerticalStrut(15));
        
        JPanel panelBotones = new JPanel(new java.awt.GridLayout(1, 5, 5, 5));
        btnGuardar = new JButton("Guardar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");
        btnCancelar = new JButton("Cancelar");
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCancelar);
        panelForm.add(panelBotones);
        panelForm.add(javax.swing.Box.createVerticalGlue());
        
        panelCenter.add(panelForm, java.awt.BorderLayout.WEST);
        
        // Panel derecho (Tabla)
        tblUsuarios = new JTable(modeloTabla);
        tblUsuarios.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(tblUsuarios);
        panelCenter.add(scrollPane, java.awt.BorderLayout.CENTER);
        
        tblUsuarios.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblUsuarios.getSelectedRow() != -1) {
                int row = tblUsuarios.getSelectedRow();
                txtUsuario.setText(tblUsuarios.getValueAt(row, 0).toString());
                txtNombre.setText(tblUsuarios.getValueAt(row, 1).toString());
                cmbRol.setSelectedItem(tblUsuarios.getValueAt(row, 2).toString());
                chkActivo.setSelected(Boolean.parseBoolean(
                    tblUsuarios.getValueAt(row, 3).toString()
                ));
            }
        });
        
        panelMain.add(panelCenter, java.awt.BorderLayout.CENTER);
        contentPane = panelMain;
    }
    
    private void cargarUsuarios() {
        modeloTabla.setRowCount(0);
        for (Map<String, Object> u : manager.getUsuarios()) {
            modeloTabla.addRow(new Object[]{
                u.get("usuario"),
                u.get("nombre"),
                u.get("rol"),
                u.get("activo")
            });
        }
    }
    
    private void configuraEventos() {
        btnGuardar.addActionListener(e -> guardarUsuario());
        btnActualizar.addActionListener(e -> actualizarUsuario());
        btnEliminar.addActionListener(e -> eliminarUsuario());
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        btnCancelar.addActionListener(e -> dispose());
        
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
                filtrarUsuarios();
            }
        });
    }
    
    private void guardarUsuario() {
        if (validarCampos()) {
            try {
                String usuario = txtUsuario.getText().trim();
                String contrasena = new String(txtContrasena.getPassword());
                String nombre = txtNombre.getText().trim();
                String rol = cmbRol.getSelectedItem().toString();
                boolean activo = chkActivo.isSelected();
                
                if (manager.obtenerUsuario(usuario) != null) {
                    JOptionPane.showMessageDialog(this, "El usuario ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                manager.agregarUsuario(usuario, contrasena, nombre, rol, activo);
                cargarUsuarios();
                limpiarFormulario();
                JOptionPane.showMessageDialog(this, "Usuario guardado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void actualizarUsuario() {
        if (validarCampos()) {
            try {
                String usuario = txtUsuario.getText().trim();
                String nombre = txtNombre.getText().trim();
                String rol = cmbRol.getSelectedItem().toString();
                boolean activo = chkActivo.isSelected();
                
                if (manager.obtenerUsuario(usuario) == null) {
                    JOptionPane.showMessageDialog(this, "El usuario no existe", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                manager.actualizarUsuario(usuario, nombre, rol, activo);
                cargarUsuarios();
                limpiarFormulario();
                JOptionPane.showMessageDialog(this, "Usuario actualizado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void eliminarUsuario() {
        if (txtUsuario.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona un usuario", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String usuario = txtUsuario.getText().trim();
        if (manager.obtenerUsuario(usuario) == null) {
            JOptionPane.showMessageDialog(this, "El usuario no existe", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int respuesta = JOptionPane.showConfirmDialog(this, 
            "¿Estás seguro de que deseas eliminar este usuario?", 
            "Confirmación", JOptionPane.YES_NO_OPTION);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            manager.eliminarUsuario(usuario);
            cargarUsuarios();
            limpiarFormulario();
            JOptionPane.showMessageDialog(this, "Usuario eliminado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void limpiarFormulario() {
        txtUsuario.setText("");
        txtContrasena.setText("");
        txtNombre.setText("");
        cmbRol.setSelectedIndex(0);
        chkActivo.setSelected(false);
    }
    
    private void filtrarUsuarios() {
        String buscar = txtBuscar.getText().toLowerCase();
        if (buscar.isEmpty()) {
            cargarUsuarios();
            return;
        }
        
        modeloTabla.setRowCount(0);
        for (Map<String, Object> u : manager.getUsuarios()) {
            String usuario = u.get("usuario").toString().toLowerCase();
            if (usuario.contains(buscar)) {
                modeloTabla.addRow(new Object[]{
                    u.get("usuario"),
                    u.get("nombre"),
                    u.get("rol"),
                    u.get("activo")
                });
            }
        }
    }
    
    private boolean validarCampos() {
        if (txtUsuario.getText().trim().isEmpty() || 
            txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Usuario y Nombre son requeridos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
