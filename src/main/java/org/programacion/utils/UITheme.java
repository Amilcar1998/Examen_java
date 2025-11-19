package org.programacion.utils;

import java.awt.*;

public class UITheme {
    // Colores principales
    public static final Color COLOR_DARK_BG = new Color(30, 30, 30);      // Fondo oscuro principal
    public static final Color COLOR_CONTENT_BG = new Color(50, 50, 50);   // Fondo contenido
    public static final Color COLOR_BUTTON_BG = new Color(60, 60, 60);    // Botones
    public static final Color COLOR_BUTTON_HOVER = new Color(80, 80, 80); // Botones hover
    public static final Color COLOR_ACCENT = new Color(100, 200, 200);    // Cyan/Turquesa
    public static final Color COLOR_TEXT = new Color(255, 255, 255);      // Texto blanco
    public static final Color COLOR_TEXT_DARK = new Color(30, 30, 30);    // Texto oscuro
    public static final Color COLOR_LOGIN_BLUE = new Color(70, 130, 180); // Azul login
    public static final Color COLOR_ERROR = new Color(220, 0, 0);         // Rojo error
    public static final Color COLOR_SUCCESS = new Color(0, 128, 0);       // Verde éxito
    
    // Fuentes
    public static final Font FONT_TITLE = new Font("Arial", Font.BOLD, 32);
    public static final Font FONT_SUBTITLE = new Font("Arial", Font.BOLD, 24);
    public static final Font FONT_HEADER = new Font("Arial", Font.BOLD, 18);
    public static final Font FONT_LABEL = new Font("Arial", Font.BOLD, 12);
    public static final Font FONT_BUTTON = new Font("Arial", Font.BOLD, 13);
    public static final Font FONT_BUTTON_SMALL = new Font("Arial", Font.BOLD, 12);
    public static final Font FONT_TEXT = new Font("Arial", Font.PLAIN, 13);
    public static final Font FONT_SMALL = new Font("Arial", Font.PLAIN, 11);
    
    // Métodos auxiliares para aplicar estilos a componentes
    public static void stylizeButton(javax.swing.JButton btn, Color bg, Color fg) {
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(FONT_BUTTON);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
    }
    
    public static void stylizeLabel(javax.swing.JLabel lbl, Font font, Color fg) {
        lbl.setFont(font);
        lbl.setForeground(fg);
    }
    
    public static void stylizePanel(javax.swing.JPanel pnl, Color bg) {
        pnl.setBackground(bg);
        pnl.setOpaque(true);
    }
}
