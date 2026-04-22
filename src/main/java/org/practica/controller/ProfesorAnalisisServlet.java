package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.practica.dao.DAOFactory;
import org.practica.model.Usuario;

import java.io.IOException;

/**
 * Servlet profesor analisis
 * desde rol profesor gestiona los analisis
 */
@WebServlet(name = "profesorAnalisis", urlPatterns = "/profesor/analisis")
public class ProfesorAnalisisServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario profesor = (Usuario) request.getSession().getAttribute("usuarioLogueado");
        request.setAttribute("analisis", DAOFactory.getCursoDAO().listarAnalisisCursos(profesor.getId()));
        request.getRequestDispatcher("/WEB-INF/views/profesor/analisis.jsp").forward(request, response);
    }
}
