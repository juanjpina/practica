package org.practica;


import org.practica.conexion.Conexion;
import org.practica.dao.DAOFactory;
import org.practica.dao.UsuarioDAO;
import org.practica.model.Estudiante;
import org.practica.model.Usuario;
import org.practica.util.SchemaInitializer;

import java.sql.*;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        try (Connection con = Conexion.getConnection()) {
            SchemaInitializer.initialize(con);
            System.out.println("Schema inicializado OK");
        } catch (SQLException e) {
            e.printStackTrace();
            return; // si falla la BD no tiene sentido continuar
        }

        // 2. Ahora sí el test
        UsuarioDAO dao = DAOFactory.getUsuarioDAO();

        Estudiante e = new Estudiante(0, "juan@test.com", "1234", "Juan", "García");
        e.setDireccion("Calle Mayor 1");
        e.setPoblacion("Madrid");
        e.setProvincia("Madrid");
        e.setCodigoPostal("28001");
        e.setAreasInteres(new java.util.ArrayList<>());

        dao.insertar(e);
        System.out.println("Insertado OK");

        List<Usuario> usuarios = dao.listarTodos();
        for (Usuario u : usuarios) {
            System.out.println("-> " + u.getNombre() + " | " + u.getRol() + " | " + u.getEmail());
        }
    }
}