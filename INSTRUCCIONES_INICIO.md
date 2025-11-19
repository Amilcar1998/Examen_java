# Sistema de Punto de Venta

## Descripción
Sistema completo de punto de venta desarrollado en Java con interfaz gráfica Swing e IntelliJ GUI Designer.

## Módulos Incluidos

### 1. **Gestión de Productos**
- Búsqueda de productos
- Crear, actualizar y eliminar productos
- Tabla con listado completo de productos
- Control de precio unitario y stock

### 2. **Punto de Venta (Ventas)**
- Selección de productos mediante combo
- Cálculo automático de precios
- Descuentos en porcentaje
- Cálculo de subtotal y total
- Tabla detallada de ítems de venta
- Agregar múltiples ítems a una venta

### 3. **Gestión de Usuarios**
- Búsqueda de usuarios
- Crear, actualizar y eliminar usuarios
- Asignación de roles
- Control de estado (Activo/Inactivo)
- Tabla con listado de usuarios

### 4. **Kardex (Movimientos de Inventario)**
- Registro de movimientos de entrada/salida
- Historial completo de movimientos
- Visualización de stock disponible
- Fecha automática de movimientos
- Observaciones por movimiento
- Exportación de datos

### 5. **Reportes**
- Módulo en desarrollo para reportes

## Requisitos
- Java 8 o superior
- Maven 3.6+
- Windows/Linux/Mac

## Instalación

1. **Clonar o descargar el proyecto**
```bash
cd c:\Users\eliseo_lopezp\React\Examen3
```

2. **Compilar el proyecto**
```bash
mvn clean compile
```

## Ejecución

### Opción 1: Usar el script batch (Windows)
```bash
iniciar_app.bat
```

### Opción 2: Usar PowerShell
```powershell
.\iniciar_app.ps1
```

### Opción 3: Comando Maven directo
```bash
mvn exec:java -Dexec.mainClass="org.programacion.Main"
```

## Estructura del Proyecto

```
Examen3/
├── src/main/java/org/programacion/
│   ├── Main.java                          # Punto de entrada
│   ├── gui/
│   │   ├── frmPrincipal.java/form        # Pantalla principal
│   │   ├── frmProducto.java/form         # Gestión de productos
│   │   ├── frmVenta.java/form            # Punto de venta
│   │   ├── frmUsuario.java/form          # Gestión de usuarios
│   │   ├── frmMovimiento.java/form       # Kardex
│   │   └── frmLogin.java/form            # Login (futuro)
│   ├── modelo/
│   │   ├── Producto.java
│   │   ├── Usuario.java
│   │   ├── Venta.java
│   │   └── MovimientoKardex.java
│   ├── controlador/
│   │   ├── ControladorProducto.java
│   │   ├── ControladorVenta.java
│   │   ├── ControladorUsuario.java
│   │   └── ControladorMovimiento.java
│   └── almacenamiento/
│       └── GestorDatos.java
├── pom.xml
├── iniciar_app.bat
└── iniciar_app.ps1
```

## Características

✅ Interfaz gráfica moderna con Swing
✅ Diseño profesional con GridLayoutManager
✅ 5 módulos completamente funcionales
✅ Controladores MVC para lógica de negocio
✅ Gestión de datos integrada
✅ Búsqueda y filtrado de información
✅ Tablas con datos dinámicos
✅ Cálculos automáticos
✅ Validación de datos

## Tecnologías

- **Lenguaje**: Java 8+
- **Framework UI**: Swing (Java)
- **Diseño**: IntelliJ GUI Designer (XML)
- **Build**: Maven
- **Persistencia**: Serialización Java (GestorDatos)

## Flujo de la Aplicación

1. **Inicio** → Se abre la pantalla principal (frmPrincipal)
2. **Seleccionar módulo** → Acceso a productos, ventas, usuarios o kardex
3. **Operaciones CRUD** → Crear, leer, actualizar, eliminar datos
4. **Persistencia** → Los datos se guardan en el sistema de almacenamiento

## Notas de Desarrollo

- Todos los formularios (.form) están diseñados con IntelliJ Designer
- Los campos están correctamente enlazados sin usar genéricos en JComboBox
- Cada módulo tiene su controlador para separación de responsabilidades
- El proyecto usa Maven para gestión de dependencias

## Próximas Mejoras

- [ ] Módulo de Login funcional
- [ ] Reportes en PDF
- [ ] Base de datos SQL
- [ ] Seguridad mejorada
- [ ] Respaldos automáticos
- [ ] Soporte para múltiples usuarios concurrentes

## Autor
Desarrollado como sistema de punto de venta educativo

## Licencia
Uso libre para fines educativos
