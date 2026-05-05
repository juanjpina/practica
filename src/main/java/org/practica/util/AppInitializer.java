package org.practica.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.mindrot.jbcrypt.BCrypt;
import org.practica.conexion.Conexion;
import org.practica.dao.DAOFactory;
import org.practica.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebListener
public class AppInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try (Connection con = Conexion.getConnection()) {
            SchemaInitializer.initialize(con);
            if (!yaInicializado(con)) {
                insertarDatosPrueba();

            } else {
                System.out.println("Datos de prueba ya existentes, omitiendo insercion");
            }

        } catch (SQLException e) {
            System.err.println("Error al inicializar: " + e.getMessage());
        }
    }

    private boolean yaInicializado(Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM usuarios")) {
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }

    private void insertarDatosPrueba() {
        String pass = BCrypt.hashpw("123", BCrypt.gensalt());

        // --- Usuarios ---
        DAOFactory.getUsuarioDAO().insertar(new Admin(0, "admin@practica.com", BCrypt.hashpw("admin123", BCrypt.gensalt()), "Admin", "Sistema"));
        DAOFactory.getUsuarioDAO().insertar(new Profesor(0, "profesor@practica.com", pass, "Carlos", "García"));
        DAOFactory.getUsuarioDAO().insertar(new Profesor(0, "profesor2@practica.com", pass, "Ana", "López"));
        DAOFactory.getUsuarioDAO().insertar(new Estudiante(0, "estudiante@practica.com", pass, "Luis", "Martínez"));
        DAOFactory.getUsuarioDAO().insertar(new Estudiante(0, "estudiante2@practica.com", pass, "María", "Sánchez"));
        DAOFactory.getUsuarioDAO().insertar(new Estudiante(0, "estudiante3@practica.com", pass, "Pedro", "Ruiz"));

        int idProf1 = getIdPorEmail("profesor@practica.com");
        int idProf2 = getIdPorEmail("profesor2@practica.com");
        int idEst1  = getIdPorEmail("estudiante@practica.com");
        int idEst2  = getIdPorEmail("estudiante2@practica.com");
        int idEst3  = getIdPorEmail("estudiante3@practica.com");

        // --- Cursos ---
        DAOFactory.getCursoDAO().insertar(new Curso(0, "Java Básico", "Introducción a Java desde cero", 20, 1, idProf1));
        DAOFactory.getCursoDAO().insertar(new Curso(0, "Spring Boot", "Desarrollo de APIs REST con Spring", 30, 2, idProf1));
        DAOFactory.getCursoDAO().insertar(new Curso(0, "HTML y CSS", "Diseño web moderno con HTML5 y CSS3", 15, 1, idProf2));

        int idCurso1 = getIdCursoPorTitulo("Java Básico");
        int idCurso2 = getIdCursoPorTitulo("Spring Boot");
        int idCurso3 = getIdCursoPorTitulo("HTML y CSS");

        // --- Contenidos Curso 1: Java Básico ---
        LocalDate ini1 = LocalDate.of(2026, 1, 1);
        LocalDate fin1 = LocalDate.of(2026, 6, 30);
        DAOFactory.getContendioDAO().insertar(new Contenido(0, "Introducción a Java", "VIDEO", "https://www.youtube.com/watch?v=dQw4w9WgXcQ", 1, ini1, fin1, idCurso1));
        DAOFactory.getContendioDAO().insertar(new Contenido(0, "Variables y tipos de datos", "PDF", "https://docs.oracle.com/javase/tutorial/", 2, ini1, fin1, idCurso1));
        DAOFactory.getContendioDAO().insertar(new Contenido(0, "Control de flujo", "VIDEO", "https://www.youtube.com/watch?v=dQw4w9WgXcQ", 3, ini1, fin1, idCurso1));
        DAOFactory.getContendioDAO().insertar(new Contenido(0, "Programación orientada a objetos", "PDF", "https://docs.oracle.com/javase/tutorial/java/concepts/", 4, ini1, fin1, idCurso1));

        // --- Contenidos Curso 2: Spring Boot ---
        LocalDate ini2 = LocalDate.of(2026, 2, 1);
        LocalDate fin2 = LocalDate.of(2026, 8, 31);
        DAOFactory.getContendioDAO().insertar(new Contenido(0, "Introducción a Spring", "VIDEO", "https://www.youtube.com/watch?v=dQw4w9WgXcQ", 1, ini2, fin2, idCurso2));
        DAOFactory.getContendioDAO().insertar(new Contenido(0, "Creando un proyecto Spring Boot", "PDF", "https://start.spring.io/", 2, ini2, fin2, idCurso2));
        DAOFactory.getContendioDAO().insertar(new Contenido(0, "Controladores REST", "VIDEO", "https://www.youtube.com/watch?v=dQw4w9WgXcQ", 3, ini2, fin2, idCurso2));

        // --- Contenidos Curso 3: HTML y CSS ---
        LocalDate ini3 = LocalDate.of(2026, 3, 1);
        LocalDate fin3 = LocalDate.of(2026, 9, 30);
        DAOFactory.getContendioDAO().insertar(new Contenido(0, "Estructura HTML5", "VIDEO", "https://www.youtube.com/watch?v=dQw4w9WgXcQ", 1, ini3, fin3, idCurso3));
        DAOFactory.getContendioDAO().insertar(new Contenido(0, "Selectores CSS", "PDF", "https://developer.mozilla.org/es/docs/Web/CSS", 2, ini3, fin3, idCurso3));
        DAOFactory.getContendioDAO().insertar(new Contenido(0, "Flexbox y Grid", "VIDEO", "https://www.youtube.com/watch?v=dQw4w9WgXcQ", 3, ini3, fin3, idCurso3));

        // --- Áreas de interés ---
        DAOFactory.getAreasDeInteresDAO().insertar(new AreasInteres(0, "Programación"));
        DAOFactory.getAreasDeInteresDAO().insertar(new AreasInteres(0, "Diseño Web"));
        DAOFactory.getAreasDeInteresDAO().insertar(new AreasInteres(0, "Bases de Datos"));
        DAOFactory.getAreasDeInteresDAO().insertar(new AreasInteres(0, "Inglés"));
        DAOFactory.getAreasDeInteresDAO().insertar(new AreasInteres(0, "Matemáticas"));

        int idAreaProg  = getIdAreaPorDescripcion("Programación");
        int idAreaDis   = getIdAreaPorDescripcion("Diseño Web");
        int idAreaBD    = getIdAreaPorDescripcion("Bases de Datos");

        // --- Áreas por curso ---
        DAOFactory.getAreasDeInteresDAO().guardarAreasCurso(idCurso1, List.of(new AreasInteres(idAreaProg, ""), new AreasInteres(idAreaBD, "")));
        DAOFactory.getAreasDeInteresDAO().guardarAreasCurso(idCurso2, List.of(new AreasInteres(idAreaProg, ""), new AreasInteres(idAreaBD, "")));
        DAOFactory.getAreasDeInteresDAO().guardarAreasCurso(idCurso3, List.of(new AreasInteres(idAreaDis, ""), new AreasInteres(idAreaProg, "")));

        // --- Áreas por usuario ---
        DAOFactory.getAreasDeInteresDAO().guardarAreasUsuario(idProf1, List.of(new AreasInteres(idAreaProg, ""), new AreasInteres(idAreaBD, "")));
        DAOFactory.getAreasDeInteresDAO().guardarAreasUsuario(idProf2, List.of(new AreasInteres(idAreaDis, "")));
        DAOFactory.getAreasDeInteresDAO().guardarAreasUsuario(idEst1,  List.of(new AreasInteres(idAreaProg, ""), new AreasInteres(idAreaBD, "")));
        DAOFactory.getAreasDeInteresDAO().guardarAreasUsuario(idEst2,  List.of(new AreasInteres(idAreaDis, ""), new AreasInteres(idAreaProg, "")));
        DAOFactory.getAreasDeInteresDAO().guardarAreasUsuario(idEst3,  List.of(new AreasInteres(idAreaProg, "")));

        // --- Inscripciones ---
        DAOFactory.getCursoDAO().inscribirEstudiante(idEst1, List.of(idCurso1, idCurso2));
        DAOFactory.getCursoDAO().inscribirEstudiante(idEst2, List.of(idCurso1, idCurso3));
        DAOFactory.getCursoDAO().inscribirEstudiante(idEst3, List.of(idCurso2, idCurso3));

        // --- Valoraciones ---
        DAOFactory.getCursoDAO().valorarCurso(idEst1, idCurso1, 5);
        DAOFactory.getCursoDAO().valorarCurso(idEst1, idCurso2, 4);
        DAOFactory.getCursoDAO().valorarCurso(idEst2, idCurso1, 3);
        DAOFactory.getCursoDAO().valorarCurso(idEst2, idCurso3, 5);
        DAOFactory.getCursoDAO().valorarCurso(idEst3, idCurso2, 4);
        DAOFactory.getCursoDAO().valorarCurso(idEst3, idCurso3, 4);

        // --- Favoritos ---
        DAOFactory.getCursoDAO().marcarFavorito(idEst1, idCurso1, true);
        DAOFactory.getCursoDAO().marcarFavorito(idEst2, idCurso3, true);
        DAOFactory.getCursoDAO().marcarFavorito(idEst3, idCurso2, true);

        // --- Progreso ---
        // est1 (cursos 1 y 2): 2 contenidos de curso1 y 1 de curso2
        DAOFactory.getProgresoDAO().registrarAcceso(idEst1, getIdContenido(idCurso1, 1));
        DAOFactory.getProgresoDAO().registrarAcceso(idEst1, getIdContenido(idCurso1, 2));
        DAOFactory.getProgresoDAO().registrarAcceso(idEst1, getIdContenido(idCurso2, 1));
        // est2 (cursos 1 y 3): 1 contenido de curso1 y 2 de curso3
        DAOFactory.getProgresoDAO().registrarAcceso(idEst2, getIdContenido(idCurso1, 1));
        DAOFactory.getProgresoDAO().registrarAcceso(idEst2, getIdContenido(idCurso3, 1));
        DAOFactory.getProgresoDAO().registrarAcceso(idEst2, getIdContenido(idCurso3, 2));
        // est3 (cursos 2 y 3): todos los contenidos de curso2 y 1 de curso3
        DAOFactory.getProgresoDAO().registrarAcceso(idEst3, getIdContenido(idCurso2, 1));
        DAOFactory.getProgresoDAO().registrarAcceso(idEst3, getIdContenido(idCurso2, 2));
        DAOFactory.getProgresoDAO().registrarAcceso(idEst3, getIdContenido(idCurso2, 3));
        DAOFactory.getProgresoDAO().registrarAcceso(idEst3, getIdContenido(idCurso3, 1));
    }

    private int getIdPorEmail(String email) {
        String sql = "SELECT id FROM usuarios WHERE email = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return -1;
    }

    private int getIdCursoPorTitulo(String titulo) {
        String sql = "SELECT id FROM cursos WHERE titulo = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, titulo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return -1;
    }

    private int getIdAreaPorDescripcion(String descripcion) {
        String sql = "SELECT id FROM areas_interes WHERE descripcion = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, descripcion);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return -1;
    }

    private int getIdContenido(int cursoId, int orden) {
        String sql = "SELECT id FROM contenidos WHERE curso_id = ? AND orden = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cursoId);
            ps.setInt(2, orden);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return -1;
    }
}
