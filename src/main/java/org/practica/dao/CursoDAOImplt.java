package org.practica.dao;

import org.practica.conexion.Conexion;
import org.practica.dto.CursoAnalisisDTO;
import org.practica.dto.CursoDTO;
import org.practica.dto.CursoParticipacionDTO;
import org.practica.dto.EstudianteProgresoDTO;
import org.practica.model.AreasInteres;
import org.practica.model.Contenido;
import org.practica.model.Curso;
import org.practica.model.Estudiante;
import org.practica.model.Contenido;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase cursoDaoimplt
 */
public class CursoDAOImplt implements CursoDAO {
    /**
     * metodo que emite un objeto curso.
     *
     * @param rs
     * @return Curso
     * @throws SQLException
     */
    private Curso construirCurso(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String titulo = rs.getString("titulo");
        String descripcion = rs.getString("descripcion");
        int duracion = rs.getInt("duracion");
        int nivel = rs.getInt("nivel");
        int profesor = rs.getInt("profesor_id");
        return new Curso(id, titulo, descripcion, duracion, nivel, profesor);
    }

    /**
     * Método inserta en BD
     *
     * @param curso
     */
    @Override
    public void insertar(Curso curso) {
        String sql = """
                INSERT INTO CURSOS 
                (titulo,descripcion,duracion,nivel,profesor_id) 
                VALUES(?,?,?,?,?)
                """;
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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

    /**
     * Método devuelve lista de cursos
     *
     * @return curso
     */
    @Override
    public List<Curso> listarTodos() {
        String sql = "SELECT * FROM CURSOS";
        List<Curso> cursos = new ArrayList<>();
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery();) {

            while (rs.next()) {
                cursos.add(construirCurso(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }


        return cursos;
    }

    /**
     * Método devuelve objeto
     *
     * @param id
     * @return curso
     */
    @Override
    public Curso obtenerCurso(int id) {
        String sql = "SELECT * FROM CURSOS WHERE id = ?";
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Curso curso = construirCurso(rs);
                curso.setAreasInteres(DAOFactory.getAreasDeInteresDAO().listarPorCurso(id));
                return curso;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return null;

    }

    /**
     * Método actualiza BD
     *
     * @param curso
     */
    @Override
    public void actualizar(Curso curso) {
        String sql = """
                        UPDATE cursos SET titulo=?,descripcion=?,duracion=?,nivel=? WHERE id=?
                """;
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
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

    /**
     * Método elimina objeto de BD
     *
     * @param id
     */
    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM CURSOS WHERE id = ?";
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

    }

    /**
     * Método devuleve relación de cursos creados por su profesor
     *
     * @return curso
     */
    @Override
    public List<CursoDTO> listarTodosConProfesor() {

        String sql = "SELECT c.*, u.nombre, u.apellidos FROM cursos c JOIN usuarios u ON u.id = c.profesor_id ";
        List<CursoDTO> cursos = new ArrayList<>();
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery();) {
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

    /**
     * Inserta en BD la inscripción de un estudiante a un curso
     *
     * @param estudianteId
     * @param cursosIds
     */
    @Override
    public void inscribirEstudiante(int estudianteId, List<Integer> cursosIds) {
        String sql = """
                INSERT INTO cursos_estudiante (fecha_inscripcion, curso_id, estudiante_id)
                SELECT CURRENT_DATE, ?, ?
                WHERE NOT EXISTS (
                    SELECT 1 FROM cursos_estudiante WHERE curso_id = ? AND estudiante_id = ?
                )
                """;
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
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

    /**
     * Método devuelve relación de inscritos en un curso
     *
     * @param estudianteId
     * @return inscritos
     */
    @Override
    public List<Integer> listarCursosInscritos(int estudianteId) {
        String sql = "SELECT curso_id FROM cursos_estudiante WHERE estudiante_id = ?";
        List<Integer> inscritos = new ArrayList<>();
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
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

    /**
     * Método lista de estudiantes en cursos con sus contenidos
     *
     * @param estudianteId
     * @return
     */
    @Override
    public List<Curso> listarInscritosConContenidos(int estudianteId) {
        String sql = """
                SELECT c.id, c.titulo, c.descripcion, c.duracion, c.nivel, c.profesor_id,
                       co.id AS co_id, co.titulo AS co_titulo, co.tipo, co.url, co.orden,                                                               
                       co.fecha_inicio, co.fecha_fin                                                                                                    
                FROM cursos c                                                                                                                           
                JOIN cursos_estudiante ce ON c.id = ce.curso_id                                                                                         
                LEFT JOIN contenidos co ON co.curso_id = c.id                                                                                           
                WHERE ce.estudiante_id = ?
                ORDER BY c.id, co.orden                                                                                                                 
                """;
        Map<Integer, Curso> mapa = new LinkedHashMap<>();
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, estudianteId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int cursoId = rs.getInt("id");
                Curso curso = mapa.get(cursoId);
                if (curso == null) {
                    curso = construirCurso(rs);
                    mapa.put(cursoId, curso);
                }
                int coId = rs.getInt("co_id");
                if (coId != 0) {
                    Contenido c = new Contenido(coId, rs.getString("co_titulo"), rs.getString("tipo"), rs.getString("url"), rs.getInt("orden"), rs.getDate("fecha_inicio") != null ? rs.getDate("fecha_inicio").toLocalDate() : null, rs.getDate("fecha_fin") != null ? rs.getDate("fecha_fin").toLocalDate() : null, cursoId);
                    curso.getContenidos().add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return new ArrayList<>(mapa.values());
    }

    /**
     * Método modifica la valoración de un estudiante a un curso
     *
     * @param estudianteId
     * @param cursoId
     * @param valoracion
     */
    @Override
    public void valorarCurso(int estudianteId, int cursoId, int valoracion) {
        String sql = "UPDATE cursos_estudiante SET valoracion=? WHERE estudiante_id=? AND curso_id=?";
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, valoracion);
            ps.setInt(2, estudianteId);
            ps.setInt(3, cursoId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * Método devuelve valoración de un estudiante a un curso.
     *
     * @param estudianteId
     * @param cursoId
     * @return valoración
     */
    @Override
    public int obtenerValoracion(int estudianteId, int cursoId) {
        String sql = "SELECT valoracion FROM cursos_estudiante WHERE estudiante_id=? AND curso_id=?";
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, estudianteId);
            ps.setInt(2, cursoId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("valoracion");
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }

    /**
     * Método modifica valoración de un curso por el estudiante.
     *
     * @param estudianteId
     * @param cursoId
     * @param favorito
     */
    @Override
    public void marcarFavorito(int estudianteId, int cursoId, boolean favorito) {
        String sql = "UPDATE cursos_estudiante SET favorito=? WHERE estudiante_id=? AND curso_id=?";
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, favorito);
            ps.setInt(2, estudianteId);
            ps.setInt(3, cursoId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * Método devulve curso favorito de un estudiante
     *
     * @param estudianteId
     * @param cursoId
     * @return favorito
     */
    @Override
    public boolean obtenerFavorito(int estudianteId, int cursoId) {
        String sql = "SELECT favorito FROM cursos_estudiante WHERE estudiante_id=? AND curso_id=?";
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, estudianteId);
            ps.setInt(2, cursoId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getBoolean("favorito");
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    /**
     * Método devuelve numero de estudiantes en un curso y media de la valoración.
     *
     * @param profesorId
     * @return lista
     */
    @Override
    public List<CursoAnalisisDTO> listarAnalisisCursos(int profesorId) {
        String sql = """
                SELECT c.id, c.titulo,
                       COUNT(ce.estudiante_id) AS num_inscritos,
                       COALESCE(ROUND(AVG(ce.valoracion), 1), 0) AS media_valoracion
                FROM cursos c
                LEFT JOIN cursos_estudiante ce ON c.id = ce.curso_id
                WHERE c.profesor_id = ?
                GROUP BY c.id, c.titulo
                ORDER BY c.titulo
                """;
        List<CursoAnalisisDTO> lista = new ArrayList<>();
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, profesorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new CursoAnalisisDTO(rs.getInt("id"), rs.getString("titulo"), rs.getInt("num_inscritos"), rs.getDouble("media_valoracion")));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return lista;
    }

    /**
     * Método devuleve por estudiante datos personales, total de contenidos por curso y contenido completado, nombre del curso
     *
     * @param profesorId
     * @return lista
     */
    @Override
    public List<EstudianteProgresoDTO> listarEstudiantesConProgreso(int profesorId) {
        String sql = """
                SELECT u.nombre, u.apellidos, u.email,
                       c.titulo AS curso_titulo,
                       COUNT(DISTINCT co.id) AS total,
                       COUNT(DISTINCT pe.id_contenido) AS completados
                FROM cursos_estudiante ce
                JOIN usuarios u ON u.id = ce.estudiante_id
                JOIN cursos c ON c.id = ce.curso_id
                LEFT JOIN contenidos co ON co.curso_id = c.id
                LEFT JOIN progreso_estudiante pe ON pe.id_estudiante = ce.estudiante_id
                                                 AND pe.id_contenido = co.id
                WHERE c.profesor_id = ?
                GROUP BY u.id, u.nombre, u.apellidos, u.email, c.id, c.titulo
                ORDER BY u.apellidos, u.nombre, c.titulo
                """;
        List<EstudianteProgresoDTO> lista = new ArrayList<>();
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, profesorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new EstudianteProgresoDTO(rs.getString("nombre"), rs.getString("apellidos"), rs.getString("email"), rs.getString("curso_titulo"), rs.getInt("total"), rs.getInt("completados")));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return lista;
    }

    /**
     * Método devuelve calculo de la media de valoración y el progreso medio.
     *
     * @return lista
     */
    @Override
    public List<CursoParticipacionDTO> listarParticipacionPorCurso() {
        String sql = """
                SELECT c.titulo,
                       COALESCE(ROUND(AVG(ce.valoracion), 1), 0)  AS media_valoracion,
                       COALESCE(ROUND(
                           100.0 * COUNT(DISTINCT pe.id_contenido)
                           / NULLIF(COUNT(DISTINCT co.id) * COUNT(DISTINCT ce.estudiante_id), 0)
                       , 1), 0) AS progreso_medio
                FROM cursos c
                LEFT JOIN cursos_estudiante ce  ON ce.curso_id      = c.id
                LEFT JOIN contenidos co         ON co.curso_id      = c.id
                LEFT JOIN progreso_estudiante pe ON pe.id_contenido = co.id
                                                AND pe.id_estudiante = ce.estudiante_id
                GROUP BY c.id, c.titulo
                ORDER BY c.titulo
                """;
        List<CursoParticipacionDTO> lista = new ArrayList<>();
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new CursoParticipacionDTO(rs.getString("titulo"), rs.getDouble("media_valoracion"), rs.getDouble("progreso_medio")));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return lista;
    }

    /**
     * Método devuleve lista de estudiantes por curso.
     *
     * @return lista
     */
    @Override
    public List<CursoAnalisisDTO> listarInscripcionesPorCurso() {
        String sql = """
                SELECT c.id, c.titulo, COUNT(ce.estudiante_id) AS num_inscritos
                FROM cursos c
                LEFT JOIN cursos_estudiante ce ON c.id = ce.curso_id
                GROUP BY c.id, c.titulo
                ORDER BY num_inscritos DESC
                """;
        List<CursoAnalisisDTO> lista = new ArrayList<>();
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new CursoAnalisisDTO(rs.getInt("id"), rs.getString("titulo"), rs.getInt("num_inscritos"), 0));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return lista;
    }

    /**
     * Método devuelve numero de estudiantes en un curso.
     *
     * @return int
     */
    @Override
    public int contarInscripciones() {
        String sql = "SELECT COUNT(*) FROM cursos_estudiante";
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }

    /**
     * Método devuelve lista de cursos favoritos por estudiante
     *
     * @param estudianteId
     * @return list
     */
    @Override
    public List<Curso> listarFavoritos(int estudianteId) {
        String sql = """
                SELECT c.* FROM cursos c
                JOIN cursos_estudiante ce ON c.id = ce.curso_id
                WHERE ce.estudiante_id = ? AND ce.favorito = true
                """;
        List<Curso> favoritos = new ArrayList<>();
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, estudianteId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                favoritos.add(construirCurso(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return favoritos;
    }
}

