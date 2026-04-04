package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.practica.dao.DAOFactory;

import java.io.IOException;

/**
 * Clase home root de la aplicación. Implementada en web.xml
 */
@WebServlet(name = "RootServlet", urlPatterns = {"/home"})
public class RootServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cursos", DAOFactory.getCursoDAO().listarTodos());
        request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
    }

}
