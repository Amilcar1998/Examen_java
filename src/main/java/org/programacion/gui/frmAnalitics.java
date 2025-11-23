/*
 * Formulario de Análisis y Gráficos del Sistema de Punto de Venta
 */
package org.programacion.gui;

import org.programacion.controlador.ControladorVenta;
import org.programacion.controlador.ControladorProducto;
import org.programacion.controlador.ControladorUsuario;
import org.programacion.controlador.ControladorMovimiento;
import org.programacion.modelo.*;
import org.programacion.almacenamiento.GestorDatosV2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Formulario de análisis de datos y generación de gráficos
 * @author Sistema POS
 */
public class frmAnalitics extends javax.swing.JInternalFrame {

    private ControladorVenta controladorVenta;
    private ControladorProducto controladorProducto;
    private ControladorUsuario controladorUsuario;
    private ControladorMovimiento controladorMovimiento;
    
    // Paneles para diferentes tipos de análisis
    private JPanel panelResumen;
    private JPanel panelGraficos;
    private JPanel panelReportes;

    public frmAnalitics() {
        initComponents();
        inicializarControladores();
        configurarPaneles();
        cargarAnalisisDatos();
    }

    private void inicializarControladores() {
        controladorVenta = new ControladorVenta();
        controladorProducto = new ControladorProducto();
        controladorUsuario = new ControladorUsuario();
        controladorMovimiento = new ControladorMovimiento();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelDashboard = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaResumen = new javax.swing.JTextArea();
        panelCharts = new javax.swing.JPanel();
        btnGenerarGraficos = new javax.swing.JButton();
        panelTablas = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAnalisis = new javax.swing.JTable();
        btnActualizar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Analytics y Reportes del Sistema");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ANALYTICS & BUSINESS INTELLIGENCE");

        areaResumen.setColumns(20);
        areaResumen.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        areaResumen.setRows(5);
        areaResumen.setEditable(false);
        jScrollPane1.setViewportView(areaResumen);

