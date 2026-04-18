package org.practica.dao;

import org.practica.conexion.Conexion;
import org.practica.model.Contenido;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContenidoDAOImplt implements ContenidoDAO{
    private Contenido construirContenido(ResultSet rs) throws SQLException{

         int id= rs.getInt("id");
         String titulo = rs.getString("titulo");
         String tipo = rs.getString("tipo");
         String url = rs.getString("url");
         int orden = rs.getInt("orden");
         LocalDate fechaInicio = rs.getDate("fecha_inicio").toLocalDate();
         LocalDate fechaFin = rs.getDate("fecha_fin").toLocalDate();
         int idCurso= rs.getInt("curso_id");
        return new Contenido(id,titulo, tipo,url,orden,fechaInicio,fechaFin,idCurso);
    }


    @Override
    public void insertar(Contenido contenido) {
        String sql="INSERT INTO contenidos (titulo,tipo,url,orden,fecha_inicio,fecha_fin,curso_id) VALUES (?,?,?,?,?,?,?)";
        try(
                Connection conn = Conexion.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ){     ps.setString(1, contenido.getTitulo());
                        ps.setString(2, contenido.getTipo());
                        ps.setString(3, contenido.getUrl());
                        ps.setInt(4, contenido.getOrden());
                        ps.setDate(5, Date.valueOf(contenido.getFechaInicio()));
                        ps.setDate(6, Date.valueOf(contenido.getFechaFin()));
                        ps.setInt(7, contenido.getIdCurso());
                        ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace(System.out);

        }

    }

    @Override
    public void eliminar(int id) {
String sql = "DELETE FROM contenidos WHERE id=?";
try(
        Connection con = Conexion.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ){
    ps.setInt(1,id);
    ps.executeUpdate();

}catch(SQLException e){
    e.printStackTrace(System.out);
}

    }

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

    @Override
    public List<Contenido> listarPorContenido(int id) {
        String sql="SELECT * FROM contenidos WHERE id=?";
        List<Contenido> cont = new ArrayList<>();
        try(
                Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                ){
            while(rs.next()){
                cont.add(construirContenido(rs));
            }

        }catch(SQLException e){
            e.printStackTrace(System.out);
        }



        return cont;
    }

    @Override
    public Void actualizar(Contenido contenido) {
        return null;
    }

}
