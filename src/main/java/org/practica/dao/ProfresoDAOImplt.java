package org.practica.dao;

import org.practica.conexion.Conexion;
import org.practica.model.Contenido;
import org.practica.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgresoDAOImplt implements ProgresoDAO{
    private Usuario construirProgreso(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int estudianre eemail = rs.getString("email");
        String password = rs.getString("password");
        String nombre = rs.getString("nombre");
        String apellidos = rs.getString("apellidos");

        @Override
    public void registrarAcceso(int estudianteId, int contenidoId) {
        String sql="INSERT INTO progreso_estudiante (id_estudiante, id_contenido, completado, fecha_acceso) VALUES(?,?,?,?)";
        try(
                Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ){
            ps.setInt(1,estudianteId);
            ps.setInt(2, contenidoId);
            ps.setBoolean(3,true);
            ps.setDate(4,java.sql.Date.valueOf(java.time.LocalDate.now()));
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace(System.out);
        }
    }

    @Override
    public Contenido buscarPorId(int contenidoId) {
        String sql="SELECT * FROM progreso_estudiante WHERE id=?";
        try(
                Connection con = Conexion.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);

        ){
            ps.setInt(1, contenidoId);
            ResultSet rs = ps.executeQuery();

        }catch (SQLException e){
            e.printStackTrace(System.out);
        }
    }
}
