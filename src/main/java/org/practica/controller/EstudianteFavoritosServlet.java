package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.practica.dao.DAOFactory;

import java.io.IOException;

@WebServlet(name = "estudianteFavoritos", urlPatterns = "/estudiante/favoritos")
public class EstudianteFavoritosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int estudianteId = (int) request.getSession().getAttribute("id");
        request.setAttribute("favoritos", DAOFactory.getCursoDAO().listarFavoritos(estudianteId));
        request.getRequestDispatcher("/WEB-INF/views/estudiante/favoritos.jsp").forward(request, response);
    }
}
