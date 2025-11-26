package com.laboratorio.chad.servicios;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsolaService {
    public static final Scanner scanner = new Scanner(System.in);

    public static void mostrarCabecera() {
        System.out.println("==============================================");
        System.out.println("  GESTOR DE EXPERIMENTOS - LABORATORIO CHAD  ");
        System.out.println("==============================================\n");
    }

    /**
     * Muestra el menú principal.
     */
    public static void mostrarMenu() {
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
    }

    public static void finalizar() {
        System.out.println("\n¡Gracias por usar el sistema!");
        scanner.close();
    }

    /**
     * Lee un texto no vacío del usuario.
     * @return El texto ingresado o null si está vacío
     */
    public static String leerTextoNoVacio(String mensaje) {
        String texto = ConsolaService.leerTexto(mensaje);
        while (texto.isEmpty()) {
            System.out.println("El texto no puede estar vacío.");
            texto = ConsolaService.leerTexto(mensaje);
        }
        return texto;
    }

    public static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return leerTexto();
    }

    public static String leerTexto() {
        return scanner.nextLine().trim();
    }


    public static int leerEntero(String mensaje) {
        Integer entero = null;
        while(entero == null) {
            try {
                System.out.print(mensaje);
                entero = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Numero inválido.");
            }
        }
        return entero;
    }

}
