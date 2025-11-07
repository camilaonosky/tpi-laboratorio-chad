package com.laboratorio.chad.modelo;

/**
 * Clase abstracta que representa un experimento en el laboratorio.
 */
public abstract class Experimento {
    private String nombre;
    private int duracionMinutos;
    private boolean exitoso;

    /**
     * Constructor de Experimento.
     * @param nombre Nombre del experimento
     * @param duracionMinutos Duración en minutos
     * @param exitoso Indica si el experimento fue exitoso
     */
    public Experimento(String nombre, int duracionMinutos, boolean exitoso) {
        this.nombre = nombre;
        this.duracionMinutos = duracionMinutos;
        this.exitoso = exitoso;
    }

    /**
     * Método abstracto para obtener el tipo de experimento.
     * @return Tipo de experimento
     */
    public abstract String getTipo();

    /**
     * Método abstracto para obtener información detallada del experimento.
     * @return Información detallada
     */
    public abstract String getDetalles();

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public boolean isExitoso() {
        return exitoso;
    }

    public void setExitoso(boolean exitoso) {
        this.exitoso = exitoso;
    }

    public String getResultado() {
        return exitoso ? "Éxito" : "Fallo";
    }

    @Override
    public String toString() {
        return "Experimento{" +
                "nombre='" + nombre + '\'' +
                ", duracion=" + duracionMinutos + " min" +
                ", resultado=" + getResultado() +
                '}';
    }
}
