package org.practica.dao;

import org.practica.model.AreasInteres;

import java.util.List;

/**
 * Inerfaz de Areas de interes DAO
 */

public interface AreasDeInteresDAO {
    void insertar(AreasInteres areasInteres);

    List<AreasInteres> listarTodos();

    List<AreasInteres> listarPorUsuario(int usuarioId);

    void guardarAreasUsuario(int usuarioId, List<AreasInteres> areas);

    void actualizar(AreasInteres areasInteres);

    void eliminar(int id);

    List<AreasInteres> listarPorCurso(int cursoId);

    void guardarAreasCurso(int cursoId, List<AreasInteres> areas);
}
