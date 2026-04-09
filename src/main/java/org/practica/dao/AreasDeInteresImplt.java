package org.practica.dao;

import org.practica.conexion.Conexion;
import org.practica.model.AreasInteres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreasDeInteresImplt implements AreasDeInteresDAO {

    private AreasInteres construirAreasDeInteres(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String descripcion = rs.getString("descripcion");
        return new AreasInteres(id, descripcion);
    }

    @Override
    public void insertar(AreasInteres areasInteres) {
        String sql = """
                INSERT INTO areas_interes (descripcion) VALUES(?)
                """;
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1, areasInteres.getDescripcion());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public List<AreasInteres> listarTodos() {
        String sql = "SELECT * FROM areas_interes";
        List<AreasInteres> areasInteres = new ArrayList<>();
        try (
                Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            System.out.println("Ejecutando SELECT..."); // debug
            while (rs.next()) {
                areasInteres.add(construirAreasDeInteres(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }


        return areasInteres;
    }

    @Override
    public List<AreasInteres> listarPorUsuario(int usuarioId) {
        String sql = """
                SELECT ai.id, ai.descripcion
                FROM areas_interes ai
                JOIN areas_interes_usuarios aiu ON ai.id = aiu.areas_id
                WHERE aiu.usuario_id = ?
                """;
        List<AreasInteres> lista = new ArrayList<>();
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, usuarioId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(construirAreasDeInteres(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return lista;
    }

    @Override
    public void guardarAreasUsuario(int usuarioId, List<AreasInteres> areas) {
        String deleteSql = "DELETE FROM areas_interes_usuarios WHERE usuario_id = ?";
        String insertSql = "INSERT INTO areas_interes_usuarios (usuario_id, areas_id) VALUES (?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement deletePs = con.prepareStatement(deleteSql);
             PreparedStatement insertPs = con.prepareStatement(insertSql)) {
            deletePs.setInt(1, usuarioId);
            deletePs.executeUpdate();
            for (AreasInteres area : areas) {
                insertPs.setInt(1, usuarioId);
                insertPs.setInt(2, area.getId());
                insertPs.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void actualizar(AreasInteres areasInteres) {

    }

    @Override
    public void eliminar(int id) {

    }

    @Override
    public List<AreasInteres> listarPorCurso(int cursoId) {
        String sql = """
                SELECT ai.id, ai.descripcion
                FROM areas_interes ai
                JOIN areas_interes_curso aic ON ai.id = aic.areas_id
                WHERE aic.curso_id = ?
                """;
        List<AreasInteres> lista = new ArrayList<>();
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cursoId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(construirAreasDeInteres(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return lista;
    }

    @Override
    public void guardarAreasCurso(int cursoId, List<AreasInteres> areas) {
        String deleteSql = "DELETE FROM areas_interes_curso WHERE curso_id = ?";
        String insertSql = "INSERT INTO areas_interes_curso (curso_id, areas_id) VALUES (?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement deletePs = con.prepareStatement(deleteSql);
             PreparedStatement insertPs = con.prepareStatement(insertSql)) {
            deletePs.setInt(1, cursoId);
            deletePs.executeUpdate();
            if (areas == null) return;
            for (AreasInteres area : areas) {
                insertPs.setInt(1, cursoId);
                insertPs.setInt(2, area.getId());
                insertPs.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }
}
