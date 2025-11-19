package org.programacion.gui;

import org.programacion.almacenamiento.GestorDatosV2;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class frmLogin extends JFrame {
    private JPanel contentPane;
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnLogin;
    private JLabel lblMensaje;

    private final GestorDatosV2 gestor = GestorDatosV2.getInstance();

    public frmLogin() {
        setContentPane(contentPane);
        setTitle("Login - Punto de Venta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        
        configuraEventos();
    }

    private void configuraEventos() {
        btnLogin.addActionListener(e -> autenticar());
        
        txtContrasena.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    autenticar();
                }
            }
        });
    }

    private void autenticar() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword());

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            lblMensaje.setText("Por favor ingresa usuario y contraseña");
            return;
        }

        if (gestor.autenticar(usuario, contrasena)) {
            lblMensaje.setText("");
            SwingUtilities.invokeLater(() -> {
                frmPrincipal ventana = new frmPrincipal();
                ventana.setVisible(true);
                dispose();
            });
        } else {
            lblMensaje.setText("Usuario o contraseña incorrectos");
            txtContrasena.setText("");
            txtContrasena.requestFocus();
        }
    }
}

