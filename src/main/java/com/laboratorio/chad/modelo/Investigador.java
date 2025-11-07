package com.laboratorio.chad.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un investigador del laboratorio.
 */
public class Investigador {
    private String nombre;
    private int edad;
    private List<Experimento> experimentos;

    /**
     * Constructor de Investigador.
     * @param nombre Nombre del investigador
     * @param edad Edad del investigador
     */
    public Investigador(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.experimentos = new ArrayList<>();
    }

    /**
     * Agrega un experimento a la lista del investigador.
     * @param experimento Experimento a agregar
     */
    public void agregarExperimento(Experimento experimento) {
        this.experimentos.add(experimento);
    }

    /**
     * Obtiene la cantidad de experimentos realizados por el investigador.
     * @return Cantidad de experimentos
     */
    public int getCantidadExperimentos() {
        return experimentos.size();
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<Experimento> getExperimentos() {
        return experimentos;
    }

    @Override
    public String toString() {
        return "Investigador{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", experimentos=" + experimentos.size() +
                '}';
    }
}
