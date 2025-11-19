import json

productos = []
for i in range(1, 1001):
    codigo = f'P{i:04d}'
    nombres = ['Laptop', 'Mouse', 'Teclado', 'Monitor', 'Cable', 'Hub', 'Webcam', 'Auriculares', 'Impresora', 'Scanner', 'Proyector', 'Pantalla', 'Bateria', 'Cargador', 'Router', 'Switch', 'Modem', 'Adaptador', 'Extensor', 'Splitter']
    modelos = ['Pro', 'Ultra', 'Plus', 'Max', 'Standard', 'Basic', 'Gaming', 'Office', 'Business', 'Professional']
    
    nombre = f"{nombres[i % 20]} {modelos[i % 10]} {i}"
    precio = round(50 + (i * 1.5) % 2000, 2)
    stock = (i * 7) % 500
    
    productos.append({
        'codigo': codigo,
        'nombre': nombre,
        'precio': precio,
        'stock': stock
    })

datos = {
    'productos': productos,
    'usuarios': [
        {'usuario': 'ELOPEZ', 'nombre': 'Eliseo López', 'rol': 'Admin', 'activo': True},
        {'usuario': 'JRODRIGUEZ', 'nombre': 'Juan Rodríguez', 'rol': 'Vendedor', 'activo': True},
        {'usuario': 'MGARCIA', 'nombre': 'María García', 'rol': 'Vendedor', 'activo': True},
        {'usuario': 'CPEREZ', 'nombre': 'Carlos Pérez', 'rol': 'Gerente', 'activo': True},
        {'usuario': 'LMARTINEZ', 'nombre': 'Laura Martínez', 'rol': 'Vendedor', 'activo': False}
    ],
    'ventas': [
        {'id': 'V001', 'fecha': '2024-11-15', 'usuario': 'JRODRIGUEZ', 'total': 2500.00, 'items': 5},
        {'id': 'V002', 'fecha': '2024-11-14', 'usuario': 'MGARCIA', 'total': 1850.00, 'items': 3},
        {'id': 'V003', 'fecha': '2024-11-13', 'usuario': 'JRODRIGUEZ', 'total': 450.00, 'items': 2}
    ],
    'movimientos': [
        {'fecha': '2024-11-19', 'producto': 'P0001', 'tipo': 'Entrada', 'cantidad': 10, 'stockAnterior': 5, 'stockNuevo': 15},
        {'fecha': '2024-11-18', 'producto': 'P0002', 'tipo': 'Salida', 'cantidad': 5, 'stockAnterior': 125, 'stockNuevo': 120},
        {'fecha': '2024-11-17', 'producto': 'P0003', 'tipo': 'Entrada', 'cantidad': 20, 'stockAnterior': 25, 'stockNuevo': 45},
        {'fecha': '2024-11-16', 'producto': 'P0004', 'tipo': 'Salida', 'cantidad': 2, 'stockAnterior': 10, 'stockNuevo': 8},
        {'fecha': '2024-11-15', 'producto': 'P0005', 'tipo': 'Entrada', 'cantidad': 100, 'stockAnterior': 100, 'stockNuevo': 200}
    ]
}

with open('src/main/resources/datos_prueba.json', 'w') as f:
    json.dump(datos, f, indent=2)

print('JSON generado con 1000 productos')
