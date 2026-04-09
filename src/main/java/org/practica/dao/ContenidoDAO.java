package org.practica.dao;

import org.practica.model.Contenido;

import java.util.List;

public interface ContenidoDAO {
    void insertar(Contenido contenido);
    void eliminar (int id);
    List<Contenido> listarTodos();
    List<Contenido> listarPorCurso(int id);
    Void actualizar(Contenido contenido);
}
