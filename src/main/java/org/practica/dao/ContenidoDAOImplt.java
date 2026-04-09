package org.practica.dao;

import org.practica.conexion.Conexion;
import org.practica.model.Contenido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
         int idCurso= rs.getInt("id_curso");
        return new Contenido(id,titulo, tipo,url,orden,fechaInicio,fechaFin,idCurso);
    }


    @Override
    public void insertar(Contenido contenido) {

    }

    @Override
    public void eliminar(int id) {

    }

    @Override
    public List<Contenido> listarTodos() {
        String sql="SELECT * FROM contenidos";
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
    public List<Contenido> listarPorCurso(int id) {
        return List.of();
    }

    @Override
    public Void actualizar(Contenido contenido) {
        return null;
    }
}
