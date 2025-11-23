package org.programacion.utils;

import org.programacion.modelo.Producto;
import org.programacion.modelo.Usuario;

import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class CargadorDatosPrueba {
    
    // Método para cargar productos como objetos Producto
    public static List<Producto> cargarProductosModelo() {
        List<Producto> productos = new ArrayList<>();

        try {
            Map<String, Object> datos = parsearJSON();
            List<Map<String, Object>> prodList = (List<Map<String, Object>>) datos.get("productos");

            if (prodList != null) {
                for (Map<String, Object> prod : prodList) {
                    String codigo = (String) prod.get("codigo");
                    String nombre = (String) prod.get("nombre");
                    Double precio = getDouble(prod.get("precio"));
                    Integer stock = getInteger(prod.get("stock"));

                    if (codigo != null && nombre != null && precio != null && stock != null) {
                        productos.add(new Producto(codigo, nombre, precio, stock));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productos;
    }

    // Método para cargar usuarios como objetos Usuario
    public static List<Usuario> cargarUsuariosModelo() {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            Map<String, Object> datos = parsearJSON();
            List<Map<String, Object>> userList = (List<Map<String, Object>>) datos.get("usuarios");

            if (userList != null) {
                for (Map<String, Object> user : userList) {
                    String usuarioNombre = (String) user.get("usuario");
                    String password = (String) user.get("password");
                    // Si no hay password en el JSON, usar "1234" por defecto
                    if (password == null || password.isEmpty()) {
                        password = "1234";
                    }
                    String nombre = (String) user.get("nombre");
                    String rol = (String) user.get("rol");

                    if (usuarioNombre != null && nombre != null && rol != null) {
                        usuarios.add(new Usuario(usuarioNombre, password, nombre, rol));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    private static Double getDouble(Object value) {
        if (value instanceof Double) {
            return (Double) value;
        } else if (value instanceof Integer) {
            return ((Integer) value).doubleValue();
        } else if (value instanceof String) {
            try {
                return Double.parseDouble((String) value);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    private static Integer getInteger(Object value) {
        if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof Double) {
            return ((Double) value).intValue();
        } else if (value instanceof String) {
            try {
                return Integer.parseInt((String) value);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public static DefaultTableModel cargarProductos() {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"Código", "Nombre", "Precio", "Stock"}, 0
        );
        
        try {
            Map<String, Object> datos = parsearJSON();
            List<Map<String, Object>> productos = (List<Map<String, Object>>) datos.get("productos");
            
            if (productos != null) {
                for (Map<String, Object> prod : productos) {
                    model.addRow(new Object[]{
                        prod.get("codigo"),
                        prod.get("nombre"),
                        String.format("$%.2f", (Double) prod.get("precio")),
                        prod.get("stock")
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return model;
    }
    
    public static DefaultTableModel cargarUsuarios() {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"Usuario", "Nombre", "Rol", "Estado"}, 0
        );
        
        try {
            Map<String, Object> datos = parsearJSON();
            List<Map<String, Object>> usuarios = (List<Map<String, Object>>) datos.get("usuarios");
            
            if (usuarios != null) {
                for (Map<String, Object> user : usuarios) {
                    model.addRow(new Object[]{
                        user.get("usuario"),
                        user.get("nombre"),
                        user.get("rol"),
                        ((Boolean) user.get("activo")) ? "Activo" : "Inactivo"
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return model;
    }
    
    public static DefaultTableModel cargarMovimientos() {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"Fecha", "Producto", "Tipo", "Cantidad", "Stock Anterior", "Stock Nuevo"}, 0
        );
        
        try {
            Map<String, Object> datos = parsearJSON();
            List<Map<String, Object>> movimientos = (List<Map<String, Object>>) datos.get("movimientos");
            
            if (movimientos != null) {
                for (Map<String, Object> mov : movimientos) {
                    model.addRow(new Object[]{
                        mov.get("fecha"),
                        mov.get("producto"),
                        mov.get("tipo"),
                        mov.get("cantidad"),
                        mov.get("stockAnterior"),
                        mov.get("stockNuevo")
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return model;
    }
    
    public static String[] obtenerProductosParaCombo() {
        List<String> productos = new ArrayList<>();
        
        try {
            Map<String, Object> datos = parsearJSON();
            List<Map<String, Object>> prodList = (List<Map<String, Object>>) datos.get("productos");
            
            if (prodList != null) {
                for (Map<String, Object> prod : prodList) {
                    String codigo = (String) prod.get("codigo");
                    String nombre = (String) prod.get("nombre");
                    productos.add(codigo + " - " + nombre);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return productos.toArray(new String[0]);
    }
    
    private static Map<String, Object> parsearJSON() throws Exception {
        InputStream is = CargadorDatosPrueba.class.getResourceAsStream("/datos_prueba.json");
        
        if (is == null) {
            // Fallback si no encuentra el archivo
            return new HashMap<>();
        }
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder json = new StringBuilder();
        String line;
        
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }
        reader.close();
        
        return parseJSON(json.toString());
    }
    
    private static Map<String, Object> parseJSON(String json) {
        Map<String, Object> result = new HashMap<>();
        
        // Parser manual simple para JSON
        json = json.trim();
        if (json.startsWith("{") && json.endsWith("}")) {
            json = json.substring(1, json.length() - 1);
            
            // Extraer productos
            result.put("productos", extraerArray(json, "productos"));
            result.put("usuarios", extraerArray(json, "usuarios"));
            result.put("ventas", extraerArray(json, "ventas"));
            result.put("movimientos", extraerArray(json, "movimientos"));
        }
        
        return result;
    }
    
    private static List<Map<String, Object>> extraerArray(String json, String key) {
        List<Map<String, Object>> list = new ArrayList<>();
        
        try {
            int start = json.indexOf("\"" + key + "\"");
            if (start == -1) return list;
            
            start = json.indexOf("[", start);
            int count = 1;
            int end = start + 1;
            
            while (end < json.length() && count > 0) {
                if (json.charAt(end) == '[') count++;
                if (json.charAt(end) == ']') count--;
                end++;
            }
            
            String arrayStr = json.substring(start + 1, end - 1);
            
            // Extraer cada objeto del array
            int objectCount = 0;
            int objStart = 0;
            
            for (int i = 0; i < arrayStr.length(); i++) {
                if (arrayStr.charAt(i) == '{') {
                    if (objectCount == 0) {
                        objStart = i;
                    }
                    objectCount++;
                } else if (arrayStr.charAt(i) == '}') {
                    objectCount--;
                    if (objectCount == 0) {
                        String objStr = arrayStr.substring(objStart, i + 1);
                        Map<String, Object> obj = parseObject(objStr);
                        if (!obj.isEmpty()) {
                            list.add(obj);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    private static Map<String, Object> parseObject(String json) {
        Map<String, Object> obj = new HashMap<>();
        
        json = json.trim();
        if (json.startsWith("{") && json.endsWith("}")) {
            json = json.substring(1, json.length() - 1);
            
            // Extraer pares clave-valor
            String[] parts = json.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            
            for (String part : parts) {
                part = part.trim();
                int colonIdx = part.indexOf(":");
                
                if (colonIdx > 0) {
                    String key = part.substring(0, colonIdx).trim().replaceAll("\"", "");
                    String value = part.substring(colonIdx + 1).trim();
                    
                    Object val = parseValue(value);
                    obj.put(key, val);
                }
            }
        }
        
        return obj;
    }
    
    private static Object parseValue(String value) {
        value = value.trim();
        
        if (value.startsWith("\"") && value.endsWith("\"")) {
            return value.substring(1, value.length() - 1);
        } else if (value.equals("true")) {
            return true;
        } else if (value.equals("false")) {
            return false;
        } else if (value.equals("null")) {
            return null;
        } else {
            try {
                if (value.contains(".")) {
                    return Double.parseDouble(value);
                } else {
                    return Integer.parseInt(value);
                }
            } catch (NumberFormatException e) {
                return value;
            }
        }
    }
}
