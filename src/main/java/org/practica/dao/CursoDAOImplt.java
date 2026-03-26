package org.practica.dao;

import org.practica.model.AreasInteres;
import org.practica.model.Curso;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CursoDAOImplt implements CursoDAO {

    private Curso construirCurso(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String titulo = rs.getString("titulo");
        String descripcion = rs.getString("descripcion");
        int duracion = rs.getInt("duracion");
        int nivel = rs.getInt("nivel");
        int profesor = rs.getInt("profesor");
        return new Curso(id, descripcion);
    }


    @Override
    public void insertar(Curso curso) {

    }

    @Override
    public List<Curso> listarTodos() {
        return List.of();
    }

    @Override
    public Curso obtenerCurso(int id) {
        return null;
    }

    @Override
    public void actualizar(Curso curso) {

    }

    @Override
    public void eliminar(int id) {

    }
}
