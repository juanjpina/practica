package org.practica.dao;

import org.practica.model.Contenido;

import java.util.List;

/**
 * Interfaz de Contenido DAO
 */
public interface ContenidoDAO {
    void insertar(Contenido contenido);

    void eliminar(int id);

    List<Contenido> listarTodos();

    Contenido buscarPorId(int id);

    void actualizar(Contenido contenido);
}
