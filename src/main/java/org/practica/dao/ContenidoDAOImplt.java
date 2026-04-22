package org.practica.dao;

import org.practica.conexion.Conexion;
import org.practica.model.Contenido;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase contenido DAO
 */
public class ContenidoDAOImplt implements ContenidoDAO {

    /**
     * Método construye objeto contenido
     *
     * @param rs
     * @return objeto
     * @throws SQLException
     */
    private Contenido construirContenido(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String titulo = rs.getString("titulo");
        String tipo = rs.getString("tipo");
        String url = rs.getString("url");
        int orden = rs.getInt("orden");
        LocalDate fechaInicio = rs.getDate("fecha_inicio").toLocalDate();
        LocalDate fechaFin = rs.getDate("fecha_fin").toLocalDate();
        int idCurso = rs.getInt("curso_id");
        return new Contenido(id, titulo, tipo, url, orden, fechaInicio, fechaFin, idCurso);
    }

    /**
     * Método inserta en BD contenido
     *
     * @param contenido
     */
    @Override
    public void insertar(Contenido contenido) {
        String sql = "INSERT INTO contenidos (titulo,tipo,url,orden,fecha_inicio,fecha_fin,curso_id) VALUES (?,?,?,?,?,?,?)";
        try (
                Connection conn = Conexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            ps.setString(1, contenido.getTitulo());
            ps.setString(2, contenido.getTipo());
            ps.setString(3, contenido.getUrl());
            ps.setInt(4, contenido.getOrden());
            ps.setDate(5, Date.valueOf(contenido.getFechaInicio()));
            ps.setDate(6, Date.valueOf(contenido.getFechaFin()));
            ps.setInt(7, contenido.getIdCurso());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);

        }

    }

    /**
     * Método elimina contenido de BD
     *
     * @param id
     */
    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM contenidos WHERE id=?";
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

    /**
     * Método devuleve lista de contenidos
     *
     * @return list
     */
    @Override
    public List<Contenido> listarTodos() {
        String sql = "SELECT * FROM contenidos";
        List<Contenido> cont = new ArrayList<>();
        try (
                Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                cont.add(construirContenido(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cont;
    }

    /**
     * Método devuelve objeto por id
     *
     * @param id
     * @return contenido
     */
    @Override
    public Contenido buscarPorId(int id) {
        String sql = "SELECT * FROM contenidos WHERE id=?";
        try (
                Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return construirContenido(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    /**
     * Método actualiza contenidos en la BD
     *
     * @param contenido
     */
    @Override
    public void actualizar(Contenido contenido) {
        String sql = "UPDATE contenidos SET titulo=?,tipo=?,url=?,orden=?,fecha_inicio=?,fecha_fin=?,curso_id=? WHERE id=?";
        try (
                Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1, contenido.getTitulo());
            ps.setString(2, contenido.getTipo());
            ps.setString(3, contenido.getUrl());
            ps.setInt(4, contenido.getOrden());
            ps.setDate(5, Date.valueOf(contenido.getFechaInicio()));
            ps.setDate(6, Date.valueOf(contenido.getFechaFin()));
            ps.setInt(7, contenido.getIdCurso());
            ps.setInt(8, contenido.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }
}