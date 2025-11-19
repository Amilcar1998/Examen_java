package org.programacion.utils;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class JsonManager {
    private static final String JSON_PATH = "src/main/resources/datos_prueba.json";
    private static JsonManager instance;
    private Map<String, Object> datos;

    private JsonManager() {
        cargarDatos();
    }

    public static JsonManager getInstance() {
        if (instance == null) {
            instance = new JsonManager();
        }
        return instance;
    }

    private void cargarDatos() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(JSON_PATH)));
            datos = parseJson(content);
        } catch (IOException e) {
            datos = new HashMap<>();
            System.err.println("Error al cargar JSON: " + e.getMessage());
        }
    }

    public void guardarDatos() {
        try {
            String json = toJson(datos);
            Files.write(Paths.get(JSON_PATH), json.getBytes());
        } catch (IOException e) {
            System.err.println("Error al guardar JSON: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getProductos() {
        return (List<Map<String, Object>>) datos.getOrDefault("productos", new ArrayList<>());
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getUsuarios() {
        return (List<Map<String, Object>>) datos.getOrDefault("usuarios", new ArrayList<>());
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getMovimientos() {
        return (List<Map<String, Object>>) datos.getOrDefault("movimientos", new ArrayList<>());
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getVentas() {
        return (List<Map<String, Object>>) datos.getOrDefault("ventas", new ArrayList<>());
    }

    public void agregarProducto(String codigo, String nombre, double precio, int stock) {
        Map<String, Object> producto = new HashMap<>();
        producto.put("codigo", codigo);
        producto.put("nombre", nombre);
        producto.put("precio", precio);
        producto.put("stock", stock);
        getProductos().add(producto);
        guardarDatos();
    }

    public void actualizarProducto(String codigo, String nombre, double precio, int stock) {
        for (Map<String, Object> p : getProductos()) {
            if (p.get("codigo").toString().equals(codigo)) {
                p.put("nombre", nombre);
                p.put("precio", precio);
                p.put("stock", stock);
                guardarDatos();
                return;
            }
        }
    }

    public void eliminarProducto(String codigo) {
        getProductos().removeIf(p -> p.get("codigo").toString().equals(codigo));
        guardarDatos();
    }

    public Map<String, Object> obtenerProducto(String codigo) {
        for (Map<String, Object> p : getProductos()) {
            if (p.get("codigo").toString().equals(codigo)) {
                return p;
            }
        }
        return null;
    }

    public void agregarUsuario(String usuario, String contrasena, String nombre, String rol, boolean activo) {
        Map<String, Object> user = new HashMap<>();
        user.put("usuario", usuario);
        user.put("contraseña", contrasena);
        user.put("nombre", nombre);
        user.put("rol", rol);
        user.put("activo", activo);
        getUsuarios().add(user);
        guardarDatos();
    }

    public void actualizarUsuario(String usuario, String nombre, String rol, boolean activo) {
        for (Map<String, Object> u : getUsuarios()) {
            if (u.get("usuario").toString().equals(usuario)) {
                u.put("nombre", nombre);
                u.put("rol", rol);
                u.put("activo", activo);
                guardarDatos();
                return;
            }
        }
    }

    public void eliminarUsuario(String usuario) {
        List<Map<String, Object>> usuarios = getUsuarios();
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).get("usuario").toString().equals(usuario)) {
                usuarios.remove(i);
                guardarDatos();
                return;
            }
        }
    }

    public Map<String, Object> obtenerUsuario(String usuario) {
        for (Map<String, Object> u : getUsuarios()) {
            if (u.get("usuario").toString().equals(usuario)) {
                return u;
            }
        }
        return null;
    }

    public void agregarMovimiento(String producto, int cantidad, String tipo, String motivo) {
        Map<String, Object> movimiento = new HashMap<>();
        movimiento.put("producto", producto);
        movimiento.put("cantidad", cantidad);
        movimiento.put("tipo", tipo);
        movimiento.put("motivo", motivo);
        movimiento.put("fecha", System.currentTimeMillis());
        getMovimientos().add(movimiento);
        guardarDatos();
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> parseJson(String json) {
        Map<String, Object> resultado = new HashMap<>();
        json = json.trim();
        if (json.startsWith("{") && json.endsWith("}")) {
            json = json.substring(1, json.length() - 1);
            String[] partes = json.split("\",\\s*\"");
            for (String parte : partes) {
                if (parte.contains(":")) {
                    String[] kv = parte.split(":", 2);
                    String clave = kv[0].replace("\"", "").trim();
                    String valor = kv[1].trim();
                    if (valor.startsWith("[")) {
                        resultado.put(clave, parseArray(valor));
                    }
                }
            }
        }
        return resultado;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> parseArray(String json) {
        List<Map<String, Object>> lista = new ArrayList<>();
        json = json.replaceAll("\\[|\\]", "").trim();
        
        int nivel = 0;
        StringBuilder item = new StringBuilder();
        for (char c : json.toCharArray()) {
            if (c == '{') {
                nivel++;
                item.append(c);
            } else if (c == '}') {
                nivel--;
                item.append(c);
                if (nivel == 0) {
                    Map<String, Object> obj = parseObject(item.toString());
                    if (!obj.isEmpty()) {
                        lista.add(obj);
                    }
                    item = new StringBuilder();
                }
            } else if (nivel > 0) {
                item.append(c);
            }
        }
        return lista;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> parseObject(String json) {
        Map<String, Object> obj = new HashMap<>();
        json = json.replaceAll("\\{|\\}", "").trim();
        
        int nivel = 0;
        StringBuilder actual = new StringBuilder();
        for (char c : json.toCharArray()) {
            if (c == ':' && nivel == 0) {
                actual.append('|');
            } else if (c == ',' && nivel == 0) {
                String[] kv = actual.toString().split("\\|");
                if (kv.length == 2) {
                    String k = kv[0].replace("\"", "").trim();
                    String v = kv[1].trim();
                    obj.put(k, parseValue(v));
                }
                actual = new StringBuilder();
            } else {
                if (c == '{' || c == '[') nivel++;
                if (c == '}' || c == ']') nivel--;
                actual.append(c);
            }
        }
        
        if (actual.length() > 0) {
            String[] kv = actual.toString().split("\\|");
            if (kv.length == 2) {
                String k = kv[0].replace("\"", "").trim();
                String v = kv[1].trim();
                obj.put(k, parseValue(v));
            }
        }
        return obj;
    }

    private Object parseValue(String v) {
        v = v.trim();
        if (v.startsWith("\"") && v.endsWith("\"")) {
            return v.substring(1, v.length() - 1);
        } else if (v.equals("true")) {
            return true;
        } else if (v.equals("false")) {
            return false;
        } else if (v.equals("null")) {
            return null;
        } else {
            try {
                if (v.contains(".")) {
                    return Double.parseDouble(v);
                }
                return Integer.parseInt(v);
            } catch (NumberFormatException e) {
                return v;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private String toJson(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder("{\n");
        List<String> keys = new ArrayList<>(map.keySet());
        
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            Object value = map.get(key);
            sb.append("  \"").append(key).append("\": ");
            
            if (value instanceof List) {
                sb.append(arrayToJson((List<?>) value));
            } else {
                sb.append(valueToJson(value));
            }
            
            if (i < keys.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        
        sb.append("}");
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    private String arrayToJson(List<?> list) {
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < list.size(); i++) {
            Object item = list.get(i);
            if (item instanceof Map) {
                String json = toJson((Map<String, Object>) item);
                String indented = json.replace("\n", "\n    ");
                sb.append("    ").append(indented);
            } else {
                sb.append("    ").append(valueToJson(item));
            }
            if (i < list.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append("  ]");
        return sb.toString();
    }

    private String valueToJson(Object value) {
        if (value instanceof String) {
            return "\"" + value.toString().replace("\"", "\\\"") + "\"";
        } else if (value instanceof Boolean) {
            return value.toString();
        } else if (value instanceof Number) {
            return value.toString();
        } else {
            return "null";
        }
    }
}
