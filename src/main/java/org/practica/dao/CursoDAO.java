package org.practica.dao;

import org.practica.dto.CursoAnalisisDTO;
import org.practica.dto.CursoDTO;
import org.practica.dto.EstudianteProgresoDTO;
import org.practica.model.Curso;

import java.util.List;

public interface CursoDAO {
    void insertar(Curso curso);
    List<Curso> listarTodos();
    Curso obtenerCurso(int id);
    void actualizar(Curso curso);
    void eliminar(int id);
    List<CursoDTO> listarTodosConProfesor();
    void inscribirEstudiante(int estudianteId, List<Integer> cursosIds);
    List<Integer> listarCursosInscritos(int estudianteId);
    List<Curso> listarInscritosConContenidos(int estudianteId);
    void valorarCurso(int estudianteId, int cursoId, int valoracion);
    int obtenerValoracion(int estudianteId, int cursoId);
    void marcarFavorito(int estudianteId, int cursoId, boolean favorito);
    boolean obtenerFavorito(int estudianteId, int cursoId);
    List<Curso> listarFavoritos(int estudianteId);
    List<CursoAnalisisDTO> listarAnalisisCursos(int profesorId);
    List<EstudianteProgresoDTO> listarEstudiantesConProgreso(int profesorId);
}
