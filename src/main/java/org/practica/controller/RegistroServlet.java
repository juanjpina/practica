package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.practica.dao.DAOFactory;
import org.practica.model.Estudiante;

import java.io.IOException;

/**
 * clase servlet de regisro de usuarios estudiantes, proferoser o admin.
 */
@WebServlet(name = "RegistroServlet", urlPatterns = "/registro")
public class RegistroServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String direccion = request.getParameter("direccion");
        String poblacion = request.getParameter("poblacion");
        String provincia = request.getParameter("provincia");
        String codigoPostal = request.getParameter("codigoPostal");
        String areasInteres = request.getParameter("areasInteres");

        if (DAOFactory.getUsuarioDAO().buscarPorEmail(email) != null) {
            request.setAttribute("error", "ya existe una cuenta con el email.");
            request.getRequestDispatcher("/WEB-INF/views/registro.jsp").forward(request, response);
            return;
        }

        Estudiante nuevo = new Estudiante(0, email,password,nombre,apellidos);
        nuevo.setDireccion(direccion);
        nuevo.setPoblacion(poblacion);
        nuevo.setProvincia(provincia);
        nuevo.setCodigoPostal(codigoPostal);
        nuevo.setAreasInteres(areasInteres);
        DAOFactory.getUsuarioDAO().insertar(nuevo);
        response.sendRedirect(request.getContextPath()+"/login?registrado=ok");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/views/registro.jsp").forward(request, response);
    }


}
