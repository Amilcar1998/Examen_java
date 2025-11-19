package org.programacion.controlador;

import org.programacion.modelo.Usuario;
import org.programacion.almacenamiento.GestorDatosV2;
import java.util.List;

public class ControladorUsuario {
    private final GestorDatosV2 gestorDatos;

    public ControladorUsuario() {
        this.gestorDatos = GestorDatosV2.getInstance();
    }
    
    public boolean guardarUsuario(String usuario, String contrasena, String nombre, String rol) {
        if (usuario == null || usuario.isEmpty() || contrasena == null || contrasena.isEmpty() ||
            nombre == null || nombre.isEmpty() || rol == null || rol.isEmpty()) {
            return false;
        }
        
        try {
            Usuario nuevoUsuario = new Usuario(usuario, contrasena, nombre, rol);
            gestorDatos.agregarUsuario(nuevoUsuario);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean actualizarUsuario(String usuario, String contrasena, String nombre, String rol) {
        if (usuario == null || usuario.isEmpty() || contrasena == null || contrasena.isEmpty() ||
            nombre == null || nombre.isEmpty() || rol == null || rol.isEmpty()) {
            return false;
        }
        
        try {
            Usuario usuarioActualizado = new Usuario(usuario, contrasena, nombre, rol);
            gestorDatos.actualizarUsuario(usuarioActualizado);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean eliminarUsuario(String usuario) {
        if (usuario == null || usuario.isEmpty()) {
            return false;
        }
        try {
            gestorDatos.eliminarUsuario(usuario);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean autenticar(String usuario, String contrasena) {
        if (usuario == null || usuario.isEmpty() || contrasena == null || contrasena.isEmpty()) {
            return false;
        }
        return gestorDatos.autenticar(usuario, contrasena);
    }
    
    public List<Usuario> obtenerUsuarios() {
        return gestorDatos.obtenerUsuarios();
    }
}
