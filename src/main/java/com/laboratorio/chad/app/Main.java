package com.laboratorio.chad.app;

import com.laboratorio.chad.interfaces.ILaboratorioService;
import com.laboratorio.chad.servicios.*;

/**
 * Clase principal con el menú interactivo del sistema.
 */
public class Main {
    private static final ILaboratorioService laboratorio = new LaboratorioService();

    public static void main(String[] args) {
        ConsolaService.mostrarCabecera();

        int opcion;
        do {
            ConsolaService.mostrarMenu();
            opcion = ConsolaService.leerEntero("Ingrese opcion: ");
            procesarOpcion(opcion);
        } while (opcion != 0);
    }


    /**
     * Procesa la opción seleccionada.
     */
    private static void procesarOpcion(int opcion) {
        System.out.println();
        switch (opcion) {
            case 1 -> InvestigadorService.registrarInvestigador(laboratorio);
            case 2 -> ExperimentoService.registrarExperimentoQuimico(laboratorio);
            case 3 -> ExperimentoService.registrarExperimentoFisico(laboratorio);
            case 4 -> ExperimentoService.mostrarListadoExperimentos(laboratorio);
            case 5 -> ExperimentoService.mostrarEstadisticas(laboratorio);
            case 6 -> ExperimentoService.mostrarExperimentoMayorDuracion(laboratorio);
            case 7 -> ReporteService.generarReporte(laboratorio);
            case 8 -> InvestigadorService.mostrarInvestigadorMasActivo(laboratorio);
            case 9 -> InvestigadorService.exportarInvestigadores(laboratorio);
            case 0 -> ConsolaService.finalizar();
            default -> System.out.println("Opción inválida. Intente nuevamente.");
        }
    }

}
