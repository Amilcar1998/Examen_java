package org.programacion.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class frmVenta extends JFrame {
    private JPanel contentPane;
    private JComboBox cmbProducto;
    private JTextField txtCantidad;
    private JTextField txtPrecio;
    private JTextField txtDescuento;
    private JTextField txtSubtotal;
    private JTextField txtTotal;
    private JTable tblItems;
    private JButton btnAgregar;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JButton btnCancelar;

    public frmVenta() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Punto de Venta");
        setSize(1100, 700);
        
        if (contentPane == null) {
            contentPane = buildFallbackPanel();
        }
        setContentPane(contentPane);
        setLocationRelativeTo(null);
    }
    
    private JPanel buildFallbackPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        // Panel superior
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(new Color(200, 100, 50));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        JLabel lblTitulo = new JLabel("PUNTO DE VENTA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        panelSuperior.add(lblTitulo, BorderLayout.WEST);
        
        panel.add(panelSuperior, BorderLayout.NORTH);
        
        // Panel central
        JPanel panelContenido = new JPanel(new BorderLayout(10, 10));
        panelContenido.setBackground(Color.WHITE);
        panelContenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Panel izquierdo: Selección
        JPanel panelSeleccion = crearPanelSeleccion();
        panelContenido.add(panelSeleccion, BorderLayout.WEST);
        
        // Panel derecho: Carrito
        JPanel panelCarrito = crearPanelCarrito();
        panelContenido.add(panelCarrito, BorderLayout.CENTER);
        
        panel.add(panelContenido, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelSeleccion() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 250, 240));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 180, 140), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        panel.setPreferredSize(new Dimension(320, 0));
        
        JLabel lblSeleccion = new JLabel("Seleccionar Productos");
        lblSeleccion.setFont(new Font("Arial", Font.BOLD, 14));
        lblSeleccion.setForeground(new Color(200, 100, 50));
        lblSeleccion.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblSeleccion);
        panel.add(Box.createVerticalStrut(15));
        
        // Producto
        JLabel lblProducto = new JLabel("Producto:");
        lblProducto.setFont(new Font("Arial", Font.BOLD, 11));
        lblProducto.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblProducto);
        
        cmbProducto = new JComboBox();
        cmbProducto.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        cmbProducto.setBackground(Color.WHITE);
        panel.add(cmbProducto);
        panel.add(Box.createVerticalStrut(12));
        
        // Cantidad
        panel.add(crearCampo("Cantidad:", "txtCantidad"));
        panel.add(Box.createVerticalStrut(10));
        
        // Precio
        panel.add(crearCampo("Precio Unitario:", "txtPrecio"));
        panel.add(Box.createVerticalStrut(10));
        
        // Descuento
        panel.add(crearCampo("Descuento %:", "txtDescuento"));
        panel.add(Box.createVerticalStrut(10));
        
        // Subtotal
        panel.add(crearCampo("Subtotal:", "txtSubtotal"));
        panel.add(Box.createVerticalStrut(10));
        
        // Total
        panel.add(crearCampo("Total:", "txtTotal"));
        panel.add(Box.createVerticalStrut(20));
        
        btnAgregar = crearBoton("➕ Agregar al Carrito", new Color(50, 150, 50));
        btnAgregar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        panel.add(btnAgregar);
        panel.add(Box.createVerticalGlue());
        
        return panel;
    }
    
    private JPanel crearPanelCarrito() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(Color.WHITE);
        
        JLabel lblCarrito = new JLabel("🛒 Carrito de Compras");
        lblCarrito.setFont(new Font("Arial", Font.BOLD, 14));
        lblCarrito.setForeground(new Color(200, 100, 50));
        panel.add(lblCarrito, BorderLayout.NORTH);
        
        // Tabla
        tblItems = new JTable(new DefaultTableModel(
            new String[]{"Producto", "Cantidad", "Precio", "Descuento", "Total"}, 0));
        tblItems.setFont(new Font("Arial", Font.PLAIN, 11));
        tblItems.setRowHeight(25);
        tblItems.getTableHeader().setBackground(new Color(200, 100, 50));
        tblItems.getTableHeader().setForeground(Color.WHITE);
        tblItems.getTableHeader().setFont(new Font("Arial", Font.BOLD, 11));
        tblItems.setSelectionBackground(new Color(255, 200, 150));
        
        JScrollPane scrollPane = new JScrollPane(tblItems);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de totales
        JPanel panelTotales = new JPanel(new GridLayout(3, 2, 10, 10));
        panelTotales.setBackground(new Color(245, 240, 235));
        panelTotales.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 150, 100)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel lblSubtotal = new JLabel("Subtotal:");
        lblSubtotal.setFont(new Font("Arial", Font.BOLD, 11));
        JLabel valSubtotal = new JLabel("$0.00");
        valSubtotal.setFont(new Font("Arial", Font.PLAIN, 11));
        panelTotales.add(lblSubtotal);
        panelTotales.add(valSubtotal);
        
        JLabel lblImpuesto = new JLabel("Impuesto (10%):");
        lblImpuesto.setFont(new Font("Arial", Font.BOLD, 11));
        JLabel valImpuesto = new JLabel("$0.00");
        valImpuesto.setFont(new Font("Arial", Font.PLAIN, 11));
        panelTotales.add(lblImpuesto);
        panelTotales.add(valImpuesto);
        
        JLabel lblTotal = new JLabel("TOTAL:");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 12));
        lblTotal.setForeground(new Color(200, 100, 50));
        JLabel valTotal = new JLabel("$0.00");
        valTotal.setFont(new Font("Arial", Font.BOLD, 12));
        valTotal.setForeground(new Color(200, 100, 50));
        panelTotales.add(lblTotal);
        panelTotales.add(valTotal);
        
        panel.add(panelTotales, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel crearCampo(String etiqueta, String id) {
        JPanel panelCampo = new JPanel(new BorderLayout());
        panelCampo.setBackground(new Color(255, 250, 240));
        panelCampo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        
        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(new Font("Arial", Font.BOLD, 11));
        
        JTextField txt = new JTextField();
        txt.setFont(new Font("Arial", Font.PLAIN, 11));
        txt.setBackground(Color.WHITE);
        txt.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        
        if (id.equals("txtCantidad")) txtCantidad = txt;
        else if (id.equals("txtPrecio")) txtPrecio = txt;
        else if (id.equals("txtDescuento")) txtDescuento = txt;
        else if (id.equals("txtSubtotal")) txtSubtotal = txt;
        else if (id.equals("txtTotal")) txtTotal = txt;
        
        panelCampo.add(lbl, BorderLayout.NORTH);
        panelCampo.add(txt, BorderLayout.CENTER);
        
        return panelCampo;
    }
    
    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 11));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(color.brighter());
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(color);
            }
        });
        
        return btn;
    }
}
