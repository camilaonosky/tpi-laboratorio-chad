package com.laboratorio.chad.servicios;

import com.laboratorio.chad.interfaces.ILaboratorioService;
import com.laboratorio.chad.modelo.Experimento;
import com.laboratorio.chad.modelo.Investigador;

import java.util.List;

public class ReporteService {
    private final ILaboratorioService laboratorio;

    public ReporteService(ILaboratorioService laboratorio) {
        this.laboratorio = laboratorio;
    }

    /**
     * Genera un reporte completo del laboratorio.
     */
    public void generarReporte() {
        System.out.println("=== REPORTE COMPLETO DEL LABORATORIO ===\n");

        List<Experimento> experimentos = laboratorio.obtenerExperimentos();
        if (experimentos.isEmpty()) {
            System.out.println("No hay datos suficientes para generar el reporte.");
            return;
        }

        double promedio = laboratorio.calcularPromedioDuracion();
        double porcentajeExito = laboratorio.calcularPorcentajeExito();
        int exitosos = laboratorio.obtenerExperimentosExitosos();
        int fallidos = laboratorio.obtenerExperimentosFallidos();

        System.out.println("ESTADÍSTICAS GENERALES:");
        System.out.printf("   • Total de experimentos: %d\n", experimentos.size());
        System.out.printf("   • Experimentos exitosos: %d\n", exitosos);
        System.out.printf("   • Experimentos fallidos: %d\n", fallidos);
        System.out.printf("   • Promedio de duración: %.2f minutos\n", promedio);
        System.out.printf("   • Porcentaje de éxito: %.2f%%\n", porcentajeExito);

        Experimento mayor = laboratorio.obtenerExperimentoMayorDuracion();
        System.out.println("\n  EXPERIMENTO MÁS LARGO:");
        System.out.printf("   • %s (%d minutos)\n", mayor.getNombre(), mayor.getDuracionMinutos());

        Investigador masActivo = laboratorio.obtenerInvestigadorMasActivo();
        if (masActivo != null) {
            System.out.println("\nINVESTIGADOR MÁS ACTIVO:");
            System.out.printf("   • %s (%d experimentos)\n",
                    masActivo.getNombre(), masActivo.getCantidadExperimentos());
        }
    }


}