        javax.swing.GroupLayout panelDashboardLayout = new javax.swing.GroupLayout(panelDashboard);
        panelDashboard.setLayout(panelDashboardLayout);
        panelDashboardLayout.setHorizontalGroup(
            panelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        panelDashboardLayout.setVerticalGroup(
            panelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Dashboard", panelDashboard);

        btnGenerarGraficos.setText("Generar Graficos Avanzados");
        btnGenerarGraficos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarGraficosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelChartsLayout = new javax.swing.GroupLayout(panelCharts);
        panelCharts.setLayout(panelChartsLayout);
        panelChartsLayout.setHorizontalGroup(
            panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChartsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGenerarGraficos)
                .addContainerGap(480, Short.MAX_VALUE))
        );
        panelChartsLayout.setVerticalGroup(
            panelChartsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChartsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGenerarGraficos)
                .addContainerGap(440, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Graficos", panelCharts);

        tblAnalisis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Métrica", "Valor", "Porcentaje", "Tendencia"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblAnalisis);

        btnActualizar.setText("Actualizar Analisis");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTablasLayout = new javax.swing.GroupLayout(panelTablas);
        panelTablas.setLayout(panelTablasLayout);
        panelTablasLayout.setHorizontalGroup(
            panelTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                    .addGroup(panelTablasLayout.createSequentialGroup()
                        .addComponent(btnActualizar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelTablasLayout.setVerticalGroup(
            panelTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTablasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnActualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Metricas", panelTablas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarGraficosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarGraficosActionPerformed
        generarGraficosAvanzados();
    }//GEN-LAST:event_btnGenerarGraficosActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        cargarAnalisisDatos();
        cargarTablaMetricas();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void configurarPaneles() {
        // Configuración inicial de paneles
        panelCharts.setLayout(new BorderLayout());
    }

    private void cargarAnalisisDatos() {
        StringBuilder resumen = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        resumen.append("═══════════════════════════════════════════════════════════════════\n");
        resumen.append("                    ANALISIS COMPLETO DEL SISTEMA POS\n");
        resumen.append("═══════════════════════════════════════════════════════════════════\n\n");
        
        // 1. RESUMEN GENERAL DEL SISTEMA
        List<Producto> productos = controladorProducto.obtenerProductos();
        List<Venta> ventas = controladorVenta.obtenerVentas();
        List<Usuario> usuarios = controladorUsuario.obtenerUsuarios();
        List<MovimientoKardex> movimientos = controladorMovimiento.obtenerMovimientos();
        
        resumen.append("🏢 RESUMEN GENERAL DEL NEGOCIO:\n");
        resumen.append("═════════════════════════════════\n");
        resumen.append(String.format("Total de Productos registrados: %d\n", productos.size()));
        resumen.append(String.format("🛒 Total de Ventas realizadas: %d\n", ventas.size()));
        resumen.append(String.format("Total de Usuarios del sistema: %d\n", usuarios.size()));
        resumen.append(String.format("📋 Total de Movimientos de inventario: %d\n", movimientos.size()));
        resumen.append(String.format("📅 Fecha del análisis: %s\n\n", LocalDateTime.now().format(formatter)));
        
        // 2. ANÁLISIS FINANCIERO
        double totalVentas = ventas.stream().mapToDouble(Venta::getTotal).sum();
        double ventaPromedio = ventas.isEmpty() ? 0 : totalVentas / ventas.size();
        OptionalDouble ventaMaxima = ventas.stream().mapToDouble(Venta::getTotal).max();
        OptionalDouble ventaMinima = ventas.stream().mapToDouble(Venta::getTotal).min();
        
        resumen.append("ANALISIS FINANCIERO:\n");
        resumen.append("═══════════════════════\n");
        resumen.append(String.format("💵 Ingresos totales por ventas: $%.2f\n", totalVentas));
        resumen.append(String.format("Venta promedio: $%.2f\n", ventaPromedio));
        resumen.append(String.format("🔥 Venta máxima: $%.2f\n", ventaMaxima.isPresent() ? ventaMaxima.getAsDouble() : 0));
        resumen.append(String.format("📉 Venta mínima: $%.2f\n", ventaMinima.isPresent() ? ventaMinima.getAsDouble() : 0));
        
        // 3. ANÁLISIS DE INVENTARIO
        double valorTotalInventario = productos.stream()
            .mapToDouble(p -> p.getPrecio() * p.getStock())
            .sum();
        int stockTotal = productos.stream().mapToInt(Producto::getStock).sum();
        long productosAgotados = productos.stream().filter(p -> p.getStock() == 0).count();
        long productosStockBajo = productos.stream().filter(p -> p.getStock() > 0 && p.getStock() <= 5).count();
        
        resumen.append("\nANALISIS DE INVENTARIO:\n");
        resumen.append("═════════════════════════════\n");
        resumen.append(String.format("Valor total del inventario: $%.2f\n", valorTotalInventario));
        resumen.append(String.format("Stock total de productos: %d unidades\n", stockTotal));
        resumen.append(String.format("ALERTA Productos agotados: %d\n", productosAgotados));
        resumen.append(String.format("🟡 Productos con stock bajo (≤5): %d\n", productosStockBajo));
        
        // 4. TOP 5 PRODUCTOS MÁS VENDIDOS
        Map<String, Integer> ventasPorProducto = new HashMap<>();
        Map<String, Double> ingresosPorProducto = new HashMap<>();
        
        for (Venta venta : ventas) {
            if (venta.getItems() != null) {
                for (ItemVenta item : venta.getItems()) {
                    if (item.getProducto() != null && item.getProducto().getNombre() != null) {
                        String nombreProducto = item.getProducto().getNombre();
                        ventasPorProducto.merge(nombreProducto, item.getCantidad(), Integer::sum);
                        ingresosPorProducto.merge(nombreProducto, item.getSubtotal(), Double::sum);
                    }
                }
            }
        }
        
        resumen.append("\n🏆 TOP 5 PRODUCTOS MÁS VENDIDOS:\n");
        resumen.append("═══════════════════════════════════\n");
        if (ventasPorProducto.isEmpty()) {
            resumen.append("ALERTA No hay datos de ventas de productos disponibles\n");
        } else {
            ventasPorProducto.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> {
                    double ingresos = ingresosPorProducto.getOrDefault(entry.getKey(), 0.0);
                    resumen.append(String.format("🎯 %s: %d unidades vendidas ($%.2f ingresos)\n", 
                        entry.getKey(), entry.getValue(), ingresos));
                });
        }
        
        // 5. ANÁLISIS TEMPORAL DE VENTAS
        if (!ventas.isEmpty()) {
            Map<String, Long> ventasPorMes = ventas.stream()
                .filter(v -> v.getFecha() != null) // Filtrar ventas con fecha null
                .collect(Collectors.groupingBy(
                    v -> v.getFecha().format(DateTimeFormatter.ofPattern("MM/yyyy")),
                    Collectors.counting()
                ));
            
            if (!ventasPorMes.isEmpty()) {
                resumen.append("\n📅 VENTAS POR MES:\n");
                resumen.append("═══════════════════\n");
                ventasPorMes.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry -> {
                        double totalMes = ventas.stream()
                            .filter(v -> v.getFecha() != null && 
                                v.getFecha().format(DateTimeFormatter.ofPattern("MM/yyyy")).equals(entry.getKey()))
                            .mapToDouble(Venta::getTotal)
                            .sum();
                        resumen.append(String.format("📈 %s: %d ventas ($%.2f)\n", 
                            entry.getKey(), entry.getValue(), totalMes));
                    });
            }
        }
        
        // 6. ANÁLISIS DE USUARIOS Y SEGURIDAD
        Map<String, Long> usuariosPorRol = usuarios.stream()
            .filter(u -> u.getRol() != null) // Filtrar usuarios con rol null
            .collect(Collectors.groupingBy(Usuario::getRol, Collectors.counting()));
        
        resumen.append("\nANALISIS DE USUARIOS:\n");
        resumen.append("══════════════════════════\n");
        if (usuariosPorRol.isEmpty()) {
            resumen.append("ALERTA No hay usuarios con roles definidos\n");
        } else {
            usuariosPorRol.forEach((rol, cantidad) -> {
                resumen.append(String.format("🔹 %s: %d usuario(s)\n", rol, cantidad));
            });
        }
        
        // Contar usuarios sin rol
        long usuariosSinRol = usuarios.stream().filter(u -> u.getRol() == null).count();
        if (usuariosSinRol > 0) {
            resumen.append(String.format("ALERTA Usuarios sin rol asignado: %d\n", usuariosSinRol));
        }
        
        // 7. INDICADORES CLAVE DE RENDIMIENTO (KPIs)
        resumen.append("\nINDICADORES CLAVE (KPIs):\n");
        resumen.append("═══════════════════════════════\n");
        
        double ticketPromedio = ventaPromedio;
        int itemsPromedioPorVenta = ventas.isEmpty() ? 0 : 
            ventas.stream().mapToInt(v -> v.getItems().size()).sum() / ventas.size();
        double rotacionInventario = stockTotal > 0 ? 
            ventasPorProducto.values().stream().mapToInt(Integer::intValue).sum() / (double) stockTotal * 100 : 0;
        
        resumen.append(String.format("🎯 Ticket promedio: $%.2f\n", ticketPromedio));
        resumen.append(String.format("Items promedio por venta: %d\n", itemsPromedioPorVenta));
        resumen.append(String.format("🔄 Rotación de inventario: %.1f%%\n", rotacionInventario));
        resumen.append(String.format("📈 Productos únicos vendidos: %d\n", ventasPorProducto.size()));
        
        // 8. RECOMENDACIONES ESTRATÉGICAS
        resumen.append("\n💡 RECOMENDACIONES ESTRATÉGICAS:\n");
        resumen.append("═══════════════════════════════════\n");
        
        if (productosAgotados > 0) {
            resumen.append("🔴 CRÍTICO: Hay productos agotados. Reabastecer urgentemente.\n");
        }
        if (productosStockBajo > 0) {
            resumen.append("🟡 ATENCIÓN: Productos con stock bajo requieren reposición.\n");
        }
        if (ticketPromedio < 50) {
            resumen.append("OPORTUNIDAD: Implementar estrategias de upselling para aumentar ticket promedio.\n");
        }
        if (ventasPorProducto.size() < productos.size() * 0.5) {
            resumen.append("ANALISIS: Muchos productos no han sido vendidos. Revisar portfolio.\n");
        }
        
        resumen.append("\n═══════════════════════════════════════════════════════════════════\n");
        resumen.append("                    📋 FIN DEL ANÁLISIS COMPLETO\n");
        resumen.append("═══════════════════════════════════════════════════════════════════\n");
        
        areaResumen.setText(resumen.toString());
        areaResumen.setCaretPosition(0); // Volver al inicio
    }

    private void cargarTablaMetricas() {
        DefaultTableModel modelo = (DefaultTableModel) tblAnalisis.getModel();
        modelo.setRowCount(0);
        
        List<Producto> productos = controladorProducto.obtenerProductos();
        List<Venta> ventas = controladorVenta.obtenerVentas();
        
        // Métricas principales
        double totalVentas = ventas.stream().mapToDouble(Venta::getTotal).sum();
        int totalProductos = productos.size();
        int stockTotal = productos.stream().mapToInt(Producto::getStock).sum();
        long productosAgotados = productos.stream().filter(p -> p.getStock() == 0).count();
        
        modelo.addRow(new Object[]{"💰 Ingresos Totales", String.format("$%.2f", totalVentas), "100%", "📈"});
        modelo.addRow(new Object[]{"🛒 Total de Ventas", ventas.size(), "100%", "📊"});
        modelo.addRow(new Object[]{"📦 Productos Activos", totalProductos, "100%", "📋"});
        modelo.addRow(new Object[]{"📊 Stock Total", stockTotal + " unidades", "100%", "📦"});
        modelo.addRow(new Object[]{"⚠️ Productos Agotados", productosAgotados, 
            String.format("%.1f%%", (productosAgotados * 100.0 / totalProductos)), "🔴"});
        
        if (!ventas.isEmpty()) {
            double ventaPromedio = totalVentas / ventas.size();
            modelo.addRow(new Object[]{"📈 Ticket Promedio", String.format("$%.2f", ventaPromedio), "100%", "💎"});
        }
        
        // Top producto más vendido
        Map<String, Integer> ventasPorProducto = new HashMap<>();
        for (Venta venta : ventas) {
            if (venta.getItems() != null) {
                for (ItemVenta item : venta.getItems()) {
                    if (item.getProducto() != null && item.getProducto().getNombre() != null) {
                        ventasPorProducto.merge(item.getProducto().getNombre(), item.getCantidad(), Integer::sum);
                    }
                }
            }
        }
        
        if (!ventasPorProducto.isEmpty()) {
            String topProducto = ventasPorProducto.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
            int maxVentas = ventasPorProducto.values().stream().max(Integer::compareTo).orElse(0);
            
            modelo.addRow(new Object[]{"🏆 Producto Top", topProducto, 
                maxVentas + " vendidos", "🔥"});
        }
    }

    private void generarGraficosAvanzados() {
        JDialog dialogoGraficos = new JDialog((Frame) null, "Graficos Avanzados del Sistema", true);
        dialogoGraficos.setSize(1000, 700);
        dialogoGraficos.setLocationRelativeTo(this);
        dialogoGraficos.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        JTabbedPane pestanas = new JTabbedPane();
        
        try {
            // Gráfico 1: Ventas por Producto (Barras ASCII)
            JPanel panelVentasProducto = crearGraficoVentasProducto();
            pestanas.addTab("Ventas x Producto", panelVentasProducto);
            
            // Gráfico 2: Distribución de Stock (Circular ASCII)
            JPanel panelStock = crearGraficoDistribucionStock();
            pestanas.addTab("Distribucion Stock", panelStock);
            
            // Gráfico 3: Análisis Temporal
            JPanel panelTemporal = crearAnalisisTemporal();
            pestanas.addTab("Analisis Temporal", panelTemporal);
            
            JButton btnCerrar = new JButton("Cerrar");
            btnCerrar.addActionListener(e -> dialogoGraficos.dispose());
            
            JPanel panelBoton = new JPanel();
            panelBoton.add(btnCerrar);
            
            dialogoGraficos.add(pestanas, BorderLayout.CENTER);
            dialogoGraficos.add(panelBoton, BorderLayout.SOUTH);
            
            dialogoGraficos.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al generar graficos: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private JPanel crearGraficoVentasProducto() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea areaGrafico = new JTextArea();
        areaGrafico.setFont(new Font("Consolas", Font.PLAIN, 10));
        areaGrafico.setEditable(false);
        areaGrafico.setBackground(Color.WHITE);
        
        try {
            List<Venta> ventas = controladorVenta.obtenerVentas();
            Map<String, Integer> ventasPorProducto = new HashMap<>();
            
            for (Venta venta : ventas) {
                if (venta != null && venta.getItems() != null) {
                    for (ItemVenta item : venta.getItems()) {
                        if (item != null && item.getProducto() != null && item.getProducto().getNombre() != null) {
                            ventasPorProducto.merge(item.getProducto().getNombre(), 
                                item.getCantidad(), Integer::sum);
                        }
                    }
                }
            }
            
            StringBuilder grafico = new StringBuilder();
            grafico.append("GRAFICO DE VENTAS POR PRODUCTO (TOP 10)\n");
            grafico.append("===============================================\n\n");
            
            if (ventasPorProducto.isEmpty()) {
                grafico.append("No hay datos de ventas disponibles.\n");
            } else {
                int maxVentas = ventasPorProducto.values().stream().max(Integer::compareTo).orElse(1);
                
                ventasPorProducto.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .limit(10)
                    .forEach(entry -> {
                        String nombre = entry.getKey();
                        int ventas_count = entry.getValue();
                        int barraLength = Math.max(1, (ventas_count * 50) / maxVentas);
                        
                        StringBuilder barra = new StringBuilder();
                        for (int i = 0; i < barraLength; i++) {
                            barra.append("█");
                        }
                        
                        grafico.append(String.format("%-25s |%s %d\n", 
                            nombre.length() > 25 ? nombre.substring(0, 22) + "..." : nombre,
                            barra.toString(), 
                            ventas_count));
                    });
            }
            
            grafico.append("\n===============================================\n");
            grafico.append("Escala: Cada bloque representa proporcionalmente las ventas\n");
            
            areaGrafico.setText(grafico.toString());
            
        } catch (Exception e) {
            areaGrafico.setText("Error al generar grafico: " + e.getMessage());
            e.printStackTrace();
        }
        
        JScrollPane scrollPane = new JScrollPane(areaGrafico);
        scrollPane.setPreferredSize(new Dimension(900, 600));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearGraficoDistribucionStock() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea areaGrafico = new JTextArea();
        areaGrafico.setFont(new Font("Consolas", Font.PLAIN, 11));
        areaGrafico.setEditable(false);
        areaGrafico.setBackground(Color.WHITE);
        
        try {
            List<Producto> productos = controladorProducto.obtenerProductos();
            
            long stockAlto = productos.stream().filter(p -> p.getStock() > 20).count();
            long stockMedio = productos.stream().filter(p -> p.getStock() >= 6 && p.getStock() <= 20).count();
            long stockBajo = productos.stream().filter(p -> p.getStock() >= 1 && p.getStock() <= 5).count();
            long stockAgotado = productos.stream().filter(p -> p.getStock() == 0).count();
            
            long total = productos.size();
            
            StringBuilder grafico = new StringBuilder();
            grafico.append("DISTRIBUCION DEL STOCK POR CATEGORIAS\n");
            grafico.append("===============================================\n\n");
            
            if (total > 0) {
                double pctAlto = (stockAlto * 100.0) / total;
                double pctMedio = (stockMedio * 100.0) / total;
                double pctBajo = (stockBajo * 100.0) / total;
                double pctAgotado = (stockAgotado * 100.0) / total;
                
                grafico.append(String.format("[ALTO] STOCK ALTO (>20):    %s %.1f%% (%d productos)\n", 
                    repetirCaracter('#', (int)(pctAlto/2)), pctAlto, stockAlto));
                grafico.append(String.format("[MEDIO] STOCK MEDIO (6-20): %s %.1f%% (%d productos)\n", 
                    repetirCaracter('#', (int)(pctMedio/2)), pctMedio, stockMedio));
                grafico.append(String.format("[BAJO] STOCK BAJO (1-5):    %s %.1f%% (%d productos)\n", 
                    repetirCaracter('#', (int)(pctBajo/2)), pctBajo, stockBajo));
                grafico.append(String.format("[AGOTADO] AGOTADO (0):      %s %.1f%% (%d productos)\n", 
                    repetirCaracter('#', (int)(pctAgotado/2)), pctAgotado, stockAgotado));
                
                grafico.append("\nRESUMEN ESTADISTICO:\n");
                grafico.append("=====================================\n");
                
                double stockPromedio = productos.stream().mapToInt(Producto::getStock).average().orElse(0);
                int stockMaximo = productos.stream().mapToInt(Producto::getStock).max().orElse(0);
                double valorInventario = productos.stream()
                    .mapToDouble(p -> p.getPrecio() * p.getStock()).sum();
                
                grafico.append(String.format("Stock promedio: %.1f unidades\n", stockPromedio));
                grafico.append(String.format("Stock maximo: %d unidades\n", stockMaximo));
                grafico.append(String.format("Valor del inventario: $%.2f\n", valorInventario));
                
                // Alerta crítica
                if (pctAgotado > 10) {
                    grafico.append("\n[ALERTA CRITICA] Mas del 10% de productos agotados!\n");
                }
                if (pctBajo + pctAgotado > 25) {
                    grafico.append("[ATENCION] Mas del 25% del inventario en estado critico!\n");
                }
            } else {
                grafico.append("No hay productos en el inventario.\n");
            }
            
            grafico.append("\n===============================================\n");
            grafico.append("Escala: Cada # = 2%  |  Total de productos analizados: " + total);
            
            areaGrafico.setText(grafico.toString());
            
        } catch (Exception e) {
            areaGrafico.setText("Error al generar grafico de stock: " + e.getMessage());
            e.printStackTrace();
        }
        
        JScrollPane scrollPane = new JScrollPane(areaGrafico);
        scrollPane.setPreferredSize(new Dimension(900, 600));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private String repetirCaracter(char c, int veces) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.max(0, veces); i++) {
            sb.append(c);
        }
        return sb.toString();
    }
    
    private JPanel crearAnalisisTemporal() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea areaGrafico = new JTextArea();
        areaGrafico.setFont(new Font("Consolas", Font.PLAIN, 11));
        areaGrafico.setEditable(false);
        areaGrafico.setBackground(Color.WHITE);
        
        try {
            List<Venta> ventas = controladorVenta.obtenerVentas();
            
            StringBuilder grafico = new StringBuilder();
            grafico.append("ANALISIS TEMPORAL DE VENTAS Y MOVIMIENTOS\n");
            grafico.append("===============================================\n\n");
            
            if (ventas == null || ventas.isEmpty()) {
                grafico.append("No hay datos de ventas para analizar.\n");
            } else {
                // Agrupar ventas por día
                Map<String, Double> ventasPorDia = ventas.stream()
                    .filter(v -> v != null && v.getFecha() != null) // Filtrar ventas sin fecha
                    .collect(Collectors.groupingBy(
                        v -> v.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        Collectors.summingDouble(Venta::getTotal)
                    ));
                
                if (!ventasPorDia.isEmpty()) {
                    grafico.append("VENTAS POR DIA (ULTIMOS REGISTROS):\n");
                    grafico.append("=========================================\n");
                    
                    double maxVentaDia = ventasPorDia.values().stream().max(Double::compareTo).orElse(1.0);
                    
                    ventasPorDia.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .forEach(entry -> {
                            String fecha = entry.getKey();
                            double total = entry.getValue();
                            int barraLength = Math.max(1, (int)((total * 30) / maxVentaDia));
                            
                            String barra = repetirCaracter('*', barraLength);
                            long cantidadVentas = ventas.stream()
                                .filter(v -> v != null && v.getFecha() != null && 
                                    v.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).equals(fecha))
                                .count();
                            
                            grafico.append(String.format("%s |%s $%.2f (%d ventas)\n", 
                                fecha, barra, total, cantidadVentas));
                        });
                }
                
                // Análisis por horas (si hay datos del mismo día)
                Map<Integer, Long> ventasPorHora = ventas.stream()
                    .filter(v -> v != null && v.getFecha() != null) // Filtrar ventas sin fecha
                    .collect(Collectors.groupingBy(
                        v -> v.getFecha().getHour(),
                        Collectors.counting()
                    ));
                
                if (ventasPorHora.size() > 1) {
                    grafico.append("\nDISTRIBUCION DE VENTAS POR HORA:\n");
                    grafico.append("==================================\n");
                    
                    long maxVentaHora = ventasPorHora.values().stream().max(Long::compareTo).orElse(1L);
                    
                    for (int hora = 0; hora < 24; hora++) {
                        long ventasHora = ventasPorHora.getOrDefault(hora, 0L);
                        if (ventasHora > 0) {
                            int barraLength = Math.max(1, (int)((ventasHora * 20) / maxVentaHora));
                            String barra = repetirCaracter('-', barraLength);
                            grafico.append(String.format("%02d:00h |%s %d ventas\n", 
                                hora, barra, ventasHora));
                        }
                    }
                }
                
                // Tendencias y predicciones simples
                grafico.append("\nANALISIS DE TENDENCIAS:\n");
                grafico.append("========================\n");
                
                double totalVentas = ventas.stream().mapToDouble(Venta::getTotal).sum();
                double ventaPromedio = totalVentas / ventas.size();
                
                LocalDateTime fechaMasReciente = ventas.stream()
                    .filter(v -> v != null && v.getFecha() != null)
                    .map(Venta::getFecha)
                    .max(LocalDateTime::compareTo)
                    .orElse(LocalDateTime.now());
                
                LocalDateTime fechaMasAntigua = ventas.stream()
                    .filter(v -> v != null && v.getFecha() != null)
                    .map(Venta::getFecha)
                    .min(LocalDateTime::compareTo)
                    .orElse(LocalDateTime.now());
                
                grafico.append(String.format("Total de ingresos: $%.2f\n", totalVentas));
                grafico.append(String.format("Promedio por venta: $%.2f\n", ventaPromedio));
                grafico.append(String.format("Periodo analizado: %s a %s\n", 
                    fechaMasAntigua.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    fechaMasReciente.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
                
                // Día con más ventas
                if (!ventasPorDia.isEmpty()) {
                    String diaTop = ventasPorDia.entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
                        .orElse("N/A");
                    double ventaTopDia = ventasPorDia.values().stream().max(Double::compareTo).orElse(0.0);
                    
                    grafico.append(String.format("Mejor dia de ventas: %s ($%.2f)\n", diaTop, ventaTopDia));
                }
            }
            
            grafico.append("\n===============================================\n");
            grafico.append("Escala proporcional  |  Analisis basado en datos reales del sistema");
            
            areaGrafico.setText(grafico.toString());
            
        } catch (Exception e) {
            areaGrafico.setText("Error al generar analisis temporal: " + e.getMessage());
            e.printStackTrace();
        }
        
        JScrollPane scrollPane = new JScrollPane(areaGrafico);
        scrollPane.setPreferredSize(new Dimension(900, 600));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
        } else {
            // Agrupar ventas por día
            Map<String, Double> ventasPorDia = ventas.stream()
                .filter(v -> v.getFecha() != null) // Filtrar ventas sin fecha
                .collect(Collectors.groupingBy(
                    v -> v.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    Collectors.summingDouble(Venta::getTotal)
                ));
            
            grafico.append("📈 VENTAS POR DÍA (ÚLTIMOS REGISTROS):\n");
            grafico.append("═══════════════════════════════════════════\n");
            
            double maxVentaDia = ventasPorDia.values().stream().max(Double::compareTo).orElse(1.0);
            
            ventasPorDia.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    String fecha = entry.getKey();
                    double total = entry.getValue();
                    int barraLength = (int)((total * 30) / maxVentaDia);
                    
                    String barra = "▓".repeat(Math.max(1, barraLength));
                    long cantidadVentas = ventas.stream()
                        .filter(v -> v.getFecha() != null && 
                            v.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).equals(fecha))
                        .count();
                    
                    grafico.append(String.format("%s |%s $%.2f (%d ventas)\n", 
                        fecha, barra, total, cantidadVentas));
                });
            
