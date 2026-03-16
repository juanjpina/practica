package org.practica.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Clase para cerrar sesion
 */
@WebServlet(name = "LogoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    /**
     * Cierra session
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws SecurityException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, SecurityException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
