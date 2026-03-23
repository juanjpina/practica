package org.practica.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.mindrot.jbcrypt.BCrypt;
import org.practica.conexion.Conexion;
import org.practica.dao.DAOFactory;
import org.practica.model.Admin;
import org.practica.model.AreasInteres;
import org.practica.model.Estudiante;
import org.practica.model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class AppInitializer implements ServletContextListener {

@Override
public void contextInitialized(ServletContextEvent sce) {
    System.out.println("Arrancando aplicacion");
    try (Connection con = Conexion.getConnection()) {
        SchemaInitializer.initialize(con);
        insertarDatosPrueba();
        insertarDatosPrueba2();
        insertarDatosPrueba3();
        insertarDatosPrueba4();
        System.out.println("Aplicacion lista");
    } catch (SQLException e) {
        System.err.println("Error al inicializar schema: " + e.getMessage());
    }
}
    private void insertarDatosPrueba() {
        Usuario admin = new Admin(0, "admin@practica.com", BCrypt.hashpw("admin123", BCrypt.gensalt()), "Admin", "Sistema");
        DAOFactory.getUsuarioDAO().insertar(admin);
        System.out.println("Usuario admin creado: admin@practica.com / admin123");
    }
    private void insertarDatosPrueba2() {
        Usuario estudiante = new Estudiante(0, "estudiante@practica.com", BCrypt.hashpw("admin123", BCrypt.gensalt()), "Admin", "Sistema");
        DAOFactory.getUsuarioDAO().insertar(estudiante);
        System.out.println("Usuario admin creado: estudiante@practica.com / admin123");
    }
    private void insertarDatosPrueba3() {
        AreasInteres areasInteres = new AreasInteres(0,"Musica");
        DAOFactory.getAreasDeInteresDAO().insertar(areasInteres);
        System.out.println("Areas de interes creadas");
    }
    private void insertarDatosPrueba4() {
        AreasInteres areasInteres = new AreasInteres(0,"literatura");
        DAOFactory.getAreasDeInteresDAO().insertar(areasInteres);
        System.out.println("Areas de interes creadas");
    }
//@Override
//public void contextDestroyed(ServletContextListener sce){
//    System.out.println("Aplicacion detenida");
//}

}
