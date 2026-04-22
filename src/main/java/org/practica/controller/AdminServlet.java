package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servet implementa el escritorio del admin
 * desde rol admin gestiona es dashboard
 */
@WebServlet(name = "AdminServlet", urlPatterns = "/admin/dashboard")
public class AdminServlet extends HttpServlet {


    /**
     * Acceso al escritorio
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);


    }


}
