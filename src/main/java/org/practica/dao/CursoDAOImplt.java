package org.practica.dao;

import org.practica.conexion.Conexion;
import org.practica.dto.CursoDTO;
import org.practica.model.AreasInteres;
import org.practica.model.Curso;
import org.practica.model.Estudiante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAOImplt implements CursoDAO {

    private Curso construirCurso(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String titulo = rs.getString("titulo");
        String descripcion = rs.getString("descripcion");
        int duracion = rs.getInt("duracion");
        int nivel = rs.getInt("nivel");
        int profesor = rs.getInt("profesor_id");
        return new Curso(id, titulo, descripcion, duracion, nivel, profesor);
    }


    @Override
    public void insertar(Curso curso) {
        String sql = """
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
            ps.setInt(5, curso.getProfesorID());
            ps.executeUpdate();
            if (curso instanceof Curso) {
                Curso c = (Curso) curso;
                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    int nuevoId = keys.getInt(1);
                    DAOFactory.getAreasDeInteresDAO().guardarAreasCurso(nuevoId, c.getAreasInteres());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public List<Curso> listarTodos() {
        String sql = "SELECT * FROM CURSOS";
        List<Curso> cursos = new ArrayList<>();
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();
        ) {
            System.out.println("Ejecutando SELECT..."); // debug
            while (rs.next()) {
                cursos.add(construirCurso(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }


        return cursos;
    }

    @Override
    public Curso obtenerCurso(int id) {
        String sql = "SELECT * FROM CURSOS WHERE id = ?";
   try(
           Connection con = Conexion.getConnection();
           PreparedStatement ps = con.prepareStatement(sql);
           ){
       ps.setInt(1, id);
       ResultSet rs = ps.executeQuery();
       if(rs.next()){
           Curso curso = construirCurso(rs);
           curso.setAreasInteres(DAOFactory.getAreasDeInteresDAO().listarPorCurso(id));
           return curso;
       }
   }catch (SQLException e){
       e.printStackTrace(System.out);
   }

return null;

    }

    @Override
    public void actualizar(Curso curso) {
        String sql = """
                        UPDATE cursos SET titulo=?,descripcion=?,duracion=?,nivel=? WHERE id=?
                """;
        try (
                Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1, curso.getTitulo());
            ps.setString(2, curso.getDescripcion());
            ps.setInt(3, curso.getDuracion());
            ps.setInt(4, curso.getNivel());
            ps.setInt(5, curso.getId());
            ps.executeUpdate();
            DAOFactory.getAreasDeInteresDAO().guardarAreasCurso(curso.getId(), curso.getAreasInteres());

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM CURSOS WHERE id = ?";
        try (
                Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

    }

    @Override
    public List<CursoDTO> listarTodosConProfesor() {

        String sql = "SELECT c.*, u.nombre, u.apellidos FROM cursos c JOIN usuarios u ON u.id = c.profesor_id ";
     List<CursoDTO>  cursos = new ArrayList<>();
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();
        ) {
            System.out.println("Ejecutando SELECT..."); // debug
            while (rs.next()) {
    Curso curso = construirCurso(rs);
    String nombreProfesor = rs.getString("nombre") + " " + rs.getString("apellidos");
                cursos.add(new CursoDTO(curso, nombreProfesor));
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }


        return cursos;
    }

    @Override
    public void inscribirEstudiante(int estudianteId, List<Integer> cursosIds) {
        String sql = """
                INSERT INTO cursos_estudiante (fecha_inscripcion, curso_id, estudiante_id)
                SELECT CURRENT_DATE, ?, ?
                WHERE NOT EXISTS (
                    SELECT 1 FROM cursos_estudiante WHERE curso_id = ? AND estudiante_id = ?
                )
                """;
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            for (int cursoId : cursosIds) {
                ps.setInt(1, cursoId);
                ps.setInt(2, estudianteId);
                ps.setInt(3, cursoId);
                ps.setInt(4, estudianteId);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public List<Integer> listarCursosInscritos(int estudianteId) {
        String sql = "SELECT curso_id FROM cursos_estudiante WHERE estudiante_id = ?";
        List<Integer> inscritos = new ArrayList<>();
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, estudianteId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                inscritos.add(rs.getInt("curso_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return inscritos;
    }
}
