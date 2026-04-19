package org.practica.dao;

import org.practica.model.Contenido;

public interface ProgresoDAO {
    void registrarAcceso(int estudianteId, int contenidoId);
    Contenido buscarPorId(int contenidoId);
}
