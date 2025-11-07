# Gestor de Experimentos - Laboratorio Chad

Sistema de gestión de experimentos científicos para el Laboratorio Chad.

## Descripción

Este proyecto implementa un sistema para registrar y gestionar experimentos científicos (químicos y físicos) realizados por investigadores. Permite llevar un control de los experimentos, generar estadísticas y exportar reportes.

## Funcionalidades

1. Registrar investigadores
2. Registrar experimentos químicos (un investigador)
3. Registrar experimentos físicos (uno o varios investigadores)
4. Mostrar listado de experimentos con tipo y resultado
5. Mostrar estadísticas (éxitos/fallos)
6. Calcular experimento de mayor duración
7. Generar reporte completo (promedio duración, % éxito)
8. Mostrar investigador más activo
9. Exportar investigadores a CSV

## Cómo ejecutar

```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar la aplicación
mvn exec:java -Dexec.mainClass="com.laboratorio.chad.app.Main"
```

## Conceptos de POO Implementados

- **Encapsulamiento**: Atributos privados con getters/setters
- **Herencia**: `ExperimentoQuimico` y `ExperimentoFisico` heredan de `Experimento`
- **Polimorfismo**: Métodos abstractos `getTipo()` y `getDetalles()`
- **Composición**: `Investigador` contiene lista de `Experimento`
- **Interfaces**: `ILaboratorioService` define el contrato de servicios
- **Abstracción**: Clase abstracta `Experimento`

## Autor

Camila Onosky
