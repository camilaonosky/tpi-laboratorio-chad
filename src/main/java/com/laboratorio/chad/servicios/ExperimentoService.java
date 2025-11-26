package com.laboratorio.chad.servicios;

import com.laboratorio.chad.interfaces.ILaboratorioService;
import com.laboratorio.chad.modelo.Experimento;
import com.laboratorio.chad.modelo.ExperimentoFisico;
import com.laboratorio.chad.modelo.ExperimentoQuimico;
import com.laboratorio.chad.modelo.Investigador;

import java.util.List;

public class ExperimentoService {
    /**
     * Registra un experimento químico.
     */
    public static void registrarExperimentoQuimico(ILaboratorioService laboratorio) {
        System.out.println("=== REGISTRAR EXPERIMENTO QUÍMICO ===");

        List<Investigador> investigadores = laboratorio.obtenerInvestigadores();
        if (!validarInvestigadoresDisponibles(investigadores)) {
            return;
        }

        String nombre = ConsolaService.leerTextoNoVacio("Nombre del experimento: ");
        if (nombre == null) return;

        Integer duracion = leerDuracion();
        if (duracion == null) return;

        boolean exitoso = leerResultadoExperimento();

        String reactivo = ConsolaService.leerTextoNoVacio("Tipo de reactivo: ");
        if (reactivo == null) return;

        Investigador investigador = seleccionarInvestigador(investigadores);
        if (investigador == null) return;

        ExperimentoQuimico experimento = new ExperimentoQuimico(
                nombre, duracion, exitoso, reactivo, investigador
        );

        laboratorio.registrarExperimento(experimento);
        System.out.println("Experimento químico registrado exitosamente.");
    }

    /**
     * Valida que haya investigadores disponibles.
     */
    private static boolean validarInvestigadoresDisponibles(List<Investigador> investigadores) {
        if (investigadores.isEmpty()) {
            System.out.println("No hay investigadores registrados. Registre uno primero.");
            return false;
        }
        return true;
    }

    /**
     * Lee la duración de un experimento.
     * @return La duración en minutos o null si es inválida
     */
    private static Integer leerDuracion() {
        int duracion = ConsolaService.leerEntero("Duración (en minutos): ");

        if (duracion <= 0) {
            System.out.println("La duración debe ser mayor a 0.");
            return null;
        }
        return duracion;
    }

    /**
     * Lee si el experimento fue exitoso o no.
     * @return true si fue exitoso, false si no
     */
    private static boolean leerResultadoExperimento() {
        String resultadoStr = ConsolaService.leerTexto("¿Fue exitoso? (si/no): ").toLowerCase();
        return resultadoStr.equals("si");
    }

    /**
     * Muestra lista de investigadores y permite seleccionar uno.
     * @return El investigador seleccionado o null si la selección es inválida
     */
    private static Investigador seleccionarInvestigador(List<Investigador> investigadores) {
        System.out.println("\nInvestigadores disponibles:");
        for (int i = 0; i < investigadores.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, investigadores.get(i).getNombre());
        }

        int seleccion = ConsolaService.leerEntero("Seleccione el investigador (número): ");

        if (seleccion < 1 || seleccion > investigadores.size()) {
            System.out.println("Selección inválida.");
            return null;
        }

