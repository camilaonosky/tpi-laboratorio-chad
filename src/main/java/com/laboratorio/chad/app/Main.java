package com.laboratorio.chad.app;

import com.laboratorio.chad.interfaces.ILaboratorioService;
import com.laboratorio.chad.modelo.*;
import com.laboratorio.chad.servicios.LaboratorioService;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal con el menú interactivo del sistema.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ILaboratorioService laboratorio = new LaboratorioService();

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("  GESTOR DE EXPERIMENTOS - LABORATORIO CHAD  ");
        System.out.println("==============================================\n");

        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 0);

        System.out.println("\n¡Gracias por usar el sistema!");
        scanner.close();
    }

    /**
     * Muestra el menú principal.
     */
    private static void mostrarMenu() {
        System.out.println("\n========== MENÚ PRINCIPAL ==========");
        System.out.println("1. Registrar investigador");
        System.out.println("2. Registrar experimento químico");
        System.out.println("3. Registrar experimento físico");
        System.out.println("4. Mostrar listado de experimentos");
        System.out.println("5. Mostrar estadísticas de resultados");
        System.out.println("6. Mostrar experimento de mayor duración");
        System.out.println("7. Generar reporte completo");
        System.out.println("8. Mostrar investigador más activo");
        System.out.println("9. Exportar investigadores a CSV");
        System.out.println("0. Salir");
        System.out.println("====================================");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Lee la opción del usuario con validación.
     */
    private static int leerOpcion() {
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine(); // para leer el enter
            return opcion;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return -1;
        }
    }

    /**
     * Procesa la opción seleccionada.
     */
    private static void procesarOpcion(int opcion) {
        System.out.println();
        switch (opcion) {
            case 1 -> registrarInvestigador();
            case 2 -> registrarExperimentoQuimico();
            case 3 -> registrarExperimentoFisico();
            case 4 -> mostrarListadoExperimentos();
            case 5 -> mostrarEstadisticas();
            case 6 -> mostrarExperimentoMayorDuracion();
            case 7 -> generarReporte();
            case 8 -> mostrarInvestigadorMasActivo();
            case 9 -> exportarInvestigadores();
            case 0 -> System.out.println("Saliendo del sistema...");
            default -> System.out.println("Opción inválida. Intente nuevamente.");
        }
    }

    /**
     * Registra un nuevo investigador.
     */
    private static void registrarInvestigador() {
        System.out.println("=== REGISTRAR INVESTIGADOR ===");
        
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }

        System.out.print("Edad: ");
        try {
            int edad = scanner.nextInt();
            scanner.nextLine();
            
            if (edad <= 0) {
                System.out.println("La edad debe ser mayor a 0.");
                return;
            }

            Investigador investigador = new Investigador(nombre, edad);
            laboratorio.registrarInvestigador(investigador);
            System.out.println("Investigador registrado exitosamente.");
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Edad inválida.");
        }
    }

    /**
     * Registra un experimento químico.
     */
    private static void registrarExperimentoQuimico() {
        System.out.println("=== REGISTRAR EXPERIMENTO QUÍMICO ===");
        
        List<Investigador> investigadores = laboratorio.obtenerInvestigadores();
        if (!validarInvestigadoresDisponibles(investigadores)) {
            return;
        }

        String nombre = leerTextoNoVacio("Nombre del experimento: ");
        if (nombre == null) return;

        Integer duracion = leerDuracion();
        if (duracion == null) return;

        boolean exitoso = leerResultadoExperimento();

        String reactivo = leerTextoNoVacio("Tipo de reactivo: ");
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
     * Lee un texto no vacío del usuario.
     * @return El texto ingresado o null si está vacío
     */
    private static String leerTextoNoVacio(String mensaje) {
        System.out.print(mensaje);
        String texto = scanner.nextLine().trim();
        
        if (texto.isEmpty()) {
            System.out.println("El texto no puede estar vacío.");
            return null;
        }
        return texto;
    }

    /**
     * Lee la duración de un experimento.
     * @return La duración en minutos o null si es inválida
     */
    private static Integer leerDuracion() {
        System.out.print("Duración (en minutos): ");
        try {
            int duracion = scanner.nextInt();
            scanner.nextLine();
            
            if (duracion <= 0) {
                System.out.println("La duración debe ser mayor a 0.");
                return null;
            }
            return duracion;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Duración inválida.");
            return null;
        }
    }

    /**
     * Lee si el experimento fue exitoso o no.
     * @return true si fue exitoso, false si no
     */
    private static boolean leerResultadoExperimento() {
        System.out.print("¿Fue exitoso? (si/no): ");
        String resultadoStr = scanner.nextLine().trim().toLowerCase();
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

        System.out.print("Seleccione el investigador (número): ");
        try {
            int seleccion = scanner.nextInt();
            scanner.nextLine();
            
            if (seleccion < 1 || seleccion > investigadores.size()) {
                System.out.println("Selección inválida.");
                return null;
            }

            return investigadores.get(seleccion - 1);
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Selección inválida.");
            return null;
        }
    }

    /**
     * Registra un experimento físico.
     */
    private static void registrarExperimentoFisico() {
        System.out.println("=== REGISTRAR EXPERIMENTO FÍSICO ===");
        
        List<Investigador> investigadores = laboratorio.obtenerInvestigadores();
        if (!validarInvestigadoresDisponibles(investigadores)) {
            return;
        }

        String nombre = leerTextoNoVacio("Nombre del experimento: ");
        if (nombre == null) return;

        Integer duracion = leerDuracion();
        if (duracion == null) return;

        boolean exitoso = leerResultadoExperimento();

        String instrumento = leerTextoNoVacio("Instrumento utilizado: ");
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
            System.out.print("Seleccione investigador (número) o 0 para terminar: ");
            try {
                int seleccion = scanner.nextInt();
                scanner.nextLine();
                
                if (seleccion == 0) {
                    agregarMas = false;
                } else if (seleccion < 1 || seleccion > investigadores.size()) {
                    System.out.println("Selección inválida.");
                } else {
                    Investigador investigador = investigadores.get(seleccion - 1);
                    experimento.agregarInvestigador(investigador);
                    System.out.println("Investigador agregado.");
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Selección inválida.");
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
    private static void mostrarListadoExperimentos() {
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
     * Muestra las estadísticas de resultados.
     */
    private static void mostrarEstadisticas() {
        System.out.println("=== ESTADÍSTICAS DE RESULTADOS ===");
        
        int exitosos = laboratorio.obtenerExperimentosExitosos();
        int fallidos = laboratorio.obtenerExperimentosFallidos();
        int total = exitosos + fallidos;

        System.out.printf("Total de experimentos: %d\n", total);
        System.out.printf("Experimentos exitosos: %d\n", exitosos);
        System.out.printf("Experimentos fallidos: %d\n", fallidos);
    }

    /**
     * Muestra el experimento de mayor duración.
     */
    private static void mostrarExperimentoMayorDuracion() {
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
     * Genera un reporte completo del laboratorio.
     */
    private static void generarReporte() {
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

    /**
     * Muestra el investigador más activo.
     */
    private static void mostrarInvestigadorMasActivo() {
        System.out.println("=== INVESTIGADOR MÁS ACTIVO ===");
        
        Investigador masActivo = laboratorio.obtenerInvestigadorMasActivo();
        if (masActivo == null) {
            System.out.println("No hay investigadores registrados.");
            return;
        }

        System.out.printf("Nombre: %s\n", masActivo.getNombre());
        System.out.printf("Edad: %d años\n", masActivo.getEdad());
        System.out.printf("Cantidad de experimentos: %d\n", masActivo.getCantidadExperimentos());
    }

    /**
     * Exporta los investigadores a un archivo CSV.
     */
    private static void exportarInvestigadores() {
        System.out.println("=== EXPORTAR INVESTIGADORES A CSV ===");
        
        List<Investigador> investigadores = laboratorio.obtenerInvestigadores();
        if (investigadores.isEmpty()) {
            System.out.println("No hay investigadores para exportar.");
            return;
        }

        System.out.print("Nombre del archivo (sin extensión): ");
        String nombreBase = scanner.nextLine().trim();
        
        if (nombreBase.isEmpty()) {
            nombreBase = "investigadores";
        }

        String nombreArchivo = nombreBase + ".csv";
        boolean exito = laboratorio.exportarInvestigadoresCSV(nombreArchivo);
        
        if (exito) {
            System.out.printf("Archivo '%s' exportado exitosamente.\n", nombreArchivo);
        } else {
            System.out.println("Error al exportar el archivo.");
        }
    }
}
