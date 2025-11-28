package com.laboratorio.chad.app;

import com.laboratorio.chad.interfaces.ILaboratorioService;
import com.laboratorio.chad.servicios.*;
import com.laboratorio.chad.utils.ConsolaUtil;

/**
 * Clase principal con el menú interactivo del sistema.
 */
public class Main {
    private static final ILaboratorioService laboratorio = new LaboratorioService();
    private static final InvestigadorService investigadorService = new InvestigadorService(laboratorio);
    private static final ExperimentoService experimentoService = new ExperimentoService(laboratorio);
    private static final ReporteService reporteService = new ReporteService(laboratorio);

    public static void main(String[] args) {
        ConsolaUtil.mostrarCabecera();

        int opcion;
        do {
            ConsolaUtil.mostrarMenu();
            opcion = ConsolaUtil.leerEntero("Ingrese opcion: ");
            procesarOpcion(opcion);
        } while (opcion != 0);
    }


    /**
     * Procesa la opción seleccionada.
     */
    private static void procesarOpcion(int opcion) {
        System.out.println();

        switch (opcion) {
            case 1 -> investigadorService.registrarInvestigador();
            case 2 -> experimentoService.registrarExperimentoQuimico();
            case 3 -> experimentoService.registrarExperimentoFisico();
            case 4 -> experimentoService.mostrarListadoExperimentos();
            case 5 -> experimentoService.mostrarEstadisticas();
            case 6 -> experimentoService.mostrarExperimentoMayorDuracion();
            case 7 -> reporteService.generarReporte();
            case 8 -> investigadorService.mostrarInvestigadorMasActivo();
            case 9 -> investigadorService.exportarInvestigadores();
            case 0 -> ConsolaUtil.finalizar();
            default -> System.out.println("Opción inválida. Intente nuevamente.");
        }
    }

}
