package org.practica.util;

import org.practica.conexion.Conexion;
import org.practica.dao.DAOFactory;
import org.practica.dao.UsuarioDAO;
import org.practica.model.Estudiante;
import org.practica.model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
public class TestUsuario {
    public static void main(String[] args) {

        // 1. Crear tablas primero
        try (Connection con = Conexion.getConnection()) {
            SchemaInitializer.initialize(con);
            System.out.println("Schema inicializado OK");  // dentro del try
        } catch (SQLException e) {
            e.printStackTrace();
            return;
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