            // Análisis por horas (si hay datos del mismo día)
            Map<Integer, Long> ventasPorHora = ventas.stream()
                .filter(v -> v.getFecha() != null) // Filtrar ventas sin fecha
                .collect(Collectors.groupingBy(
                    v -> v.getFecha().getHour(),
                    Collectors.counting()
                ));
            
            if (ventasPorHora.size() > 1) {
                grafico.append("\n🕐 DISTRIBUCIÓN DE VENTAS POR HORA:\n");
                grafico.append("══════════════════════════════════════════\n");
                
                long maxVentaHora = ventasPorHora.values().stream().max(Long::compareTo).orElse(1L);
                
                for (int hora = 0; hora < 24; hora++) {
                    long ventasHora = ventasPorHora.getOrDefault(hora, 0L);
                    if (ventasHora > 0) {
                        int barraLength = (int)((ventasHora * 20) / maxVentaHora);
                        String barra = "░".repeat(Math.max(1, barraLength));
                        grafico.append(String.format("%02d:00h |%s %d ventas\n", 
                            hora, barra, ventasHora));
                    }
                }
            }
            
            // Tendencias y predicciones simples
            grafico.append("\n📊 ANÁLISIS DE TENDENCIAS:\n");
            grafico.append("════════════════════════════════\n");
            
