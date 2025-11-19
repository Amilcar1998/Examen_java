package org.programacion;

import org.programacion.gui.frmLogin;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                frmLogin login = new frmLogin();
                login.setVisible(true);
            }
        });
    }
}