package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.practica.dao.DAOFactory;
import org.practica.model.Usuario;

import java.io.IOException;

@WebServlet(name = "profesorEstudiantes", urlPatterns = "/profesor/estudiantes")
public class ProfesorEstudiantesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario profesor = (Usuario) request.getSession().getAttribute("usuarioLogueado");
        request.setAttribute("estudiantes", DAOFactory.getCursoDAO().listarEstudiantesConProgreso(profesor.getId()));
        request.getRequestDispatcher("/WEB-INF/views/profesor/estudiantes.jsp").forward(request, response);
    }
}
