package com.laboratorio.chad.servicios;

import com.laboratorio.chad.interfaces.ILaboratorioService;
import com.laboratorio.chad.modelo.Experimento;
import com.laboratorio.chad.modelo.Investigador;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del servicio de gestión del laboratorio.
 */
public class LaboratorioService implements ILaboratorioService {
    private List<Investigador> investigadores;
    private List<Experimento> experimentos;

    /**
     * Constructor del servicio.
     */
    public LaboratorioService() {
        this.investigadores = new ArrayList<>();
        this.experimentos = new ArrayList<>();
    }

    @Override
    public void registrarInvestigador(Investigador investigador) {
        if (investigador != null) {
            investigadores.add(investigador);
        }
    }

    @Override
    public void registrarExperimento(Experimento experimento) {
        if (experimento != null) {
            experimentos.add(experimento);
        }
    }

    @Override
    public List<Experimento> obtenerExperimentos() {
        return new ArrayList<>(experimentos);
    }

    @Override
    public List<Investigador> obtenerInvestigadores() {
        return new ArrayList<>(investigadores);
    }

    @Override
    public int obtenerExperimentosExitosos() {
        int count = 0;
        for (Experimento exp : experimentos) {
            if (exp.isExitoso()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int obtenerExperimentosFallidos() {
        int count = 0;
        for (Experimento exp : experimentos) {
            if (!exp.isExitoso()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public Experimento obtenerExperimentoMayorDuracion() {
        if (experimentos.isEmpty()) {
            return null;
        }
        
        Experimento mayor = experimentos.get(0);
        for (Experimento exp : experimentos) {
            if (exp.getDuracionMinutos() > mayor.getDuracionMinutos()) {
                mayor = exp;
            }
        }
        return mayor;
    }

    @Override
    public double calcularPromedioDuracion() {
        if (experimentos.isEmpty()) {
            return 0.0;
        }
        
        int totalDuracion = 0;
        for (Experimento exp : experimentos) {
            totalDuracion += exp.getDuracionMinutos();
        }
        return (double) totalDuracion / experimentos.size();
    }

    @Override
    public double calcularPorcentajeExito() {
        if (experimentos.isEmpty()) {
            return 0.0;
        }
        
        int exitosos = obtenerExperimentosExitosos();
        return (exitosos * 100.0) / experimentos.size();
    }

    @Override
    public Investigador obtenerInvestigadorMasActivo() {
        if (investigadores.isEmpty()) {
            return null;
        }
        
        Investigador masActivo = investigadores.get(0);
        for (Investigador inv : investigadores) {
            if (inv.getCantidadExperimentos() > masActivo.getCantidadExperimentos()) {
                masActivo = inv;
            }
        }
        return masActivo;
    }
}
