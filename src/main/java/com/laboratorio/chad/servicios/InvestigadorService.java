package com.laboratorio.chad.servicios;

import com.laboratorio.chad.interfaces.ILaboratorioService;
import com.laboratorio.chad.modelo.Investigador;
import com.laboratorio.chad.utils.ConsolaUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class InvestigadorService {
    private final ILaboratorioService laboratorio;

    public InvestigadorService(ILaboratorioService laboratorio) {
        this.laboratorio = laboratorio;
    }

    /**
     * Registra un nuevo investigador.
     */
    public void registrarInvestigador() {
        System.out.println("=== REGISTRAR INVESTIGADOR ===");

        String nombre = ConsolaUtil.leerTextoNoVacio("Nombre: ");

        int edad = ConsolaUtil.leerEntero("Edad: ");
        if (edad <= 0) {
            System.out.println("La edad debe ser mayor a 0.");
            return;
        }

        Investigador investigador = new Investigador(nombre, edad);
        laboratorio.registrarInvestigador(investigador);
        System.out.println("Investigador registrado exitosamente.");
    }

    /**
     * Muestra el investigador más activo.
     */
    public void mostrarInvestigadorMasActivo() {
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
    public void exportarInvestigadores() {
        System.out.println("=== EXPORTAR INVESTIGADORES A CSV ===");

        List<Investigador> investigadores = laboratorio.obtenerInvestigadores();
        if (investigadores.isEmpty()) {
            System.out.println("No hay investigadores para exportar.");
            return;
        }

        String nombreBase = ConsolaUtil.leerTextoNoVacio("Nombre del archivo (sin extensión): ");
        String nombreArchivo = nombreBase + ".csv";

        boolean exito = exportarInvestigadoresCSV(nombreArchivo, investigadores);
        if (exito) {
            System.out.printf("Archivo '%s' exportado exitosamente.\n", nombreArchivo);
        } else {
            System.out.println("Error al exportar el archivo.");
        }
    }

    private static boolean exportarInvestigadoresCSV(String nombreArchivo, List<Investigador> investigadores) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            // Escribir cabecera
            writer.println("nombre,edad,cantidad_experimentos");

            // Escribir datos de cada investigador
            for (Investigador inv : investigadores) {
                writer.printf("%s,%d,%d%n",
                        inv.getNombre(),
                        inv.getEdad(),
                        inv.getCantidadExperimentos());
            }

            return true;
        } catch (IOException e) {
            System.err.println("Error al exportar archivo CSV: " + e.getMessage());
            return false;
        }
    }
}
