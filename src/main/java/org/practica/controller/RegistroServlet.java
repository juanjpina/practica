package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;
import org.practica.dao.DAOFactory;
import org.practica.model.AreasInteres;
import org.practica.model.Estudiante;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet de regisro de usuarios estudiantes, proferoser o admin.
 */
@WebServlet(name = "RegistroServlet", urlPatterns = "/registro")
public class RegistroServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String passwordPlano = request.getParameter("password");
        String hashPassword = BCrypt.hashpw(passwordPlano, BCrypt.gensalt());
        String email = request.getParameter("email");
        String password = hashPassword;
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String direccion = request.getParameter("direccion");
        String poblacion = request.getParameter("poblacion");
        String provincia = request.getParameter("provincia");
        String codigoPostal = request.getParameter("codigoPostal");
        String[] areasIds = request.getParameterValues("areasInteres");

        if (DAOFactory.getUsuarioDAO().buscarPorEmail(email) != null) {
            request.setAttribute("error", "ya existe una cuenta con el email.");
            request.getRequestDispatcher("/WEB-INF/views/registro.jsp").forward(request, response);
            return;
        }

        Estudiante nuevo = new Estudiante(0, email, password, nombre, apellidos);
        nuevo.setDireccion(direccion);
        nuevo.setPoblacion(poblacion);
        nuevo.setProvincia(provincia);
        nuevo.setCodigoPostal(codigoPostal);
        List<AreasInteres> areas = new ArrayList<>();
        if (areasIds != null) {
            for (String areaId : areasIds) {
                AreasInteres a = new AreasInteres();
                a.setId(Integer.parseInt(areaId));
                areas.add(a);
            }
        }
        nuevo.setAreasInteres(areas);
        DAOFactory.getUsuarioDAO().insertar(nuevo);
        response.sendRedirect(request.getContextPath() + "/login?registrado=ok");

    }
    @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/views/registro.jsp").forward(request, response);
    }


}
