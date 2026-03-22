package org.practica.dao;

import org.practica.conexion.Conexion;
import org.practica.model.AreasInteres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AreasDeInteresImplt implements AreasDeInteresDAO {

    private AreasInteres construirAreasDeInteres(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String descripcion= rs.getString("descripcion");
        return new AreasInteres(id,descripcion);
    }

    @Override
    public void insertar(AreasInteres areasInteres) {
String sql= """
        INSERT INTO areas_interes (descripcion) VALUES(?)
        """;
try(Connection con = Conexion.getConnection();
    PreparedStatement ps = con.prepareStatement(sql);
        ){
    ps.setString(1,areasInteres.getDescripcion());
    ps.executeUpdate();
}catch(SQLException e){
    e.printStackTrace(System.out);
        }
    }

    @Override
    public List<AreasInteres> listarTodos() {
        return List.of();
    }

    @Override
    public void actualizar(AreasInteres areasInteres) {

    }

    @Override
    public void eliminar(int id) {

    }
}
