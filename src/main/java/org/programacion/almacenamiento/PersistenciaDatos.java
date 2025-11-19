package org.programacion.almacenamiento;

import org.programacion.modelo.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class PersistenciaDatos {
    private static final String DATOS_DIR = "datos";
    private static final String PRODUCTOS_FILE = DATOS_DIR + "/productos.dat";
    private static final String VENTAS_FILE = DATOS_DIR + "/ventas.dat";
    private static final String KARDEX_FILE = DATOS_DIR + "/kardex.dat";
    private static final String USUARIOS_FILE = DATOS_DIR + "/usuarios.dat";

    static {
        crearDirectorio();
    }

    private static void crearDirectorio() {
        try {
            Path path = Paths.get(DATOS_DIR);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            System.err.println("Error creando directorio: " + e.getMessage());
        }
    }

    // PRODUCTOS
    public static void guardarProductos(List<Producto> productos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PRODUCTOS_FILE))) {
            oos.writeObject(productos);
        } catch (IOException e) {
            System.err.println("Error guardando productos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Producto> cargarProductos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PRODUCTOS_FILE))) {
            return (List<Producto>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    // VENTAS
    public static void guardarVentas(List<Venta> ventas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(VENTAS_FILE))) {
            oos.writeObject(ventas);
        } catch (IOException e) {
            System.err.println("Error guardando ventas: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Venta> cargarVentas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(VENTAS_FILE))) {
            return (List<Venta>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    // KARDEX
    public static void guardarKardex(List<MovimientoKardex> kardex) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(KARDEX_FILE))) {
            oos.writeObject(kardex);
        } catch (IOException e) {
            System.err.println("Error guardando kardex: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<MovimientoKardex> cargarKardex() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(KARDEX_FILE))) {
            return (List<MovimientoKardex>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    // USUARIOS
    public static void guardarUsuarios(List<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USUARIOS_FILE))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            System.err.println("Error guardando usuarios: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Usuario> cargarUsuarios() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USUARIOS_FILE))) {
            return (List<Usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}

