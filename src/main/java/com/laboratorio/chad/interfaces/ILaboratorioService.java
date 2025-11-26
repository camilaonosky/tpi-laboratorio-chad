package com.laboratorio.chad.interfaces;

import com.laboratorio.chad.modelo.Experimento;
import com.laboratorio.chad.modelo.Investigador;
import java.util.List;

/**
 * Interfaz que define los servicios del laboratorio.
 */
public interface ILaboratorioService {
    
    /**
     * Registra un nuevo investigador.
     * @param investigador Investigador a registrar
     */
    void registrarInvestigador(Investigador investigador);
    
    /**
     * Registra un nuevo experimento.
     * @param experimento Experimento a registrar
     */
    void registrarExperimento(Experimento experimento);
    
    /**
     * Obtiene la lista de todos los experimentos.
     * @return Lista de experimentos
     */
    List<Experimento> obtenerExperimentos();
    
    /**
     * Obtiene la lista de todos los investigadores.
     * @return Lista de investigadores
     */
    List<Investigador> obtenerInvestigadores();
    
    /**
     * Calcula el total de experimentos exitosos.
     * @return Cantidad de experimentos exitosos
     */
    int obtenerExperimentosExitosos();
    
    /**
     * Calcula el total de experimentos fallidos.
     * @return Cantidad de experimentos fallidos
     */
    int obtenerExperimentosFallidos();
    
    /**
     * Encuentra el experimento de mayor duración.
     * @return Experimento de mayor duración o null si no hay experimentos
     */
    Experimento obtenerExperimentoMayorDuracion();
    
    /**
     * Calcula el promedio de duración de los experimentos.
     * @return Promedio de duración en minutos
     */
    double calcularPromedioDuracion();
    
    /**
     * Calcula el porcentaje de éxito de los experimentos.
     * @return Porcentaje de éxito (0-100)
     */
    double calcularPorcentajeExito();
    
    /**
     * Encuentra al investigador que realizó más experimentos.
     * @return Investigador con más experimentos o null si no hay investigadores
     */
    Investigador obtenerInvestigadorMasActivo();
}
