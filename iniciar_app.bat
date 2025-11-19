@echo off
REM Script para compilar y ejecutar el Sistema de Punto de Venta

cd /d "C:\Users\eliseo_lopezp\React\Examen3"

echo ========================================
echo Sistema de Punto de Venta
echo ========================================
echo.
echo Compilando el proyecto...
call mvn clean compile

if errorlevel 1 (
    echo Error en la compilación
    pause
    exit /b 1
)

echo.
echo Compilación exitosa!
echo Ejecutando la aplicación...
echo.

call mvn exec:java -Dexec.mainClass="org.programacion.Main"

pause