            double totalVentas = ventas.stream().mapToDouble(Venta::getTotal).sum();
            double ventaPromedio = totalVentas / ventas.size();
            
            LocalDateTime fechaMasReciente = ventas.stream()
                .filter(v -> v.getFecha() != null)
                .map(Venta::getFecha)
                .max(LocalDateTime::compareTo)
                .orElse(LocalDateTime.now());
            
            LocalDateTime fechaMasAntigua = ventas.stream()
                .filter(v -> v.getFecha() != null)
                .map(Venta::getFecha)
                .min(LocalDateTime::compareTo)
                .orElse(LocalDateTime.now());
            
            grafico.append(String.format("💰 Total de ingresos: $%.2f\n", totalVentas));
            grafico.append(String.format("📊 Promedio por venta: $%.2f\n", ventaPromedio));
            grafico.append(String.format("📅 Período analizado: %s a %s\n", 
                fechaMasAntigua.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                fechaMasReciente.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            
            // Día con más ventas
            String diaTop = ventasPorDia.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
            double ventaTopDia = ventasPorDia.values().stream().max(Double::compareTo).orElse(0.0);
            
            grafico.append(String.format("🏆 Mejor día de ventas: %s ($%.2f)\n", diaTop, ventaTopDia));
        }
        
        grafico.append("\n═══════════════════════════════════════════════════════════════\n");
        grafico.append("📈 Escala proporcional  |  Análisis basado en datos reales del sistema");
        
        areaGrafico.setText(grafico.toString());
        panel.add(new JScrollPane(areaGrafico), BorderLayout.CENTER);
        
        return panel;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaResumen;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnGenerarGraficos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panelCharts;
    private javax.swing.JPanel panelDashboard;
    private javax.swing.JPanel panelTablas;
    private javax.swing.JTable tblAnalisis;
    // End of variables declaration//GEN-END:variables
}