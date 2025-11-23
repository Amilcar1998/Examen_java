package org.programacion.almacenamiento;

import org.programacion.modelo.*;
import org.programacion.utils.CargadorDatosPrueba;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestorDatosV2 {
    private static GestorDatosV2 instancia;
    private List<Producto> productos;
    private List<Venta> ventas;
    private List<MovimientoKardex> kardex;
    private List<Usuario> usuarios;
    private Usuario usuarioActual;

    private GestorDatosV2() {
        // Cargar datos persistidos o crear nuevos
        productos = PersistenciaDatos.cargarProductos();
        ventas = PersistenciaDatos.cargarVentas();
        kardex = PersistenciaDatos.cargarKardex();
        usuarios = PersistenciaDatos.cargarUsuarios();

        System.out.println("=== INICIO DE CARGA DE DATOS ===");
        System.out.println("Productos cargados desde .dat: " + productos.size());
        System.out.println("Usuarios cargados desde .dat: " + usuarios.size());

        // SIEMPRE intentar cargar y fusionar datos del JSON
        cargarYFusionarDatosJSON();

        System.out.println("Total final - Productos: " + productos.size() + ", Usuarios: " + usuarios.size());
        System.out.println("=================================");
    }

    private void cargarYFusionarDatosJSON() {
        // Cargar productos del JSON
        List<Producto> productosJSON = CargadorDatosPrueba.cargarProductosModelo();
        int productosAgregados = 0;

        for (Producto productoJSON : productosJSON) {
            // Solo agregar si no existe ya (comparar por código)
            boolean existe = productos.stream()
                .anyMatch(p -> p.getCodigo().equals(productoJSON.getCodigo()));

            if (!existe) {
                productos.add(productoJSON);
                productosAgregados++;
            }
        }

        if (productosAgregados > 0) {
            System.out.println("✓ Agregados " + productosAgregados + " productos nuevos desde JSON");
        } else {
            System.out.println("✓ No se agregaron productos nuevos (todos ya existen)");
        }

        // Cargar usuarios del JSON
        List<Usuario> usuariosJSON = CargadorDatosPrueba.cargarUsuariosModelo();
        int usuariosAgregados = 0;

        for (Usuario usuarioJSON : usuariosJSON) {
            // Solo agregar si no existe ya (comparar por nombre de usuario)
            boolean existe = usuarios.stream()
                .anyMatch(u -> u.getUsuario().equals(usuarioJSON.getUsuario()));

            if (!existe) {
                usuarios.add(usuarioJSON);
                usuariosAgregados++;
            }
        }

        if (usuariosAgregados > 0) {
            System.out.println("✓ Agregados " + usuariosAgregados + " usuarios nuevos desde JSON (password: 1234)");
        } else {
            System.out.println("✓ No se agregaron usuarios nuevos (todos ya existen)");
        }

        // Si agregamos datos nuevos, guardar
        if (productosAgregados > 0 || usuariosAgregados > 0) {
            guardarDatos();
            System.out.println("✓ Datos guardados en .dat");
        }
    }

    public static GestorDatosV2 getInstance() {
        if (instancia == null) {
            instancia = new GestorDatosV2();
        }
        return instancia;
    }


    // ===== MÉTODOS DE AUTENTICACIÓN =====
    public boolean autenticar(String usuario, String contraseña) {
        Optional<Usuario> usuarioOpt = usuarios.stream()
                .filter(u -> u.getUsuario().equals(usuario) && u.getContraseña().equals(contraseña))
                .findFirst();

        if (usuarioOpt.isPresent()) {
            usuarioActual = usuarioOpt.get();
            return true;
        }
        return false;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void cerrarSesion() {
        usuarioActual = null;
    }

    // ===== MÉTODOS DE PRODUCTOS =====
    public List<Producto> obtenerProductos() {
        return new ArrayList<>(productos);
    }

    public Optional<Producto> obtenerProductoPorCodigo(String codigo) {
        return productos.stream().filter(p -> p.getCodigo().equals(codigo)).findFirst();
    }

    public void agregarProducto(Producto producto) {
        if (!productos.stream().anyMatch(p -> p.getCodigo().equals(producto.getCodigo()))) {
            productos.add(producto);
            registrarMovimientoKardex(producto.getCodigo(), producto.getNombre(),
                    MovimientoKardex.TipoMovimiento.ENTRADA, producto.getStock(),
                    0, producto.getStock(), "INICIAL", usuarioActual.getUsuario(), producto.getPrecio());
            guardarDatos();
        }
    }

    public void actualizarProducto(Producto producto) {
        Optional<Producto> existente = obtenerProductoPorCodigo(producto.getCodigo());
        existente.ifPresent(p -> {
            int stockAnterior = p.getStock();
            p.setNombre(producto.getNombre());
            p.setPrecio(producto.getPrecio());
            p.setStock(producto.getStock());

            // Registrar ajuste si hay cambio en stock
            if (stockAnterior != producto.getStock()) {
                registrarMovimientoKardex(producto.getCodigo(), producto.getNombre(),
                        MovimientoKardex.TipoMovimiento.AJUSTE, Math.abs(producto.getStock() - stockAnterior),
                        stockAnterior, producto.getStock(), "AJUSTE", usuarioActual.getUsuario(), producto.getPrecio());
            }
            guardarDatos();
        });
    }

    public void eliminarProducto(String codigo) {
        productos.removeIf(p -> p.getCodigo().equals(codigo));
        guardarDatos();
    }

    // ===== MÉTODOS DE VENTAS =====
    public List<Venta> obtenerVentas() {
        return new ArrayList<>(ventas);
    }

    public void registrarVenta(Venta venta) {
        if (!venta.getItems().isEmpty()) {
            // Actualizar stock de productos y registrar movimientos
            venta.getItems().forEach(item -> {
                Producto p = item.getProducto();
                int stockAnterior = p.getStock();
                p.setStock(p.getStock() - item.getCantidad());

                // Registrar movimiento en kardex
                registrarMovimientoKardex(p.getCodigo(), p.getNombre(),
                        MovimientoKardex.TipoMovimiento.SALIDA, item.getCantidad(),
                        stockAnterior, p.getStock(), venta.getId(), usuarioActual.getUsuario(), p.getPrecio());
            });
            ventas.add(venta);
            guardarDatos();
        }
    }

    public double obtenerTotalVentas() {
        return ventas.stream().mapToDouble(Venta::getTotal).sum();
    }

    public int obtenerCantidadVentas() {
        return ventas.size();
    }

    // ===== MÉTODOS DE KARDEX =====
    public List<MovimientoKardex> obtenerKardex() {
        return new ArrayList<>(kardex);
    }

    public List<MovimientoKardex> obtenerKardexPorProducto(String codigo) {
        List<MovimientoKardex> resultado = new ArrayList<>();
        for (MovimientoKardex m : kardex) {
            if (m.getCodigo().equals(codigo)) {
                resultado.add(m);
            }
        }
        return resultado;
    }

    private void registrarMovimientoKardex(String codigo, String producto,
                                          MovimientoKardex.TipoMovimiento tipo, int cantidad,
                                          int stockAnterior, int stockNuevo, String referencia,
                                          String usuario, double precioUnitario) {
        MovimientoKardex movimiento = new MovimientoKardex(codigo, producto, tipo, cantidad,
                stockAnterior, stockNuevo, referencia, usuario, precioUnitario);
        kardex.add(movimiento);
        guardarDatos();
    }

    // ===== MÉTODOS DE USUARIOS =====
    public List<Usuario> obtenerUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public List<String> obtenerRoles() {
        List<String> roles = new ArrayList<>();
        roles.add("Administrador");
        roles.add("Vendedor");
        roles.add("Almacenero");
        roles.add("Gerente");
        return roles;
    }

    public void agregarUsuario(Usuario usuario) {
        if (!usuarios.stream().anyMatch(u -> u.getUsuario().equals(usuario.getUsuario()))) {
            usuarios.add(usuario);
            guardarDatos();
        }
    }

    public void actualizarUsuario(Usuario usuario) {
        Optional<Usuario> existente = usuarios.stream()
                .filter(u -> u.getUsuario().equals(usuario.getUsuario()))
                .findFirst();
        existente.ifPresent(u -> {
            if (usuario.getContraseña() != null && !usuario.getContraseña().isEmpty()) {
                u.setContraseña(usuario.getContraseña());
            }
            u.setNombre(usuario.getNombre());
            u.setRol(usuario.getRol());
            guardarDatos();
        });
    }

    public void eliminarUsuario(String usuario) {
        usuarios.removeIf(u -> u.getUsuario().equals(usuario));
        guardarDatos();
    }

    // ===== PERSISTENCIA =====
    public void guardarDatos() {
        PersistenciaDatos.guardarProductos(productos);
        PersistenciaDatos.guardarVentas(ventas);
        PersistenciaDatos.guardarKardex(kardex);
        PersistenciaDatos.guardarUsuarios(usuarios);
    }
}

