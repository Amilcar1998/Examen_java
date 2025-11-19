#!/usr/bin/env pwsh
# Script para compilar y ejecutar el Sistema de Punto de Venta

$projectPath = "C:\Users\eliseo_lopezp\React\Examen3"
Set-Location $projectPath

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Sistema de Punto de Venta" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

Write-Host "Compilando el proyecto..." -ForegroundColor Yellow
mvn clean compile

if ($LASTEXITCODE -ne 0) {
    Write-Host "Error en la compilación" -ForegroundColor Red
    pause
    exit 1
}

Write-Host ""
Write-Host "Compilación exitosa!" -ForegroundColor Green
Write-Host "Ejecutando la aplicación..." -ForegroundColor Yellow
Write-Host ""

mvn exec:java -Dexec.mainClass="org.programacion.Main"
