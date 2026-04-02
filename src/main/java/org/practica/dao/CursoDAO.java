package org.practica.dao;

import org.practica.dto.CursoDTO;
import org.practica.model.Curso;

import java.util.List;

public interface CursoDAO {
    void insertar(Curso curso);
    List<Curso> listarTodos();
    Curso obtenerCurso(int id);
    void actualizar(Curso curso);
    void eliminar(int id);
    List<CursoDTO> listarTodosConProfesor();
}
