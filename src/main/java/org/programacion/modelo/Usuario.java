package org.programacion.modelo;

import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    private String usuario;
    private String contraseña;
    private String nombre;
    private String rol; // ADMIN, VENDEDOR

    public Usuario(String usuario, String contraseña, String nombre, String rol) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return usuario + " (" + nombre + ") - " + rol;
    }
}

