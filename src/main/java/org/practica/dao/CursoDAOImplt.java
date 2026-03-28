package org.practica.dao;

import org.practica.conexion.Conexion;
import org.practica.model.AreasInteres;
import org.practica.model.Curso;
import org.practica.model.Estudiante;

import java.sql.*;
import java.util.List;

public class CursoDAOImplt implements CursoDAO {

    private Curso construirCurso(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String titulo = rs.getString("titulo");
        String descripcion = rs.getString("descripcion");
        int duracion = rs.getInt("duracion");
        int nivel = rs.getInt("nivel");
        int profesor = rs.getInt("profesor_id");
        return new Curso(id, titulo,descripcion,duracion,nivel,profesor);
    }


    @Override
    public void insertar(Curso curso) {
String sql= """
        INSERT INTO CURSOS 
        (titulo,descripcion,duracion,nivel,profesor_id) 
        VALUES(?,?,?,?,?)
        """;
try (Connection con = Conexion.getConnection();
     PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
    ps.setString(1, curso.getTitulo());
    ps.setString(2, curso.getDescripcion());
    ps.setInt(3, curso.getDuracion());
    ps.setInt(4, curso.getNivel());
    ps.setInt(5,curso.getProfesorID());
    ps.executeUpdate();
    if (curso instanceof Curso) {
        Curso c = (Curso) curso;
        ResultSet keys = ps.getGeneratedKeys();
        if (keys.next()) {
            int nuevoId = keys.getInt(1);
            DAOFactory.getAreasDeInteresDAO().guardarAreasCurso(nuevoId, c.getAreasInteres());
        }
    }
}catch (SQLException e){
    System.out.println(e);
}
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
