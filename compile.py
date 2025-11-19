#!/usr/bin/env python3
import subprocess
import os
import sys

os.chdir("c:\\Users\\eliseo_lopezp\\React\\Examen3")

# Crear directorio target si no existe
os.makedirs("target/classes", exist_ok=True)

# Archivos a compilar
files_to_compile = [
    "src/main/java/org/programacion/Main.java",
    "src/main/java/org/programacion/modelo/Usuario.java",
    "src/main/java/org/programacion/modelo/Producto.java",
    "src/main/java/org/programacion/modelo/Venta.java",
    "src/main/java/org/programacion/modelo/ItemVenta.java",
    "src/main/java/org/programacion/modelo/MovimientoKardex.java",
    "src/main/java/org/programacion/almacenamiento/GestorDatos.java",
    "src/main/java/org/programacion/almacenamiento/GestorDatosV2.java",
    "src/main/java/org/programacion/almacenamiento/PersistenciaDatos.java",
    "src/main/java/org/programacion/utils/JsonManager.java",
    "src/main/java/org/programacion/gui/frmLogin.java",
    "src/main/java/org/programacion/gui/frmPrincipal.java",
    "src/main/java/org/programacion/gui/frmProducto.java",
    "src/main/java/org/programacion/gui/frmUsuario.java",
    "src/main/java/org/programacion/gui/frmMovimiento.java"
]

# Compilar con JDK 1.6
javac_path = "C:\\Program Files\\Java\\jdk1.6.0_30\\bin\\javac.exe"
cmd = [
    javac_path,
    "-source", "1.6",
    "-target", "1.6",
    "-encoding", "UTF-8",
    "-d", "target/classes"
] + files_to_compile

print("Compilando con JDK 1.6...")
result = subprocess.run(cmd, capture_output=True, text=True)

if result.stdout:
    print("STDOUT:")
    print(result.stdout)
if result.stderr:
    print("STDERR:")
    print(result.stderr)

if result.returncode == 0:
    print("✓ Compilación exitosa")
else:
    print(f"✗ Error de compilación (código {result.returncode})")

sys.exit(result.returncode)
