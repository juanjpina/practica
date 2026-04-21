package org.practica.dao;

import org.practica.conexion.Conexion;
import org.practica.model.Contenido;
import org.practica.model.Progreso;
import org.practica.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProgresoDAOImplt implements ProgresoDAO {

    private Progreso construirProgreso(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int estudianteId = rs.getInt("id_estudiante");
        int contenidoId = rs.getInt("id_contenido");
        boolean completado = rs.getBoolean("completado");
        LocalDate fechaAcceso = rs.getDate("fecha_acceso").toLocalDate();
        return new Progreso(id, estudianteId, contenidoId, completado, fechaAcceso);
    }


    @Override
    public void registrarAcceso(int estudianteId, int contenidoId) {
        String sql = "INSERT INTO progreso_estudiante (id_estudiante, id_contenido, completado, fecha_acceso) VALUES(?,?,?,?)";
        try (
                Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setInt(1, estudianteId);
            ps.setInt(2, contenidoId);
            ps.setBoolean(3, true);
            ps.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public Progreso buscarPorId(int contenidoId) {
        String sql = "SELECT * FROM progreso_estudiante WHERE id=?";

        try (
                Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);

        ) {
            ps.setInt(1, contenidoId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Progreso progreso = construirProgreso(rs);
                return progreso;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public List<Progreso> listarProgresoPorCurso(int estudianteId, int cursoId) {
        String sql = "SELECT pe.* FROM progreso_estudiante pe " +
                     "JOIN contenidos c ON pe.id_contenido = c.id " +
                     "WHERE pe.id_estudiante = ? AND c.curso_id = ?";
        List<Progreso> lista = new ArrayList<>();
        try (
                Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, estudianteId);
            ps.setInt(2, cursoId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(construirProgreso(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return lista;
    }

    @Override
    public boolean existeProgreso(int estudianteId, int contenidoId) {
        String sql = "SELECT 1 FROM progreso_estudiante WHERE id_estudiante=? AND id_contenido=?";
        try (
                Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, estudianteId);
            ps.setInt(2, contenidoId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return false;
    }
}