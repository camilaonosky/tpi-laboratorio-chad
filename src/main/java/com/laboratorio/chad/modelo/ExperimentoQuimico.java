package com.laboratorio.chad.modelo;

/**
 * Clase que representa un experimento químico.
 * Solo puede ser realizado por un investigador.
 */
public class ExperimentoQuimico extends Experimento {
    private String tipoReactivo;
    private Investigador investigador;

    /**
     * Constructor de ExperimentoQuimico.
     * @param nombre Nombre del experimento
     * @param duracionMinutos Duración en minutos
     * @param exitoso Indica si fue exitoso
     * @param tipoReactivo Tipo de reactivo utilizado
     * @param investigador Investigador que realiza el experimento
     */
    public ExperimentoQuimico(String nombre, int duracionMinutos, boolean exitoso, 
                             String tipoReactivo, Investigador investigador) {
        super(nombre, duracionMinutos, exitoso);
        this.tipoReactivo = tipoReactivo;
        this.investigador = investigador;
        investigador.agregarExperimento(this);
    }

    @Override
    public String getTipo() {
        return "Químico";
    }

    @Override
    public String getDetalles() {
        return "Reactivo: " + tipoReactivo + ", Investigador: " + investigador.getNombre();
    }

    // Getters y Setters
    public String getTipoReactivo() {
        return tipoReactivo;
    }

    public void setTipoReactivo(String tipoReactivo) {
        this.tipoReactivo = tipoReactivo;
    }

    public Investigador getInvestigador() {
        return investigador;
    }

    public void setInvestigador(Investigador investigador) {
        this.investigador = investigador;
    }

    @Override
    public String toString() {
        return super.toString() + " [" + getTipo() + "] - " + getDetalles();
    }
}
