package com.laboratorio.chad.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un experimento físico.
 * Puede ser realizado por uno o varios investigadores.
 */
public class ExperimentoFisico extends Experimento {
    private String instrumentoUtilizado;
    private List<Investigador> investigadores;

    /**
     * Constructor de ExperimentoFisico.
     * @param nombre Nombre del experimento
     * @param duracionMinutos Duración en minutos
     * @param exitoso Indica si fue exitoso
     * @param instrumentoUtilizado Instrumento utilizado
     */
    public ExperimentoFisico(String nombre, int duracionMinutos, boolean exitoso, 
                            String instrumentoUtilizado) {
        super(nombre, duracionMinutos, exitoso);
        this.instrumentoUtilizado = instrumentoUtilizado;
        this.investigadores = new ArrayList<>();
    }

    /**
     * Agrega un investigador al experimento.
     * @param investigador Investigador a agregar
     */
    public void agregarInvestigador(Investigador investigador) {
        if (!investigadores.contains(investigador)) {
            investigadores.add(investigador);
            investigador.agregarExperimento(this);
        }
    }

    @Override
    public String getTipo() {
        return "Físico";
    }

    @Override
    public String getDetalles() {
        StringBuilder sb = new StringBuilder();
        sb.append("Instrumento: ").append(instrumentoUtilizado);
        sb.append(", Investigadores: ");
        for (int i = 0; i < investigadores.size(); i++) {
            sb.append(investigadores.get(i).getNombre());
            if (i < investigadores.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    // Getters y Setters
    public String getInstrumentoUtilizado() {
        return instrumentoUtilizado;
    }

    public void setInstrumentoUtilizado(String instrumentoUtilizado) {
        this.instrumentoUtilizado = instrumentoUtilizado;
    }

    public List<Investigador> getInvestigadores() {
        return investigadores;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + getTipo() + "] - " + getDetalles();
    }
}
