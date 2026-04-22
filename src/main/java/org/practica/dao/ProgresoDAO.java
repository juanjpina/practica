package org.practica.dao;

import org.practica.model.Progreso;

import java.util.List;

/**
 * Interfaz de progreso DAO
 */
public interface ProgresoDAO {
    void registrarAcceso(int estudianteId, int contenidoId);

    Progreso buscarPorId(int progresoId);

    boolean existeProgreso(int estudianteId, int contenidoId);

    List<Progreso> listarProgresoPorCurso(int estudianteId, int cursoId);
}