        return investigadores.get(seleccion - 1);
    }

    /**
     * Registra un experimento físico.
     */
    public static void registrarExperimentoFisico(ILaboratorioService laboratorio) {
        System.out.println("=== REGISTRAR EXPERIMENTO FÍSICO ===");

        List<Investigador> investigadores = laboratorio.obtenerInvestigadores();
        if (!validarInvestigadoresDisponibles(investigadores)) {
            return;
        }

        String nombre = ConsolaService.leerTextoNoVacio("Nombre del experimento: ");
        if (nombre == null) return;

        Integer duracion = leerDuracion();
        if (duracion == null) return;

        boolean exitoso = leerResultadoExperimento();

        String instrumento = ConsolaService.leerTextoNoVacio("Instrumento utilizado: ");
        if (instrumento == null) return;

        ExperimentoFisico experimento = new ExperimentoFisico(
                nombre, duracion, exitoso, instrumento
        );

        if (!seleccionarMultiplesInvestigadores(experimento, investigadores)) {
            return;
        }

        laboratorio.registrarExperimento(experimento);
        System.out.println("Experimento físico registrado exitosamente.");
    }

    /**
     * Permite seleccionar múltiples investigadores para un experimento físico.
     * @return true si se agregó al menos un investigador, false en caso contrario
     */
    private static boolean seleccionarMultiplesInvestigadores(
            ExperimentoFisico experimento, List<Investigador> investigadores) {

        System.out.println("\nInvestigadores disponibles:");
        for (int i = 0; i < investigadores.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, investigadores.get(i).getNombre());
        }

        boolean agregarMas = true;
        while (agregarMas && experimento.getInvestigadores().size() < investigadores.size()) {
            int seleccion = ConsolaService.leerEntero("Seleccione investigador (número) o 0 para terminar: ");

            if (seleccion == 0) {
                agregarMas = false;
            } else if (seleccion < 1 || seleccion > investigadores.size()) {
                System.out.println("Selección inválida.");
            } else {
                Investigador investigador = investigadores.get(seleccion - 1);
                experimento.agregarInvestigador(investigador);
                System.out.println("Investigador agregado.");
            }
        }

        if (experimento.getInvestigadores().isEmpty()) {
            System.out.println("Debe asignar al menos un investigador.");
            return false;
        }

        return true;
    }

    /**
     * Muestra el listado de experimentos.
     */
    public static void mostrarListadoExperimentos(ILaboratorioService laboratorio) {
        System.out.println("=== LISTADO DE EXPERIMENTOS ===");

        List<Experimento> experimentos = laboratorio.obtenerExperimentos();
        if (experimentos.isEmpty()) {
            System.out.println("No hay experimentos registrados.");
            return;
        }

        for (int i = 0; i < experimentos.size(); i++) {
            Experimento exp = experimentos.get(i);
            System.out.printf("\n%d. %s\n", i + 1, exp.getNombre());
            System.out.printf("   Tipo: %s\n", exp.getTipo());
            System.out.printf("   Duración: %d minutos\n", exp.getDuracionMinutos());
            System.out.printf("   Resultado: %s\n", exp.getResultado());
            System.out.printf("   Detalles: %s\n", exp.getDetalles());
        }
    }

    /**
     * Muestra el experimento de mayor duración.
     */
    public static void mostrarExperimentoMayorDuracion(ILaboratorioService laboratorio) {
        System.out.println("=== EXPERIMENTO DE MAYOR DURACIÓN ===");

        Experimento mayor = laboratorio.obtenerExperimentoMayorDuracion();
        if (mayor == null) {
            System.out.println("No hay experimentos registrados.");
            return;
        }

        System.out.printf("Nombre: %s\n", mayor.getNombre());
        System.out.printf("Tipo: %s\n", mayor.getTipo());
        System.out.printf("Duración: %d minutos\n", mayor.getDuracionMinutos());
        System.out.printf("Resultado: %s\n", mayor.getResultado());
        System.out.printf("Detalles: %s\n", mayor.getDetalles());
    }

    /**
     * Muestra las estadísticas de resultados.
     */
    public static void mostrarEstadisticas(ILaboratorioService laboratorio) {
        System.out.println("=== ESTADÍSTICAS DE RESULTADOS ===");

        int exitosos = laboratorio.obtenerExperimentosExitosos();
        int fallidos = laboratorio.obtenerExperimentosFallidos();
        int total = exitosos + fallidos;

        System.out.printf("Total de experimentos: %d\n", total);
        System.out.printf("Experimentos exitosos: %d\n", exitosos);
        System.out.printf("Experimentos fallidos: %d\n", fallidos);
    }

}
