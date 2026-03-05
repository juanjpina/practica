package org.practica.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.practica.conexion.Conexion;

import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class AppInitializer implements ServletContextListener {

@Override
public void contextInitialized(ServletContextEvent sce) {
    System.out.println("Arrancando aplicacion");
    try (Connection con = Conexion.getConnection()) {
        SchemaInitializer.initialize(con);
        System.out.println("Aplicacion lista");
    } catch (SQLException e) {
        System.err.println("Error al inicializar schema: " + e.getMessage());
    }
}

//@Override
//public void contextDestroyed(ServletContextListener sce){
//    System.out.println("Aplicacion detenida");
//}

}
