package org.practica.dao;

import org.practica.model.AreasInteres;
import org.practica.model.Usuario;

import java.util.List;

public interface AreasDeInteresDAO {
    void insertar(AreasInteres areasInteres);

    List<AreasInteres> listarTodos();

    void actualizar(AreasInteres areasInteres);

    void eliminar(int id);
}
