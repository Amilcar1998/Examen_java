@echo off
REM Script para compilar y ejecutar Sistema de Punto de Venta
REM Ubicación: c:\Users\eliseo_lopezp\React\Examen3\ejecutar.bat

setlocal enabledelayedexpansion

REM Variables de ruta
set JAVA_HOME=C:\Users\eliseo_lopezp\.jdks\openjdk-24.0.1
set PROJECT_DIR=c:\Users\eliseo_lopezp\React\Examen3
set JAVAC=!JAVA_HOME!\bin\javac.exe
set JAVA=!JAVA_HOME!\bin\java.exe
set SRC_DIR=%PROJECT_DIR%\src\main\java\org\programacion
set TARGET_DIR=%PROJECT_DIR%\target\classes

REM Crear directorio de clases si no existe
if not exist "%TARGET_DIR%" mkdir "%TARGET_DIR%"

REM Lista de archivos a compilar
set "FILES=%SRC_DIR%\Main.java %SRC_DIR%\almacenamiento\GestorDatos.java %SRC_DIR%\almacenamiento\PersistenciaDatos.java %SRC_DIR%\almacenamiento\GestorDatosV2.java %SRC_DIR%\modelo\Usuario.java %SRC_DIR%\modelo\Producto.java %SRC_DIR%\modelo\Venta.java %SRC_DIR%\modelo\MovimientoKardex.java %SRC_DIR%\modelo\ItemVenta.java %SRC_DIR%\gui\frmLogin.java %SRC_DIR%\gui\frmProducto.java %SRC_DIR%\gui\frmUsuario.java %SRC_DIR%\gui\frmMovimiento.java %SRC_DIR%\gui\frmPrincipal.java %SRC_DIR%\utils\JsonManager.java %SRC_DIR%\utils\UITheme.java"

echo.
echo ============================================
echo Compilando con Java 24...
echo ============================================
echo.

REM Compilar con Java 24 (sin restricciones de versión)
"%JAVAC%" -d "%TARGET_DIR%" %FILES%

if errorlevel 1 (
    echo.
    echo [ERROR] La compilacion fallo.
    echo.
    pause
    exit /b 1
)

echo.
echo ============================================
echo Compilacion exitosa!
echo Iniciando aplicacion...
echo ============================================
echo.

REM Detener proceso Java anterior si existe
taskkill /F /IM java.exe >nul 2>&1

REM Esperar 1 segundo
timeout /t 1 /nobreak >nul

REM Ejecutar aplicacion
cd /d "%PROJECT_DIR%"
"%JAVA%" -cp "%TARGET_DIR%" org.programacion.Main

pause
