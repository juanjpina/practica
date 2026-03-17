package org.practica.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Clase implementa el escritorio del admin
 */
@WebServlet(name = "AdminServlet", urlPatterns = "/admin/dashboard")
public class AdminServlet extends HttpServlet {


    /**
     * Acceso al escritorio
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        // Comprobar que el rol es correcto
        String rol = (String) session.getAttribute("rol");
        if (!"ADMIN".equals(rol)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }


        request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);


    }


}
