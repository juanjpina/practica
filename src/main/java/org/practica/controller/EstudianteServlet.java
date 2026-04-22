package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet estudiante
 * desde rol estudiante gestiona el dashboard
 */
@WebServlet(name = "EstudianteServlet", urlPatterns = "/estudiante/dashboard")
public class EstudianteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/estudiante/dashboard.jsp").forward(request, response);
    }


}
